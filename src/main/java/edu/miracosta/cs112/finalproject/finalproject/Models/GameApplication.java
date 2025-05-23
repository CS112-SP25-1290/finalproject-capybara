package edu.miracosta.cs112.finalproject.finalproject.Models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApplication extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
            FXMLLoader loader = new FXMLLoader(GameApplication.class.getResource("/edu/miracosta/cs112/finalproject/finalproject/MainMenu.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setTitle("Space X Saga");
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}