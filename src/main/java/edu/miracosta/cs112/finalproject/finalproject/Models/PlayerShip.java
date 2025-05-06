package edu.miracosta.cs112.finalproject.finalproject.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class PlayerShip extends GameEntity { //standalone class
    private final int speed = 5;
    private final List<Bullet> bullets;
    private final ImageView imageView;

    public PlayerShip(double x, double y) {
        super(x, y);
        this.bullets = new ArrayList<>();
        this.imageView = new ImageView(new Image(getClass().getResource("/images/player.png").toExternalForm()));
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }

    public void moveLeft() {
        if (x - speed >= 0) {
            x -= speed;
            imageView.setLayoutX(x);
        }
    }

    public void moveRight(double boundary) {
        if (x + speed <= boundary - imageView.getImage().getWidth()) {
            x += speed;
            imageView.setLayoutX(x);
        }
    }

    public void shoot() {
        bullets.add(new Bullet(x + imageView.getImage().getWidth() / 2, y));
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

    public List<Bullet> getBullets() {
        return bullets;
    }

    public ImageView getImageView() {
        return imageView;
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
    }
}