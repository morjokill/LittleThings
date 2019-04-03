package little.things.algorithm.dynamic.subseq;

public class Main {
    public static void main(String[] args) {
        String line1 = "Hello world";
        String line2 = "ella";

        char[] mas1 = line1.toCharArray();
        char[] mas2 = line2.toCharArray();

        int n = mas1.length;
        int m = mas2.length;

        int[][] subMatrix = new int[n][m];
        char[][] pair = new char[n][m];

        for (int j = 0; j < m; j++) {
            if (mas1[0] == mas2[j]) {
                subMatrix[0][j] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (mas1[i] == mas2[0]) {
                subMatrix[i][0] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mas1[i] == mas2[j]) {
                    subMatrix[i][j] = subMatrix[i - 1][j - 1] + 1;
                } else {
                    if (subMatrix[i - 1][j] >= subMatrix[i][j - 1]) {
                        subMatrix[i][j] = subMatrix[i - 1][j];
                    } else {
                        subMatrix[i][j] = subMatrix[i][j - 1];
                    }
                }
            }
        }

        for (int[] matrix : subMatrix) {
            for (int i : matrix) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
