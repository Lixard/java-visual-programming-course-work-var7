package org.student.questionnaire;

import org.student.questionnaire.data.Question;

import java.util.ArrayList;

public class QuestionChanger {
    private static QuestionChanger instance;
    private Database database = Database.getInstance();

    private int questionnaireId;

    private QuestionChanger() {
    }

    public static QuestionChanger getInstance() {
        if (instance == null) {
            instance = new QuestionChanger();
        }
        return instance;
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public ArrayList<Question> getQuestions() {
        return database.showQuestions(questionnaireId);

    }

    public void changeAnswer(int id, String answer) {
        database.changeAnswer(id, answer);
    }

    public void close() {
        instance = null;
    }
}
