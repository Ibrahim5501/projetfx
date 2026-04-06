package command;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import observer.ObservableCanvas;

public class DeleteShapeCommand implements Command {

    private Pane canvas;
    private Shape shape;

    public DeleteShapeCommand(Pane canvas, Shape shape) {
        this.canvas = canvas;
        this.shape = shape;
    }

    @Override
    public void execute() {
        canvas.getChildren().remove(shape);
        if (canvas instanceof ObservableCanvas oc)
            oc.notifyShapeRemoved(shape);
    }

    @Override
    public void undo() {
        canvas.getChildren().add(shape);
        if (canvas instanceof ObservableCanvas oc)
            oc.notifyShapeAdded(shape);
    }
}