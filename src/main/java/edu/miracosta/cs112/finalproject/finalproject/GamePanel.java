package edu.miracosta.cs112.finalproject.finalproject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GamePanel extends Canvas{
    private final PlayerShip player;
    private final Alien alien;

    public GamePanel(){
        super(500, 500); //directly pass width and height in Canvas constructor
        this.player = new PlayerShip(250, 450);
        this.alien = new Alien(100, 50);
    }

    public void render() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0,0,getWidth(),getHeight()); //clear previous frame

        //call render method for both player and alien
        player.render(gc); // ensure PlayerShip has render method
        alien.render(gc); // ensure Alien has render method
    }
}
