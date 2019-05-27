package little.things.timestamp.simple_date_format;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    public static String getDateLine(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println(getDateLine(new Date(System.currentTimeMillis()), "yyyy.MM.dd HH:mm:ss"));
    }
}
