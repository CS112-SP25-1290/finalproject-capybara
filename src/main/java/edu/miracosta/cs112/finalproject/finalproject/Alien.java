package edu.miracosta.cs112.finalproject.finalproject;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;

public class Alien extends GameEntity { //Concrete Class
    private final double speed = 2; //marked as final so it doesn't change

    public Alien(double x, double y) {
        super(x, y); //ensure super constructor is called
    }

    public void move() {
        y += speed; // Move downward
    }

    @Override
    public void update() {
        move(); //overrides abstract method
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(x,y, 30, 30);
    }
}
