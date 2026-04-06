package decorator;

import model.DrawableShape;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class BorderDecorator extends ShapeDecorator {

    private Color borderColor;
    private double width;

    public BorderDecorator(DrawableShape shape, Color borderColor, double width) {
        super(shape);
        this.borderColor = borderColor;
        this.width = width;
    }

    @Override
    public Node draw() {
        Node node = super.draw();

        if (node instanceof Shape s) {
            s.setStroke(borderColor);
            s.setStrokeWidth(width);
        }

        return node;
    }
}

