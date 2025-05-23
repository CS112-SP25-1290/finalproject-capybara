package edu.miracosta.cs112.finalproject.finalproject.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class PlayerShip extends GameEntity { //standalone class
    private final int speed = 5;
    private final ImageView imageView;

    public PlayerShip(double x, double y) {
        super(x, y);

        URL imageURL = getClass().getResource("/images/Player.png");
        if (imageURL == null) {
            throw new RuntimeException("Image not found: /images/Player.png");
        }
        this.imageView = new ImageView(new Image(imageURL.toExternalForm()));

        //scales image to ensure it's not gigantic or off-screen
        imageView.setFitWidth(64);
        imageView.setFitHeight(64);
        imageView.setPreserveRatio(true);

        //position using setX/Y for a plain Pane
        imageView.setX(x);
        imageView.setY(y);
    }

    public void moveLeft() {
        if (x - speed >= 0) {
            x -= speed;
            imageView.setX(x);
        }
    }

    public void moveRight(double boundary) {
        if (x + speed <= boundary - imageView.getFitWidth()) {
            x += speed;
            imageView.setX(x);
        }
    }

    /**
     * Fires a bullet from the player's current position
     * @return a new BulletEntity to be added to the gameEntities list
     */

    public BulletEntity fire() {
        double bulletX = x + imageView.getFitWidth() / 2 - 2.5;
        double bulletY = y;
        return new BulletEntity(bulletX, bulletY);
    }

    @Override
    public void update() {
        // Placeholder for future power-ups or player animation logic
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }
}
