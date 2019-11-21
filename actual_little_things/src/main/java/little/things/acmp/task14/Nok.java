package little.things.acmp.task14;

import little.things.acmp.utils.InputUtil;
import little.things.acmp.utils.OutputUtil;

import java.util.LinkedList;
import java.util.List;

import static little.things.acmp.utils.Paths.RESOURCES_PATH;

public class Nok {
    private static final String INPUT_PATH = RESOURCES_PATH.getPath() + "task15/INPUT.TXT";
    private static final String OUTPUT_PATH = RESOURCES_PATH.getPath() + "task15/OUTPUT.TXT";

    public static void main(String[] args) {
        List<int[]> allInputNumbers = InputUtil.getAllInputNumbers(INPUT_PATH);
        List<long[]> outputNumbers = new LinkedList<>();
        for (int[] allInputNumber : allInputNumbers) {
            outputNumbers.add(new long[]{findNok(allInputNumber[0], allInputNumber[1])});
        }
        OutputUtil.writeAllOutputNumbers(OUTPUT_PATH, outputNumbers);
    }

    private static long findNok(int first, int second) {
        long variants = first * second;

        return variants / findNod(first, second);
    }

    private static long findNod(int first, int second) {
        int cup;
        if (first > second) {
            cup = first;
            first = second;
            second = cup;
        }

        int left;
        while ((left = first % second) != 0) {
            first = second;
            second = left;
        }
        return second;
    }
}
