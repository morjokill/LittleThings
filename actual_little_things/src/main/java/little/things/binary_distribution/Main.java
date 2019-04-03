package little.things.binary_distribution;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        findPair(array);
    }

    private static void findPair(int[] array) {
        if (array.length % 2 == 0 && array.length > 0) {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    System.out.println(array[i] + " " + array[j]);
                    int[] newArray = new int[array.length - 2];
                    int count = 0;
                    for (int k = 0; k < array.length; k++) {
                        if (k != i && k != j) {
                            newArray[count] = array[k];
                            count++;
                        }
                    }
                    findPair(newArray);
                }
            }
        } else {
            System.out.println();
        }
    }
}
