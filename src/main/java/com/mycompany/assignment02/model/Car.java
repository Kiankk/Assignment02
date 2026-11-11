package com.mycompany.assignment02.model;

import javafx.scene.paint.Color;

/**
 * Stores the data of one race car.
 *
 * @author Kian
 */
public class Car {

    private final String type;
    private final int number;
    private final String colorName;
    private final Color color;
    private double x;
    private double y;
    private double speed;

    public Car(String type, int number, String colorName, Color color) {
        this.type = type;
        this.number = number;
        this.colorName = colorName;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public String getColorName() {
        return colorName;
    }

    public Color getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }
}
