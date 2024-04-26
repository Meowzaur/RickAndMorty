package views;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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

public class RegistroController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnRegistrar;

	@FXML
	private ImageView ivLogo;

	@FXML
	private Label lblPassword;

	@FXML
	private Label lblRegistrarse;

	@FXML
	private Label lblRepetirPassword;

	@FXML
	private Label lblUsuario;

	@FXML
	private AnchorPane panelFondo;

	@FXML
	private AnchorPane panelRegistro;

	@FXML
	private PasswordField pfPassword;

	@FXML
	private PasswordField pfRepetirPassword;

	@FXML
	private TextField tfUsuario;

	@FXML
	void initialize() {

	}

	@FXML
	void registrar(ActionEvent event) {
		if (tfUsuario.getText().isEmpty() || pfPassword.getText().isEmpty() || pfRepetirPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No puede haber celdas vacías.");
		} else {

			if (pfPassword.getText().equals(pfRepetirPassword.getText())) {
				boolean nombreRepetido = false;

				for (Usuario u : Listas.listaUsuarios) {
					if (u.getNombre().equals(tfUsuario.getText()))
						nombreRepetido = true;
				}

				if (nombreRepetido) {

					JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre");

				} else {
					Usuario nuevo = new Usuario(tfUsuario.getText(), pfPassword.getText());
					Listas.listaUsuarios.add(nuevo);

					JOptionPane.showMessageDialog(null, "El nuevo usuario ha sido registrado.");

					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
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

			} else {
				JOptionPane.showMessageDialog(null, "Las contraseñas son distintas.");
			}
		}
	}

	@FXML
	void atras(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
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

}
