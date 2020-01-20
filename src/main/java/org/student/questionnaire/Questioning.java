package org.student.questionnaire;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Questioning {
    private static Questioning instance;

    private final int answersLimit;
    private int answersCount;
    private String name;
    private String[] userAnswers;

    private Questioning() {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream(System.getProperty("user.dir")
                    + "\\src\\main\\resources\\questions.properties"), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        answersLimit = Integer.parseInt(properties.getProperty("answersLimit"));
        userAnswers = new String[answersLimit];
        answersCount = 0;
    }

    public static Questioning getInstance() {
        if (instance == null) {
            instance = new Questioning();
        }
        return instance;
    }

    public void addAnswer(String answer) {
        if (answersCount > answersLimit)
            throw new ArrayIndexOutOfBoundsException();
        userAnswers[answersCount] = answer;
        answersCount++;
    }

    public int getAnswersCount() {
        return answersCount;
    }

    public int getAnswersLimit() {
        return answersLimit;
    }

    public int getIncAnswersCount() {
        return answersCount + 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void save() {
        Database database = Database.getInstance();
        database.save(name, userAnswers);
    }

    public void exit() {
        instance = null;
    }

}
