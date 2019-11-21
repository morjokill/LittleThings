package little.things.acmp.task13;

import little.things.acmp.utils.InputUtil;
import little.things.acmp.utils.OutputUtil;

import java.util.LinkedList;
import java.util.List;

import static little.things.acmp.utils.Paths.RESOURCES_PATH;

public class BullsCows {
    private static final String INPUT_PATH = RESOURCES_PATH.getPath() + "task13/INPUT.TXT";
    private static final String OUTPUT_PATH = RESOURCES_PATH.getPath() + "task13/OUTPUT.TXT";

    public static void main(String[] args) {
        List<int[]> allInputNumbers = InputUtil.getAllInputNumbers(INPUT_PATH);
        List<long[]> outputNumbers = new LinkedList<>();
        for (int[] allInputNumber : allInputNumbers) {
            int origin = allInputNumber[0];
            int guess = allInputNumber[1];

            long[] bullsCows = getBullsCows(origin, guess);
            outputNumbers.add(bullsCows);
        }
        OutputUtil.writeAllOutputNumbers(OUTPUT_PATH, outputNumbers);
    }

    private static long[] getBullsCows(int origin, int guess) {
        long[] bullsCows = new long[2];

        int[] originDigits = getDigitsFromNumber(origin);
        int[] guessDigits = getDigitsFromNumber(guess);

        bullsCows[0] = getBulls(originDigits, guessDigits);
        bullsCows[1] = getCows(originDigits, guessDigits);

        return bullsCows;
    }

    private static int[] getDigitsFromNumber(int number) {
        int count = 0;
        while (number / Math.pow(10, count) >= 1) {
            count++;
        }

        int[] digits = new int[count];
        for (int i = 0; i < count; i++) {
            int lastDigits = (int) (number % Math.pow(10, i + 1));
            int firstOfLast = (int) (lastDigits / Math.pow(10, i));
            digits[count - i - 1] = firstOfLast;
        }
        return digits;
    }

    private static long getBulls(int[] originDigits, int[] guessDigits) {
        long bulls = 0;
        for (int i = 0; i < originDigits.length; i++) {
            if (originDigits[i] == guessDigits[i]) {
                bulls++;
            }
        }
        return bulls;
    }

    private static long getCows(int[] originDigits, int[] guessDigits) {
        long cows = 0;
        for (int i = 0; i < originDigits.length; i++) {
            for (int j = 0; j < guessDigits.length; j++) {
                if (originDigits[i] == guessDigits[j] && i != j) {
                    cows++;
                }
            }
        }
        return cows;
    }
}
