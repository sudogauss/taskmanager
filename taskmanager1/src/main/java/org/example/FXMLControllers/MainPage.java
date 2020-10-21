package org.example.FXMLControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.example.Loader;

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
            root = FXMLLoader.load(Loader.class.getResource("personal_task_form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        controlPane.setCenter(root);
    }
}
