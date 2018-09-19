package algorithm.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Peek<T> {
    private T value;
    private List<Peek<T>> connectedTo;
    private List<Peek<T>> connectedFrom;
    private String specialCondition;

    public Peek(T value) {
        this.value = value;
        this.connectedTo = new LinkedList<Peek<T>>();
        this.connectedFrom = new LinkedList<Peek<T>>();
    }

    public Peek(T value, String specialCondition) {
        this.value = value;
        this.connectedTo = new LinkedList<Peek<T>>();
        this.connectedFrom = new LinkedList<Peek<T>>();
        this.specialCondition = specialCondition;
    }

    public T getValue() {
        return value;
    }

    public List<Peek<T>> getConnectedTo() {
        return connectedTo;
    }

    public List<Peek<T>> getConnectedFrom() {
        return connectedFrom;
    }

    private String getConnectedToString() {
        StringBuilder sb = new StringBuilder();
        for (Peek<T> tPeek : connectedTo) {
            sb.append(tPeek.getValue().toString()).append(" ");
        }
        return sb.toString();
    }

    private String getConnectedFromString() {
        StringBuilder sb = new StringBuilder();
        for (Peek<T> tPeek : connectedFrom) {
            sb.append(tPeek.getValue().toString()).append(" ");
        }
        return sb.toString();
    }

    public void removeConnectionTo(Peek<T> value) {
        connectedTo.remove(value);
    }

    public void removeConnectionFrom(Peek<T> value) {
        connectedFrom.remove(value);
    }

    public void addConnectionTo(Peek<T> value) {
        connectedTo.add(value);
    }

    public void addConnectionFrom(Peek<T> value) {
        connectedFrom.add(value);
    }

    public boolean hasNext() {
        return connectedTo.size() != 0;
    }

    public Peek<T> next() {
        return connectedTo.iterator().next();
    }

    public int countST() {
        return connectedTo.size() + connectedFrom.size();
    }

    public String getSpecialCondition() {
        return specialCondition;
    }

    @Override
    public String toString() {
        return "Peek{" +
                "value=" + value +
                ", connectedFROM=" + getConnectedFromString() +
                ", connectedTO=" + getConnectedToString() +
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
