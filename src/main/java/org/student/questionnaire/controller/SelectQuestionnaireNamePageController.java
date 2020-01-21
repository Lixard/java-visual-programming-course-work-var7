package org.student.questionnaire.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.student.questionnaire.Database;
import org.student.questionnaire.Questioning;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;


public class SelectQuestionnaireNamePageController extends ControllerUtil {

    private Questioning questioning = Questioning.getInstance();

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button backButton;

    @FXML
    private TextField questionnaireName;

    @FXML
    private Label errorMessage;

    @FXML
    private Button startButton;

    @FXML
    private void initialize() {
        backButton.setOnAction(actionEvent -> {
            goToPage(mainPane, "mainPage");
        });
        startButton.setOnAction(actionEvent -> {
            String name = questionnaireName.getText();
            if (name.equals("") || isNameExists(name)) {
                errorMessage.setVisible(true);
            } else {
                questioning.setName(name);
                if (isWriteType()) {
                    goToQuestionType(mainPane, 2);
                } else {
                    goToQuestionType(mainPane, 1);
                }
            }
        });
    }

    private boolean isWriteType() {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream(PROPERTIES_PATH), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("question" + questioning.getAnswersCount() + "answer").equals("");
    }

    private boolean isNameExists(String name) {
        Database database = Database.getInstance();
        HashMap<Integer, String> map = database.showQuestionnaires();
        return map.containsValue(name);
    }
}
