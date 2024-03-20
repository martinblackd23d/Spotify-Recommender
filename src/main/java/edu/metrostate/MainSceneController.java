package edu.metrostate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;

public class MainSceneController implements ValueChangedListener {

    @FXML
    private Label label;

    @FXML
    private Label value;

    @FXML
    private MainToolBar mainToolBar;

    @FXML
    private ListView<Song> recListView;

    @FXML
    private Button loginButton;

    @FXML
    private void handleLogin() {
        System.out.println("Login button clicked");

        //Log in to Spotify
        Auth auth = new Auth();
        auth.login();

        //Create a playlist from a recommendation to test the API
        Recommendation recommendation = new Recommendation();
        recommendation.addSong(new Song("2QTDuJIGKUjR7E2Q6KupIh", auth));
        Playlist playlist = recommendation.getRecommendation(auth);

        //Update the list view with the new playlist
        this.updateRecListView(playlist);

        //Export the playlist to the user's Spotify account
        playlist.exportPlaylist("test playlist", auth);
        System.out.println("Playlist created");
    };

    private ValueStore store;

    private final String valueFormatString = "Current value: %d";

    public void initialize() {
        //label.setText("Hello, ICS372 JavaFX");
    }

    public void setValueStore(ValueStore store) {
        this.store = store;
        if (this.store != null) {
            this.store.registerValueChangeListener(this);
        }
    }

    @Override
    public void onValueChange(int newValue) {
        //value.setText(String.format(valueFormatString, newValue));
    }

    public void updateRecListView(Playlist playlist) {
        recListView.getItems().clear();
        recListView.getItems().addAll(playlist.getList());
    }
}
