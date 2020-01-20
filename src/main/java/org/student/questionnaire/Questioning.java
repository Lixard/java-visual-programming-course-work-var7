package org.student.questionnaire;


public class Questioning {
    private static Questioning instance;

    private final int answersLimit = 10;
    private int answersCount = 0;

    private String name = null;

    private String[] userAnswers = new String[answersLimit];

    private Questioning() {
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

    public int getNextAnswerCount() {
        return answersCount + 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void pushToDatabase() {
        Database database = Database.getInstance();
        database.pushAnswers(userAnswers);
    }

    public void exit() {
        instance = null;
    }

}
