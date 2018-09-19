package algorithm.graphs;

public class Edge<T> {
    private Peek<T> first;
    private Peek<T> second;

    public Edge(Peek<T> first, Peek<T> second) {
        this.first = first;
        this.second = second;
    }

    public Peek<T> getFirst() {
        return first;
    }

    public Peek<T> getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "first=" + first.getValue() +
                ", second=" + second.getValue() +
                '}';
    }
}
