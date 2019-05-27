package little.things.database;

import little.things.utils.property.PropertyReader;

public class DatabaseConstants {
    public static final String DB_DRIVER;
    public static final String CONNECTION_STRING;
    public static final String CONNECTION_USER;
    public static final String CONNECTION_PASSWORD;
    public static final boolean DB_AUTOCOMMIT;
    public static final int INITIAL_SIZE;
    public static final int MAX_TOTAL;
    public static final int MAX_IDLE;
    public static final int MIN_IDLE;
    public static final boolean TEST_ON_RETURN;
    public static final boolean TEST_WHILE_IDLE;
    public static final boolean UNDERLYING;
    public static final boolean TEST_ON_BORROW;
    public static final int REMOVE_TIMEOUT;
    public static final boolean REMOVE_ABANDONED;
    public static final int TIME_BETWEEN_EVICTION_RUNS_MILLIS;

    static {
        DB_DRIVER = PropertyReader.getValue("db_driver");
        CONNECTION_STRING = PropertyReader.getValue("db_connection");
        CONNECTION_USER = PropertyReader.getValue("db_user");
        CONNECTION_PASSWORD = PropertyReader.getValue("db_password");

        DB_AUTOCOMMIT = PropertyReader.getBoolean("db_autocommit");

        INITIAL_SIZE = PropertyReader.getInt("bds_initial_size");
        MAX_TOTAL = PropertyReader.getInt("bds_maxTotal");
        MAX_IDLE = PropertyReader.getInt("bds_maxIdle");
        MIN_IDLE = PropertyReader.getInt("bds_minIdle");
        REMOVE_ABANDONED = PropertyReader.getBoolean("bds_removeAbandoned");
        REMOVE_TIMEOUT = PropertyReader.getInt("bds_removeAbandonedTimeout");
        TEST_ON_RETURN = PropertyReader.getBoolean("bds_testOnReturn");
        TEST_WHILE_IDLE = PropertyReader.getBoolean("bds_testWhileIdle");
        UNDERLYING = PropertyReader.getBoolean("bds_accessToUnderlyingConnectionAllowed");
        TEST_ON_BORROW = PropertyReader.getBoolean("bds_testOnBorrow");
        TIME_BETWEEN_EVICTION_RUNS_MILLIS = PropertyReader.getInt("bds_time_between_eviction_runs_millis");
    }
}
