package algorithm.graphs;

import java.util.*;

public class Domino {
    public static void main(String[] args) {
        int[] dominoes = new int[]{0, 3, 4, 5, 3, 5};
        Map<Integer, Peek<Integer>> graph = new HashMap<>();
        if (dominoes.length % 2 == 0) {
            for (int i = 0; i < dominoes.length; i = i + 2) {
                int first = dominoes[i];
                int last = dominoes[i + 1];
                String firstString = "|" + first + " | " + last + "|";
                String secondString = "|" + last + " | " + first + "|";
                System.out.println(firstString);
                Peek<Integer> firstPeek;
                Peek<Integer> lastPeek;
                if (!graph.containsKey(first)) {
                    firstPeek = new Peek<>(first);
                    graph.put(first, firstPeek);
                } else {
                    firstPeek = graph.get(first);
                }
                if (!graph.containsKey(last)) {
                    lastPeek = new Peek<>(last);
                    graph.put(last, lastPeek);
                } else {
                    lastPeek = graph.get(last);
                }
                Edge<Integer> edgeTo = new Edge<>(graph.get(first), graph.get(last), firstString);
                Edge<Integer> edgeFrom = new Edge<>(graph.get(last), graph.get(first), secondString);
                firstPeek.addConnectionTo(edgeTo);
                firstPeek.addConnectionFrom(edgeFrom);
                lastPeek.addConnectionTo(edgeTo);
                lastPeek.addConnectionFrom(edgeFrom);
            }

            for (Integer integer : graph.keySet()) {
                System.out.println(integer);
                Peek<Integer> peek = graph.get(integer);
                System.out.println(peek);
            }

            Graph<Integer> graph1 = new Graph<>();
            Peek<Integer> pathPeek = null;
            boolean pathFound = false;
            List<Edge<Integer>> path = null;

            for (Integer integer : graph.keySet()) {
                Peek<Integer> peek = graph.get(integer);
                path = graph1.findEilPath(graph, peek);
                if (Objects.nonNull(path)) {
                    pathFound = true;
                    pathPeek = peek;
                    break;
                } else {
                    for (Peek<Integer> integerPeek : graph.values()) {
                        integerPeek.reset();
                    }
                }
            }

            System.out.println();
            if (pathFound) {
                ListIterator<Edge<Integer>> revIterator = path.listIterator(path.size());
                System.out.println("PATH FOUND FOR INTEGER: " + pathPeek.getValue());
                while (revIterator.hasPrevious()) {
                    System.out.print(revIterator.previous().getValue() + " - ");
                }
            } else {
                System.out.println("NO PATH");
            }
        } else {
            System.out.println("NO PATH");
        }
    }
}
