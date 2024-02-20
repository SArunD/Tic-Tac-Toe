package com.example.tictactoe.controllers;

import com.example.tictactoe.Helper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML private BorderPane borderPane;
    @FXML private ImageView moveDisplay;
    @FXML private Label displayScoreX, displayScoreO;

    private int[] gridArray;
    private int moveCount;
    private static int scoreX, scoreO;
    private String currPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Helper.getGamePane() == null) { Helper.setGamePane(borderPane); }
        this.gridArray = new int[9];
        updateScores();
    }

    @FXML private void handleMove(MouseEvent e) throws IOException {
        ImageView gridCell = (ImageView) e.getTarget();
        if (gridCell.getImage() == null) {
            gridCell.setImage(moveDisplay.getImage());
            updateMove();
            int cellIndex = ((GridPane) borderPane.getCenter()).getChildren().indexOf(gridCell);
            gridArray[cellIndex] = (currPlayer.equals("X")) ? 1 : 2;
            moveCount++;

            int val = checker();
            if (val != -1) {
                String phrase = "Draw";
                if (val == 1 && currPlayer.equals("X")) {
                    phrase = "Player #1";
                    scoreX += 1;
                } else if (val == 1 && currPlayer.equals("O")) {
                    phrase = "Phrase #2";
                    scoreO += 1;
                }
                updateScores();
                setEndScene(phrase);
            }
        }
    }

    private void updateScores() {
        displayScoreX.setText(Integer.toString(scoreX));
        displayScoreO.setText(Integer.toString(scoreO));
    }

    private void setEndScene(String phrase) throws IOException {
        FXMLLoader loader = Helper.changeGameScreen("EndScreen.fxml");
        EndController controller = loader.getController();
        controller.setLabel(phrase);
    }

    private void updateMove() {
        String relativeURL = "/images/";
        if (currPlayer == null || currPlayer.equals("O")) {
            currPlayer = "X";
            relativeURL += "O.png";
        } else {
            currPlayer = "O";
            relativeURL += "X.png";
        }
        moveDisplay.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(relativeURL))));
    }

    private int checker() {
        for (int i = 0, r = 0; i < 3; i++, r += 2) {
            if (gridArray[i+r] == gridArray[i+r+1] &&
                gridArray[i+r+1] == gridArray[i+r+2] &&
                gridArray[i+r] != 0) {
                return 1;
            }
            if (gridArray[i] == gridArray[i+3] &&
                gridArray[i+3] == gridArray[i+6] &&
                gridArray[i] != 0) {
                return 1;
            }
        }
        if ((gridArray[0] == gridArray[4] &&
            gridArray[4] == gridArray[8] &&
            gridArray[0] != 0) ||
            ((gridArray[2] == gridArray[4] &&
            gridArray[4] == gridArray[6] &&
            gridArray[6] != 0))) {
            return 1;
        }
        if (moveCount >= 9) {
            return 0;
        }
        return -1;
    }
}