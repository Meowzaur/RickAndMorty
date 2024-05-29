package views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import api.BuscarPersonajesApi;
import dao.UsuarioDaoImpl;
import javafx.application.Platform;
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

public class LoginController {

	// Atributos
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

	Session session = HibernateUtil.getSession();
	UsuarioDaoImpl usuDao = new UsuarioDaoImpl(session);

	public static Usuario u;

	/**
	 * Método que se activa al iniciar la ventana.
	 * 
	 * Si se pulsa el botón de cerrar ventana, el programa cierra la sesión de
	 * hibernate.
	 */
	@FXML
	void initialize() {
		Platform.runLater(() -> {
			Stage stage = (Stage) panelFondo.getScene().getWindow();
			stage.setOnCloseRequest(event -> {
				HibernateUtil.closeSession();
			});
		});
	}

	/**
	 * Método que comprueba si el login es correcto o no. Si alguna celda está vacía
	 * o alguna celda no coincide con los datos en la base de datos, saltará una
	 * ventana emergente con su respectivo error. En caso de que coincida, irá
	 * directo a la ventana de personajes (tarda unos segundos en hacer esta
	 * acción).
	 * 
	 * @param event Evento de clickar en el botón de login.
	 */
	@FXML
	void Logearse(ActionEvent event) {

		// Comprueba que las celdas estén vacías.
		if (tfUsuario.getText().isEmpty() || pfPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No puede haber celdas vacías.");
		} else {
			// Comprueba que el usuario esté en la base de datos.
			if (usuarioCorrecto()) {

				u = getUsuario();

				// Agrega los personajes de la API (es esta acción la que tarda unos segundos).
				BuscarPersonajesApi bpa = new BuscarPersonajesApi();
				bpa.agregarPersonajesApi();

				// Abre la ventana de Personajes y cierra la actual.
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Personajes.fxml"));
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
			} else {
				JOptionPane.showMessageDialog(null, "Los datos son incorrectos.");
			}
		}

	}

	/**
	 * Método que abre la ventana de Registro, cerrando la ventana actual.
	 * 
	 * @param event Evento de clickar en el label "Registrarse".
	 */
	@FXML
	void irARegistro(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Registro.fxml"));
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

	/**
	 * Método que obtiene una lista de usuarios de la base de datos, y si el usuario
	 * y la contraseña coinciden con alguna de las entidades almacenada en la base
	 * de datos, el método devuelve true de un boolean.
	 * 
	 * @return Devuelve true si coinciden los datos. False en caso contrario.
	 */
	private boolean usuarioCorrecto() {
		boolean usuarioCorrecto = false;

		List<Usuario> listaUsu = usuDao.searchAll();
		for (Usuario usu : listaUsu) {
			if (usu.getNombre().equals(tfUsuario.getText()) && usu.getPassword().equals(pfPassword.getText()))
				usuarioCorrecto = true;
		}

		return usuarioCorrecto;
	}

	/**
	 * Getter de usuario
	 * 
	 * @return Usuario
	 */
	private Usuario getUsuario() {
		List<Usuario> listaUsu = usuDao.searchAll();
		for (Usuario usu : listaUsu) {
			if (usu.getNombre().equals(tfUsuario.getText()) && usu.getPassword().equals(pfPassword.getText())) {
				return usu;
			}
		}
		return null;
	}

}
