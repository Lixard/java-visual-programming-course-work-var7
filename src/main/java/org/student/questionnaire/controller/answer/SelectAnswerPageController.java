package org.student.questionnaire.controller.answer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.student.questionnaire.Questioning;
import org.student.questionnaire.controller.ControllerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

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
