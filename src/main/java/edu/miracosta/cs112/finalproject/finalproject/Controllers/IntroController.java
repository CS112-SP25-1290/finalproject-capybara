package edu.miracosta.cs112.finalproject.finalproject.Controllers;

import edu.miracosta.cs112.finalproject.finalproject.Models.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;

public class IntroController {

    @FXML
    Button skipButton;

    @FXML
    VBox scrollContainer;

    @FXML
    public void initialize() {
        // start the scrolling animation when the scene is initialized
        startScrollAnimation();
    }

    private void startScrollAnimation() {
        // start the VBox from the bottom of the screen
        scrollContainer.setTranslateY(500); // adjust the Y position to start off the screen

        // create the TranslateTransition for the scrolling effect
        TranslateTransition scroll = new TranslateTransition(Duration.seconds(50), scrollContainer);
        scroll.setFromY(250); // starting Y position (off-screen)
        scroll.setToY(-1000); // ending Y position (off-screen at the top)
        scroll.setCycleCount(1); // only scroll once
        scroll.setAutoReverse(false); // No reverse animation
        scroll.play(); // play the scroll animation

    }

    // immediately switch scenes without waiting for the animation
    @FXML
    private void handleSkipButton(ActionEvent event) {
        switchToGameScene();
    }

    public void switchToGameScene() {
        try{
            // load the intro scene (Intro.fxml)
            Parent gameRoot = FXMLLoader.load(GameApplication.class.getResource("/edu/miracosta/cs112/finalproject/finalproject/GameScene.fxml"));
            Stage stage = (Stage) skipButton.getScene().getWindow();
            stage.setScene(new Scene(gameRoot));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}