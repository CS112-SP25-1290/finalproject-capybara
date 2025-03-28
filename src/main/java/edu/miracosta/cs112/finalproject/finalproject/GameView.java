package edu.miracosta.cs112.finalproject.finalproject;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameView extends Canvas {
    private PlayerShip player;

    public GameView() {
        super(500, 500);
        this.player = new PlayerShip(250, 450);
    }

    public void render() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(Color.BLUE);
        gc.fillRect(player.getX(), player.getY(), 30, 30);

        gc.setFill(Color.RED);
        player.getBullets().forEach(b -> gc.fillOval(b.getX(), b.getY(), 5, 10));
    }

    public PlayerShip getPlayer() {
        return player;
    }
}

