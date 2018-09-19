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
            sb.append(tEdge.getSecond().getValue().toString()).append(" - ").append(tEdge.getValue()).append(" ");
        }
        return sb.toString();
    }

    private String getConnectedFromString() {
        StringBuilder sb = new StringBuilder();
        for (Edge<T> tEdge : connectedFrom) {
            sb.append(tEdge.getFirst().getValue().toString()).append(" - ").append(tEdge.getValue()).append(" ");
        }
        return sb.toString();
    }

    public void removeConnectionTo(Edge<T> value) {
        connectedTo.remove(value);
    }

    public void removeConnectionFrom(Edge<T> value) {
        connectedFrom.remove(value);
    }

    public void addConnectionTo(Peek<T> value) {
        connectedTo.add(new Edge<>(this, value));
    }

    public void addConnectionTo(Edge<T> value) {
        connectedTo.add(value);
    }

    public void addConnectionFrom(Peek<T> value) {
        connectedFrom.add(new Edge<>(value, this));
    }

    public void addConnectionFrom(Edge<T> value) {
        connectedFrom.add(value);
    }

    public boolean hasNext() {
        return connectedTo.size() != 0;
    }

    public boolean hasPrevious() {
        return connectedFrom.size() != 0;
    }

    public Edge<T> next() {
        return connectedTo.iterator().next();
    }

    public int countST() {
        return connectedTo.size() + connectedFrom.size();
    }

    @Override
    public String toString() {
        return "Peek{" +
                "value=" + value +
                ", connectedTO=" + getConnectedToString() +
                ", connectedFROM=" + getConnectedFromString() +
                '}';
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
