package edu.miracosta.cs112.finalproject.finalproject;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class PlayerShip extends GameEntity { //standalone class
    private final int speed;
    private final List<Bullet> bullets;

    public PlayerShip(double x, double y) {
        super(x, y);
        this.speed = 5; // Default movement speed
        this.bullets = new ArrayList<>();
    }

    public void moveLeft() {
        if (x - speed >= 0) {
            x -= speed;
        }
    }

    public void moveRight(double boundary) {
        if (x + speed <= boundary)
            x += speed;
    }

    public void shoot() {
        bullets.add(new Bullet(x + 15, y));
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public void update() {
        bullets.removeIf(Bullet::isOffScreen);
        for (Bullet bullet : bullets) {
            try {
                bullet.moveUp(); // Move the bullet up
            } catch (OutOfBoundsException e) {
                System.out.println("Out of bounds");
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, 30, 30); // Render the player ship

        // Render all bullets
        for (Bullet bullet : bullets) {
            bullet.render(gc);
        }
    }

    public static class Bullet { // Inner class
        private double x, y;
        private final double speed = 10;

        public Bullet(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void moveUp() throws OutOfBoundsException {
            y -= speed;
            if (y < 0) throw new OutOfBoundsException("Bullet went off screen");
        }

        public boolean isOffScreen() {
            return y < 0;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public void render(GraphicsContext gc) {
            gc.setFill(Color.RED); // Color for the bullet
            gc.fillRect(x, y, 5, 10); // Render the bullet
        }
    }
}