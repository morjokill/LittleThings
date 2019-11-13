package little.things.acmp.task10;

import java.util.LinkedList;
import java.util.List;

import static little.things.acmp.utils.InputUtil.getAllInputNumbers;
import static little.things.acmp.utils.OutputUtil.writeAllOutputNumbersGeneric;
import static little.things.acmp.utils.Paths.RESOURCES_PATH;

public class Equation {
    public static final String INPUT_PATH = RESOURCES_PATH.getPath() + "task10/INPUT.TXT";
    public static final String OUTPUT_PATH = RESOURCES_PATH.getPath() + "task10/OUTPUT.TXT";
    public static final int LEFT_BORDER = -100;
    public static final int RIGHT_BORDER = 100;

    public static void main(String[] args) {
        List<int[]> allInputNumbers = getAllInputNumbers(INPUT_PATH);
        List<Object[]> allOutputNumbers = new LinkedList<>();
        for (int[] allInputNumber : allInputNumbers) {
            List<Integer> roots = new LinkedList<>();
            for (int i = LEFT_BORDER; i <= RIGHT_BORDER; i++) {
                if (calculateEquation(i, allInputNumber) == 0) {
                    roots.add(i);
                }
            }
            allOutputNumbers.add(roots.toArray());
        }
        writeAllOutputNumbersGeneric(OUTPUT_PATH, allOutputNumbers);
    }

    private static double calculateEquation(int number, int[] coefficients) {
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] * Math.pow(number, coefficients.length - i - 1);
        }
        return sum;
    }
}
