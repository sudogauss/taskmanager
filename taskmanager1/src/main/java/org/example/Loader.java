/* Loader is a class to upload resources
   It's mostly fxml files
*/

package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Loader {

    // static method used to load fxml
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getResource(fxml + ".fxml")); // we can access resources from org.example
        return fxmlLoader.load(); // returns Parent element
    }
}
