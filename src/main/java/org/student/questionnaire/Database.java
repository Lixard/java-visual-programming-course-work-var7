package org.student.questionnaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/questionnaire";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123 ";
    private static Database instance;
    public Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void pushAnswers(String[] answers) {
    }
}
