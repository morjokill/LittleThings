package algorithm.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Peek<T> {
    private T value;
    private List<Edge<T>> connectedTo;
    private List<Edge<T>> connectedFrom;

    public Peek(T value) {
        this.value = value;
        this.connectedTo = new LinkedList<>();
        this.connectedFrom = new LinkedList<>();
    }

    public T getValue() {
        return value;
    }

    public List<Edge<T>> getConnectedTo() {
        return connectedTo;
    }

    public List<Edge<T>> getConnectedFrom() {
        return connectedFrom;
    }

    private String getConnectedToString() {
        StringBuilder sb = new StringBuilder();
        for (Edge<T> tEdge : connectedTo) {
            sb.append(tEdge.getSecond().getValue().toString()).append(" - ").append(tEdge.getValue()).append(", ");
        }
        return sb.toString();
    }

    private String getConnectedFromString() {
        StringBuilder sb = new StringBuilder();
        for (Edge<T> tEdge : connectedFrom) {
            sb.append(tEdge.getFirst().getValue().toString()).append(" - ").append(tEdge.getValue()).append(", ");
        }
        return sb.toString();
    }

    public void removeConnectionTo(Edge<T> value) {
        int index = connectedTo.indexOf(value);
        connectedTo.get(index).remove();
    }

    public void removeConnectionFrom(Edge<T> value) {
        int index = connectedFrom.indexOf(value);
        connectedFrom.get(index).remove();
    }

    public void addConnectionTo(Peek<T> value) {
        connectedTo.add(new Edge<>(this, value));
    }

    public void addConnectionTo(Peek<T> value, String edgeValue) {
        connectedTo.add(new Edge<>(this, value, edgeValue));
    }

    public void addConnectionTo(Edge<T> value) {
        connectedTo.add(value);
    }

    public void addConnectionFrom(Peek<T> value) {
        connectedFrom.add(new Edge<>(value, this));
    }

    public void addConnectionFrom(Peek<T> value, String edgeValue) {
        connectedFrom.add(new Edge<>(value, this, edgeValue));
    }

    public void addConnectionFrom(Edge<T> value) {
        connectedFrom.add(value);
    }

    public boolean hasNext() {
        for (Edge<T> edge : connectedTo) {
            if (edge.isExist()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPrevious() {
        for (Edge<T> edge : connectedFrom) {
            if (edge.isExist()) {
                return true;
            }
        }
        return false;
    }

    public Edge<T> next() {
        for (Edge<T> edge : connectedTo) {
            if (edge.isExist()) {
                return edge;
            }
        }
        return null;
    }

    public int countST() {
        return connectedTo.size() + connectedFrom.size();
    }

    public void reset() {
        for (Edge<T> edge : connectedTo) {
            edge.reset();
        }
        for (Edge<T> edge : connectedFrom) {
            edge.reset();
        }
    }

    public Edge<T> getConnectingEdge(Peek<T> otherPeek) {
        for (Edge<T> edge : connectedTo) {
            if (edge.getOther(this).equals(otherPeek)) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Peek{" + "value=").append(value);
        if (!connectedTo.isEmpty()) {
            sb.append("; connectedTO=").append(getConnectedToString());
        }
        if (!connectedFrom.isEmpty()) {
            sb.append("; connectedFROM=").append(getConnectedFromString());
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peek<?> peek = (Peek<?>) o;
        return Objects.equals(value, peek.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, connectedTo, connectedFrom);
    }
}
