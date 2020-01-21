package org.student.questionnaire.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.student.questionnaire.Database;
import org.student.questionnaire.QuestionChanger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class SavedQuestionnairesPageController extends ControllerUtil {

    private Database database = Database.getInstance();

    private HashMap<Integer, String> questionnaires;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button backButton;

    @FXML
    private ListView<String> questionnairesList;

    @FXML
    private Button showButton;

    @FXML
    private Button deleteButton;

    @FXML
    private void initialize() {
        updateList();
        backButton.setOnAction(event -> {
            goToPage(mainPane, "mainPage");
        });
        showButton.setOnAction(event -> {
            String selected = questionnairesList.getSelectionModel().getSelectedItem();
            QuestionChanger questionChanger = QuestionChanger.getInstance();
            questionChanger.setQuestionnaireId(getKey(selected));
            goToPage(mainPane, "savedAnswersPage");
        });
        deleteButton.setOnAction(event -> {
            String selected = questionnairesList.getSelectionModel().getSelectedItem();
            database.remove(getKey(selected));
            updateList();
        });
    }

    private void updateList() {
        questionnaires = database.showQuestionnaires();
        ObservableList<String> list = FXCollections.observableList(new ArrayList<>(questionnaires.values()));
        questionnairesList.setItems(list);
    }

    private int getKey(String value) {
        Collection<Integer> keySet = questionnaires.keySet();
        for (Integer key : keySet) {
            String mapValue = questionnaires.get(key);
            if (key != null) {
                if (value.equals(mapValue)) {
                    return key;
                }
            }
        }
        return -1;
    }

}
