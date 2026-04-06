package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleShape implements DrawableShape {

    private double x, y, width, height;

    public RectangleShape(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public Node draw() {
        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setFill(Color.BLUE);
        return rect;
    }
}