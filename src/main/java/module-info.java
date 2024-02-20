module com.example.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.tictactoe to javafx.fxml;
    exports com.example.tictactoe;
    exports com.example.tictactoe.controllers;
    opens com.example.tictactoe.controllers to javafx.fxml;
}