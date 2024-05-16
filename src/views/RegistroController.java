package views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import dao.UsuarioDaoImpl;
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
import resources.HibernateUtil;
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

	Session session = HibernateUtil.getSession();
	UsuarioDaoImpl usuDao = new UsuarioDaoImpl(session);

	@FXML
	void initialize() {

	}

	@FXML
	void registrar(ActionEvent event) {
		if (tfUsuario.getText().isEmpty() || pfPassword.getText().isEmpty() || pfRepetirPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No puede haber celdas vacías.");
		} else {

			if (pfPassword.getText().equals(pfRepetirPassword.getText())) {

				if (nombreRepetido()) {

					JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre");

				} else {
					Usuario nuevo = new Usuario(tfUsuario.getText(), pfPassword.getText());
					usuDao.insert(nuevo);

					JOptionPane.showMessageDialog(null, "El nuevo usuario ha sido registrado.");

					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
						Parent root = loader.load();
						Stage nuevaStage = new Stage();
						nuevaStage.setScene(new Scene(root));
						nuevaStage.resizableProperty().setValue(false);
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
			nuevaStage.resizableProperty().setValue(false);
			nuevaStage.show();
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean nombreRepetido() {
		boolean nombreRepetido = false;

		List<Usuario> listaUsu = usuDao.searchAll();
		for (Usuario usu : listaUsu) {
			if (usu.getNombre().equals(tfUsuario.getText()))
				nombreRepetido = true;
		}

		return nombreRepetido;
	}

}
