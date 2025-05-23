package edu.miracosta.cs112.finalproject.finalproject.Controllers;

import edu.miracosta.cs112.finalproject.finalproject.Models.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    @FXML private Pane gameView;
    @FXML private Label scoreLabel;
    @FXML private Label livesLabel;
    @FXML private Label highScoreLabel;

    private PlayerShip player;
    private final List<GameEntity> gameEntities = new ArrayList<>();
    private final List<GameEntity> bullets = new ArrayList<>();
    private final Set<KeyCode> keysPressed = new HashSet<>();
    private HighScoreManager highScoreManager = new HighScoreManager();

    private Timeline gameLoop;
    private int score = 0;
    private int lives = 3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        highScoreLabel.setText("High Score: " + highScoreManager.getHighScore());
        createMarsHorizon();
        createStarrySky(100);
        initPlayer();

        Platform.runLater(() -> {
            startGameLoop();
            gameView.requestFocus();
        });
    }

    private void initPlayer() {
        player = new PlayerShip(300, 500);
        gameView.getChildren().add(player.getImageView());
        gameEntities.add(player);
    }

    public void setHighScoreManager(HighScoreManager manager) {
        this.highScoreManager = manager;
    }

    public void handleKeyPress(KeyEvent event) {
        keysPressed.add(event.getCode());
        if (event.getCode() == KeyCode.SPACE) {
            fireBullet();
        }
    }

    public void handleKeyRelease(KeyEvent event) {
        keysPressed.remove(event.getCode());
    }

    private void fireBullet() {
        GameEntity bullet = player.fire();
        if (bullet != null) {
            bullets.add(bullet);
            gameEntities.add(bullet);
            gameView.getChildren().add(bullet.getImageView());
        }
    }

    private void startGameLoop() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> updateGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    private void updateGame() {
        handleMovement();
        maybeSpawnAlien();
        updateEntities();
        checkBulletCollisions();
    }

    private void handleMovement() {
        if (keysPressed.contains(KeyCode.LEFT) || keysPressed.contains(KeyCode.A)) {
            player.moveLeft();
        }
        if (keysPressed.contains(KeyCode.RIGHT) || keysPressed.contains(KeyCode.D)) {
            player.moveRight(gameView.getWidth());
        }
    }

    private void maybeSpawnAlien() {
        if (Math.random() < 0.01) {
            double spawnX = Math.random() * (gameView.getWidth() - 40);
            Alien alien = new Alien(spawnX, 0);
            gameEntities.add(alien);
            gameView.getChildren().add(alien.getImageView());
        }
    }

    private void updateEntities() {
        List<GameEntity> toRemove = new ArrayList<>();

        for (GameEntity entity : gameEntities) {
            entity.update();

            if (entity.getImageView().getLayoutY() > gameView.getHeight()) {
                toRemove.add(entity);
            } else if (entity instanceof Alien && player.collidesWith(entity)) {
                lives--;
                livesLabel.setText("Lives: " + lives);
                toRemove.add(entity);

                if (lives == 0) {
                    endGame();
                    return;
                }
            }
        }

        for (GameEntity entity : toRemove) {
            gameView.getChildren().remove(entity.getImageView());
            gameEntities.remove(entity);
            bullets.remove(entity);
        }
    }

    private void checkBulletCollisions() {
        List<GameEntity> bulletsToRemove = new ArrayList<>();
        List<GameEntity> targetsToRemove = new ArrayList<>();

        for (GameEntity bullet : bullets) {
            for (GameEntity target : gameEntities) {
                if (target instanceof Alien && bullet.collidesWith(target)) {
                    bulletsToRemove.add(bullet);
                    targetsToRemove.add(target);
                    score += 100;
                    scoreLabel.setText("Score: " + score);
                    break;
                }
            }
        }

        for (GameEntity bullet : bulletsToRemove) {
            gameView.getChildren().remove(bullet.getImageView());
            gameEntities.remove(bullet);
            bullets.remove(bullet);
        }

        for (GameEntity alien : targetsToRemove) {
            gameView.getChildren().remove(alien.getImageView());
            gameEntities.remove(alien);
        }
    }

    private void endGame() {
        gameLoop.stop();

        if (score > highScoreManager.getHighScore()) {
            highScoreManager.saveScore("Capybara", score);
        }

        try {
            FXMLLoader loader = new FXMLLoader(GameApplication.class.getResource(
                    "/edu/miracosta/cs112/finalproject/finalproject/GameOver.fxml"));
            Parent root = loader.load();

            GameOverController controller = loader.getController();
            controller.setScores(score, highScoreManager.getHighScore());
            controller.setHighScoreManager(highScoreManager);


            Stage stage = (Stage) gameView.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds glowing stars below the Mars horizon.
     */
    private void createStarrySky(int numberOfStars) {
        double marsHeight = gameView.getPrefHeight() * 0.25;

        for (int i = 0; i < numberOfStars; i++) {
            double x = Math.random() * gameView.getPrefWidth();
            double y = marsHeight + Math.random() * (gameView.getPrefHeight() - marsHeight);

            Circle star = new Circle(x, y, Math.random() * 2 + 1);
            star.setFill(Color.WHITE);
            star.setOpacity(Math.random() * 0.6 + 0.4);
            gameView.getChildren().add(star);
            addTwinkleEffect(star);
        }
    }

    /**
     * Creates a red Mars horizon at the top of the game view.
     */
    private void createMarsHorizon() {
        Rectangle mars = new Rectangle();
        mars.setY(0);
        mars.setHeight(gameView.getPrefHeight() * 0.25); // Fixed height for horizon

        // Bind width to gameView width so it stretches dynamically
        mars.widthProperty().bind(gameView.widthProperty());

        Stop[] stops = new Stop[]{
                new Stop(0, Color.DARKRED),
                new Stop(1, Color.SANDYBROWN)
        };

        mars.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops));

        gameView.getChildren().add(mars);
    }

    /**
     * Animates a star to twinkle.
     */
    private void addTwinkleEffect(Circle star) {
        FadeTransition ft = new FadeTransition(Duration.seconds(2 + Math.random() * 2), star);
        ft.setFromValue(0.3);
        ft.setToValue(1.0);
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }
}
