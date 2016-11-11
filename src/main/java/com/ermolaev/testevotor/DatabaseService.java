package com.ermolaev.testevotor;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ivan Ermolaev
 * @date 16:16 09-11-2016.
 */
public class DatabaseService {

    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/evotor_tst";

    private static final String USER = "evotor";
    private static final String PASSWORD = "123456";

    private static DatabaseService instance;

    public static DatabaseService getInstance() {
        if(instance != null) {
            return instance;
        }
        instance = new DatabaseService();
        return instance;
    }

    private final HikariDataSource dataSource;

    private DatabaseService() {
        dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(20);
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setJdbcUrl(JDBC_URL);
        dataSource.addDataSourceProperty("user", USER);
        dataSource.addDataSourceProperty("password", PASSWORD);
        dataSource.setAutoCommit(false);
    }

    public Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
}
