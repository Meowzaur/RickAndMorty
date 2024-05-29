package views;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import dao.PersonajeDaoImpl;
import dao.UsuarioPersonajesDaoImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Personaje;
import models.Usuario;
import models.UsuarioPersonajes;
import models.UsuarioPersonajesId;
import resources.HibernateUtil;
import utils.Listas;

public class PersonajesController {

	// Atributos
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnAnterior;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnFavorito1;

	@FXML
	private Button btnFavorito2;

	@FXML
	private Button btnFavorito3;

	@FXML
	private Button btnFavorito4;

	@FXML
	private Button btnFavorito5;

	@FXML
	private Button btnFavoritos;

	@FXML
	private Button btnSiguiente;

	@FXML
	private GridPane gpPersonaje1;

	@FXML
	private GridPane gpPersonaje2;

	@FXML
	private GridPane gpPersonaje3;

	@FXML
	private GridPane gpPersonaje4;

	@FXML
	private GridPane gpPersonaje5;

	@FXML
	private ImageView ivLogo;

	@FXML
	private ImageView ivPersonaje1;

	@FXML
	private ImageView ivPersonaje2;

	@FXML
	private ImageView ivPersonaje3;

	@FXML
	private ImageView ivPersonaje4;

	@FXML
	private ImageView ivPersonaje5;

	@FXML
	private Label lblBuscarAviso;

	@FXML
	private Label lblPersonaje1Estado;

	@FXML
	private Label lblPersonaje1EstadoTexto;

	@FXML
	private Label lblPersonaje1Nombre;

	@FXML
	private Label lblPersonaje1NombreTexto;

	@FXML
	private Label lblPersonaje2Estado;

	@FXML
	private Label lblPersonaje2EstadoTexto;

	@FXML
	private Label lblPersonaje2Nombre;

	@FXML
	private Label lblPersonaje2NombreTexto;

	@FXML
	private Label lblPersonaje3Estado;

	@FXML
	private Label lblPersonaje3EstadoTexto;

	@FXML
	private Label lblPersonaje3Nombre;

	@FXML
	private Label lblPersonaje3NombreTexto;

	@FXML
	private Label lblPersonaje4Estado;

	@FXML
	private Label lblPersonaje4EstadoTexto;

	@FXML
	private Label lblPersonaje4Nombre;

	@FXML
	private Label lblPersonaje4NombreTexto;

	@FXML
	private Label lblPersonaje5Estado;

	@FXML
	private Label lblPersonaje5EstadoTexto;

	@FXML
	private Label lblPersonaje5Nombre;

	@FXML
	private Label lblPersonaje5NombreTexto;

	@FXML
	private Label lblTitulo1;

	@FXML
	private Label lblTitulo2;

	@FXML
	private AnchorPane panelFondo;

	@FXML
	private AnchorPane panelInicio;

	@FXML
	private TextField tfBuscar;

	int pagina = 0;

	Session session = HibernateUtil.getSession();
	PersonajeDaoImpl perDao = new PersonajeDaoImpl(session);
	UsuarioPersonajesDaoImpl usuPerDao = new UsuarioPersonajesDaoImpl(session);

	private Usuario usuario = LoginController.u;

	/**
	 * Getter usuario
	 * 
	 * @return usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Método que se inicia al cargar la ventana. Añade los personajes cargados por
	 * la API a una lista auxiliar, que es con la que el programa va a trabajar,
	 * dejando la lista de personajes intacta. También tiene un método que termina
	 * la sesión de Hibernate cuando se cierra la ventana.
	 */
	@FXML
	void initialize() {
		Listas.listaAuxiliar.clear();
		Listas.listaAuxiliar.addAll(Listas.listaPersonajes);

		imprimirPersonajes();

		Platform.runLater(() -> {
			Stage stage = (Stage) panelFondo.getScene().getWindow();
			stage.setOnCloseRequest(event -> {
				HibernateUtil.closeSession();
			});
		});
	}

	/**
	 * Añade el personaje a favoritos que está arriba izquierda de la ventana.
	 * 
	 * @param event Pulsar el botón del corazón que está arriba izquierda.
	 */
	@FXML
	void addFavorito1(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje1Nombre, btnFavorito1, pagina * 5);
	}

	/**
	 * Añade el personaje a favoritos que está arriba derecha de la ventana.
	 * 
	 * @param event Pulsar el botón del corazón que está arriba derecha.
	 */
	@FXML
	void addFavorito2(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje2Nombre, btnFavorito2, pagina * 5 + 1);
	}

	/**
	 * Añade el personaje a favoritos que está abajo izquierda de la ventana.
	 * 
	 * @param event Pulsar el botón del corazón que está abajo izquierda.
	 */
	@FXML
	void addFavorito3(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje3Nombre, btnFavorito3, pagina * 5 + 2);
	}

	/**
	 * Añade el personaje a favoritos que está abajo centro de la ventana.
	 * 
	 * @param event Pulsar el botón del corazón que está abajo centro.
	 */
	@FXML
	void addFavorito4(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje4Nombre, btnFavorito4, pagina * 5 + 3);
	}

	/**
	 * Añade el personaje a favoritos que está abajo izquierda de la ventana.
	 * 
	 * @param event Pulsar el botón del corazón que está abajo izquierda.
	 */
	@FXML
	void addFavorito5(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje5Nombre, btnFavorito5, pagina * 5 + 4);
	}

	/**
	 * Carga los 5 personajes anteriores, dependiendo de cuáles estén cargados
	 * actualmente. Si está mostrando los primeros, mostrará los últimos.
	 * 
	 * @param event Pulsar el botón anterior.
	 */
	@FXML
	void anterior(ActionEvent event) {
		if (Listas.listaAuxiliar.size() < 6) {
			JOptionPane.showMessageDialog(null, "No hay más personajes que mostrar.");
		} else {
			if (pagina <= 0) {
				pagina = (Listas.listaAuxiliar.size() - 1) / 5;
			} else {
				pagina--;
			}
			imprimirPersonajes();
		}
	}

	/**
	 * Carga los 5 personajes siguientes, dependiendo de cuáles estén cargados
	 * actualmente. Si está mostrando los últimos, mostrará los primeros.
	 * 
	 * @param event Pulsar el botón anterior.
	 */
	@FXML
	void siguiente(ActionEvent event) {
		if (Listas.listaAuxiliar.size() < 6) {
			JOptionPane.showMessageDialog(null, "No hay más personajes que mostrar.");
		} else {
			if (pagina >= (Listas.listaAuxiliar.size() - 1) / 5) {
				pagina = 0;
			} else {
				pagina++;
			}
			JOptionPane.showMessageDialog(null, "Estás en la página " + pagina + ".");
			imprimirPersonajes();
		}
	}

	/**
	 * Método que modificará la lista auxiliar con los personajes que coincidan con
	 * el texto indicado, ya sean en minúsculas o mayúsculas.
	 * 
	 * @param event Busca dependiendo del contenido del textfield tfBuscar.
	 */
	@FXML
	void buscar(ActionEvent event) {
		Listas.listaAuxiliar.clear();
		if (tfBuscar.getText().isEmpty()) {
			Listas.listaAuxiliar.addAll(Listas.listaPersonajes);
		} else {
			for (Personaje p : Listas.listaPersonajes) {
				if (p.getName().toLowerCase().contains(tfBuscar.getText().toLowerCase())) {
					Listas.listaAuxiliar.add(p);
				}
			}
		}
		pagina = 0;
		imprimirPersonajes();
	}

	/**
	 * Método que abre la ventana de Favoritos, cerrando la ventana actual.
	 * 
	 * @param event Evento de clickar en el botón "Favoritos".
	 */
	@FXML
	void irAFavoritos(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Favoritos.fxml"));
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
	 * Actualiza los datos e imágenes de los personajes según las opciones
	 * seleccionadas.
	 */
	private void imprimirPersonajes() {
		imprimirPersonajeGeneral(lblPersonaje1Nombre, lblPersonaje1Estado, ivPersonaje1, btnFavorito1, pagina * 5);
		imprimirPersonajeGeneral(lblPersonaje2Nombre, lblPersonaje2Estado, ivPersonaje2, btnFavorito2, pagina * 5 + 1);
		imprimirPersonajeGeneral(lblPersonaje3Nombre, lblPersonaje3Estado, ivPersonaje3, btnFavorito3, pagina * 5 + 2);
		imprimirPersonajeGeneral(lblPersonaje4Nombre, lblPersonaje4Estado, ivPersonaje4, btnFavorito4, pagina * 5 + 3);
		imprimirPersonajeGeneral(lblPersonaje5Nombre, lblPersonaje5Estado, ivPersonaje5, btnFavorito5, pagina * 5 + 4);
	}

	/**
	 * Control individual de las actualizaciones de los datos de los personajes,
	 * cambiando el nombre, el estado y la imagen del personaje. También detecta si
	 * el personaje está o no en favoritos para activar o desactivar el botón de
	 * favoritos. En caso de que no haya personaje (mostrar menos personajes de 5),
	 * coloca tres guiones como que no hay personaje, junto con una imagen de 'no
	 * imagen avaliable'.
	 * 
	 * @param nombre El nombre del personaje correspondiente.
	 * @param estado El estado del personaje correspondiente.
	 * @param imagen Url de la imagen del personaje correspondiente.
	 * @param button Estado del botón del botón correspondiente.
	 * @param num    Número en la posición del personaje en la lista auxiliar.
	 */
	private void imprimirPersonajeGeneral(Label nombre, Label estado, ImageView imagen, Button button, int num) {
		if (Listas.listaAuxiliar.size() > num) {
			nombre.setText(Listas.listaAuxiliar.get(num).getName());
			estado.setText(Listas.listaAuxiliar.get(num).getStatus());
			imagen.setImage(new Image(Listas.listaAuxiliar.get(num).getImage()));
			if (estaEnFavoritos(num)) {
				button.setDisable(true);
				button.setVisible(false);
			} else {
				button.setDisable(false);
				button.setVisible(true);
			}
		} else {
			nombre.setText("---");
			estado.setText("---");
			imagen.setImage(new Image(new File("src/images/NoImagen.jpg").toURI().toString()));
			button.setDisable(true);
			button.setVisible(false);
		}
	}

	/**
	 * Comprueba si el personaje está en la base de datos, y Si no está, lo agrega.
	 * Comprueba si el personaje está asociado a favoritos del usuario, y si no
	 * está, lo agrega a favoritos. Desactiva el botón que acaba de ser pulsado.
	 * 
	 * @param label  Nombre del personaje elegido.
	 * @param button Botón cual ha sido pulsado.
	 * @param num    Número de la lista auxiliar.
	 */
	private void addFavoritoGeneral(Label label, Button button, int num) {
		if (!estaEnPersonajes(num)) {
			perDao.insert(Listas.listaAuxiliar.get(num));
		}

		if (!estaEnFavoritos(num)) {
			usuPerDao.insert(
					new UsuarioPersonajes(new UsuarioPersonajesId(getUsuario(), Listas.listaAuxiliar.get(num))));
			JOptionPane.showMessageDialog(null,
					"El personaje " + label.getText() + " SE HA AGREGADO a tu lista de favoritos.");

		}
		button.setDisable(true);
		button.setVisible(false);
	}

	/**
	 * Comprueba si el personaje está en la lista de personajes de la base de datos.
	 * 
	 * @param num Es el número de la posición en la lista auxiliar de los
	 *            personajes.
	 * @return Devuelve true si el personaje ya existe en la base de datos. False si
	 *         no existe en la base de datos.
	 */
	private boolean estaEnPersonajes(int num) {
		boolean esta = false;

		List<Personaje> listaPer = perDao.searchAll();
		for (Personaje per : listaPer) {
			if (per.getId() == Listas.listaAuxiliar.get(num).getId()) {
				esta = true;
			}
		}

		return esta;
	}

	/**
	 * Comprueba si el personaje está en la lista de favoritos de la base de datos
	 * junto con el nombre del usuario que desea comprobarlo.
	 * 
	 * @param num Es el número de la posición en la lista auxiliar de los
	 *            personajes.
	 * @return Devuelve true si el personaje junto con el usuario ya existen
	 *         enlazados en la base de datos. False si no están enlazados.
	 */
	private boolean estaEnFavoritos(int num) {
		boolean esta = false;

		List<UsuarioPersonajes> listaUsuPer = usuPerDao.searchAll();
		for (UsuarioPersonajes usuPer : listaUsuPer) {
			if (usuPer.getId().getUsuario().getNombre().equals(getUsuario().getNombre())
					&& usuPer.getId().getPersonaje().getId() == num + 1) {
				esta = true;
			}
		}

		return esta;
	}

}
