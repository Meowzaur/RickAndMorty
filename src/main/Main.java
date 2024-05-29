package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		AnchorPane ap;

		// Inicia el programa abriendo la ventana de Login
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/views/Login.fxml"));
			ap = loader.load();
			Scene scene = new Scene(ap);
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().setValue(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}