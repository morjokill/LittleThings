package algorithm.graphs.traps;

import algorithm.graphs.Edge;
import algorithm.graphs.Graph;
import algorithm.graphs.Peek;

import java.util.*;

public class Trap {
    public static void main(String[] args) {
        int[] traps = new int[]{
                1, 1, 5, 2,
                1, 3, 6, 5,
                1, 1, 4, 1,
                1, 3, 5, 1,
                1, 4, 1, 1};
        Map<Integer, Peek<Integer>> graph = new HashMap<>();
        if (traps.length % 4 == 0) {
            int trapIndex = 0;
            for (int i = 0; i < traps.length; i += 4) {
                int a = traps[i];
                int b = traps[i + 1];
                int c = traps[i + 2];
                int d = traps[i + 3];
                int cd = c - d;
                String trapezeLine = "|" + "a = " + a + ", b = " + b + ", c - d = " + cd + "|";
                System.out.println(trapezeLine);
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
                bPeek.addConnectionTo(edgeTo);
                cdPeek.addConnectionFrom(edgeTo);
                trapIndex++;
            }

            for (Integer integer : graph.keySet()) {
                System.out.println(integer);
                Peek<Integer> peek = graph.get(integer);
                System.out.println(peek);
            }

            Graph<Integer> graph1 = new Graph<>();
            List<Peek<Integer>> pathPeeks = null;
            boolean pathFoundForPeeks = false;
            Peek<Integer> pathPeekForPeeks = null;

            Set<Peek<Integer>> startPeeks = new HashSet<>();
            for (Integer angle : graph.keySet()) {
                List<Edge<Integer>> edgeList = graph.get(angle).getConnectedTo();
                for (Edge<Integer> integerEdge : edgeList) {
                    String[] value = integerEdge.getValue().split(" ");
                    if (Integer.valueOf(value[0]) - Integer.valueOf(value[1]) == 0) {
                        startPeeks.add(graph.get(angle));
                    }
                }
            }

            if (!startPeeks.isEmpty()) {
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
                        }
                    }
                    for (Peek<Integer> peek : graph.values()) {
                        peek.reset();
                    }
                }
                if (pathFoundForPeeks) {
                    System.out.println("PATH FOUND FOR ANGLE: " + pathPeekForPeeks.getValue());
                    Set<Integer> excludedIndexes = new HashSet<>();
                    for (int i = pathPeeks.size() - 1; i > 0; i--) {
                        Edge<Integer> connectingEdge = pathPeeks.get(i).getConnectingEdge(pathPeeks.get(i - 1), excludedIndexes);
                        String[] separatedValue = connectingEdge.getValue().split(" ");
                        String trapezeLine = "|" + "a = " + separatedValue[0] + ", b = " + separatedValue[1] + ", c - d = " +
                                separatedValue[2] + ", index = " + separatedValue[3] + "|";
                        excludedIndexes.add(Integer.valueOf(separatedValue[3]));
                        System.out.println(trapezeLine);

                    }
                } else {
                    System.out.println("NO PATH");
                }
            } else {
                System.out.println("NO START PEEK");
            }
        } else {
            System.out.println("NO PATH");
        }
    }
}
