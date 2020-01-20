package org.student.questionnaire.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ControllerUtil {
    public final static String PROPERTIES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\questions.properties";

    protected void goToPage(Pane mainPane, String pageName) {
        goTo(mainPane, "/FXMLs/" + pageName + ".fxml");
    }

    protected void goToQuestionType(Pane mainPane, int questionType) {
        switch (questionType) {
            case 1:
                goTo(mainPane, "/FXMLs/questionTypes/selectAnswerTypePage.fxml");
                break;
            case 2:
                goTo(mainPane, "/FXMLs/questionTypes/writeAnswerTypePage.fxml");
                break;
        }
    }

    private void goTo(Pane mainPane, String URL) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(URL));
            mainPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
