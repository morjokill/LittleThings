package algorithm.dynamic.subseq;

import java.util.Objects;

public class Pair {
    private char value;
    private int count;

    public Pair() {
    }

    public Pair(char value, int count) {
        this.count = count;
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getIndex() {
        return count;
    }

    public void setIndex(int index) {
        this.count = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return count == pair.count &&
                value == pair.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, count);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "value='" + value + '\'' +
                ", index=" + count +
                '}';
    }
}
