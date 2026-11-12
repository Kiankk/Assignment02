package com.mycompany.assignment02.model;

import java.util.ArrayList;
import java.util.List;
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
}
