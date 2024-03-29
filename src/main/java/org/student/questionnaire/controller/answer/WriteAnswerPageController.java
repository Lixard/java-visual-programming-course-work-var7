package org.student.questionnaire.controller.answer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WriteAnswerPageController extends AbstractAnswerPageController {

    @FXML
    private TextField answer;

    @FXML
    private Label errorMessage;

    @FXML
    private void initialize() {
        loadProperties();
        setCounter();
        setQuestion();
        bindings();
    }

    private void bindings() {
        escapeButton.setOnAction(event -> {
            questioning.exit();
            goToPage(mainPane, "mainPage");
        });
        nextButton.setOnAction(event -> {
            if (answer.getText().equals("")) {
                errorMessage.setVisible(true);
            } else {
                questioning.addAnswer(answer.getText());
                nextButtonBinding();
            }
        });
    }

}
