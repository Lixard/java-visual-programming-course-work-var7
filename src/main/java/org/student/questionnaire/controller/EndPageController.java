package org.student.questionnaire.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class EndPageController extends ControllerUtil {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button mainPageButton;

    @FXML
    private Button savedQuestionnairesButton;

    @FXML
    private void initialize() {
        mainPageButton.setOnAction(event -> {
            goToPage(mainPane, "mainPage");
        });
        savedQuestionnairesButton.setOnAction(event -> {
            goToPage(mainPane, "savedQuestionnairesPage");
        });
    }
}
