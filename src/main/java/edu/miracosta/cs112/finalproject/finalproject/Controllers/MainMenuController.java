package edu.miracosta.cs112.finalproject.finalproject.Controllers;

import edu.miracosta.cs112.finalproject.finalproject.Models.GameApplication;
import edu.miracosta.cs112.finalproject.finalproject.Models.HighScoreManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class MainMenuController {
    @FXML
    public Button playButton;

    @FXML
    public Button instructionsButton;

    @FXML
    public Button soundButton;

    @FXML
    public Button exitButton;

    @FXML
    Label highScoreLabel;

    private HighScoreManager highScoreManager;

    @FXML
    public void initialize() {
        highScoreManager = new HighScoreManager();
        updateHighScoreLabel();
        // handle the "Play" button click event
        playButton.setOnAction(e -> switchToIntroScene());
        soundButton.setOnAction(e -> toggleSound());
        exitButton.setOnAction(e -> System.exit(0));
    }

    public void switchToIntroScene() {
        try{
            // load the intro scene (Intro.fxml)
            Parent introRoot = FXMLLoader.load(GameApplication.class.getResource("/edu/miracosta/cs112/finalproject/finalproject/Intro.fxml"));
            Stage stage = (Stage) playButton.getScene().getWindow();
            stage.setScene(new Scene(introRoot));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateHighScoreLabel() {
        int highScore = highScoreManager.getHighScore();
        highScoreLabel.setText("High Score: " + highScore);
    }

    @FXML
    private void handlePlayButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/miracosta/cs112/finalproject/finalproject/Intro.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) playButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   @FXML
    private void handleInstructionButton() {
        //TODO: add navigation to Instruction screen
   }

   @FXML
   private void handleExitButton() {
        System.exit(0);
   }

    private void toggleSound() {
        System.out.println("sound toggled.");
    }
}

