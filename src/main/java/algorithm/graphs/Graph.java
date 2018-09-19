package algorithm.graphs;

import java.util.*;

public class Graph<T> {
    private Map<T, Peek<T>> graph;

    public Graph(Map<T, Peek<T>> graph) {
        this.graph = graph;
    }

    private int getEdgeCount(Map<T, Peek<T>> graph) {
        Iterator<Peek<T>> iterator = graph.values().iterator();
        Peek<T> cur;
        int sum = 0;
        while (iterator.hasNext()) {
            cur = iterator.next();
            sum += cur.countST();
        }
        return sum / 2;
    }

    public String findEilPath(Map<T, Peek<T>> graph) {
        Stack<Edge<T>> stack = new Stack<>();
        List<Edge<T>> result = new ArrayList<>();
        int edgeCount = getEdgeCount(graph);
        if (!graph.isEmpty()) {
            Peek<T> first = graph.get(graph.keySet().iterator().next());
            Peek<T> cur = first;
            while (!stack.isEmpty() && cur.hasNext()) {
                while (!cur.hasNext()) {
                    if (!stack.isEmpty()) {
                        Edge<T> edge = stack.pop();
                        cur.addConnectionFrom(edge.getFirst());
                        cur = edge.getFirst();
                        cur.addConnectionTo(edge.getSecond());
                        result.add(edge);
                    } else {
                        return "No path here";
                    }
                }
                do {
                    if (cur.hasNext()) {
                        Peek<T> next = cur.next();
                        stack.push(new Edge<>(cur, next));
                        next.removeConnectionFrom(cur);
                        cur.removeConnectionTo(next);
                        cur = next;
                    } else {
                        return "No path here";
                    }
                } while (!cur.equals(first));
            }
            if (result.size() != edgeCount) {
                return "No path here";
            } else {
                return Arrays.toString(result.toArray());
            }
        } else {
            return "Graph is empty";
        }
    }
}
