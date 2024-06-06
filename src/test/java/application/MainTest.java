package application;

import java.io.IOException;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import dao.UsuarioDaoImpl;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import models.Usuario;
import utils.HibernateUtil;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

@ExtendWith(ApplicationExtension.class)
class MainTest {

	@Start
	public void start(Stage stage) {

		// Inicia el programa abriendo la ventana de Login
		try {
			Parent mainNode;
			try {
				mainNode = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
				stage.setScene(new Scene(mainNode));
				stage.show();
				stage.toFront();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeAll
	public static void setUpAll() throws Exception {
		Session session = HibernateUtil.getSession();
		UsuarioDaoImpl usuDao = new UsuarioDaoImpl(session);
		usuDao.delete(new Usuario("test", "test"));
	}

	@AfterAll
	public static void tearDownAll() throws Exception {
		Session session = HibernateUtil.getSession();
		UsuarioDaoImpl usuDao = new UsuarioDaoImpl(session);
		usuDao.delete(new Usuario("test", "test"));
	}
	
	@BeforeEach
	public void setUp() throws Exception {

	}

	@AfterEach
	public void tearDown() throws Exception {
		FxToolkit.hideStage();
		FxRobot fxRobot = new FxRobot();
		fxRobot.release(new KeyCode[] {});
		fxRobot.release(new MouseButton[] {});
	}

	@Test
	public void testLoginVacio() {
		FxRobot fxRobot = new FxRobot();
		
		fxRobot.clickOn("#btnLogin");

		FxAssert.verifyThat("#CeldasVacias", NodeMatchers.isVisible());
	}

	@Test
	public void testLoginIncorrecto() {
		FxRobot fxRobot = new FxRobot();
		
		fxRobot.clickOn("#tfUsuario");
		fxRobot.write("Error");
		fxRobot.clickOn("#pfPassword");
		fxRobot.write("Error");
		fxRobot.clickOn("#btnLogin");

		FxAssert.verifyThat("#DatosIncorrectos", NodeMatchers.isVisible());
	}

	@Test
	public void testRegistroVacio() {
		FxRobot fxRobot = new FxRobot();
		
		fxRobot.clickOn("#lblRegistrarse");
		fxRobot.clickOn("#btnRegistrar");

		FxAssert.verifyThat("#CeldasVacias", NodeMatchers.isVisible());
	}

	@Test
	public void testRegistroPasswords() {
		FxRobot fxRobot = new FxRobot();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fxRobot.clickOn("#lblRegistrarse");
		fxRobot.clickOn("#tfUsuario");
		fxRobot.write("Hola");
		fxRobot.clickOn("#pfPassword");
		fxRobot.write("Hola");
		fxRobot.clickOn("#pfRepetirPassword");
		fxRobot.write("Adios");
		fxRobot.clickOn("#btnRegistrar");

		FxAssert.verifyThat("#PasswordsIncorrectas", NodeMatchers.isVisible());
	}

	@Test
	public void testRegistroCoincide(){
		FxRobot fxRobot = new FxRobot();
		
		fxRobot.clickOn("#lblRegistrarse");
		fxRobot.clickOn("#tfUsuario");
		fxRobot.write("a");
		fxRobot.clickOn("#pfPassword");
		fxRobot.write("a");
		fxRobot.clickOn("#pfRepetirPassword");
		fxRobot.write("a");
		fxRobot.clickOn("#btnRegistrar");

		FxAssert.verifyThat("#UsuarioExistente", NodeMatchers.isVisible());
	}

	@Test
	public void testRegistroCorrecto(){
		FxRobot fxRobot = new FxRobot();
		
		fxRobot.clickOn("#lblRegistrarse");
		fxRobot.clickOn("#tfUsuario");
		fxRobot.write("test");
		fxRobot.clickOn("#pfPassword");
		fxRobot.write("test");
		fxRobot.clickOn("#pfRepetirPassword");
		fxRobot.write("test");
		fxRobot.clickOn("#btnRegistrar");

		FxAssert.verifyThat("#RegistroCorrecto", NodeMatchers.isVisible());
		
		
	}

	@Test
	public void testLoginCorrecto() {
		FxRobot fxRobot = new FxRobot();
		
		fxRobot.clickOn("#tfUsuario");
		fxRobot.write("a");
		fxRobot.clickOn("#pfPassword");
		fxRobot.write("a");
		fxRobot.clickOn("#btnLogin");

		FxAssert.verifyThat("#lblTitulo1", LabeledMatchers.hasText("Todos los personajes de"));
	}

}
