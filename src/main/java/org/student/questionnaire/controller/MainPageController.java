package org.student.questionnaire.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainPageController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button doQuestionnaireButton;

    @FXML
    private Button savedQuestionnairesButton;

    @FXML
    private void initialize() {
        doQuestionnaireButton.setOnAction(actionEvent -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLs/selectQuestionnaireNamePage.fxml"));
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        savedQuestionnairesButton.setOnAction(actionEvent -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLs/savedQuestionnairesPage.fxml"));
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
