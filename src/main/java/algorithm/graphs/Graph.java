package algorithm.graphs;

import java.util.*;

public class Graph<T> {
    public List<Edge<T>> findEilPath(Map<T, Peek<T>> graph, Peek<T> first, int edgeCount) {
        Stack<Edge<T>> stack = new Stack<>();
        List<Edge<T>> result = new ArrayList<>();
        if (!graph.isEmpty()) {
            if (!checkPotentialPath(graph)) return null;
            Peek<T> cur = first;
            while (!stack.isEmpty() || cur.hasNext()) {
                while (!cur.hasNext()) {
                    if (!stack.isEmpty()) {
                        Edge<T> edge = stack.pop();
                        System.out.println("EDGE FROM STACK: " + edge.toString());
                        cur = edge.getOther(cur);
                        result.add(edge);
                    } else {
                        break;
                    }
                }
                do {
                    if (cur.hasNext()) {
                        Edge<T> nextEdge = cur.next();
                        Peek<T> next = nextEdge.getOther(cur);
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

    public List<Peek<T>> findEilCircle(Map<T, Peek<T>> graph, Peek<T> first) {
        if (!checkPotentialPath(graph)) return null;
        List<Peek<T>> result = new ArrayList<>();
        Stack<Peek<T>> stack = new Stack<>();
        stack.push(first);
        Peek<T> cur;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.hasNext()) {
                Edge<T> edge = cur.next();
                stack.push(edge.getOther(cur));
                cur.removeConnectionTo(edge);
                edge.getOther(cur).removeConnectionFrom(edge);
            }
            if (cur.equals(stack.peek())) {
                System.out.println("EDGE FROM STACK: " + stack.peek().toString());
                result.add(stack.pop());
            }
        }
        return result;
    }

    public int getEdgeCount(Map<T, Peek<T>> graph, boolean isOriented) {
        Iterator<Peek<T>> iterator = graph.values().iterator();
        Peek<T> cur;
        int sum = 0;
        while (iterator.hasNext()) {
            cur = iterator.next();
            sum += cur.countST();
        }
        if (isOriented) {
            return sum / 2;
        } else {
            return sum / 4;
        }
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

    private boolean checkPotentialCircle(Map<T, Peek<T>> graph) {
        for (Peek<T> tPeek : graph.values()) {
            if (tPeek.countST() % 2 != 0) return false;
        }
        return true;
    }
}
