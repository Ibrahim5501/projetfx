package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleShape implements DrawableShape {

    private double centerX, centerY, radius;

    public CircleShape(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public Node draw() {
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(Color.GREEN);
        return circle;
    }
}