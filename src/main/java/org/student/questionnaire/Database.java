package org.student.questionnaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    private static Database instance;

    private static final String URL = "jdbc:postgresql://localhost:5432/questionnaire";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123 ";

    public Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }
}
