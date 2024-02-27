module App {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;
    requires java.desktop;
    requires jdk.httpserver;

    opens edu.metrostate to javafx.fxml;
    exports edu.metrostate;
}