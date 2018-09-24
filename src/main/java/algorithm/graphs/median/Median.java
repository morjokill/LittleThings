package algorithm.graphs.median;

public class Median {
    public static void main(String[] args) {
        int[][] graph = new int[][]{
              // 0  1  2  3
                {0, 1, 8, 10},  //0
                {-1, 0, 2, -1}, //1
                {-1, -1, 0, 3}, //2
                {-1, -1, -1, 0} //3
        };
        int[][] minGraph = new int[graph.length][graph[0].length];
        int[][] minPath = new int[graph.length][graph[0].length];

        for (int[] ints : graph) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {

            }
        }
    }
}
