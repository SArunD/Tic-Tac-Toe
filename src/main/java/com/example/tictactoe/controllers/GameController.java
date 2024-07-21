package com.example.tictactoe.controllers;

import com.example.tictactoe.Helper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML private BorderPane borderPane;
    @FXML private ImageView moveDisplay;
    @FXML private Label displayScoreX, displayScoreO;

    private GridController gridController;
    private String currPlayer;
    private int scoreX, scoreO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Helper.getGamePane() == null) {
            Helper.setGamePane(borderPane);
            setGameBox();
        }
    }

    public void setGameBox() {
        FXMLLoader fxmlLoader = safelyChangeScreen("GridScreen.fxml");
        gridController = fxmlLoader.getController();
        gridController.setCurrMove(moveDisplay);
        gridController.setGameController(this);
        gridController.setCurrPlayer("X");
    }

    private void setEndBox(String phrase) {
        FXMLLoader fxmlLoader = safelyChangeScreen("EndScreen.fxml");
        EndController endController = fxmlLoader.getController();
        endController.setLabel(phrase);
        endController.setGameController(this);
    }

    public void updateMove() {
        String relativeURL = "/images/";
        if (currPlayer == null || currPlayer.equals("O")) {
            currPlayer = "X";
            relativeURL += "O.png";
        } else {
            currPlayer = "O";
            relativeURL += "X.png";
        }
        moveDisplay.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(relativeURL))));
        gridController.setCurrPlayer(currPlayer);
    }

    public void updateScore(String winner, String statement) {
        if (winner.equals("X")) {
            scoreX += 1;
        } else if (winner.equals("O")) {
            scoreO += 1;
        }

        displayScoreO.setText(Integer.toString(scoreO));
        displayScoreX.setText(Integer.toString(scoreX));

        setEndBox(statement);
    }

    private FXMLLoader safelyChangeScreen(String path) {
        return Helper.changeGameScreen(path);
    }
}