package little.things.acmp.task11;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static little.things.acmp.utils.InputUtil.getAllInputNumbers;
import static little.things.acmp.utils.OutputUtil.writeAllOutputNumbersGeneric;
import static little.things.acmp.utils.Paths.RESOURCES_PATH;

public class Rabbit {
    public static final String INPUT_PATH = RESOURCES_PATH.getPath() + "task11/INPUT.TXT";
    public static final String OUTPUT_PATH = RESOURCES_PATH.getPath() + "task11/OUTPUT.TXT";

    public static void main(String[] args) {
        List<int[]> allInputNumbers = getAllInputNumbers(INPUT_PATH);
        List<BigDecimal[]> allOutputNumbers = new LinkedList<>();
        for (int[] inputNumbers : allInputNumbers) {
            BigDecimal variants = calculateVariants(inputNumbers[0], inputNumbers[1]);
            allOutputNumbers.add(new BigDecimal[]{variants});
        }
        writeAllOutputNumbersGeneric(OUTPUT_PATH, allOutputNumbers);
    }

    private static BigDecimal calculateVariants(int k, int n) {
        BigDecimal[] stairsVariants = new BigDecimal[n + 1];
        Arrays.fill(stairsVariants, BigDecimal.ZERO);
        stairsVariants[0] = BigDecimal.ONE;
        for (int i = 1; i < stairsVariants.length; i++) {
            for (int j = i - k; j < i; j++) {
                if (j >= 0) {
                    stairsVariants[i] = stairsVariants[i].add(stairsVariants[j]);
                }
            }
        }
        return stairsVariants[n];
    }
}
