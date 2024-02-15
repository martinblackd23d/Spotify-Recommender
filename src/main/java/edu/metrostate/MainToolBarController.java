package edu.metrostate;

public class MainToolBarController {

    private final MainToolBar toolBar;
    private final ValueStore store;

    MainToolBarController(MainToolBar toolBar, ValueStore store) {
        this.toolBar = toolBar;
        this.store = store;
        this.toolBar.addButton.setOnAction(action -> {
            this.store.increment();
        });
        this.toolBar.subtractButton.setOnAction(action -> {
            this.store.decrement();
        });
    }
}
