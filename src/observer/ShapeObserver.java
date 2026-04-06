package observer;

import javafx.scene.shape.Shape;

public interface ShapeObserver {
    void onShapeAdded(Shape shape);
    void onShapeRemoved(Shape shape);
    void onShapeMoved(Shape shape);
}