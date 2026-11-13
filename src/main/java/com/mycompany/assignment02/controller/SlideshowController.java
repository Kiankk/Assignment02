package com.mycompany.assignment02.controller;

import com.mycompany.assignment02.App;
import com.mycompany.assignment02.model.Car;
import com.mycompany.assignment02.model.CarRaceModel;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Shows the cars one at a time with music, then starts the race.
 *
 * @author Kian
 */
public class SlideshowController {

    private static final double SLIDE_SECONDS = 2.5;

    @FXML
    private ImageView carImageView;
    @FXML
    private Label carNameLabel;
    @FXML
    private Label carDetailLabel;

    private CarRaceModel model;
    private MediaPlayer player;
    private Runnable onFinished;

    public void setModel(CarRaceModel model) {
        this.model = model;
    }

    public void setOnFinished(Runnable onFinished) {
        this.onFinished = onFinished;
    }

    /**
     * Starts the music and plays the slideshow of all cars.
     */
    public void play() {
        startMusic();
        SequentialTransition slideshow = new SequentialTransition();
        for (Car car : model.getCars()) {
            slideshow.getChildren().add(createSlide(car));
        }
        slideshow.setOnFinished(event -> finish());
        slideshow.play();
    }

    private SequentialTransition createSlide(Car car) {
        PauseTransition show = new PauseTransition(Duration.seconds(0.01));
        show.setOnFinished(event -> showCar(car));
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), carImageView);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        PauseTransition hold = new PauseTransition(Duration.seconds(SLIDE_SECONDS));
        return new SequentialTransition(show, fadeIn, hold);
    }

    private void showCar(Car car) {
        String path = "/com/mycompany/assignment02/images/car" + car.getNumber() + ".png";
        carImageView.setImage(new Image(App.class.getResource(path).toExternalForm()));
        carNameLabel.setText("Car " + car.getNumber() + " - " + car.getType());
        carDetailLabel.setText("Color: " + car.getColorName());
    }

    private void startMusic() {
        String path = "/com/mycompany/assignment02/sounds/slideshow.wav";
        player = new MediaPlayer(new Media(App.class.getResource(path).toExternalForm()));
        player.setVolume(0.4);
        player.play();
    }

    private void finish() {
        player.stop();
        if (onFinished != null) {
            onFinished.run();
        }
    }
}
