package edu.miracosta.cs112.finalproject.finalproject.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Random;

public class Alien extends GameEntity { //Concrete Class
    private ImageView imageView;
    private final double verticalSpeed = 0.5;
    private final double horizontalSpeed = 4;
    private int direction = 0; // -1 = left, 0 = still, 1 = right
    private final Random rand = new Random();
    private int framesUntilDirectionChange = 0;

    public Alien(double x, double y) {
        super(x, y); //ensure super constructor is called

        URL imageURL = getClass().getResource("/images/Martian.png");
        if (imageURL == null) {
            throw new RuntimeException("Image not found: /images/Martian.png");
        }

        imageView = new ImageView(imageURL.toExternalForm());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }

    @Override
    public void update() {
        //move down slowly
        y += verticalSpeed;

        //change horizontal direction every 30–60 frames
        if (framesUntilDirectionChange <= 0) {
            direction = rand.nextInt(3) - 1; // -1, 0, or 1
            framesUntilDirectionChange = 30 + rand.nextInt(30);
        } else {
            framesUntilDirectionChange--;
        }

        //move left or right quickly
        x += direction * horizontalSpeed;

        //update position
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }
}
