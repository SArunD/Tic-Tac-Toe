package com.example.tictactoe.controllers;

import com.example.tictactoe.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndController {
    @FXML private Label endLabel;

    public void setLabel(String value) {
        endLabel.setText(value);
    }

    @FXML private void restart(ActionEvent e) {

    }
}
