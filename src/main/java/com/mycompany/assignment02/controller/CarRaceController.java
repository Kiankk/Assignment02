package com.mycompany.assignment02.controller;

import com.mycompany.assignment02.model.Car;
import com.mycompany.assignment02.model.CarRaceModel;
import com.mycompany.assignment02.view.CarView;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Controls the race simulation and the user interactions.
 *
 * @author Kian
 */
public class CarRaceController {

    private static final double WHEEL_CIRCUMFERENCE = 50;

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
    private final List<RotateTransition> spins = new ArrayList<>();
    private boolean paused;

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
        pauseButton.setDisable(true);
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> applySpeed());
        messageArea.setText("Welcome! Press Start to begin the race.");
    }

    @FXML
    private void handleStart() {
        if (paused) {
            resumeRace();
            return;
        }
        model.randomizeSpeeds();
        moves.clear();
        spins.clear();
        for (CarView carView : carViews) {
            moves.add(createMove(carView));
            spins.addAll(createSpins(carView));
        }
        startButton.setDisable(true);
        pauseButton.setDisable(false);
        messageArea.setText("The race has started. Good luck to all drivers!");
        applySpeed();
        for (TranslateTransition move : moves) {
            move.play();
        }
        for (RotateTransition spin : spins) {
            spin.play();
        }
    }

    private void applySpeed() {
        double rate = speedSlider.getValue();
        speedLabel.setText(String.format("Speed: %.1fx", rate));
        for (TranslateTransition move : moves) {
            move.setRate(rate);
        }
        for (RotateTransition spin : spins) {
            spin.setRate(rate);
        }
    }

    private List<RotateTransition> createSpins(CarView carView) {
        List<RotateTransition> carSpins = new ArrayList<>();
        double secondsPerTurn = WHEEL_CIRCUMFERENCE / carView.getCar().getSpeed();
        for (Group wheel : carView.getWheels()) {
            RotateTransition spin = new RotateTransition(Duration.seconds(secondsPerTurn), wheel);
            spin.setByAngle(360);
            spin.setCycleCount(Animation.INDEFINITE);
            spin.setInterpolator(Interpolator.LINEAR);
            carSpins.add(spin);
        }
        return carSpins;
    }

    private TranslateTransition createMove(CarView carView) {
        Car car = carView.getCar();
        carView.translateXProperty().addListener((observable, oldValue, newValue)
                -> car.moveTo(CarRaceModel.START_X + newValue.doubleValue(), car.getY()));
        double seconds = model.getTrackDistance() / car.getSpeed();
        TranslateTransition move = new TranslateTransition(Duration.seconds(seconds), carView);
        move.setToX(model.getTrackDistance());
        move.setInterpolator(Interpolator.LINEAR);
        return move;
    }

    @FXML
    private void handlePause() {
        for (TranslateTransition move : moves) {
            move.pause();
        }
        for (RotateTransition spin : spins) {
            spin.pause();
        }
        paused = true;
        startButton.setDisable(false);
        startButton.setText("Resume");
        pauseButton.setDisable(true);
        messageArea.appendText("\nThe race is paused.");
    }

    private void resumeRace() {
        paused = false;
        for (TranslateTransition move : moves) {
            move.play();
        }
        for (RotateTransition spin : spins) {
            spin.play();
        }
        startButton.setDisable(true);
        startButton.setText("Start");
        pauseButton.setDisable(false);
        messageArea.appendText("\nThe race continues!");
    }

    @FXML
    private void handleReset() {
        for (TranslateTransition move : moves) {
            move.stop();
        }
        for (RotateTransition spin : spins) {
            spin.stop();
        }
        moves.clear();
        spins.clear();
        model.reset();
        for (CarView carView : carViews) {
            carView.setTranslateX(0);
            for (Group wheel : carView.getWheels()) {
                wheel.setRotate(0);
            }
        }
        paused = false;
        startButton.setText("Start");
        startButton.setDisable(false);
        pauseButton.setDisable(true);
        messageArea.setText("The race was reset. Press Start to race again.");
    }
}
