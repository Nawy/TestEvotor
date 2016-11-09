package com.ermolaev.testevotor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ivan Ermolaev
 * @date 16:16 09-11-2016.
 */
public class DatabaseService {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost/testdb";

    private static final String USER = "testadmin";
    private static final String PASSWORD = "12345678";

    private static DatabaseService instance;

    public static DatabaseService getInstance() {
        return instance;
    }

    public static void init() {
        instance = new DatabaseService();
    }

    public static void destroy() {
        instance.close();
    }


    private Connection connection;

    private DatabaseService() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            if(connection != null) {
                connection.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
