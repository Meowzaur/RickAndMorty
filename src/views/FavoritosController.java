package views;

import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Personaje;
import utils.Listas;

public class FavoritosController {

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

	@FXML
	void initialize() {
		Listas.listaAuxiliar.clear();
		Listas.listaAuxiliar.addAll(Listas.listaFavoritos);

		imprimirPersonajesFav();
	}

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
			JOptionPane.showMessageDialog(null, "Estás en la página " + pagina + ".");
		}
		imprimirPersonajesFav();
	}

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
			JOptionPane.showMessageDialog(null, "Estás en la página " + pagina + ".");
		}
		imprimirPersonajesFav();
	}

	@FXML
	void buscarFav(ActionEvent event) {
		Listas.listaAuxiliar.clear();
		if (tfBuscar.getText().isEmpty()) {
			Listas.listaAuxiliar.addAll(Listas.listaFavoritos);
		} else {
			for (Personaje p : Listas.listaFavoritos) {
				if (p.getName().toLowerCase().contains(tfBuscar.getText().toLowerCase())) {
					Listas.listaAuxiliar.add(p);
				}
			}
		}
		pagina = 0;
		imprimirPersonajesFav();
	}

	@FXML
	void quitarFavorito1(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5);
	}

	@FXML
	void quitarFavorito2(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5 + 1);
	}

	@FXML
	void quitarFavorito3(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5 + 2);
	}

	@FXML
	void quitarFavorito4(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5 + 3);
	}

	@FXML
	void quitarFavorito5(ActionEvent event) {
		quitarFavoritoGeneral(pagina * 5 + 4);
	}

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

	void quitarFavoritoGeneral(int num) {
		int id = Listas.listaAuxiliar.get(num).getId();
		for (Personaje p : Listas.listaFavoritos) {
			if (p.getId() == id) {
				Listas.listaFavoritos.remove(p);
				break;
			}
		}
		for (Personaje p : Listas.listaAuxiliar) {
			if (p.getId() == id) {
				Listas.listaAuxiliar.remove(p);
				break;
			}
		}

//		if (Listas.listaFavoritos.isEmpty()) {
//			
//		} else {
//			
//		}

		imprimirPersonajesFav();
	}

}
