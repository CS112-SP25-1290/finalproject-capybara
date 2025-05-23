package edu.miracosta.cs112.finalproject.finalproject.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;

public class BulletEntity extends GameEntity {
    private final double speed = 10;
    private final ImageView imageView;

    public BulletEntity(double x, double y) {
        super(x, y);

        URL imageURL = getClass().getResource("/images/Bullet.png");
        if (imageURL == null) {
            throw new RuntimeException("Image not found: /images/Bullet.png");
        }

        this.imageView = new ImageView(new Image(imageURL.toExternalForm()));

        imageView.setFitWidth(15);
        imageView.setFitHeight(30);
        imageView.setPreserveRatio(true);

        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }


    @Override
    public void update() {
        y -= speed;
        imageView.setLayoutY(y);
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

}

