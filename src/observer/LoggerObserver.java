package observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.Node;
import javafx.scene.shape.Shape;
import logger.LoggerStrategy;

public class LoggerObserver implements ShapeObserver {

    private LoggerStrategy logger;

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public LoggerObserver(LoggerStrategy logger) {
        this.logger = logger;
    }

    private String now() {
        return LocalDateTime.now().format(formatter);
    }

    @Override
    public void onShapeAdded(Node node) {
        logger.log(now() + " / Shape added: " + getShapeName(node));
    }

    @Override
    public void onShapeRemoved(Node node) {
        logger.log(now() + " / Shape removed: " + getShapeName(node));
    }

    @Override
    public void onShapeMoved(Node node) {
        logger.log(now() + " / Shape moved: " + getShapeName(node));
    }

    // 🔥 Helper method
    private String getShapeName(Node node) {
        if (node instanceof Shape s) {
            return s.getClass().getSimpleName();
        }
        return node.getClass().getSimpleName();
    }
}