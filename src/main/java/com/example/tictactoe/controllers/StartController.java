package com.example.tictactoe.controllers;

import com.example.tictactoe.Helper;
import com.example.tictactoe.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
    @FXML private void selectType(ActionEvent e) {
        String id = ((Button) e.getSource()).getId();
        Helper.type = (id.equals(Type.PLAYER.name())) ? Type.PLAYER : Type.AI;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "GameScreen.fxml", "Tic-Tac-Toe");
    }
}
