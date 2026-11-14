package com.mycompany.assignment02.view;

import com.mycompany.assignment02.model.Car;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Draws a car with JavaFX shapes so that all parts move together.
 *
 * @author Kian
 */
public class CarView extends Group {

    private static final double WHEEL_RADIUS = 8;

    private final Car car;
    private final List<Group> wheels = new ArrayList<>();

    public CarView(Car car) {
        this.car = car;
        wheels.add(createWheel(20));
        wheels.add(createWheel(70));
        getChildren().addAll(createBody(), createRoof(), createWindow(36), createWindow(50));
        getChildren().addAll(wheels);
        getChildren().add(createNumber());
    }

    public List<Group> getWheels() {
        return wheels;
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

    private Group createWheel(double centerX) {
        double centerY = 34;
        Circle tire = new Circle(centerX, centerY, WHEEL_RADIUS, Color.DIMGRAY);
        tire.setStroke(Color.BLACK);
        Line spoke = new Line(centerX, centerY, centerX + WHEEL_RADIUS, centerY);
        spoke.setStroke(Color.WHITE);
        spoke.setStrokeWidth(2);
        return new Group(tire, spoke);
    }

    private Text createNumber() {
        Text number = new Text(38, 28, String.valueOf(car.getNumber()));
        number.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        number.setFill(Color.WHITE);
        return number;
    }
}
