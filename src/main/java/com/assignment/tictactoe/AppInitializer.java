package com.assignment.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();

        Image image = new Image(getClass().getResourceAsStream("/Images/tic-tac-toe (1).png"));
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.show();
    }
}
