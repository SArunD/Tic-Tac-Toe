package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private BorderPane borderPane;
    @FXML private ImageView moveDisplay;
    @FXML private Label endLabel;
    @FXML private Label displayScoreX, displayScoreO;

    private int[] gridArray;
    private int moveCount;
    private static int scoreX, scoreO;
    private String currPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gridArray = new int[9];
        if (displayScoreX != null) { displayScoreX.setText(Integer.toString(scoreX)); }
        if (displayScoreO != null) { displayScoreO.setText(Integer.toString(scoreO)); }
    }

    @FXML private void handleMove(MouseEvent e) throws IOException {
        ImageView gridCell = (ImageView) e.getTarget();
        if (gridCell.getImage() == null) {
            gridCell.setImage(moveDisplay.getImage());
            updateMove();
            int cellIndex = ((GridPane) borderPane.getCenter()).getChildren().indexOf(gridCell);
            gridArray[cellIndex] = (currPlayer.equals("X")) ? 1 : 2;
            moveCount++;
        }
        if (checkForWin() || moveCount >= 9) {
            setEndScene();
        }
    }

    @FXML private void restart(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameScene.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private void setEndScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EndScene.fxml")));
        endLabel = (Label) root.getChildrenUnmodifiable().get(0);
        if (moveCount >= 9) {
            endLabel.setText("Draw!");
        } else {
            endLabel.setText(((currPlayer.equals("X")) ? "Player #1" : "Player #2") + " Wins!");
            if (currPlayer.equals("X")) {
                displayScoreX.setText(Integer.toString(++scoreX));
            } else {
                displayScoreO.setText(Integer.toString(++scoreO));
            }
        }
        borderPane.setCenter(root);
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

    private boolean checkForWin() {
        // Rows
        for (int i = 0; i < 7; i += 3) {
            if ((gridArray[i] == gridArray[i + 1]) && (gridArray[i + 1] == gridArray[i + 2])) {
                return gridArray[i] != 0;
            }
        }
        // Columns
        for (int i = 0; i < 3; i++) {
            if ((gridArray[i] == gridArray[i + 3]) && (gridArray[i + 3] == gridArray[i + 6])) {
                return gridArray[i] != 0;
            }
        }
        // Diagonals
        int j = 4;
        for (int i = 0; j > 1; i += 2, j -= 2) {
            if ((gridArray[i] == gridArray[i + j]) && (gridArray[i + j] == gridArray[i + j + j])) {
                return gridArray[i] != 0;
            }
        }
        return false;
    }
}