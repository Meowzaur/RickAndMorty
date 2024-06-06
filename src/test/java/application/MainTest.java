package application;

import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
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
				mainNode = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
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
	public void testRegistroCoincide() {
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
	public void testRegistroCorrecto() {
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

		// FxAssert.verifyThat("#DatosIncorrectos", NodeMatchers.isVisible());
	}

}
