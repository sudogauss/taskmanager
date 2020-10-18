package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPage {

    @FXML
    private BorderPane controlPane;

    @FXML
    private Button personalTaskSwitch;

    @FXML
    public void showPersonalTaskForm(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("personal_task_form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        controlPane.setCenter(root);
    }
}
