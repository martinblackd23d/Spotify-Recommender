package edu.metrostate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MainSceneController implements ValueChangedListener {

    @FXML
    private Label label;

    @FXML
    private Label value;

    @FXML
    private MainToolBar mainToolBar;

    @FXML
    private ListView<Song> recListView;

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
