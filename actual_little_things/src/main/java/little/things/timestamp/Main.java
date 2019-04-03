package little.things.timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date(1539667630000L)));
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(1539667630000L);
        System.out.println(sqlTimestamp);
    }
}
