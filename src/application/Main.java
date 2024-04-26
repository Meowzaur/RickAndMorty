package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import models.Usuario;
import utils.Listas;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		Usuario u0 = new Usuario("a", "a");
		Usuario u1 = new Usuario("Pablo", "Bruno");
		Listas.listaUsuarios.add(u0);
		Listas.listaUsuarios.add(u1);

		AnchorPane ap;

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/views/Login.fxml"));
			ap = loader.load();
			Scene scene = new Scene(ap);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}