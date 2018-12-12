package algorithm.geometry;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Shell {
    public static void main(String[] args) {
        int test = 100;
        Long grahamTime = 0L;
        Long quickHullTime = 0L;
        for (int testCount = 0; testCount < test; testCount++) {
            int n = 2000;

            Set<Dot> set = new HashSet<>();
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                int random1 = random.nextInt(350 - 1) + 1;
                int random2 = random.nextInt(350 - 1) + 1;
                Dot dot = new Dot(random1, random2);
                set.add(dot);
            }

            int[] x = new int[set.size()];
            int[] y = new int[set.size()];
            int k = 0;
            for (Dot dot : set) {
                x[k] = dot.getX();
                y[k] = dot.getY();
                k++;
            }

            Long beforeG = System.currentTimeMillis();
            Set<Dot> grahamScan = findGrahamScan(x, y);
            Long afterG = System.currentTimeMillis();
            Long beforeQ = System.currentTimeMillis();
            Set<Dot> quickHull = findQuickHull(x, y);
            Long afterQ = System.currentTimeMillis();

            long grahamTime1 = afterG - beforeG;
            grahamTime += grahamTime1;
            long quickHullTime1 = afterQ - beforeQ;
            quickHullTime += quickHullTime1;

//        showFrame(x, y, grahamScan, quickHull);
        }

        System.out.println("grahamTime " + grahamTime + " (ms)");
        System.out.println("quickHullTime " + quickHullTime + " (ms)");
    }

    private static void showFrame(int[] x, int[] y, Set<Dot> grahamScan, Set<Dot> quickHull) {
        new JFrame() {
            {
                setBounds(0, 0, 1600, 500);
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

            public void paint(Graphics graphics) {
                int scale = 1;
                super.paint(graphics);
                for (int i = 0; i < x.length; i++) {
                    int x1 = 20 + (x[i]) * scale;
                    int y1 = 40 + (y[i]) * scale;
                    graphics.fillOval(x1, y1, 10, 10);
                    graphics.drawChars((x[i] + " " + y[i]).toCharArray(), 0, (x[i] + " " + y[i]).toCharArray().length, x1, y1);
                }
                for (Dot dot : grahamScan) {
                    int x1 = 600 + (dot.getX()) * scale;
                    int y1 = 40 + (dot.getY()) * scale;
                    graphics.fillOval(x1, y1, 10, 10);
                    graphics.drawChars((dot.getX() + " " + dot.getY()).toCharArray(), 0, (dot.getX() + " " + dot.getY()).toCharArray().length, x1, y1);
                }
                for (Dot dot : quickHull) {
                    int x1 = 1200 + (dot.getX()) * scale;
                    int y1 = 40 + (dot.getY()) * scale;
                    graphics.fillOval(x1, y1, 10, 10);
                    graphics.drawChars((dot.getX() + " " + dot.getY()).toCharArray(), 0, (dot.getX() + " " + dot.getY()).toCharArray().length, x1, y1);
                }
            }
        };
    }

    private static Set<Dot> findGrahamScan(int[] x, int[] y) {
        Set<Dot> shellResult = new HashSet<>();
        try {
            if (x.length < 4) {
                for (int i = 0; i < x.length; i++) {
                    shellResult.add(new Dot(x[i], y[i]));
                }
                return shellResult;
            } else {
                int mostLeftDotIndex = 0;
                for (int i = 1; i < y.length; i++) {
                    if (x[i] < x[mostLeftDotIndex]) {
                        mostLeftDotIndex = i;
                    } else {
                        if (x[i] == x[mostLeftDotIndex]) {
                            if (y[i] < y[mostLeftDotIndex]) {
                                mostLeftDotIndex = i;
                            }
                        }
                    }
                }

                int cup = x[0];
                x[0] = x[mostLeftDotIndex];
                x[mostLeftDotIndex] = cup;
                cup = y[0];
                y[0] = y[mostLeftDotIndex];
                y[mostLeftDotIndex] = cup;

                for (int i = 1; i < x.length; i++) {
                    int minIndex = i;
                    for (int j = i; j < x.length; j++) {
                        if (isLower(x[j], y[j], x[0], y[0], x[minIndex], y[minIndex])) {
                            minIndex = j;
                        }
                    }
                    cup = x[i];
                    x[i] = x[minIndex];
                    x[minIndex] = cup;
                    cup = y[i];
                    y[i] = y[minIndex];
                    y[minIndex] = cup;
                }

                List<Integer> stack = new LinkedList<>();
                stack.add(0);
                stack.add(1);

                int first = 0;
                int second = 1;
                for (int i = 2; i < x.length; i++) {
                    while (!isHigher(x[i], y[i], x[first], y[first], x[second], y[second])) {
                        ((LinkedList<Integer>) stack).removeLast();
                        second = stack.get(stack.size() - 1);
                        first = stack.get(stack.size() - 2);
                    }
                    stack.add(i);
                    first = second;
                    second = i;
                    if (i == x.length - 1) {
                        while (!isHigher(x[0], y[0], x[first], y[first], x[second], y[second])) {
                            ((LinkedList<Integer>) stack).removeLast();
                            second = stack.get(stack.size() - 1);
                            first = stack.get(stack.size() - 2);
                        }
                    }
                }

                for (Integer i : stack) {
                    shellResult.add(new Dot(x[i], y[i]));
                }
            }
        } catch (Exception e) {
        }
        return shellResult;
    }

    private static Set<Dot> findQuickHull(int[] x, int[] y) {
        Set<Dot> shellResult = new HashSet<>();
        if (x.length < 4) {
            for (int i = 0; i < x.length; i++) {
                shellResult.add(new Dot(x[i], y[i]));
            }
            return shellResult;
        } else {
            int mostLeftDotIndex = findMinIndex(x);
            int mostRightDotIndex = findMaxIndex(x);

            shellResult.add(new Dot(x[mostLeftDotIndex], y[mostLeftDotIndex]));
            shellResult.add(new Dot(x[mostRightDotIndex], y[mostRightDotIndex]));

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
                double triangleArea = getTriangleArea(x[i], y[i], x[mostLeftDotIndex], y[mostLeftDotIndex], x[mostRightDotIndex], y[mostRightDotIndex]);
                if (triangleArea > maxArea) {
                    maxArea = triangleArea;
                    maxAreaIndex = i;
                }
            }

            shellResult.add(new Dot(x[maxAreaIndex], y[maxAreaIndex]));

            List<Integer> outOfTriangleHigherLeftDots = new LinkedList<>();
            List<Integer> outOfTriangleHigherRightDots = new LinkedList<>();
            outOfTriangleHigherLeftDots.add(mostLeftDotIndex);
            outOfTriangleHigherLeftDots.add(maxAreaIndex);
            outOfTriangleHigherRightDots.add(mostRightDotIndex);
            outOfTriangleHigherRightDots.add(maxAreaIndex);
            for (Integer higherDot : higherDots) {
                if (!isLower(x[higherDot], y[higherDot], x[mostLeftDotIndex], y[mostLeftDotIndex], x[maxAreaIndex], y[maxAreaIndex])) {
                    outOfTriangleHigherLeftDots.add(higherDot);
                }
                if (!isHigher(x[higherDot], y[higherDot], x[mostRightDotIndex], y[mostRightDotIndex], x[maxAreaIndex], y[maxAreaIndex])) {
                    outOfTriangleHigherRightDots.add(higherDot);
                }
            }

            maxArea = 0;
            maxAreaIndex = 0;
            for (Integer i : lowerDots) {
                double triangleArea = getTriangleArea(x[i], y[i], x[mostLeftDotIndex], y[mostLeftDotIndex], x[mostRightDotIndex], y[mostRightDotIndex]);
                if (triangleArea > maxArea) {
                    maxArea = triangleArea;
                    maxAreaIndex = i;
                }
            }


            shellResult.add(new Dot(x[maxAreaIndex], y[maxAreaIndex]));

            List<Integer> outOfTriangleLowerLeftDots = new LinkedList<>();
            List<Integer> outOfTriangleLowerRightDots = new LinkedList<>();
            outOfTriangleLowerLeftDots.add(mostLeftDotIndex);
            outOfTriangleLowerLeftDots.add(maxAreaIndex);
            outOfTriangleLowerRightDots.add(mostRightDotIndex);
            outOfTriangleLowerRightDots.add(maxAreaIndex);
            for (Integer lowerDot : lowerDots) {
                if (!isHigher(x[lowerDot], y[lowerDot], x[mostLeftDotIndex], y[mostLeftDotIndex], x[maxAreaIndex], y[maxAreaIndex])) {
                    outOfTriangleLowerLeftDots.add(lowerDot);
                }
                if (!isLower(x[lowerDot], y[lowerDot], x[mostRightDotIndex], y[mostRightDotIndex], x[maxAreaIndex], y[maxAreaIndex])) {
                    outOfTriangleLowerRightDots.add(lowerDot);
                }
            }

            int[] outHigherLeftX = new int[outOfTriangleHigherLeftDots.size()];
            int[] outHigherRightX = new int[outOfTriangleHigherRightDots.size()];
            int[] outHigherLeftY = new int[outOfTriangleHigherLeftDots.size()];
            int[] outHigherRightY = new int[outOfTriangleHigherRightDots.size()];
            int i = 0;
            fillArray(x, y, outOfTriangleHigherLeftDots, outHigherLeftX, outHigherLeftY, i);
            fillArray(x, y, outOfTriangleHigherRightDots, outHigherRightX, outHigherRightY, i);
            int[] outLowerLeftX = new int[outOfTriangleLowerLeftDots.size()];
            int[] outLowerLeftY = new int[outOfTriangleLowerLeftDots.size()];
            int[] outLowerRightX = new int[outOfTriangleLowerRightDots.size()];
            int[] outLowerRightY = new int[outOfTriangleLowerRightDots.size()];
            int j = 0;
            fillArray(x, y, outOfTriangleLowerLeftDots, outLowerLeftX, outLowerLeftY, j);
            fillArray(x, y, outOfTriangleLowerRightDots, outLowerRightX, outLowerRightY, j);
            shellResult.addAll(findQuickHull(outHigherLeftX, outHigherLeftY));
            shellResult.addAll(findQuickHull(outHigherRightX, outHigherRightY));
            shellResult.addAll(findQuickHull(outLowerLeftX, outLowerLeftY));
            shellResult.addAll(findQuickHull(outLowerRightX, outLowerRightY));

            return shellResult;
        }
    }

    private static void fillArray(int[] x, int[] y, List<Integer> outOfTriangleLowerDots, int[] outLowerX, int[] outLowerY, int j) {
        for (Integer outLower : outOfTriangleLowerDots) {
            outLowerX[j] = x[outLower];
            outLowerY[j] = y[outLower];
            j++;
        }
    }

    private static int findVectorX(int Ax, int Bx) {
        return Bx - Ax;
    }

    private static int findVectorY(int Ay, int By) {
        return By - Ay;
    }

    private static double findIndirectMultiply(int Ax, int Ay, int Bx, int By) {
        return Ax * By - Bx * Ay;
    }

    private static double getIndirectMultiply(int Ox, int Oy, int Ax, int Ay, int Bx, int By) {
        int vectorABX = findVectorX(Ax, Bx);
        int vectorAOX = findVectorX(Ax, Ox);
        int vectorABY = findVectorY(Ay, By);
        int vectorAOY = findVectorY(Ay, Oy);
        return findIndirectMultiply(vectorABX, vectorABY, vectorAOX, vectorAOY);
    }

    private static boolean isLower(int Ox, int Oy, int Ax, int Ay, int Bx, int By) {
        double indirectMultiply = getIndirectMultiply(Ox, Oy, Ax, Ay, Bx, By);
        return indirectMultiply < 0;
    }

    private static boolean isHigher(int Ox, int Oy, int Ax, int Ay, int Bx, int By) {
        double indirectMultiply = getIndirectMultiply(Ox, Oy, Ax, Ay, Bx, By);
        return indirectMultiply > 0;
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
