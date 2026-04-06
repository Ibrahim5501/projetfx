package model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineShape implements DrawableShape {

    private double x1, y1, x2, y2;

    public LineShape(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public Node draw() {
        Line line = new Line(x1, y1, x2, y2);
        line.setStroke(Color.BLACK);
        return line;
    }
}