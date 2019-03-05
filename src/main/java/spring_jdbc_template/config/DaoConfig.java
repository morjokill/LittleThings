package spring_jdbc_template.config;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

public class DaoConfig {
    public static DataSource getDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        return dataSource;
    }
}
