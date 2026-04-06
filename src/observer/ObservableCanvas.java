package observer;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class ObservableCanvas extends Pane implements ShapeSubject {

    private List<ShapeObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(ShapeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ShapeObserver observer) {
        observers.remove(observer);
    }

    public void notifyShapeAdded(Shape shape) {
        observers.forEach(o -> o.onShapeAdded(shape));
    }

    public void notifyShapeRemoved(Shape shape) {
        observers.forEach(o -> o.onShapeRemoved(shape));
    }

    public void notifyShapeMoved(Shape shape) {
        observers.forEach(o -> o.onShapeMoved(shape));
    }
}