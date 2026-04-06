package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
	    Scene scene = new Scene(loader.load(), 1000, 700); // Bigger window

	    stage.setTitle("Drawing App");
	    stage.setScene(scene);
	    stage.setMinWidth(800);   // optional
	    stage.setMinHeight(600);  // optional
	    stage.show();
	}

    public static void main(String[] args) {
        launch();
    }
}