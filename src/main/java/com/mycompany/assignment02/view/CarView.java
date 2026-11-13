package com.mycompany.assignment02.view;

import com.mycompany.assignment02.model.Car;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * Draws a car with JavaFX shapes so that all parts move together.
 *
 * @author Kian
 */
public class CarView extends Group {

    public static final double WIDTH = 90;

    private final Car car;

    public CarView(Car car) {
        this.car = car;
        getChildren().addAll(createBody(), createRoof(), createWindow(36), createWindow(50));
    }

    public Car getCar() {
        return car;
    }

    private Polygon createBody() {
        Polygon body = new Polygon(0, 32, 0, 16, 10, 12, 80, 12, 90, 18, 90, 32);
        body.setFill(car.getColor());
        body.setStroke(Color.BLACK);
        return body;
    }

    private Polygon createRoof() {
        Polygon roof = new Polygon(22, 12, 32, 0, 62, 0, 72, 12);
        roof.setFill(car.getColor().darker());
        roof.setStroke(Color.BLACK);
        return roof;
    }

    private Rectangle createWindow(double x) {
        Rectangle window = new Rectangle(x, 3, 10, 9);
        window.setFill(Color.LIGHTSKYBLUE);
        window.setStroke(Color.BLACK);
        return window;
    }
}
