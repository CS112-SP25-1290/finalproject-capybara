package edu.miracosta.cs112.finalproject.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start (Stage stage) {
        GameView gameView = new GameView();
        PlayerShip player = gameView.getPlayer();
        new GameController(gameView, player);

        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        stage.setTitle("Mars Attacks: SpaceX Saga");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}