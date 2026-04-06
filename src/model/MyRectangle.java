package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class MyRectangle extends ShapeModel {

    @Override
    public Shape draw(double x, double y) {
        Rectangle rect = new Rectangle(x, y, 100, 60);
        rect.setFill(Color.RED);
        rect.setStroke(Color.RED);
        return rect;
    }
}