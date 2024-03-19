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

        MainToolBar mainToolBar = new MainToolBar();
        MainToolBarController mainToolBarController = new MainToolBarController(mainToolBar, store);
        root.setTop(mainToolBar);

        Scene scene = new Scene(root);

        loadStylesheetIntoScene(scene);

        stage.setTitle("ICS 372 - HelloFX");
        stage.setScene(scene);
        stage.show();

        //Log in to Spotify
        Auth auth = new Auth();
        auth.login();

        //Create a playlist to test the API
        System.out.println("Created playlist with id: " + createPlaylist("test playlist", auth));
    }

    /**
     * Creates a playlist with the given name.
     * @param name
     * @param auth
     * @return
     */
    private  String createPlaylist(String name, Auth auth) {
        String url = "https://api.spotify.com/v1/users/" + auth.getUserId() + "/playlists";
        
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + auth.getAccessToken());
        headers.put("Content-Type", "application/json");
        
        String data = "{\"name\":\"" + name + "\",\"public\":false}";
        
        try {
            JsonObject response = Request.request("POST", url, null, headers, data);
            return response.get("id").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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