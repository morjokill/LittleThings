package little.things.timestamp.local.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class DateParser {
    private static LocalDate parseDateLine(String timestamp) throws Exception {
        LocalDate currentDate = LocalDate.now();

        List<DateTimeFormatter> formatterList = new LinkedList<>();
        List<String> formatPatterns = Arrays.asList("dd-MM-yyyy", "dd-MM-yy",
                "dd.MM.yyyy", "dd.MM.yy", "dd.MM", "dd-MM", "dd");

        for (String formatPattern : formatPatterns) {
            formatterList.add(buildFormatFromPattern(formatPattern, currentDate));
            formatterList.add(buildFormatFromPattern(formatPattern));
        }

        LocalDate dateFromLine;
        for (DateTimeFormatter formatter : formatterList) {
            try {
                dateFromLine = LocalDate.parse(timestamp, formatter);
                return dateFromLine;
            } catch (Exception e) {
                //try next
            }
        }

        Day day = Day.getDay(timestamp);
        if (Objects.nonNull(day)) {
            return day.getDate();
        }

        throw new Exception("Could not parse date");
    }

    private static DateTimeFormatter buildFormatFromPattern(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    private static DateTimeFormatter buildFormatFromPattern(String pattern, LocalDate currentDate) {
        return new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, currentDate.getMonthValue())
                .parseDefaulting(ChronoField.YEAR, currentDate.getYear())
                .parseDefaulting(ChronoField.DAY_OF_MONTH, currentDate.getDayOfMonth())
                .toFormatter();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(parseDateLine("сегодня"));
        System.out.println(parseDateLine("Завтра"));
        System.out.println(parseDateLine("ПослеЗавтра"));
        System.out.println(parseDateLine("27.05.2019"));
        System.out.println(parseDateLine("27-05-2019"));
        System.out.println(parseDateLine("27.05.19"));
        System.out.println(parseDateLine("27-05-19"));
        System.out.println(parseDateLine("27.05"));
        System.out.println(parseDateLine("27-05"));
        System.out.println(parseDateLine("27"));
    }
}
