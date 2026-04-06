/**
 * 
 */
/**
 * 
 */
module projetfx {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires org.xerial.sqlitejdbc;
	
	exports app;
	exports example;
	
	opens app to javafx.graphics;
	opens controller to javafx.fxml;
	opens observer to javafx.fxml;
}