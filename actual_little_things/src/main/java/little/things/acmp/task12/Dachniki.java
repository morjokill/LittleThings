package little.things.acmp.task12;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static little.things.acmp.utils.InputUtil.getAllInputNumbers;
import static little.things.acmp.utils.OutputUtil.writeAllOutputNumbers;
import static little.things.acmp.utils.Paths.RESOURCES_PATH;

public class Dachniki {
    public static final String INPUT_PATH = RESOURCES_PATH.getPath() + "task12/INPUT.TXT";
    public static final String OUTPUT_PATH = RESOURCES_PATH.getPath() + "task12/OUTPUT.TXT";

    public static void main(String[] args) {
        List<int[]> allInputNumbers = getAllInputNumbers(INPUT_PATH);
        List<long[]> allOutputNumbers = new LinkedList<>();
        int fitsCount = 0;
        for (int i = 1; i < allInputNumbers.size(); i++) {
            int[] inputNumbers = allInputNumbers.get(i);
            if (checkIfPointFitsInArea(inputNumbers[0], inputNumbers[1],
                    Arrays.copyOfRange(inputNumbers, 2, inputNumbers.length))) {
                fitsCount++;
            }
        }
        allOutputNumbers.add(new long[]{fitsCount});
        writeAllOutputNumbers(OUTPUT_PATH, allOutputNumbers);
    }

    private static boolean checkIfPointFitsInArea(int x, int y, int[] areaPoints) {
        for (int i = 0; i <= areaPoints.length - 2; i += 2) {
            int x1, y1, x2, y2;
            x1 = areaPoints[i];
            y1 = areaPoints[i + 1];
            if (i != areaPoints.length - 2) {
                x2 = areaPoints[i + 2];
                y2 = areaPoints[i + 3];
            } else {
                x2 = areaPoints[0];
                y2 = areaPoints[1];
            }
            if (!checkIfPointFitsInLine(x, y, x1, y1, x2, y2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkIfPointFitsInLine(long x, long y, long x1, long y1, long x2, long y2) {
        return x * (y1 - y2) + y * (x2 - x1) + (x1 * y2 - x2 * y1) <= 0;
    }
}
