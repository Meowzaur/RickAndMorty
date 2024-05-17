package views;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import dao.PersonajeDaoImpl;
import dao.UsuarioPersonajesDaoImpl;
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

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@FXML
	void initialize() {
		Listas.listaAuxiliar.clear();
		Listas.listaAuxiliar.addAll(Listas.listaPersonajes);

		imprimirPersonajes();
	}

	@FXML
	void addFavorito1(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje1Nombre, btnFavorito1, pagina * 5);
	}

	@FXML
	void addFavorito2(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje2Nombre, btnFavorito2, pagina * 5 + 1);
	}

	@FXML
	void addFavorito3(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje3Nombre, btnFavorito3, pagina * 5 + 2);
	}

	@FXML
	void addFavorito4(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje4Nombre, btnFavorito4, pagina * 5 + 3);
	}

	@FXML
	void addFavorito5(ActionEvent event) {
		addFavoritoGeneral(lblPersonaje5Nombre, btnFavorito5, pagina * 5 + 4);
	}

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
			JOptionPane.showMessageDialog(null, "Estás en la página " + pagina + ".");
			imprimirPersonajes();
		}
	}

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

	@FXML
	void irAFavoritos(ActionEvent event) {
		if (Listas.listaFavoritos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "La lista de personajes favoritos está vacía.");
		} else {
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
	}

	private void imprimirPersonajes() {
		imprimirPersonajeGeneral(lblPersonaje1Nombre, lblPersonaje1Estado, ivPersonaje1, btnFavorito1, pagina * 5);
		imprimirPersonajeGeneral(lblPersonaje2Nombre, lblPersonaje2Estado, ivPersonaje2, btnFavorito2, pagina * 5 + 1);
		imprimirPersonajeGeneral(lblPersonaje3Nombre, lblPersonaje3Estado, ivPersonaje3, btnFavorito3, pagina * 5 + 2);
		imprimirPersonajeGeneral(lblPersonaje4Nombre, lblPersonaje4Estado, ivPersonaje4, btnFavorito4, pagina * 5 + 3);
		imprimirPersonajeGeneral(lblPersonaje5Nombre, lblPersonaje5Estado, ivPersonaje5, btnFavorito5, pagina * 5 + 4);
	}

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

	private void addFavoritoGeneral(Label label, Button button, int num) {
		if (!estaEnPersonajes(num)) {
			perDao.insert(Listas.listaAuxiliar.get(num));
		}

		if (estaEnFavoritos(num)) {
			JOptionPane.showMessageDialog(null,
					"El personaje " + label.getText() + " YA EXISTE en tu lista de favoritos.");
		} else {

			usuPerDao.insert(
					new UsuarioPersonajes(new UsuarioPersonajesId(getUsuario(), Listas.listaAuxiliar.get(num))));
			JOptionPane.showMessageDialog(null,
					"El personaje " + label.getText() + " SE HA AGREGADO a tu lista de favoritos.");

		}
		button.setDisable(true);
		button.setVisible(false);
	}

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

	private boolean estaEnFavoritos(int num) {
		boolean esta = false;

		List<UsuarioPersonajes> listaUsuPer = usuPerDao.searchAll();
		for (UsuarioPersonajes usuPer : listaUsuPer) {
			System.out.println(usuPer.getId().getUsuario().getNombre());
			System.out.println(getUsuario().getNombre());
			System.out.println(usuPer.getId().getPersonaje().getId());
			System.out.println(num);
			if (usuPer.getId().getUsuario().getNombre().equals(getUsuario().getNombre())
					&& usuPer.getId().getPersonaje().getId() == num + 1) {
				esta = true;
			}
		}

		return esta;
	}

}
