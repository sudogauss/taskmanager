/* It's a main class for this application */

package org.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Loader;

import java.io.IOException;

public class App extends Application {

    //creating an application from main_page fxml file

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(Loader.loadFXML("main_page"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}