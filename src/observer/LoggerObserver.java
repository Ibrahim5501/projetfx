package observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.shape.Shape;
import logger.LoggerStrategy;

public class LoggerObserver implements ShapeObserver {

	
	private LocalDateTime now = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
    private String dateTime = now.format(formatter);
	
    private LoggerStrategy logger;

    public LoggerObserver(LoggerStrategy logger) {
        this.logger = logger;
    }

    @Override
    public void onShapeAdded(Shape shape) {
        logger.log(dateTime + " / Shape added: " + shape.getClass().getSimpleName());
    }

    @Override
    public void onShapeRemoved(Shape shape) {
        logger.log(dateTime + " / Shape removed: " + shape.getClass().getSimpleName());
    }

    @Override
    public void onShapeMoved(Shape shape) {
        logger.log(dateTime + " / Shape moved: " + shape.getClass().getSimpleName());
    }
}