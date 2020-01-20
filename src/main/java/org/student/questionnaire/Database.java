package org.student.questionnaire;

import java.sql.*;
import java.util.HashMap;

public class Database {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/questionnaire";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";
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

    public void save(String name, String[] answers) {
        int questionnaireId = saveName(name);
        try {
            PreparedStatement statement = connection.prepareStatement("insert into answers(questionnaire_id, question_number, answer) values (?, ?, ?);");
            for (int i = 0; i < answers.length; i++) {
                statement.setInt(1, questionnaireId);
                statement.setInt(2, i);
                statement.setString(3, answers[i]);
                statement.execute();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public HashMap<Integer, String> show() {
        HashMap<Integer, String> map = new HashMap<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select id, name from questionnaire");
            while (resultSet.next()) {
                map.put(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void remove(int id) {
        try {
            PreparedStatement statementForAnswers = connection.prepareStatement("delete from answers where questionnaire_id = ?");
            PreparedStatement statementForQuestionnaire = connection.prepareStatement("delete from questionnaire where id = ?");

            statementForAnswers.setInt(1, id);
            statementForQuestionnaire.setInt(1, id);

            statementForAnswers.execute();
            statementForQuestionnaire.execute();

            statementForAnswers.close();
            statementForQuestionnaire.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int saveName(String name) {
        int questionnaireId = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("insert into questionnaire(name) values (?) returning id;");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                questionnaireId = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionnaireId;
    }
}
