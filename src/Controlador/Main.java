package Controlador;

import Vista.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import Design.MiTema;
import Modelo.*;

/**
 * @author Kiss, Henry, Nacho, Mario
 */
public class Main {
	/**
	 * Main method that initializes the application.
	 */
	public static void main(String[] args) {
		FlatMacLightLaf.registerCustomDefaultsSource("design.MiTema.properties");
		FlatMacLightLaf.setup();
		try {
			UIManager.setLookAndFeel(new MiTema());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Controlador miControlador = new Controlador();
		Modelo miModelo = new Modelo();
		Vista[] misVistas = new Vista[11];

		misVistas[0] = new _00_Login();
		misVistas[1] = new _01_Registro();
		misVistas[2] = new _02_Registro2();
		misVistas[3] = new _03_RegistroAdmin();
		misVistas[4] = new _04_RecuperarPwd();
		misVistas[5] = new _05_RecuperarPwd2();
		misVistas[6] = new _06_PaginaPrincipal();
		misVistas[7] = new _07_PaginaPrincipalAdmin();
		misVistas[8] = new _08_PublicarDenuncia();
		misVistas[9] = new _09_Estadisticas();
		misVistas[10] = new _10_InfoPersonal();

		miControlador.setModelo(miModelo);
		miControlador.setVistas(misVistas);

		miModelo.setControlador(misVistas);

		for (Vista vista : misVistas) {
			vista.setModelo(miModelo);
			vista.setControlador(miControlador);
		}

		misVistas[6].setVisible(true);
	}
}