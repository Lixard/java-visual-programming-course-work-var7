package org.student.questionnaire.controller.answer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.Arrays;

public class SelectAnswerPageController extends AbstractAnswerPageController {

    @FXML
    private ComboBox<String> answers;

    @FXML
    private void initialize() {
        loadProperties();
        setCounter();
        setQuestion();
        setAnswers();
        bindings();
    }

    private void bindings() {
        escapeButton.setOnAction(event -> {
            questioning.exit();
            goToPage(mainPane, "mainPage");
        });
        nextButton.setOnAction(event -> {
            questioning.addAnswer(answers.getValue());
            nextButtonBinding();
        });
    }


    private void setAnswers() {
        String[] answersArray = properties.getProperty("question" + questioning.getAnswersCount() + "answer").split(",");
        ObservableList<String> observableList = FXCollections.observableList(Arrays.asList(answersArray));
        answers.setItems(observableList);
        answers.setValue(observableList.get(0));
    }

}
