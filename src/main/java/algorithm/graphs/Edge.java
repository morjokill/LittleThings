package algorithm.graphs;

public class Edge<T> {
    private String value;
    private Peek<T> first;
    private Peek<T> second;

    public Edge(Peek<T> first, Peek<T> second) {
        this.first = first;
        this.second = second;
    }


    public Edge(Peek<T> first, Peek<T> second, String value) {
        this.value = value;
        this.first = first;
        this.second = second;
    }

    public String getValue() {
        return value;
    }

    public Peek<T> getFirst() {
        return first;
    }

    public Peek<T> getSecond() {
        return second;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isFirst(Peek<T> peek) {
        return first.equals(peek);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "value=" + value +
                ", first=" + first.getValue() +
                ", second=" + second.getValue() +
                '}';
    }
}
