package org.student.questionnaire.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.student.questionnaire.QuestionChanger;
import org.student.questionnaire.data.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

public class SavedAnswersPageController extends ControllerUtil {
    private QuestionChanger questionChanger = QuestionChanger.getInstance();
    private Properties properties = new Properties();


    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Question> tableView;

    @FXML
    private TableColumn<Question, String> questionsColumn = new TableColumn<>("Вопрос");

    @FXML
    private TableColumn<Question, String> answersColumn = new TableColumn<>("Ответ");

    @FXML
    private Button changeButton;

    @FXML
    private void initialize() {
        try {
            properties.load(new InputStreamReader(new FileInputStream(PROPERTIES_PATH), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionsColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        answersColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        ObservableList<Question> list = FXCollections.observableList(questionChanger.getQuestions());
        tableView.getColumns().add(questionsColumn);
        tableView.getColumns().add(answersColumn);
        tableView.setItems(list);

        backButton.setOnAction(event -> {
            questionChanger.close();
            goToPage(mainPane, "savedQuestionnairesPage");
        });

        changeButton.setOnAction(event -> {
            Question selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                boolean isWriteType = properties.getProperty("question" + selected.getQuestionNumber() + "answer").equals("");
                if (isWriteType) {
                    textAnswerDialog(selected);
                } else {
                    choiceAnswerDialog(selected);
                }
            }
        });
    }

    private void update() {
        ObservableList<Question> list = FXCollections.observableList(questionChanger.getQuestions());
        tableView.setItems(list);
    }

    private void choiceAnswerDialog(Question selected) {
        String[] answersArray = properties.getProperty("question" + selected.getQuestionNumber() + "answer").split(",");
        ObservableList<String> choices = FXCollections.observableList(Arrays.asList(answersArray));
        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Изменить");
        dialog.setHeaderText("Выберите новый ответ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(answer -> questionChanger.changeAnswer(selected.getId(), answer));
        update();
    }

    private void textAnswerDialog(Question selected) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Изменить");
        dialog.setHeaderText("Введите новый ответ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(answer -> questionChanger.changeAnswer(selected.getId(), answer));
        update();
    }

}
