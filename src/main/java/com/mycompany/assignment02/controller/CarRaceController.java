package com.mycompany.assignment02.controller;

import com.mycompany.assignment02.model.Car;
import com.mycompany.assignment02.model.CarRaceModel;
import com.mycompany.assignment02.view.CarView;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Controls the race simulation and the user interactions.
 *
 * @author Kian
 */
public class CarRaceController {

    @FXML
    private Pane trackPane;
    @FXML
    private Button startButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resetButton;
    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedLabel;
    @FXML
    private TextArea messageArea;

    private CarRaceModel model;
    private final List<CarView> carViews = new ArrayList<>();
    private final List<TranslateTransition> moves = new ArrayList<>();

    /**
     * Gives the controller its model and draws the cars on the track.
     *
     * @param model the race data
     */
    public void setModel(CarRaceModel model) {
        this.model = model;
        for (Car car : model.getCars()) {
            CarView carView = new CarView(car);
            carView.setLayoutX(car.getX());
            carView.setLayoutY(car.getY());
            carViews.add(carView);
            trackPane.getChildren().add(carView);
        }
        messageArea.setText("Welcome! Press Start to begin the race.");
    }

    @FXML
    private void handleStart() {
        model.randomizeSpeeds();
        moves.clear();
        for (CarView carView : carViews) {
            moves.add(createMove(carView));
        }
        startButton.setDisable(true);
        messageArea.setText("The race has started. Good luck to all drivers!");
        for (TranslateTransition move : moves) {
            move.play();
        }
    }

    private TranslateTransition createMove(CarView carView) {
        Car car = carView.getCar();
        double seconds = model.getTrackDistance() / car.getSpeed();
        TranslateTransition move = new TranslateTransition(Duration.seconds(seconds), carView);
        move.setToX(model.getTrackDistance());
        move.setInterpolator(Interpolator.LINEAR);
        return move;
    }

    @FXML
    private void handlePause() {
    }

    @FXML
    private void handleReset() {
    }
}
