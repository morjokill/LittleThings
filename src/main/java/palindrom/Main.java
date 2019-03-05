package palindrom;

public class Main {
    public static void main(String[] args) {
        System.out.println(findMax("11111111111111155555555555555555555555555555555555555555"));
    }

    private static String findMax(String line) {
        String max = "" + line.charAt(0);
        for (int i = 0; i < line.length(); i++) {
            right: for (int j = line.length() - 1; j > i; j--) {
                if (line.charAt(i) == line.charAt(j)) {
                    if (j - i > max.length()) {
                        int left = i;
                        int right = j;
                        while (right > left) {
                            if (line.charAt(left) == line.charAt(right)) {
                                left++;
                                right--;
                            } else {
                                break right;
                            }
                        }
                        max = line.substring(i, j + 1);
                        break;
                    }
                }
            }
        }
        return max;
    }
}
