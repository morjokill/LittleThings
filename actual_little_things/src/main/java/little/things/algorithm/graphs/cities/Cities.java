package little.things.algorithm.graphs.cities;

import little.things.algorithm.graphs.Edge;
import little.things.algorithm.graphs.Graph;
import little.things.algorithm.graphs.Peek;

import java.util.*;

public class Cities {
    public static void main(String[] args) {
        String[] cities = new String[]{"Kn", "So", "Ok"};
        Map<Character, Peek<Character>> graph = new HashMap<>();
        for (String city : cities) {
            city = city.toLowerCase();
            char first = city.charAt(0);
            char last = city.charAt(city.length() - 1);
            Peek<Character> firstPeek;
            Peek<Character> lastPeek;
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
            Edge<Character> edge = new Edge<>(graph.get(first), graph.get(last), city);
            firstPeek.addConnectionTo(edge);
            lastPeek.addConnectionFrom(edge);
        }

        Graph<Character> graph1 = new Graph<>();
        Peek<Character> pathPeek = null;
        boolean pathFound = false;
        List<Edge<Character>> path = null;

        for (Character character : graph.keySet()) {
            System.out.println(character);
            Peek<Character> peek = graph.get(character);
            System.out.println(peek);
        }

        System.out.println();
        for (Character character : graph.keySet()) {
            Peek<Character> peek = graph.get(character);
            path = graph1.findEilPath(graph, peek, graph1.getEdgeCount(graph, true));
            if (Objects.nonNull(path)) {
                pathFound = true;
                pathPeek = peek;
                break;
            } else {
                for (Peek<Character> characterPeek : graph.values()) {
                    characterPeek.reset();
                }
            }
        }

        System.out.println();
        if (pathFound) {
            ListIterator<Edge<Character>> revIterator = path.listIterator(path.size());
            System.out.println("PATH FOUND FOR CHARACTER: " + pathPeek.getValue());
            while (revIterator.hasPrevious()) {
                System.out.print(revIterator.previous().getValue() + " - ");
            }
        } else {
            System.out.println("NO PATH");
        }
    }
}
