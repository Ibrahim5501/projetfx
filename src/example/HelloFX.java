package example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloFX extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Hello JavaFX");
		primaryStage.setScene(new Scene(new Label("Bonjour JavaFX !"), 300, 200));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}