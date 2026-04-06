package command;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import observer.ObservableCanvas;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class ClearCommand implements Command {

    private Pane canvas;
    private List<Node> backup = new ArrayList<>();

    public ClearCommand(Pane canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        backup.clear();
        backup.addAll(canvas.getChildren());
        canvas.getChildren().clear();

        if (canvas instanceof ObservableCanvas oc) {
        	for (Node node : backup) {
        	    if (node instanceof Shape shape) {
        	        oc.notifyShapeRemoved(shape);
        	    }
        	}
        }
    }

    @Override
    public void undo() {
        canvas.getChildren().addAll(backup);

        if (canvas instanceof ObservableCanvas oc) {
        	for (Node node : backup) {
        	    if (node instanceof Shape shape) {
        	        oc.notifyShapeRemoved(shape);
        	    }
        	}
        }
    }
}