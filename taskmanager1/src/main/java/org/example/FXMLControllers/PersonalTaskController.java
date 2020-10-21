package org.example.FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.app.TaskManager;

import java.time.format.DateTimeFormatter;

public class PersonalTaskController {

    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private Slider importanceSlider;

    @FXML
    private Button createButton;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField themeField;

    @FXML
    private TextField deadlineHour;

    @FXML
    private TextField deadlineMinutes;

    @FXML
    private TextField deadlineSeconds;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public void createNewPersonalTask() {
        String deadline = deadlinePicker.getValue().format(this.formatter) + " " + deadlineHour.getText() + ":" + deadlineMinutes.getText() + ":" + deadlineSeconds.getText();
        double importance = importanceSlider.getValue();
        String description = descriptionArea.getText();
        String username = usernameField.getText();
        String theme = themeField.getText();
        TaskManager.addPersonalTask(importance,theme,description,deadline,username);
    }
}
