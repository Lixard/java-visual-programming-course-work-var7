package org.student.questionnaire.data;

import javafx.beans.property.SimpleStringProperty;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static org.student.questionnaire.controller.ControllerUtil.PROPERTIES_PATH;

public class Question {
    private int id;
    private SimpleStringProperty question;
    private SimpleStringProperty answer;

    public Question(int id, int questionNumber, String answer) {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream(PROPERTIES_PATH), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = id;
        this.question = new SimpleStringProperty(properties.getProperty("question" + questionNumber));
        this.answer = new SimpleStringProperty(answer);
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question.get();
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public String getAnswer() {
        return answer.get();
    }

    public SimpleStringProperty answerProperty() {
        return answer;
    }
}
