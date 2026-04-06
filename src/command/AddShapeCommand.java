package command;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import observer.ObservableCanvas;

public class AddShapeCommand implements Command {

    private Pane canvas;
    private Shape shape;

    public AddShapeCommand(Pane canvas, Shape shape) {
        this.canvas = canvas;
        this.shape = shape;
    }

    @Override
    public void execute() {
        canvas.getChildren().add(shape);
        if (canvas instanceof ObservableCanvas oc)
            oc.notifyShapeAdded(shape);
    }

    @Override
    public void undo() {
        canvas.getChildren().remove(shape);
        if (canvas instanceof ObservableCanvas oc)
            oc.notifyShapeRemoved(shape);
    }
}