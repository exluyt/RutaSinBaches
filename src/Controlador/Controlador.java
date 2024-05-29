package Controlador;

import javax.swing.JFrame;
import Modelo.*;
import Vista.*;

/**
 * Controlador class that manages the interaction between the model and views.
 * It handles screen changes and data transfer.
 * 
 * @author Kiss, Henry, Nacho, Mario
 */
public class Controlador extends JFrame {
	private Modelo miModelo; // Model reference
	private Vista[] misVistas; // Array of views
	private String[] datosRegistro = new String[3]; // Array to hold registration data
	private String nick; // Variable to hold the username for password recovery.

	/**
	 * Sets the model reference.
	 * 
	 * @param miModelo the model instance to be set
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	/**
	 * Sets the array of views.
	 * 
	 * @param misVistas the array of views to be set
	 */
	public void setVistas(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

	/**
	 * Changes the screen visibility.
	 * 
	 * @param cerrar the index of the view to be hidden
	 * @param abrir  the index of the view to be shown
	 */
	public void cambiarPantalla(int cerrar, int abrir) {
		misVistas[abrir].setVisible(true); // Make the new screen visible
		misVistas[cerrar].setVisible(false); // Hide the current screen
	}

	public void comprobarUsuario() {
		_00_Login loginView = (_00_Login) misVistas[0];
		String nick = loginView.getNick();
		String password = loginView.getPassword();
		miModelo.comprobarUsuario(nick, password);
	}

	/**
	 * Sets registration data and prints it for debugging.
	 * 
	 * @param txtNombre    the name to be set
	 * @param txtApellidos the last name to be set
	 * @param txtCp        the postal code to be set
	 */
	public void registro2Pantalla(String txtNombre, String txtApellidos, String txtCp) {
		datosRegistro[0] = txtNombre; // Store the name
		datosRegistro[1] = txtApellidos; // Store the last name
		datosRegistro[2] = txtCp; // Store the postal code

		// Print the registration data for debugging
		System.out.println(datosRegistro[0] + datosRegistro[1] + datosRegistro[2]);
	}

	/**
	 * Gets the registration data.
	 * 
	 * @return the array of registration data
	 */
	public String[] getDatosRegistro() {
		return datosRegistro;
	}

	/**
	 * Sets the username for password recovery.
	 * 
	 * @param nick the username to be set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Gets the username for password recovery.
	 * 
	 * @return the username for password recovery
	 */
	public String getNick() {
		return nick;
	}

	public boolean agregarUsuario() {
		_02_Registro2 registroView = (_02_Registro2) misVistas[0];
		String usuario = registroView.getUsuario();
		String nombre = datosRegistro[0];
		String apellidos = datosRegistro[1];
		String cp = datosRegistro[2];
		String pwd = registroView.getPwd();
		int pregunta = registroView.getPregunta();
		String admin = registroView.getAdmin();
		String respuesta = registroView.getRespuesta();
		miModelo.agregarUsuario(usuario, nombre, apellidos, pwd, pregunta, admin, pregunta, respuesta);
		return false;
	}
}
