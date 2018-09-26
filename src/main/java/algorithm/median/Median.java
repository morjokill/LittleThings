package algorithm.median;

import java.util.Arrays;
import java.util.Random;

public class Median {
    public static void main(String[] args) {
        int[] array = new int[]{5, 3, 2, 4, 6, 1, 7, 8};
        if (array.length % 2 != 0) {
            System.out.println("RESULT: " + findKElement(array, array.length / 2));
        } else {
            System.out.println("RESULT: " + (findKElement(array, array.length / 2) + findKElement(array, array.length / 2 + 1)) / 2);
        }
    }

    private static int findKElement(int[] array, int k) {
        System.out.println("k: " + k);
        Random random = new Random();
        int pivot = array[random.nextInt(array.length - 1)];
        System.out.println("Pivot: " + pivot);
        ArrayPivot arrayPivot = leftRightFromPivot(array, pivot);
        array = arrayPivot.getArray();
        int pivotPos = arrayPivot.getPivotPos();
        System.out.println("PivotPos: " + pivotPos);
        if (pivotPos == k) {
            return array[pivotPos];
        } else {
            if (pivotPos < k) {
                System.out.println("PIVOTPOS < K");
                return findKElement(Arrays.copyOfRange(array, pivotPos + 1, array.length + 1), k - pivotPos);
            } else {
                System.out.println("PIVOTPOS > K");
                return findKElement(Arrays.copyOfRange(array, 0, pivotPos), k);
            }
        }
    }

    private static ArrayPivot leftRightFromPivot(int[] array, int pivot) {
        int[] result = new int[array.length];
        int left = 0;
        int right = array.length - 1;
        for (int anArray : array) {
            if (anArray < pivot) {
                result[left] = anArray;
                left++;
            }
            if (anArray > pivot) {
                result[right] = anArray;
                right--;
            }
        }
        Arrays.fill(result, left, right + 1, pivot);
        System.out.println(Arrays.toString(result));
        return new ArrayPivot(result, right);
    }
}
