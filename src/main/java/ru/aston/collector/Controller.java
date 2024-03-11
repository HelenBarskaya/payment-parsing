package ru.aston.collector;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private TextArea textArea;

    @FXML
    protected void onHelloButtonClick() {
        textArea.setText("Welcome to JavaFX Application!");
    }
}