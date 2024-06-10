package views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;

import dao.UsuarioDaoImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Usuario;
import utils.HibernateUtil;

public class RegistroController {

	// Atributos
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnRegistrar;

	@FXML
	private ImageView ivLogo;

	@FXML
	private Label lblPasswordR;

	@FXML
	private Label lblRegistrarse;

	@FXML
	private Label lblRepetirPassword;

	@FXML
	private Label lblUsuarioR;

	@FXML
	private AnchorPane panelFondo;

	@FXML
	private AnchorPane panelRegistro;

	@FXML
	private PasswordField pfPasswordR;

	@FXML
	private PasswordField pfRepetirPassword;

	@FXML
	private TextField tfUsuarioR;

	Session session = HibernateUtil.getSession();
	UsuarioDaoImpl usuDao = new UsuarioDaoImpl(session);

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
	 * Método que comprueba si el registro es correcto. Si alguna celda está vacía,
	 * si las celdas de contraseñas son diferentes, o si el nombre de usuario
	 * coincide con un nombre existente en la base de datos, entonces el método
	 * mostrará una ventana que informará del error cometido. En caso de que ninguno
	 * de estos errores suceda, agregará este usuario a la base de datos y abrirá la
	 * ventana de login y cerrará la ventana actual.
	 * 
	 * @param event Clickar en el botón "Registrar"
	 */
	@FXML
	void registrar(ActionEvent event) {
		// Comprueba si hay celdas vacías
		if (tfUsuarioR.getText().isEmpty() || pfPasswordR.getText().isEmpty() || pfRepetirPassword.getText().isEmpty()) {
			ventanaError("No puede haber celdas vacías.", "CeldasVacias");
		} else {
			// Comprueba que las contraseñas coincidan
			if (pfPasswordR.getText().equals(pfRepetirPassword.getText())) {
				// Comprueba que el nombre de usuario esté o no en la base de datos
				if (nombreRepetido()) {

					ventanaError("Ya existe un usuario con ese nombre.", "UsuarioExistente");

				} else {
					// Agrega el nuevo usuario
					usuDao.insert(new Usuario(tfUsuarioR.getText(), pfPasswordR.getText()));
					
					// Mensaje de usuario correcto registrado
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("El nuevo usuario ha sido registrado.");
					Button errorButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
			        errorButton.setId("RegistroCorrecto");
					alert.showAndWait();

					// Abre la ventana de login y cierra la ventana actual
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
				ventanaError("Las contraseñas son distintas.", "PasswordsIncorrectas");
			}
		}
	}

	/**
	 * Método que abre la ventana de Login y cierra la ventana actual
	 * 
	 * @param event clickar en el label "atrás".
	 */
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

	/**
	 * Método que comprueba que el nombre en la celda de usuario se repita o no con
	 * alguno de la base de datos
	 * 
	 * @return Devuelve true si el nombre es coincidente. False en caso contrario.
	 */
	private boolean nombreRepetido() {
		boolean nombreRepetido = false;

		List<Usuario> listaUsu = usuDao.searchAll();
		for (Usuario usu : listaUsu) {
			if (usu.getNombre().equals(tfUsuarioR.getText()))
				nombreRepetido = true;
		}

		return nombreRepetido;
	}

	/**
	 * Método que muestra una ventana de Error
	 * 
	 * @param mensaje Mensaje en String con el tipo de error sucedido
	 */
	private void ventanaError(String mensaje, String id) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Ventana de Error");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		Button errorButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        errorButton.setId(id);

		alert.showAndWait();
	}

}
