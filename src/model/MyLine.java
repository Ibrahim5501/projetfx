package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class MyLine extends ShapeModel {

    @Override
    public Shape draw(double x, double y) {
        Line line = new Line(x, y, x + 100, y + 50);
        line.setStroke(Color.BLACK);
        return line;
    }
}