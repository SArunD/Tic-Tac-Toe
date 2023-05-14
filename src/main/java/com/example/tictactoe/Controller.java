package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    @FXML private GridPane gridPane;
    @FXML private Label myLabel;

    private final int[] gridArray = new int[9];

    private final Image moveX = new Image(Objects.requireNonNull(getClass().getResourceAsStream("X.png")));
    private final Image moveO = new Image(Objects.requireNonNull(getClass().getResourceAsStream("O.png")));
    private Image currMove = moveX;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int moveCount = 0;

    public void handleMove(MouseEvent e) throws IOException {
        ImageView curr = ((ImageView) e.getTarget());
        if (curr.getImage() == null) {
            curr.setImage(currMove);
            curr.setTranslateX(3);
            curr.setTranslateY(3);
            currMove = (currMove == moveX ? moveO : moveX);
            gridArray[gridPane.getChildren().indexOf(curr)] = (currMove == moveX) ? 1 : 2;
            moveCount++;
        }
        if (winCheck() || moveCount >= 9) {
            switchToScene2(e);
        }
    }

    public void restart(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene.fxml")));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToScene2(MouseEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene2.fxml")));
        myLabel = (Label) root.getChildrenUnmodifiable().get(0);
        if (moveCount >= 9) {
            myLabel.setText("Draw!");
        } else {
            myLabel.setText(((currMove == moveX) ? "Player 2" : "Player 1") + " Wins!");
        }
        myLabel.setStyle("-fx-border-color: black");
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean winCheck() {
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