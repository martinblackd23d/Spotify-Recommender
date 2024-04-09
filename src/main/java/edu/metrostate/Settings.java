package edu.metrostate;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Settings {
    private Integer limit = 20;

    public void showDialog() {
        System.out.println("Showing settings dialog");
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Settings");

        Label label = new Label("Song limit:");
        TextField textField = new TextField(limit.toString());

        Button btnOk = new Button("OK");
        btnOk.setOnAction(e -> {
            try {
                Integer.parseInt(textField.getText());
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Please enter a number between 1 and 100.");
                alert.showAndWait();
                return;
            }
            if (textField.getText().isEmpty() || Integer.parseInt(textField.getText()) < 1 || Integer.parseInt(textField.getText()) > 100){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Please enter a number between 1 and 100.");
                alert.showAndWait();
            } else {
                limit = Integer.parseInt(textField.getText());
                dialogStage.close();
            }
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(e -> dialogStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, textField, btnOk, btnCancel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 200, 150);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    public Integer getLimit() {
        return limit;
    }
}