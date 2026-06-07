package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import logger.*;
import model.DrawableShape;
import model.ShapeFactory;
import observer.LoggerObserver;
import observer.ObservableCanvas;
import decorator.*;

public class DrawingController {

	@FXML
	private ObservableCanvas canvas;

	@FXML
	private ListView<Integer> drawingList;

	@FXML
	private ComboBox<String> loggerSelector;

	@FXML
	private ColorPicker colorPicker;

	@FXML
	private CheckBox borderCheckBox;

	@FXML
	private Slider sizeSlider;

	private LoggerObserver loggerObserver;
	private LoggerStrategy logger = ConsoleLogger.getInstance();

	private String selectedShape = "rectangle";

	private UndoManager undoManager = new UndoManager();

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	private double offsetX, offsetY;

	// ================= INIT =================
	@FXML
	public void initialize() {

		canvas.setOnMouseClicked(e -> drawShape(e.getX(), e.getY()));

		loggerSelector.getItems().addAll("Console", "File", "Database");
		loggerSelector.setValue("Console");

		loggerObserver = new LoggerObserver(logger);
		canvas.addObserver(loggerObserver);

		loggerSelector.setOnAction(e -> switchLogger());
	}

	// ================= DRAW =================
	public void drawShape(double x, double y) {

		double size = 80;

		DrawableShape shape = ShapeFactory.createShape(selectedShape, x, y, x + size, y + size);

		// 🎨 Decorators
		if (colorPicker.getValue() != null) {
			shape = new ColorDecorator(shape, colorPicker.getValue());
		}

		if (borderCheckBox.isSelected()) {
			shape = new BorderDecorator(shape, Color.BLACK, 2);
		}

		if (sizeSlider.getValue() != 1.0) {
			shape = new ResizeDecorator(shape, sizeSlider.getValue());
		}

		Command cmd = new AddShapeCommand(canvas, shape);
		undoManager.execute(cmd);

		log("Shape added");

		// Enable interaction AFTER creation
		Shape node = (Shape) ((AddShapeCommand) cmd).getNode();
		enableShapeInteraction(node);
	}

	// ================= SHAPE SELECTION =================
	@FXML
	public void selectRectangle() {
		selectedShape = "rectangle";
	}

	@FXML
	public void selectCircle() {
		selectedShape = "circle";
	}

	@FXML
	public void selectLine() {
		selectedShape = "line";
	}

	// ================= COMMANDS =================
	@FXML
	public void undo() {
		undoManager.undo();
		log("Undo action");
	}

	@FXML
	public void redo() {
		undoManager.redo();
		log("Redo action");
	}

	@FXML
	public void clearCanvas() {
		Command cmd = new ClearCommand(canvas);
		undoManager.execute(cmd);
		log("Canvas cleared");
	}

	// ================= INTERACTION =================
	private void enableShapeInteraction(Shape shape) {

		shape.setOnMousePressed(e -> {
			if (e.isSecondaryButtonDown()) {
				Shape shapeNode = (Shape) e.getSource();

				Command cmd = new DeleteShapeCommand(canvas, shapeNode);
				undoManager.execute(cmd);
			} else {
				offsetX = e.getSceneX() - shape.getLayoutX();
				offsetY = e.getSceneY() - shape.getLayoutY();
			}
		});

		shape.setOnMouseDragged(e -> {
			shape.setLayoutX(e.getSceneX() - offsetX);
			shape.setLayoutY(e.getSceneY() - offsetY);

			canvas.notifyShapeMoved(shape);
		});
	}

	// ================= LOGGER =================
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

		log("Logger switched to " + choice);
	}

	private void log(String message) {
		String time = LocalDateTime.now().format(formatter);
		logger.log(time + " / " + message);
	}
}