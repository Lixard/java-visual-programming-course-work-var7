package org.student.questionnaire.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;


public class SavedQuestionnairesPageController extends ControllerUtil {

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
            goToPage(mainPane, "mainPage");
        });
    }

}
