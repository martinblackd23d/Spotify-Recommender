package edu.metrostate;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class MainToolBar extends ToolBar {

    Button addButton;

    Button subtractButton;

    public MainToolBar() {
        addButton = new Button("Add");
        subtractButton = new Button("Subtract");
        getItems()
                .addAll(addButton, subtractButton);
    }
}
