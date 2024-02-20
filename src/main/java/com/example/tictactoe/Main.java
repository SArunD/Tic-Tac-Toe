package com.example.tictactoe;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Helper.type = Type.UNSELECTED;
        Helper.changeScreen(stage, "StartScreen.fxml", "Tic-Tac-Toe");
    }

    public static void main(String[] args) {
        launch();
    }
}
