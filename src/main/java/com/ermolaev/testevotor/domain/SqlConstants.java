package com.ermolaev.testevotor.domain;

/**
 * @author Ivan Ermolaev
 * @date 10:48 11-11-2016.
 */
public class SqlConstants {

    public static final int DUPLICATE_KEYS = 1062;
    public static final String SELECT_USER = "SELECT password, balance FROM users WHERE login=?";
    public static final String INSERT_USER = "INSERT INTO users(login, password, balance) VALUES(?, ?, 0.0)";
}
