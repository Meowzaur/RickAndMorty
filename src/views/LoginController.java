package views;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import api.BuscarPersonajesApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Usuario;
import utils.Listas;

public class LoginController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnLogin;

	@FXML
	private ImageView ivLogo;

	@FXML
	private Label lblPassword;

	@FXML
	private Label lblRegistrarse;

	@FXML
	private Label lblUsuario;

	@FXML
	private AnchorPane panelFondo;

	@FXML
	private AnchorPane panelLogin;

	@FXML
	private PasswordField pfPassword;

	@FXML
	private TextField tfUsuario;

	@FXML
	void Logearse(ActionEvent event) {

		if (tfUsuario.getText().isEmpty() || pfPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No puede haber celdas vacías.");
		} else {
			boolean usuarioCorrecto = false;
			for (Usuario u : Listas.listaUsuarios) {
				if (u.getNombre().equals(tfUsuario.getText()) && u.getPassword().equals(pfPassword.getText()))
					usuarioCorrecto = true;
			}

			if (usuarioCorrecto) {
				JOptionPane.showMessageDialog(null, "Usuario correcto.");

				BuscarPersonajesApi bpa = new BuscarPersonajesApi();
				bpa.agregarPersonajesApi();

				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Personajes.fxml"));
					Parent root = loader.load();
					Stage nuevaStage = new Stage();
					nuevaStage.setScene(new Scene(root));
					nuevaStage.show();
					Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
					stage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Los datos son incorrectos.");
			}
		}

	}

	@FXML
	void irARegistro(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Registro.fxml"));
			Parent root = loader.load();
			Stage nuevaStage = new Stage();
			nuevaStage.setScene(new Scene(root));
			nuevaStage.show();
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {

	}

}