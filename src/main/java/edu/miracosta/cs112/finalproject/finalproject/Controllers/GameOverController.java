package edu.miracosta.cs112.finalproject.finalproject.Controllers;

import edu.miracosta.cs112.finalproject.finalproject.Models.HighScoreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {

    @FXML
    public Button mainMenuButton;

    @FXML
    public Button playAgainButton;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label highScoreLabel;

    @FXML
    private TextField textField;

    @FXML
    private Button saveScoreButton;

    private HighScoreManager highScoreManager;
    private int score;
    private int highScore;

    //called before initialize() if loaded via FXMLLoader manually
    public void setHighScoreManager(HighScoreManager manager) {
        this.highScoreManager = manager;
    }

    public void setScores(int score, int highScore) {
        this.score = score;
        this.highScore = highScore;
        updateLabels();

        saveScoreButton.setOnAction(e -> {
            String playerName = textField.getText().trim();
            if (!playerName.isEmpty()) {
                highScoreManager.saveScore(playerName, score);
                saveScoreButton.setDisable(true); // Optional: prevents multiple clicks
            }
        });


    }

    //for game over scene
    @FXML
    private void handleMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/miracosta/cs112/finalproject/finalproject/MainMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) scoreLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for game over scene
    @FXML
    private void handlePlayAgain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/miracosta/cs112/finalproject/finalproject/GameScene.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) scoreLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //if setScores hasn't been called yet, wait for it.
    }

    private void updateLabels() {
        if (scoreLabel != null && highScoreLabel != null) {
            scoreLabel.setText("Score: " + score);
            highScoreLabel.setText("High Score: " + highScore);
        }
    }
}