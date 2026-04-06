package decorator;

import model.DrawableShape;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class ColorDecorator extends ShapeDecorator {

    private Color color;

    public ColorDecorator(DrawableShape shape, Color color) {
        super(shape);
        this.color = color;
    }

    @Override
    public Node draw() {
        Node node = super.draw();

        if (node instanceof Shape s) {
            s.setFill(color);
        }

        return node;
    }
}

