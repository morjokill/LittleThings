package algorithm.graphs;

import java.util.*;

public class Graph<T> {
    public List<Edge<T>> findEilPath(Map<T, Peek<T>> graph, Peek<T> first) {
        Stack<Edge<T>> stack = new Stack<>();
        List<Edge<T>> result = new ArrayList<>();
        int edgeCount = getEdgeCount(graph);
        if (!graph.isEmpty()) {
            if (!checkPotentialPath(graph)) return null;
            Peek<T> cur = first;
            while (!stack.isEmpty() || cur.hasNext()) {
                while (!cur.hasNext()) {
                    if (!stack.isEmpty()) {
                        Edge<T> edge = stack.pop();
                        cur = edge.getFirst();
                        result.add(edge);
                    } else {
                        break;
                    }
                }
                do {
                    if (cur.hasNext()) {
                        Edge<T> nextEdge = cur.next();
                        Peek<T> next = nextEdge.getSecond();
                        stack.push(nextEdge);
                        next.removeConnectionFrom(nextEdge);
                        cur.removeConnectionTo(nextEdge);
                        cur = next;
                    } else {
                        break;
                    }
                } while (!cur.equals(first));
            }
            if (result.size() != edgeCount) {
                return null;
            } else {
                return result;
            }
        } else {
            return null;
        }
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

    private boolean checkPotentialPath(Map<T, Peek<T>> graph) {
        int unevenCount = 0;
        for (Peek<T> tPeek : graph.values()) {
            if (tPeek.countST() % 2 != 0) {
                unevenCount++;
            }
        }
        return unevenCount == 0 || unevenCount == 2;
    }
}
