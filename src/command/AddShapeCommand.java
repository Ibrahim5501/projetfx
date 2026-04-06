package command;

import javafx.scene.Node;
import model.DrawableShape;
import observer.ObservableCanvas;

public class AddShapeCommand implements Command {

    private ObservableCanvas canvas;
    private DrawableShape shape;
    private Node node;

    public AddShapeCommand(ObservableCanvas canvas, DrawableShape shape) {
        this.canvas = canvas;
        this.shape = shape;
    }

    @Override
    public void execute() {
        node = shape.draw();   // ✅ FIX HERE

        canvas.getChildren().add(node);
        canvas.notifyShapeAdded(node);
    }

    @Override
    public void undo() {
        canvas.getChildren().remove(node);
        canvas.notifyShapeRemoved(node);
    }

    public Node getNode() {
        return node;
    }
}