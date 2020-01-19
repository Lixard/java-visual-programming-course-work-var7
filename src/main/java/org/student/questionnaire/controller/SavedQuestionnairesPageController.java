package org.student.questionnaire.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SavedQuestionnairesPageController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button backButton;

    @FXML
    private ListView<?> questionnairesList;

    @FXML
    private Button openButton;

    @FXML
    private Button changeButton;

    @FXML
    private Button deleteButton;

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
    }

}
