package algorithm.graphs.median;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Median {
    public static void main(String[] args) {
        int[][] graph = new int[][]{
                // 0  1  2  3
                {0, 1, 6, -1},  //0
                {-1, 0, 4, 1}, //1
                {-1, -1, 0, -1}, //2
                {-1, -1, 1, 0} //3
        };

        int[] vST = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] != -1 && graph[i][j] != 0) {
                    vST[i]++;
                }
            }
        }

        int[][] minPath = new int[graph.length][graph[0].length];
        for (int i = 0; i < minPath.length; i++) {
            for (int j = 0; j < minPath.length; j++) {
                if (i == j) {
                    minPath[i][j] = i;
                } else {
                    if (graph[i][j] != -1) {
                        minPath[i][j] = j;
                    } else {
                        minPath[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[j][i] != -1) {
                    for (int k = 0; k < graph.length; k++) {
                        if (graph[i][k] != -1) {
                            if (graph[j][i] + graph[i][k] < graph[j][k] || graph[j][k] == -1) {
                                graph[j][k] = graph[j][i] + graph[i][k];
                                minPath[j][k] = minPath[j][i];
                            }
                        }
                    }
                }
            }
        }

        int outerMedian = -1;
        int innerMedian = -1;

        int outerSum = 0;
        int innerSum = 0;

        int outerMinSum = Integer.MAX_VALUE;
        int innerMinSum = Integer.MAX_VALUE;

        int outerCenter = -1;
        int innerCenter = -1;

        int outerMax = Integer.MIN_VALUE;
        int innerMax = Integer.MIN_VALUE;

        int outerMin = Integer.MAX_VALUE;
        int innerMin = Integer.MAX_VALUE;

        System.out.println("MINIMUM:");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j] + " ");
                if (outerSum != -1) {
                    if (graph[i][j] != -1) {
                        outerSum += graph[i][j];
                    } else {
                        outerSum = -1;
                    }
                }
                if (innerSum != -1) {
                    if (graph[j][i] != -1) {
                        innerSum += graph[j][i];
                    } else {
                        innerSum = -1;
                    }
                }
                if (outerMax != -1) {
                    if (graph[i][j] != -1) {
                        outerMax = Math.max(outerMax, graph[i][j]);
                    } else {
                        outerMax = -1;
                    }
                }
                if (innerMax != -1) {
                    if (graph[j][i] != -1) {
                        innerMax = Math.max(innerMax, graph[j][i]);
                    } else {
                        innerMax = -1;
                    }
                }
            }
            System.out.println();
            if (outerSum < outerMinSum && outerSum != -1) {
                outerMinSum = outerSum;
                outerMedian = i;
            }
            if (innerSum < innerMinSum && innerSum != -1) {
                innerMinSum = innerSum;
                innerMedian = i;
            }
            if (outerMax < outerMin && outerMax != -1) {
                outerMin = outerMax;
                outerCenter = i;
            }
            if (innerMax < innerMin && innerMax != -1) {
                innerMin = innerMax;
                innerCenter = i;
            }
            outerSum = 0;
            innerSum = 0;
            outerMax = Integer.MIN_VALUE;
            innerMax = Integer.MIN_VALUE;
        }

        System.out.println();
        System.out.println("MIN PATH:");
        for (int[] ints : minPath) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        System.out.println();
        if (outerMedian != -1) {
            System.out.println("OUTER MEDIAN: " + outerMedian);
        }
        if (innerMedian != -1) {
            System.out.println("INNER MEDIAN: " + innerMedian);
        }
        if (outerCenter != -1) {
            System.out.println("OUTER CENTER: " + outerCenter);
        }
        if (innerCenter != -1) {
            System.out.println("INNER CENTER: " + innerCenter);
        }

        System.out.println();
        System.out.println("ST: ");
        System.out.println(Arrays.toString(vST));

        List<Integer> unevenList = new ArrayList<>();
        int unevenCount = 0;
        for (int i : vST) {
            if (i % 2 == 1) {
                unevenCount++;
                unevenList.add(i);
            }
        }

        if (unevenCount % 2 == 0) {
            if (unevenCount >= 2 && unevenCount <= 6) {
                System.out.println("NEED TO BUILD EIL");
                for (int i = 0; i < unevenList.size(); i++) {

                }
            }
        } else {
            System.out.println("UNREAL TO BUILD EIL");
        }
    }
}
