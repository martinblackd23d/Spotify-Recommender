package edu.metrostate;

import java.util.HashMap;
import java.util.Optional;

import com.google.gson.JsonObject;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

public class MainSceneController {


    /*
     * FXML elements
     */
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

    /*
     * FXML event handlers
     * Each handler is responsible for calling the appropriate method to handle the user input
     * Each method is called in a Task, so UI updates can be performed in the JavaFX thread
     */
    // Settings button clicked
    @FXML
    private void handleSettings() {
        System.out.println("Settings button clicked");
        if (loading) {
            return;
        }
        startLoading();

        settings();
        stopLoading();
    }

    // Login button clicked
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

    // Export button clicked
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

    // Search button clicked
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

    // RecListView playlist item clicked
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

    /*
     * Methods to handle user input
     */
    // Login to Spotify
    private void login() {
        auth = new Auth();
        auth.login();
        // Show error message if login failed
        if (auth.getAccessToken() == null) {
            Platform.runLater(() -> {
                auth = null;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Login failed");
                alert.setContentText("Please try again");
                alert.showAndWait();
            });
        }
    }

    // Export playlist to Spotify
    private void export() {
        System.out.println("Exporting playlist");
        // Show error message if no playlist to export
        if (playlist == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No playlist to export");
                alert.setContentText("Please create a playlist before exporting");
                alert.showAndWait();
            });
        } else {
            // Show dialog to get playlist name
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
                // Export playlist
                playlist.exportPlaylist(name, auth);
                System.out.println("Playlist created");
            });
        }
    }

    // Search for songs on Spotify
    private void search() {
        System.out.println("Searching for songs");
        // Show error message if not logged in
        if (auth == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not logged in");
                alert.setContentText("Please log in to Spotify before searching");
                alert.showAndWait();
            });
        } else if (searchBox.getText().equals("")) {
            // Show error message if no search query
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No search query");
                alert.setContentText("Please enter a search query before searching");
                alert.showAndWait();
            });
        } else {
            // Create search query
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("q", searchBox.getText());
            params.put("type", "track");
            params.put("limit", settingsClass.getLimit().toString());
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", "Bearer " + auth.getAccessToken());
            try {
                playlist = new ArrayPlaylist();
                JsonObject response = Request.request("GET", "https://api.spotify.com/v1/search", params, headers, null);
                // Add search results to playlist and display it
                for (int i = 0; i < response.get("tracks").getAsJsonObject().get("items").getAsJsonArray().size(); i++) {
                    JsonObject track = response.get("tracks").getAsJsonObject().get("items").getAsJsonArray().get(i).getAsJsonObject();
                    Song song = new Song(track.get("id").getAsString(), auth);
                    playlist.addSong(song);
                }
                seed = playlist.getList().get(0);
                updateRecListView(playlist);
            } catch (Exception e) {
                // Show error message if search failed
                Platform.runLater(() -> {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Search failed");
                    alert.setContentText("Please try again");
                    alert.showAndWait();
                });
            }
        }
    }

    // Recommend songs based on selected song
    private void select() {
        if (recListView.getSelectionModel().getSelectedItem() != null) {
            seed = recListView.getSelectionModel().getSelectedItem();
            Recommendation rec = new Recommendation();
            rec.setLimit(settingsClass.getLimit());
            rec.addSong(seed);
            playlist = rec.getRecommendation(auth);
            playlist.addSong(seed, 0);
            updateRecListView(playlist);
        }
    }

    // Open settings dialog
    private void settings() {
        settingsClass.showDialog();
    }

    private Auth auth;
    private Song seed;
    private Playlist playlist;
    private boolean loading = false;
    private Settings settingsClass = new Settings();

    // Disable buttons and change background color while loading
    private void startLoading() {
        loading = true;
        System.out.println("Loading...");
        Platform.runLater(() -> {
            borderPane.setStyle("-fx-background-color: DARKGRAY;");
        });
    }

    // Enable buttons and change background color when done loading
    private void stopLoading() {
        loading = false;
        System.out.println("Done loading");
        Platform.runLater(() -> {
            borderPane.setStyle("-fx-background-color: GRAY;");
        });
    }

    public void initialize() {
        stopLoading();
    }

    public void updateRecListView(Playlist playlist) {
        Platform.runLater(() -> {
            recListView.getItems().clear();
            recListView.getItems().addAll(playlist.getList());
        });
    }
}
