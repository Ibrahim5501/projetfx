package command;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import observer.ObservableCanvas;

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
                oc.notifyShapeRemoved(node); // ✅ correct
            }
        }
    }

    @Override
    public void undo() {
        canvas.getChildren().addAll(backup);

        if (canvas instanceof ObservableCanvas oc) {
            for (Node node : backup) {
                oc.notifyShapeAdded(node); // ✅ FIXED
            }
        }
    }
}