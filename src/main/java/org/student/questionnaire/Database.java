package org.student.questionnaire;

import org.student.questionnaire.data.Question;

import java.sql.*;
import java.util.ArrayList;
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


    public HashMap<Integer, String> showQuestionnaires() {
        HashMap<Integer, String> map = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, name from questionnaire");
            while (resultSet.next()) {
                map.put(resultSet.getInt(1), resultSet.getString(2));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public ArrayList<Question> showQuestions(int questionnaireId) {
        ArrayList<Question> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select id, question_number, answer from answers where questionnaire_id = ? order by id asc");
            statement.setInt(1, questionnaireId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int questionNumber = resultSet.getInt(2);
                String answer = resultSet.getString(3);
                Question question = new Question(id, questionNumber, answer);
                result.add(question);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void changeAnswer(int id, String answer) {
        try {
            PreparedStatement statement = connection.prepareStatement("update answers set answer = ? where id = ?");
            statement.setString(1, answer);
            statement.setInt(2, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
