package little.things.algorithm.dynamic.seq;

import java.util.Arrays;

public class LCS {
    private static char[] seq1 = "Hello".toCharArray();
    private static char[] seq2 = "ella".toCharArray();

    private static int[][] subSeq = new int[seq1.length + 1][seq2.length + 1];
    private static Pair[][] subSeqIndex = new Pair[seq1.length + 1][seq2.length + 1];

    public static void main(String[] args) {
//        findLCS();
        findIncLCS();

        System.out.println(Arrays.deepToString(subSeq));
        print(subSeqIndex.length - 1, subSeqIndex[0].length - 1);
    }

    private static void findLCS() {
        for (int i = 0; i < seq1.length; i++) {
            subSeq[i][0] = 0;
        }
        for (int i = 0; i < seq2.length; i++) {
            subSeq[0][i] = 0;
        }

        for (int i = 1; i < subSeq.length; i++) {
            for (int j = 1; j < subSeq[0].length; j++) {
                if (seq1[i - 1] == seq2[j - 1]) {
                    subSeq[i][j] = subSeq[i - 1][j - 1] + 1;
                    subSeqIndex[i][j] = new Pair(i - 1, j - 1);
                } else {
                    if (subSeq[i][j - 1] >= subSeq[i - 1][j]) {
                        subSeq[i][j] = subSeq[i][j - 1];
                        subSeqIndex[i][j] = new Pair(i, j - 1);
                    } else {
                        subSeq[i][j] = subSeq[i - 1][j];
                        subSeqIndex[i][j] = new Pair(i - 1, j);
                    }
                }
            }
        }
    }

    private static void print(int i, int j) {
        if (i != 0 && j != 0) {
            if (subSeqIndex[i][j].getI() == i - 1 && subSeqIndex[i][j].getJ() == j - 1) {
                print(i - 1, j - 1);
                System.out.print(seq1[i - 1]);
            } else {
                if (subSeqIndex[i][j].getI() == i - 1 && subSeqIndex[i][j].getJ() == j) {
                    print(i - 1, j);
                } else {
                    if (subSeqIndex[i][j].getI() == i && subSeqIndex[i][j].getJ() == j - 1) {
                        print(i, j - 1);
                    }
                }
            }
        }
    }

    private static void insertSort() {
        for (int i = 0; i < seq2.length; i++) {
            for (int j = i; j < seq2.length; j++) {
                if (seq2[j] < seq2[i]) {
                    char cup = seq2[i];
                    seq2[i] = seq2[j];
                    seq2[j] = cup;
                }
            }
        }
    }

    private static void findIncLCS() {
        insertSort();
        findLCS();
    }
}
