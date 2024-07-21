package com.example.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndController {
    @FXML private Label endLabel;

    private GameController gameController;

    public void setLabel(String value) { endLabel.setText(value); }
    public void setGameController(GameController gameController) { this.gameController = gameController; }

    @FXML private void restart(ActionEvent e) {
        gameController.setGameBox();
    }
}
