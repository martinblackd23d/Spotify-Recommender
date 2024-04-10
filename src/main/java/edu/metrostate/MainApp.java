package edu.metrostate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;


public class MainApp extends Application {

    public MainApp() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));
        BorderPane root = loader.load();

        MainSceneController mainSceneController = loader.getController();

        //MainToolBar mainToolBar = new MainToolBar();
        //MainToolBarController mainToolBarController = new MainToolBarController(mainToolBar, store);
        //root.setTop(mainToolBar);

        Scene scene = new Scene(root);

        loadStylesheetIntoScene(scene);

        stage.setTitle("ICS 372 - Spotify Recommendations");
        stage.setScene(scene);
        stage.show();
    }

    private void loadStylesheetIntoScene(Scene scene) {
        URL stylesheetURL = getClass().getResource("style.css");
        if (stylesheetURL == null) {
            return;
        }
        String urlString = stylesheetURL.toExternalForm();
        if (urlString == null) {
            return;
        }
        scene.getStylesheets().add(urlString);
    }
}