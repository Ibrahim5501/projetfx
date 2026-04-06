package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleShape extends ShapeModel {

    @Override
    public Shape draw(double x, double y) {
        Circle circle = new Circle(x, y, 40);
        circle.setFill(Color.BLUE);
        circle.setStroke(Color.BLUE);
        return circle;
    }
}