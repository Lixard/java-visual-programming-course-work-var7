package org.student.questionnaire.controller.answer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.student.questionnaire.Questioning;
import org.student.questionnaire.controller.ControllerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

public class SelectAnswerPageController extends ControllerUtil {

    private Questioning questioning = Questioning.getInstance();
    private Properties properties = new Properties();

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button escapeButton;

    @FXML
    private Label questionLabel;

    @FXML
    private Label counter;

    @FXML
    private ComboBox<String> answers;

    @FXML
    private Button nextButton;

    @FXML
    private void initialize() {
        loadProperties();
        setCounter();
        setQuestion();
        setAnswers();
        bindings();
    }

    private void loadProperties() {
        try {
            properties.load(new InputStreamReader(new FileInputStream(PROPERTIES_PATH), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bindings() {
        escapeButton.setOnAction(event -> {
            questioning.exit();
            goToPage(mainPane, "mainPage");
        });
        nextButton.setOnAction(event -> {
            questioning.addAnswer(answers.getValue());
            boolean isQuestioningEnds = questioning.getAnswersCount() >= questioning.getAnswersLimit();
            if (isQuestioningEnds) {
                questioning.exit();
                goToPage(mainPane, "endPage");
            }
            else if (isWriteType()) {
                goToQuestionType(mainPane, 2);
            } else {
                goToQuestionType(mainPane, 1);
            }
        });
    }

    private void setCounter() {
        counter.setText("Вопрос " + questioning.getAnswersCount() + 1);
    }

    private void setQuestion() {
        questionLabel.setText(properties.getProperty("question" + questioning.getAnswersCount()));
    }

    private void setAnswers() {
        String[] answersArray = properties.getProperty("question" + questioning.getAnswersCount() + "answer").split(",");
        ObservableList<String> observableList = FXCollections.observableList(Arrays.asList(answersArray));
        answers.setItems(observableList);
        answers.setValue(observableList.get(0));
    }

    private boolean isWriteType() {
        return properties.getProperty("question" + questioning.getAnswersCount() + "answer").equals("");
    }

}
