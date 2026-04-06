package decorator;

import model.DrawableShape;
import javafx.scene.Node;

public class ResizeDecorator extends ShapeDecorator {

    private double scale;

    public ResizeDecorator(DrawableShape shape, double scale) {
        super(shape);
        this.scale = scale;
    }

    @Override
    public Node draw() {
        Node node = super.draw();

        node.setScaleX(scale);
        node.setScaleY(scale);

        return node;
    }
}

