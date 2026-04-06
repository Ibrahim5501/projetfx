package decorator;

import model.DrawableShape;
import javafx.scene.Node;

public abstract class ShapeDecorator implements DrawableShape {

    protected DrawableShape decoratedShape;

    public ShapeDecorator(DrawableShape shape) {
        this.decoratedShape = shape;
    }

    @Override
    public Node draw() {
        return decoratedShape.draw();
    }
}