package algorithm.median;

public class ArrayPivot {
    private int[] array;
    private int pivotPos;

    public ArrayPivot(int[] array, int pivot) {
        this.array = array;
        this.pivotPos = pivot;
    }

    public int[] getArray() {
        return array;
    }

    public int getPivotPos() {
        return pivotPos;
    }
}
