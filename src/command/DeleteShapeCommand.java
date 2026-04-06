package command;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import observer.ObservableCanvas;

public class DeleteShapeCommand implements Command {

    private Pane canvas;
    private Node node;

    public DeleteShapeCommand(Pane canvas, Node node) {
        this.canvas = canvas;
        this.node = node;
    }

    @Override
    public void execute() {
        canvas.getChildren().remove(node);

        if (canvas instanceof ObservableCanvas oc) {
            oc.notifyShapeRemoved(node);
        }
    }

    @Override
    public void undo() {
        canvas.getChildren().add(node);

        if (canvas instanceof ObservableCanvas oc) {
            oc.notifyShapeAdded(node);
        }
    }
}