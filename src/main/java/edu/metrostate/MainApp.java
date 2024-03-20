package edu.metrostate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.JsonObject;

public class MainApp extends Application {

    private final ValueStore store;

    public MainApp() {
        this.store = new ValueStore();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));
        BorderPane root = loader.load();

        MainSceneController mainSceneController = loader.getController();
        mainSceneController.setValueStore(store);

        //MainToolBar mainToolBar = new MainToolBar();
        //MainToolBarController mainToolBarController = new MainToolBarController(mainToolBar, store);
        //root.setTop(mainToolBar);

        Scene scene = new Scene(root);

        loadStylesheetIntoScene(scene);

        stage.setTitle("ICS 372 - HelloFX");
        stage.setScene(scene);
        stage.show();

        //Log in to Spotify
        Auth auth = new Auth();
        auth.login();

        //Create a playlist from a recommendation to test the API
        Recommendation recommendation = new Recommendation();
        recommendation.addSong(new Song("2QTDuJIGKUjR7E2Q6KupIh", auth));
        Playlist playlist = recommendation.getRecommendation(auth);
        playlist.exportPlaylist("test playlist", auth);
        System.out.println("Playlist created");
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