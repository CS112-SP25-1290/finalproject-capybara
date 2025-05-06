package edu.miracosta.cs112.finalproject.finalproject.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Alien extends GameEntity { //Concrete Class
    private final ImageView imageView;

    public Alien(double x, double y) {
        super(x, y); //ensure super constructor is called
        this.imageView = new ImageView(new Image(getClass().getResource("/images/alien.png").toExternalForm()));
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }

    @Override
    public void update() {
        // TODO: implement alien movement logic
    }

    public ImageView getImageView() {
        return imageView;
    }
}
