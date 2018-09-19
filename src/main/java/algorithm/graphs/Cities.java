package algorithm.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Cities {
    public static void main(String[] args) {
        String[] cities = new String[]{"Rostov", "Volgograd", "Dnepropetrovsk", "Riga", "Piter"};
        Map<Character, List<Peek<Character>>> graph = new HashMap<>();
        for (String city : cities) {
            city = city.toLowerCase();
            char first = city.charAt(0);
            char last = city.charAt(city.length() - 1);
            final Peek<Character> peek1 = new Peek<>(first);
            final Peek<Character> peek2 = new Peek<>(last);
            Edge<Character> edge = new Edge<>(peek1, peek2, city);
            peek1.addConnectionTo(edge);
            peek2.addConnectionFrom(edge);
            if (graph.containsKey(first)) {
                graph.get(first).add(peek1);
            } else {
                graph.put(first, new LinkedList<Peek<Character>>(){{add(peek1);}});
            }
            if (graph.containsKey(last)) {
                graph.get(last).add(peek2);
            } else {
                graph.put(last, new LinkedList<Peek<Character>>(){{add(peek2);}});
            }
        }

        for (List<Peek<Character>> peeks : graph.values()) {
            for (Peek<Character> peek : peeks) {
                if (peek.hasNext()) {
                    List<Peek<Character>> list = graph.get(peek.getValue());
                    for (Peek<Character> characterPeek : list) {
                        if (characterPeek != peek) {
                            if (characterPeek.hasPrevious()) {
                                peek.addConnectionFrom(characterPeek);
                            }
                        }
                    }
                } else {
                    List<Peek<Character>> list = graph.get(peek.getValue());
                    for (Peek<Character> characterPeek : list) {
                        if (characterPeek != peek) {
                            if (characterPeek.hasNext()) {
                                peek.addConnectionTo(characterPeek);
                            }
                        }
                    }
                }
            }
        }

        for (Character character : graph.keySet()) {
            System.out.println(character);
            List<Peek<Character>> list = graph.get(character);
            for (Peek<Character> characterPeek : list) {
                System.out.println(characterPeek);
            }
        }

        Graph<Character> graph1 = new Graph<>(graph);
    }
}
