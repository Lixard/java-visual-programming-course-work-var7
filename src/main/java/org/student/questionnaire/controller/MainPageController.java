package org.student.questionnaire.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class MainPageController extends ControllerUtil {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button doQuestionnaireButton;

    @FXML
    private Button savedQuestionnairesButton;

    @FXML
    private void initialize() {
        doQuestionnaireButton.setOnAction(actionEvent -> {
            goToPage(mainPane, "selectQuestionnaireNamePage");
        });
        savedQuestionnairesButton.setOnAction(actionEvent -> {
            goToPage(mainPane, "savedQuestionnairesPage");
        });
    }
}
