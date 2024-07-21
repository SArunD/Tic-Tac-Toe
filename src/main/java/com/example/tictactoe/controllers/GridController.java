package com.example.tictactoe.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class GridController {
    @FXML private GridPane gridPane;

    private ImageView currMove;
    private String currPlayer;
    private GameController gameController;
    private int moveCount;
    private final int[] gridArray;

    public GridController() {
        this.moveCount = 0;
        this.gridArray = new int[9];
    }

    public void setCurrMove(ImageView currMove) { this.currMove = currMove; }
    public void setCurrPlayer(String currPlayer) { this.currPlayer = currPlayer; }
    public void setGameController(GameController gameController) { this.gameController = gameController; }

    @FXML private void handleMove(MouseEvent e) throws IOException {
        ImageView gridCell = (ImageView) e.getTarget();
        if (gridCell.getImage() == null) {
            gridCell.setImage(currMove.getImage());
            gameController.updateMove();

            int cellIndex = gridPane.getChildren().indexOf(gridCell);
            gridArray[cellIndex] = (currPlayer.equals("X")) ? 1 : 2;
            moveCount++;

            int val = checker();
            if (val != -1) {
                String phrase = "Draw";
                String player = "Player #";
                if (val == 1 && currPlayer.equals("X")) {
                    phrase = "X";
                    player += "1 Has Won!";
                } else if (val == 1 && currPlayer.equals("O")) {
                    phrase = "O";
                    player += "2 Has Won!";
                }
                gameController.updateScore(phrase, player);
            }
        }
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
