package org.student.questionnaire.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SelectQuestionnaireNamePageController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button backButton;

    @FXML
    private TextField questionnaireName;

    @FXML
    private Button startButton;

    @FXML
    private void initialize() {
        backButton.setOnAction(actionEvent -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLs/mainPage.fxml"));
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        startButton.setOnAction(actionEvent -> {
            // здесь должен начинаться тест
        });
    }

}
