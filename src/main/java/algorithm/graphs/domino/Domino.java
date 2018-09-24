package algorithm.graphs.domino;

import algorithm.graphs.Edge;
import algorithm.graphs.Graph;
import algorithm.graphs.Peek;

import java.util.*;

public class Domino {
    public static void main(String[] args) {
        int[] dominoes = new int[]{6, 4, 5, 4, 4, 2, 2, 6};
        Map<Integer, Peek<Integer>> graph = new HashMap<>();
        if (dominoes.length % 2 == 0) {
            for (int i = 0; i < dominoes.length; i = i + 2) {
                int first = dominoes[i];
                int last = dominoes[i + 1];
                String firstString = "|" + first + " | " + last + "|";
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
                firstPeek.addConnectionTo(edgeTo);
                firstPeek.addConnectionFrom(edgeTo);
                lastPeek.addConnectionTo(edgeTo);
                lastPeek.addConnectionFrom(edgeTo);
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

            for (Peek<Integer> integerPeek : graph.values()) {
                System.out.println();
                System.out.println("FINDING PATH FOR: " + integerPeek.getValue());
                pathPeeks = graph1.findEilCircle(graph, integerPeek);
                if (Objects.nonNull(pathPeeks)) {
                    if (pathPeeks.get(0).equals(pathPeeks.get(pathPeeks.size() - 1))) {
                        pathFoundForPeeks = true;
                        pathPeekForPeeks = integerPeek;
                        break;
                    }
                }
                for (Peek<Integer> peek : graph.values()) {
                    peek.reset();
                }
            }

            System.out.println();
            if (pathFoundForPeeks) {
                System.out.println("PATH FOUND FOR INTEGER: " + pathPeekForPeeks.getValue());
                for (int i = pathPeeks.size() - 1; i > 0; i--) {
                    System.out.println("|" + pathPeeks.get(i).getValue() + " | " + pathPeeks.get(i - 1).getValue() + "|");
                }
            } else {
                System.out.println("NO PATH");
            }
        } else {
            System.out.println("NO PATH");
        }
    }
}
