package edu.miracosta.cs112.finalproject.finalproject;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class GameController {
    private final PlayerShip player;
    private final GameView gameView;

    public GameController (GameView gameView, PlayerShip player){
        this.gameView = gameView;
        this.player = player;
        gameView.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT -> player.moveLeft();
            case RIGHT -> player.moveRight(500);
            case SPACE -> player.shoot();
        }
        gameView.render();
    }
}