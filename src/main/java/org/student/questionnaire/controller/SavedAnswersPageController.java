package org.student.questionnaire.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.student.questionnaire.QuestionChanger;
import org.student.questionnaire.data.Question;

public class SavedAnswersPageController extends ControllerUtil {
    private QuestionChanger questionChanger = QuestionChanger.getInstance();

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
        questionsColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        answersColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        ObservableList<Question> list = FXCollections.observableList(questionChanger.getQuestions());
        tableView.setItems(list);
        tableView.getColumns().add(questionsColumn);
        tableView.getColumns().add(answersColumn);
        backButton.setOnAction(event -> {
            questionChanger.close();
            goToPage(mainPane, "savedQuestionnairesPage");
        });
    }

}
