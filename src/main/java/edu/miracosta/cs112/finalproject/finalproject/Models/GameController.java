package edu.miracosta.cs112.finalproject.finalproject.Models;

import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameController {

    @FXML
    private GameView gameView;

    private PlayerShip player;
    private Alien alien;

    public void initialize() {
        player = new PlayerShip(250, 450);
        alien = new Alien(100, 50);

        gameView.getChildren().addAll(player.getImageView(), alien.getImageView());
    }

    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT -> player.moveLeft();
            case RIGHT -> player.moveRight(gameView.getWidth());
            case SPACE -> {
                player.shoot();
                player.getBullets().forEach(bullet -> {
                    Rectangle bulletShape = new Rectangle(bullet.getX(), bullet.getY(), 5, 10);
                    bulletShape.setFill(Color.MAGENTA);
                    gameView.getChildren().add(bulletShape);
                });
            }
        }
    }
}