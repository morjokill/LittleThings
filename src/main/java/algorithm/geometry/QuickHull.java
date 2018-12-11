package algorithm.geometry;

import java.util.LinkedList;
import java.util.List;

public class QuickHull {
    public static void main(String[] args) {
        int[] x = new int[]{0, 1, 3, 4, 2, 2};
        int[] y = new int[]{0, 0, 2, 4, 3, 2};

        int mostLeftDotIndex = findMinIndex(x);
        int mostRightDotIndex = findMaxIndex(x);

        List<Integer> lowerDots = new LinkedList<>();
        List<Integer> higherDots = new LinkedList<>();
        for (int i = 0; i < x.length; i++) {
            if (isLower(x[i], y[i], x[mostLeftDotIndex], y[mostLeftDotIndex], x[mostRightDotIndex], y[mostRightDotIndex])) {
                lowerDots.add(i);
            }
            if (isHigher(x[i], y[i], x[mostLeftDotIndex], y[mostLeftDotIndex], x[mostRightDotIndex], y[mostRightDotIndex])) {
                higherDots.add(i);
            }
        }

        double maxArea = 0;
        int maxAreaIndex = 0;
        for (Integer i : higherDots) {
            if (getTriangleArea(x[i], y[i], x[mostLeftDotIndex], y[mostLeftDotIndex], x[mostRightDotIndex], y[mostRightDotIndex]) > maxArea) {
                maxArea = getTriangleArea(x[i], y[i], x[mostLeftDotIndex], y[mostLeftDotIndex], x[mostRightDotIndex], y[mostRightDotIndex]);
                maxAreaIndex = i;
            }
        }



        System.out.println(mostLeftDotIndex);
        System.out.println(mostRightDotIndex);
    }

    private static double findIndirectMultiply(int Ax, int Ay, int Bx, int By) {
        return Ax * By - Bx * Ay;
    }

    private static boolean isLower(int Ox, int Oy, int Ax, int Ay, int Bx, int By) {
        return findIndirectMultiply(Ax, Ay, Bx, By) <= findIndirectMultiply(Ax, Ay, Ox, Oy);
    }

    private static boolean isHigher(int Ox, int Oy, int Ax, int Ay, int Bx, int By) {
        return findIndirectMultiply(Ax, Ay, Bx, By) >= findIndirectMultiply(Ax, Ay, Ox, Oy);
    }

    private static int findMinIndex(int[] array) {
        int min = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[min]) min = i;
        }
        return min;
    }

    private static int findMaxIndex(int[] array) {
        int max = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[max]) max = i;
        }
        return max;
    }

    private static double getDistAB(int Ax, int Ay, int Bx, int By) {
        return Math.sqrt((Ax - Bx) * (Ax - Bx) + (Ay - By) * (Ay - By));
    }

    private static double getHalfPerimeter(double distA, double distB, double distC) {
        return (distA + distB + distC) / 2;
    }

    private static double getTriangleArea(int Ax, int Ay, int Bx, int By, int Cx, int Cy) {
        double distA = getDistAB(Ax, Ay, Bx, By);
        double distB = getDistAB(Ax, Ay, Cx, Cy);
        double distC = getDistAB(Bx, By, Cx, Cy);
        double halfPerimeter = getHalfPerimeter(distA, distB, distC);
        return Math.sqrt(halfPerimeter * (halfPerimeter - distA) * (halfPerimeter - distB) * (halfPerimeter - distC));
    }
}
