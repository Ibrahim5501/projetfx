// old file

/*
package command;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class DrawCommand implements Command {

    private Pane canvas;
    private Shape shape;

    public DrawCommand(Pane canvas, Shape shape) {
        this.canvas = canvas;
        this.shape = shape;
    }

    @Override
    public void execute() {
        canvas.getChildren().add(shape);
    }

    @Override
    public void undo() {
        canvas.getChildren().remove(shape);
    }
}
*/