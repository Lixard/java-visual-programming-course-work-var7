package org.student.questionnaire.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button doQuestionnaireButton;

    @FXML
    private Button savedQuestionnairesButton;

    @FXML
    private void initialize() {
        doQuestionnaireButton.setOnAction(actionEvent -> {
            doQuestionnaireButton.setText("Something Done!");
        });
        savedQuestionnairesButton.setOnAction(actionEvent -> {
            savedQuestionnairesButton.setText("Something Done!");
        });
    }
}
