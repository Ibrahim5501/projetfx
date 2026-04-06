package observer;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.DrawableShape;

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

    public void notifyShapeAdded(Node node) {
        observers.forEach(o -> o.onShapeAdded(node));
    }

    public void notifyShapeRemoved(Node node) {
        observers.forEach(o -> o.onShapeRemoved(node));
    }

    public void notifyShapeMoved(Node node) {
        observers.forEach(o -> o.onShapeMoved(node));
    }
}