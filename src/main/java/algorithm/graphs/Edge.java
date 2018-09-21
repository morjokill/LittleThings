package algorithm.graphs;

public class Edge<T> {
    private String value;
    private Peek<T> first;
    private Peek<T> second;
    private boolean exists;

    Edge(Peek<T> first, Peek<T> second) {
        this.first = first;
        this.second = second;
        exists = true;
    }


    public Edge(Peek<T> first, Peek<T> second, String value) {
        this.value = value;
        this.first = first;
        this.second = second;
        exists = true;
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

    public Peek<T> getOther(Peek<T> peek) {
        if (first.equals(peek)) {
            return second;
        }
        if (second.equals(peek)) {
            return first;
        }
        return null;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isFirst(Peek<T> peek) {
        return first.equals(peek);
    }

    public void remove() {
        exists = false;
    }

    public void reset() {
        exists = true;
    }

    public boolean isExist() {
        return exists;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "value='" + value + '\'' +
                ", first=" + first.getValue() +
                ", second=" + second.getValue() +
                ", exists=" + exists +
                '}';
    }
}
