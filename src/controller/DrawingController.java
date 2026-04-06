package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.AddShapeCommand;
import command.ClearCommand;
import command.Command;
import command.DeleteShapeCommand;
import command.UndoManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.shape.Shape;
import logger.ConsoleLogger;
import logger.DBLogger;
import logger.FileLogger;
import logger.LoggerStrategy;
import model.ShapeFactory;
import model.ShapeModel;
import observer.LoggerObserver;
import observer.ObservableCanvas;

public class DrawingController {

	@FXML
	private ObservableCanvas canvas;

	@FXML
	private ListView<Integer> drawingList;
	
	private LoggerObserver loggerObserver;
	
	@FXML
	private ComboBox<String> loggerSelector;
	private LoggerStrategy logger = ConsoleLogger.getInstance();

	
	private LocalDateTime now = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
    private String dateTime = now.format(formatter);
    
	private String selectedShape = "RECTANGLE";
	
	private UndoManager undoManager = new UndoManager();
	
	private double offsetX, offsetY;

	@FXML
	public void initialize() {
	    canvas.setOnMouseClicked(e -> drawShape(e.getX(), e.getY()));

	    // Logger options
	    loggerSelector.getItems().addAll("Console", "File", "Database");
	    loggerSelector.setValue("Console");

	    loggerObserver = new LoggerObserver(logger);
	    canvas.addObserver(loggerObserver);
	    
	    loggerSelector.setOnAction(e -> switchLogger());
	}

	private void drawShape(double x, double y) {
		ShapeModel model = ShapeFactory.createShape(selectedShape);
		Shape shape = model.draw(x, y);

		enableShapeInteraction(shape);

		Command cmd = new AddShapeCommand(canvas, shape);
		undoManager.execute(cmd);

		//logger.log(dateTime + " / Shape drawn: " + selectedShape);
	}

	@FXML
	public void selectRectangle() {
		selectedShape = "RECTANGLE";
	}

	@FXML
	public void selectCircle() {
		selectedShape = "CIRCLE";
	}

	@FXML
	public void selectLine() {
		selectedShape = "LINE";
	}

	@FXML
	public void undo() {
		undoManager.undo();
		logger.log(dateTime + " / Undo action");
	}

	@FXML
	public void redo() {
		undoManager.redo();
		logger.log(dateTime + " / Redo action");
	}

	@FXML
	public void clearCanvas() {
	    Command cmd = new ClearCommand(canvas);
	    undoManager.execute(cmd);
	    logger.log(dateTime + " / Canvas cleared");
	}

	private void enableShapeInteraction(Shape shape) {

		shape.setOnMousePressed(e -> {
			if (e.isSecondaryButtonDown()) {
				canvas.getChildren().remove(shape);
				//logger.log(dateTime + " / Shape deleted");
				e.consume();
			}
			offsetX = e.getSceneX() - shape.getLayoutX();
			offsetY = e.getSceneY() - shape.getLayoutY();

		});

		shape.setOnMouseDragged(e -> {
		    shape.setLayoutX(e.getSceneX() - offsetX);
		    shape.setLayoutY(e.getSceneY() - offsetY);

		    if (canvas instanceof ObservableCanvas oc)
		        oc.notifyShapeMoved(shape);
		});

		shape.setOnMouseClicked(e -> {
		    if (e.isSecondaryButtonDown()) {
		        Command cmd = new DeleteShapeCommand(canvas, shape);
		        undoManager.execute(cmd);
		        //logger.log(dateTime + " / Shape deleted");
		    }
		    e.consume();
		});
	}
	
	private void switchLogger() {
	    String choice = loggerSelector.getValue();

	    switch (choice) {
	        case "Console":
	            logger = ConsoleLogger.getInstance();
	            break;
	        case "File":
	            logger = FileLogger.getInstance();
	            break;
	        case "Database":
	            logger = DBLogger.getInstance();
	            break;
	    }

	    canvas.removeObserver(loggerObserver);
	    loggerObserver = new LoggerObserver(logger);
	    canvas.addObserver(loggerObserver);

	    logger.log("Logger switched to " + choice);
	}
}