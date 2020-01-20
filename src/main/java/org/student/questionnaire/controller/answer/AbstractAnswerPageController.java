package org.student.questionnaire.controller.answer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.student.questionnaire.Questioning;
import org.student.questionnaire.controller.ControllerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class AbstractAnswerPageController extends ControllerUtil {
    protected Questioning questioning = Questioning.getInstance();
    protected Properties properties = new Properties();

    @FXML
    protected AnchorPane mainPane;

    @FXML
    protected Button escapeButton;

    @FXML
    protected Label questionLabel;

    @FXML
    protected Label counter;

    @FXML
    protected Button nextButton;

    protected void loadProperties() {
        try {
            properties.load(new InputStreamReader(new FileInputStream(PROPERTIES_PATH), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setCounter() {
        counter.setText("Вопрос " + questioning.getIncAnswersCount());
    }

    protected void setQuestion() {
        questionLabel.setText(properties.getProperty("question" + questioning.getAnswersCount()));
    }

    protected void nextButtonBinding() {
        if (questioning.getAnswersCount() >= questioning.getAnswersLimit()) {
            questioning.save();
            questioning.exit();
            goToPage(mainPane, "endPage");
        } else if (isWriteType()) {
            goToQuestionType(mainPane, 2);
        } else {
            goToQuestionType(mainPane, 1);
        }
    }

    protected boolean isWriteType() {
        return properties.getProperty("question" + questioning.getAnswersCount() + "answer").equals("");
    }

}
