package org.student.questionnaire;

public class Questioning {
    private final int answersLimit = 15;
    private int answersCount = 0;

    private final String name;
    private String[] answers = new String[answersLimit];

    public Questioning(String name) {
        this.name = name;
    }

    public void addAnswer(String answer) {
        if (answersCount == answersLimit - 1)
            throw new ArrayIndexOutOfBoundsException();
        answers[answersCount] = answer;
        answersCount++;
    }

}
