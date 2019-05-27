package little.things.algorithm.graphs.traps;

import little.things.algorithm.graphs.Edge;
import little.things.algorithm.graphs.Graph;
import little.things.algorithm.graphs.Peek;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Trap {
    public static void main(String[] args) {
        boolean isOriented = true;
        Scanner scIn = null;
        FileWriter scOut = null;
        for (int fileN = 1; fileN <= 9; fileN++) {
            System.out.println("FILE: " + fileN);
            try {
                String errorMsg = "NO";
                String fileSuffix;
                if (isOriented) fileSuffix = "or";
                else fileSuffix = "un";
                scIn = new Scanner(new File("actual_little_things/src/main/resources/trapeze/in/" + fileN));
                scOut = new FileWriter(new File("actual_little_things/src/main/resources/trapeze/out/" + fileN + fileSuffix));
                int n = scIn.nextInt();
                int[] traps = new int[n * 3];
                int index = 0;
                while (scIn.hasNextInt()) {
                    traps[index] = scIn.nextInt();
                    index++;
                }
                Map<Integer, Peek<Integer>> graph = new HashMap<>();
                if (traps.length % 3 == 0) {
                    int trapIndex = 0;
                    for (int i = 0; i < traps.length; i += 3) {
                        int a = 0;
                        int b = traps[i];
                        int c = traps[i + 1];
                        int d = traps[i + 2];
                        int cd = c - d;
                        String trapezeLine = "|" + "a = " + a + ", b = " + b + ", c - d = " + cd + "|";
//                        System.out.println(trapezeLine);
                        Peek<Integer> bPeek;
                        Peek<Integer> cdPeek;
                        if (!graph.containsKey(b)) {
                            bPeek = new Peek<>(b);
                            graph.put(b, bPeek);
                        } else {
                            bPeek = graph.get(b);
                        }
                        if (!graph.containsKey(cd)) {
                            cdPeek = new Peek<>(cd);
                            graph.put(cd, cdPeek);
                        } else {
                            cdPeek = graph.get(cd);
                        }
                        Edge<Integer> edgeTo = new Edge<>(graph.get(b), graph.get(cd), a + " " + b + " " + cd + " " + trapIndex);
                        if (isOriented) {
                            bPeek.addConnectionTo(edgeTo);
                            cdPeek.addConnectionFrom(edgeTo);
                        } else {
                            bPeek.addConnectionTo(edgeTo);
                            bPeek.addConnectionFrom(edgeTo);
                            cdPeek.addConnectionFrom(edgeTo);
                            cdPeek.addConnectionTo(edgeTo);
                        }
                        trapIndex++;
                    }

                    Graph<Integer> graph1 = new Graph<>();
                    List<Peek<Integer>> pathPeeks = null;
                    boolean pathFoundForPeeks = false;
                    Peek<Integer> pathPeekForPeeks = null;

                    Set<Peek<Integer>> startPeeks = new HashSet<>();
                    Set<Peek<Integer>> endPeeks = new HashSet<>();

                    if (Objects.nonNull(graph.get(0))) {
                        List<Edge<Integer>> connectedTo = graph.get(0).getConnectedTo();
                        List<Edge<Integer>> connectedFrom = graph.get(0).getConnectedFrom();
                        if (!connectedTo.isEmpty()) {
                            startPeeks.add(graph.get(0));
                        }
                        if (!connectedFrom.isEmpty()) {
                            endPeeks.add(graph.get(0));
                        }
                    }

                    if (!startPeeks.isEmpty()) {
                        if (!endPeeks.isEmpty()) {
                            for (Peek<Integer> startPeek : startPeeks) {
                                System.out.println();
                                System.out.println("FINDING PATH FOR: " + startPeek.getValue());
                                pathPeeks = graph1.findEilCircle(graph, startPeek);
                                if (Objects.nonNull(pathPeeks)) {
                                    if (pathPeeks.get(0).getValue() == 0) {
                                        pathPeekForPeeks = startPeek;
                                        pathFoundForPeeks = true;
                                        break;
                                    } else {
                                        System.out.println("NO END PEEK");
                                        errorMsg = "NO END PEEK";
                                    }
                                }
                                for (Peek<Integer> peek : graph.values()) {
                                    peek.reset();
                                }
                            }
                            if (pathFoundForPeeks) {
                                System.out.println("PATH FOUND FOR ANGLE: " + pathPeekForPeeks.getValue());
                                Set<Integer> excludedIndexes = new HashSet<>();
                                int lastCD = 0;
                                for (int i = pathPeeks.size() - 1; i > 0; i--) {
                                    Edge<Integer> connectingEdge = pathPeeks.get(i).getConnectingEdge(pathPeeks.get(i - 1), excludedIndexes);
                                    String[] separatedValue = connectingEdge.getValue().split(" ");
                                    String trapezeLine = "|" + "a = " + separatedValue[0] + ", b = " + separatedValue[1] + ", c - d = " +
                                            separatedValue[2] + ", index = " + separatedValue[3] + "|";
                                    excludedIndexes.add(Integer.valueOf(separatedValue[3]));
                                    if (!isOriented) {
                                        if (i == pathPeeks.size() - 1) {
                                            if (connectingEdge.getFirst().getValue() == 0) {
                                                lastCD = connectingEdge.getSecond().getValue();
                                                scOut.append(separatedValue[3]).append("(0)").append(" ");
                                            } else {
                                                lastCD = connectingEdge.getFirst().getValue();
                                                scOut.append(separatedValue[3]).append("(1)").append(" ");
                                            }
                                        } else {
                                            if (lastCD == connectingEdge.getFirst().getValue()) {
                                                lastCD = connectingEdge.getSecond().getValue();
                                                scOut.append(separatedValue[3]).append("(0)").append(" ");
                                            } else {
                                                lastCD = connectingEdge.getFirst().getValue();
                                                scOut.append(separatedValue[3]).append("(1)").append(" ");
                                            }
                                        }
                                    } else {
                                        scOut.append(separatedValue[3]).append(" ");
                                    }
                                    System.out.println(trapezeLine);
                                }
                            } else {
                                System.out.println("NO PATH");
                                scOut.append(errorMsg);
                            }
                        } else {
                            System.out.println("NO END PEEK");
                            scOut.append("NO END PEEK");
                        }
                    } else {
                        System.out.println("NO START PEEK");
                        scOut.append("NO START PEEK");
                    }
                } else {
                    System.out.println("NO PATH");
                    scOut.append("NO PATH");
                }
            } catch (IOException e) {
                System.out.println("NO FILE " + fileN);
            } finally {
                assert scIn != null;
                scIn.close();
                try {
                    assert scOut != null;
                    scOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
