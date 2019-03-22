package dao.factory;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

public class HandleFactory {
    private static Jdbi jdbi;

    public static Handle getHandle() {
        if (null == jdbi) {
            initJdbi();
        }
        return jdbi.open();
    }

    private static void initJdbi() {
        jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
    }
}

