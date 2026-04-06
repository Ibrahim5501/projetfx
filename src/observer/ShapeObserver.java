package observer;

import javafx.scene.Node;

public interface ShapeObserver {

    void onShapeAdded(Node node);
    void onShapeRemoved(Node node);
    void onShapeMoved(Node node);
}