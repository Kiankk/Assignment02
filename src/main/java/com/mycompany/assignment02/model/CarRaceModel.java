package com.mycompany.assignment02.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Stores the cars and the data of the race.
 *
 * @author Kian
 */
public class CarRaceModel {

    public static final double START_X = 120;
    public static final double FINISH_X = 820;
    public static final double LANE_HEIGHT = 70;
    public static final double LANE_TOP = 20;

    private final List<Car> cars = new ArrayList<>();
    private final List<Car> finishOrder = new ArrayList<>();
    private final Random random = new Random();

    public CarRaceModel() {
        cars.add(new Car("Sedan", 1, "Red", Color.CRIMSON));
        cars.add(new Car("Coupe", 2, "Blue", Color.ROYALBLUE));
        cars.add(new Car("Hatchback", 3, "Green", Color.SEAGREEN));
        cars.add(new Car("SUV", 4, "Orange", Color.DARKORANGE));
        cars.add(new Car("Roadster", 5, "Purple", Color.MEDIUMPURPLE));
        resetPositions();
    }

    public List<Car> getCars() {
        return cars;
    }

    /**
     * Puts every car on the start line in its own lane.
     */
    public void resetPositions() {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).moveTo(START_X, LANE_TOP + i * LANE_HEIGHT);
        }
    }

    /**
     * Gives every car a new random speed in pixels per second.
     */
    public void randomizeSpeeds() {
        for (Car car : cars) {
            car.setSpeed(110 + random.nextInt(60));
        }
    }

    /**
     * Records that a car crossed the finish line.
     *
     * @param car the car that finished
     */
    public void addFinisher(Car car) {
        if (!finishOrder.contains(car)) {
            finishOrder.add(car);
        }
    }

    public List<Car> getFinishOrder() {
        return finishOrder;
    }

    public Car getWinner() {
        return finishOrder.isEmpty() ? null : finishOrder.get(0);
    }

    public boolean isRaceOver() {
        return finishOrder.size() == cars.size();
    }

    /**
     * Clears the results and puts the cars back on the start line.
     */
    public void reset() {
        finishOrder.clear();
        resetPositions();
    }
}
