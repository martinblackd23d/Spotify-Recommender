package edu.metrostate;

import java.util.HashMap;
import java.util.Optional;

import com.google.gson.JsonObject;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;

public class MainSceneController implements ValueChangedListener {

    @FXML
    private Label label;

    @FXML
    private Label value;

    @FXML
    private Label bottom;

    @FXML
    private MainToolBar mainToolBar;

    @FXML
    private ListView<Song> recListView;

    @FXML
    private Button loginButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchBox;

    @FXML
    private BorderPane borderPane;

    @FXML
    private void handleLogin() {
        System.out.println("Login button clicked");
        if (loading) {
            return;
        }
        startLoading();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                login();
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                stopLoading();
            }

            @Override
            protected void failed() {
                super.failed();
                stopLoading();
            }
        };
        new Thread(task).start();
    };

    @FXML
    private void handleExport() {
        System.out.println("Export button clicked");
        if (loading) {
            return;
        }
        startLoading();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                export();
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                stopLoading();
            }

            @Override
            protected void failed() {
                super.failed();
                stopLoading();
            }
        };
        new Thread(task).start();
    }

    @FXML
    private void handleSearch() {
        System.out.println("Search button clicked");
        if (loading) {
            return;
        }
        startLoading();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                search();
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                stopLoading();
            }

            @Override
            protected void failed() {
                super.failed();
                stopLoading();
            }
        };
        new Thread(task).start();
    }

    @FXML
    private void handleMouseClick() {
        System.out.println("Item selected");
        if (loading) {
            return;
        }
        startLoading();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                select();
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                stopLoading();
            }

            @Override
            protected void failed() {
                super.failed();
                stopLoading();
            }
        };
        new Thread(task).start();
    }

    private void login() {
        auth = new Auth();
        auth.login();
        if (auth.getAccessToken() == null) {
            auth = null;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login failed");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }
    }

    private void export() {
        System.out.println("Exporting playlist");
        if (playlist == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No playlist to export");
            alert.setContentText("Please create a playlist before exporting");
            alert.showAndWait();
        } else {
            Platform.runLater(() -> {
                System.out.println("Taking input");
                String name = "Recommendations - " + seed.getTitle();
                TextInputDialog textInputDialog = new TextInputDialog(name);
                textInputDialog.setTitle("Export Playlist");
                textInputDialog.setHeaderText("Enter the name of the playlist");
                Optional<String> result = textInputDialog.showAndWait();
                if (result.isPresent()) {
                    name = result.get();
                }
                playlist.exportPlaylist(name, auth);
                System.out.println("Playlist created");
            });
        }
    }

    private void search() {
        if (auth == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Not logged in");
            alert.setContentText("Please log in to Spotify before searching");
            alert.showAndWait();
        } else if (searchBox.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No search query");
            alert.setContentText("Please enter a search query before searching");
            alert.showAndWait();
        } else {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("q", searchBox.getText());
            params.put("type", "track");
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", "Bearer " + auth.getAccessToken());
            try {
                playlist = new ArrayPlaylist();
                JsonObject response = Request.request("GET", "https://api.spotify.com/v1/search", params, headers, null);
                for (int i = 0; i < response.get("tracks").getAsJsonObject().get("items").getAsJsonArray().size(); i++) {
                    JsonObject track = response.get("tracks").getAsJsonObject().get("items").getAsJsonArray().get(i).getAsJsonObject();
                    Song song = new Song(track.get("id").getAsString(), auth);
                    playlist.addSong(song);
                }
                seed = playlist.getList().get(0);
                updateRecListView(playlist);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Search failed");
                alert.setContentText("Please try again");
                alert.showAndWait();
            }
        }
    }

    private void select() {
        if (recListView.getSelectionModel().getSelectedItem() != null) {
            Song selected = recListView.getSelectionModel().getSelectedItem();
            Recommendation rec = new Recommendation();
            rec.addSong(selected);
            playlist = rec.getRecommendation(auth);
            updateRecListView(playlist);
        }
    }

    private ValueStore store;

    private Auth auth;
    private Song seed;
    private Playlist playlist;

    private boolean loading = false;

    private void startLoading() {
        loading = true;
        System.out.println("Loading...");
        Platform.runLater(() -> {
            borderPane.setStyle("-fx-background-color: DARKGRAY;");
        });
    }

    private void stopLoading() {
        loading = false;
        System.out.println("Done loading");
        Platform.runLater(() -> {
            borderPane.setStyle("-fx-background-color: GRAY;");
        });
    }

    public void initialize() {
        //label.setText("Hello, ICS372 JavaFX");
        stopLoading();
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
        Platform.runLater(() -> {
            recListView.getItems().clear();
            recListView.getItems().addAll(playlist.getList());
        });
    }
}
