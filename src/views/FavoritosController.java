package views;

import java.io.File;
import java.net.URL;
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

public class FavoritosController {

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
	private Button btnDesfavorito1;

	@FXML
	private Button btnDesfavorito2;

	@FXML
	private Button btnDesfavorito3;

	@FXML
	private Button btnDesfavorito4;

	@FXML
	private Button btnDesfavorito5;

	@FXML
	private Button btnSiguiente;

	@FXML
	private Button btnVolver;

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
	private Label lblCorazon;

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
	 * Método que se inicia al cargar la ventana. Añade los personajes favoritos del
	 * usuario a la lista auxiliar. También tiene un método que termina la sesión de
	 * Hibernate cuando se cierra la ventana.
	 */
	@FXML
	void initialize() {
		Listas.listaAuxiliar.clear();
		Listas.listaAuxiliar.addAll(usuPerDao.personajesPorUsuario(usuario.getNombre()));

		imprimirPersonajesFav();

		Platform.runLater(() -> {
			Stage stage = (Stage) panelFondo.getScene().getWindow();
			stage.setOnCloseRequest(event -> {
				HibernateUtil.closeSession();
			});
		});
	}

	/**
	 * Carga los 5 personajes anteriores, dependiendo de cuáles estén cargados
	 * actualmente. Si está mostrando los primeros, mostrará los últimos.
	 * 
	 * @param event Pulsar el botón anterior.
	 */
	@FXML
	void anteriorFav(ActionEvent event) {
		if (Listas.listaAuxiliar.size() < 6) {
			JOptionPane.showMessageDialog(null, "No hay más personajes que mostrar.");
			pagina = 0;
		} else {
			if (pagina <= 0) {
				pagina = (Listas.listaAuxiliar.size() - 1) / 5;
			} else {
				pagina--;
			}
		}
		imprimirPersonajesFav();
	}

	/**
	 * Carga los 5 personajes siguientes, dependiendo de cuáles estén cargados
	 * actualmente. Si está mostrando los últimos, mostrará los primeros.
	 * 
	 * @param event Pulsar el botón anterior.
	 */
	@FXML
	void siguienteFav(ActionEvent event) {
		if (Listas.listaAuxiliar.size() < 6) {
			JOptionPane.showMessageDialog(null, "No hay más personajes que mostrar.");
			pagina = 0;
		} else {
			if (pagina >= (Listas.listaAuxiliar.size() - 1) / 5) {
				pagina = 0;
			} else {
				pagina++;
			}
		}
		imprimirPersonajesFav();
	}

	/**
	 * Método que modificará la lista auxiliar con los personajes que coincidan con
	 * el texto indicado, ya sean en minúsculas o mayúsculas.
	 * 
	 * @param event Busca dependiendo del contenido del textfield tfBuscar.
	 */
	@FXML
	void buscarFav(ActionEvent event) {
		Listas.listaAuxiliar.clear();
		if (tfBuscar.getText().isEmpty()) {
			Listas.listaAuxiliar.addAll(usuPerDao.personajesPorUsuario(usuario.getNombre()));
		} else {
			for (Personaje p : usuPerDao.personajesPorUsuario(usuario.getNombre())) {
				if (p.getName().toLowerCase().contains(tfBuscar.getText().toLowerCase())) {
					Listas.listaAuxiliar.add(p);
				}
			}
		}
		pagina = 0;
		imprimirPersonajesFav();
	}

	/**
	 * Elimina el enlace entre el usuario y el personaje favorito que está arriba
	 * izquierda de la ventana.
	 * 
	 * @param event Pulsar el botón de la 'x' que está arriba izquierda.
	 */
	@FXML
	void quitarFavorito1(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5);
	}

	/**
	 * Elimina el enlace entre el usuario y el personaje favorito que está arriba
	 * derecha de la ventana.
	 * 
	 * @param event Pulsar el botón de la 'x' que está arriba derecha.
	 */
	@FXML
	void quitarFavorito2(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5 + 1);
	}

	/**
	 * Elimina el enlace entre el usuario y el personaje favorito que está abajo
	 * izquierda de la ventana.
	 * 
	 * @param event Pulsar el botón de la 'x' que está abajo izquierda.
	 */
	@FXML
	void quitarFavorito3(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5 + 2);
	}

	/**
	 * Elimina el enlace entre el usuario y el personaje favorito que está abajo
	 * centro de la ventana.
	 * 
	 * @param event Pulsar el botón de la 'x' que está abajo centro.
	 */
	@FXML
	void quitarFavorito4(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5 + 3);
	}

	/**
	 * Elimina el enlace entre el usuario y el personaje favorito que está abajo
	 * derecha de la ventana.
	 * 
	 * @param event Pulsar el botón de la 'x' que está abajo derecha.
	 */
	@FXML
	void quitarFavorito5(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5 + 4);
	}

	/**
	 * Método que abre la ventana de Personajes, cerrando la ventana actual.
	 * 
	 * @param event Evento de clickar en el botón "Volver".
	 */
	@FXML
	void volver(ActionEvent event) {
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
	}

	/**
	 * Actualiza los datos e imágenes de los personajes favoritos según las opciones
	 * seleccionadas.
	 */
	void imprimirPersonajesFav() {
		imprimirPersonajeGeneralFav(lblPersonaje1Nombre, lblPersonaje1Estado, ivPersonaje1, btnDesfavorito1,
				pagina * 5);
		imprimirPersonajeGeneralFav(lblPersonaje2Nombre, lblPersonaje2Estado, ivPersonaje2, btnDesfavorito2,
				pagina * 5 + 1);
		imprimirPersonajeGeneralFav(lblPersonaje3Nombre, lblPersonaje3Estado, ivPersonaje3, btnDesfavorito3,
				pagina * 5 + 2);
		imprimirPersonajeGeneralFav(lblPersonaje4Nombre, lblPersonaje4Estado, ivPersonaje4, btnDesfavorito4,
				pagina * 5 + 3);
		imprimirPersonajeGeneralFav(lblPersonaje5Nombre, lblPersonaje5Estado, ivPersonaje5, btnDesfavorito5,
				pagina * 5 + 4);
	}

	/**
	 * Control individual de las actualizaciones de los datos de los personajes
	 * favoritos, cambiando el nombre, el estado y la imagen del personaje. En caso
	 * de que no haya personaje (mostrar menos personajes de 5), coloca tres guiones
	 * como que no hay personaje, junto con una imagen de 'no imagen avaliable'.
	 * 
	 * @param nombre El nombre del personaje correspondiente.
	 * @param estado El estado del personaje correspondiente.
	 * @param imagen Url de la imagen del personaje correspondiente.
	 * @param button Estado del botón del botón correspondiente.
	 * @param num    Número en la posición del personaje en la lista auxiliar.
	 */
	void imprimirPersonajeGeneralFav(Label nombre, Label estado, ImageView imagen, Button button, int num) {
		if (Listas.listaAuxiliar.size() > num) {
			nombre.setText(Listas.listaAuxiliar.get(num).getName());
			estado.setText(Listas.listaAuxiliar.get(num).getStatus());
			imagen.setImage(new Image(Listas.listaAuxiliar.get(num).getImage()));
			button.setDisable(false);
			button.setVisible(true);
		} else {
			nombre.setText("---");
			estado.setText("---");
			imagen.setImage(new Image(new File("src/images/NoImagen.jpg").toURI().toString()));
			button.setDisable(true);
			button.setVisible(false);
		}
	}

	/**
	 * Elimina tanto de la lista auxiliar el personaje como de la base de datos el
	 * enlace entre el usuario y el personaje. Actualiza los datos.
	 * 
	 * @param num
	 */
	void quitarFavoritoGeneral(int num) {
		int id = Listas.listaAuxiliar.get(num).getId();
		
		
		usuPerDao.eliminarPorIdPersonajeDeUsuario(usuario.getNombre(), id);

		for (Personaje p : Listas.listaAuxiliar) {
			if (p.getId() == id) {
				Listas.listaAuxiliar.remove(p);
				break;
			}
		}

		imprimirPersonajesFav();
	}

}
