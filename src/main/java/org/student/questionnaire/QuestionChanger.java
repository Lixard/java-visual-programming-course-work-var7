package org.student.questionnaire;

import org.student.questionnaire.data.Question;

import java.util.ArrayList;

public class QuestionChanger {
    private static QuestionChanger instance;

    private int questionnaireId;
    private ArrayList<Question> questions;

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
        return questions;
    }

    public void createQuestions() {
        Database database = Database.getInstance();
        this.questions = database.showQuestions(questionnaireId);
    }

    public void close() {
        instance = null;
    }
}
