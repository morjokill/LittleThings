package little.things.database.dbcp;

import little.things.utils.property.PropertyReader;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Enumeration;

import static little.things.database.DatabaseConstants.*;

public class ConnectionFactory {
    private static BasicDataSource basicDataSource;

    public static void init() {
        System.out.println("Initializing basicDataSource");
        basicDataSource = new BasicDataSource();
        System.out.println("Configuring database pool");
        try {
            Class.forName(DB_DRIVER);

            basicDataSource.setDriverClassName(DB_DRIVER);
            basicDataSource.setDefaultAutoCommit(DB_AUTOCOMMIT);
            basicDataSource.setUrl(CONNECTION_STRING);
            basicDataSource.setUsername(CONNECTION_USER);
            basicDataSource.setPassword(CONNECTION_PASSWORD);
            basicDataSource.setInitialSize(INITIAL_SIZE);
            basicDataSource.setMaxActive(MAX_TOTAL);
            basicDataSource.setMaxIdle(MAX_IDLE);
            basicDataSource.setMinIdle(MIN_IDLE);
            basicDataSource.setTestOnReturn(TEST_ON_RETURN);
            basicDataSource.setTestWhileIdle(TEST_WHILE_IDLE);
            basicDataSource.setAccessToUnderlyingConnectionAllowed(UNDERLYING);
            basicDataSource.setTestOnBorrow(TEST_ON_BORROW);
            basicDataSource.setRemoveAbandoned(REMOVE_ABANDONED);
            basicDataSource.setRemoveAbandonedTimeout(REMOVE_TIMEOUT);
            basicDataSource.setTimeBetweenEvictionRunsMillis(TIME_BETWEEN_EVICTION_RUNS_MILLIS);
            if (DB_DRIVER.toLowerCase().contains("oracle")) {
                basicDataSource.setValidationQuery("SELECT 1 FROM DUAL");
            } else {
                basicDataSource.setValidationQuery("SELECT 1");
            }
            System.out.println("Pool successfully created");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Database driver: " + DB_DRIVER + " was not found. " + cnfe + ". Exiting the program");
            System.exit(0);
        }
    }

    public static boolean isDatabaseHealthy() {
        System.out.println("Getting database connection: " + CONNECTION_STRING);
        Connection connection;
        int tries = 6;
        long sleepSeconds = 1;
        for (int i = 0; i < tries; i++) {
            try {
                connection = basicDataSource.getConnection();
                selectOne(connection);
                return true;
            } catch (Exception e) {
                System.out.println("Could not establish connection with database: " + e);
                System.out.println("Sleeping for " + sleepSeconds + " seconds...");
                try {
                    Thread.sleep(sleepSeconds * 1000);
                } catch (InterruptedException ie) {
                    System.out.println("Thread interrupted: " + ie);
                }
            }
        }
        return false;
    }

    private static void selectOne(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String testQuery;
        if (DB_DRIVER.toLowerCase().contains("oracle")) {
            testQuery = "SELECT 1 FROM DUAL";
        } else {
            testQuery = "SELECT 1";
        }
        System.out.println("Checking connection with test query: " + testQuery);
        ResultSet rs = statement.executeQuery(testQuery);
        if (!rs.next()) {
            throw new SQLException("Could not SELECT 1 from database: connection is broken");
        }
        System.out.println("Created connection is valid");
    }

    public static DataSource getDataSource() {
        return basicDataSource;
    }

    public static void close() {
        System.out.println("Closing ConnectionFactory");
        if (null != basicDataSource && !basicDataSource.isClosed()) {
            try {
                basicDataSource.close();
                System.out.println("Pool closed");
                deregisterDrivers();
                System.out.println("Drivers closed");
            } catch (SQLException e) {
                System.out.println("Could not close basicDataSource: " + e);
            }
        }
    }

    private static void deregisterDrivers() throws SQLException {
        System.out.println("Getting all drivers...");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println("Deregistering driver: " + driver.toString());
            DriverManager.deregisterDriver(driver);
        }
    }

    public static void main(String[] args) throws SQLException, ConfigurationException {
        PropertyReader.initProp("src\\main\\resources", "database.properties");
        ConnectionFactory.init();
        DataSource dataSource = ConnectionFactory.getDataSource();
        System.out.println(dataSource.getConnection());
        System.out.println(isDatabaseHealthy());
    }
}
