module com.example.proyectoads {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;

    opens controller to javafx.fxml;
    opens com.example.proyectoads to javafx.fxml;

    exports com.example.proyectoads;
    exports controller;
}