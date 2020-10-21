module org.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.app to javafx.fxml;
    exports org.example.app;

    opens org.example.FXMLControllers to javafx.fxml;
    exports org.example.FXMLControllers;
}