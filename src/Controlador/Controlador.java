package Controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	private String nick; // Variable to hold the username for password recovery

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
	
	public boolean comprobarAdmin() {
		String nick = ((_00_Login) misVistas[0]).getNick();
		String password = ((_00_Login) misVistas[0]).getPassword();
		String resultado = miModelo.comprobarUsuario(nick, password);
		switch (resultado) {
		case "trueAdm":
			return true;
		case "trueUsr":
			return false;
		}
		return false;
	}

	public void comprobarUsuario() {
		String nick = ((_00_Login) misVistas[0]).getNick();
		this.nick = nick;
		String password = ((_00_Login) misVistas[0]).getPassword();
		String resultado = miModelo.comprobarUsuario(nick, password);
		switch (resultado) {
		case "trueAdm":
			cambiarPantalla(0, 7);
			break;
		case "trueUsr":
			cambiarPantalla(0, 6);
			break;
		case "falso":
			System.out.println("Usuario no encontrado o contraseña incorrecta.");
			miModelo.incrementarFallos();
			break;
		}

	}

	public void comprobarUsuarioPagina() {
		String nick = ((_00_Login) misVistas[0]).getNick();
		if (miModelo.comprobarUsuarioAdmin(nick)) {
			cambiarPantalla(8, 7);
		} else {
			cambiarPantalla(8, 6);
		}
	}

	public void comprobarUsuarioPaginaInfo() {
		String nick = ((_00_Login) misVistas[0]).getNick();
		if (miModelo.comprobarUsuarioAdmin(nick)) {
			cambiarPantalla(10, 7);
		} else {
			cambiarPantalla(10, 6);
		}
	}

	public boolean comprobarUsuarioRegistro() {
		String nick = ((_02_Registro2) misVistas[2]).getUsuario();
		if (miModelo.comprobarUsuarioRegistro(nick)) {
			cambiarPantalla(2, 0);
			return true;
		} else {
			System.out.println("Usuario ya registrado.");
			return false;
		}
	}

	public void comprobarUsuarioRecuperar() {
		String usuario = ((_04_RecuperarPwd) misVistas[4]).getNick();
		String respuesta = ((_04_RecuperarPwd) misVistas[4]).getRespuesta();
		int pregunta = ((_04_RecuperarPwd) misVistas[4]).getPregunta();
		if (miModelo.comprobarRespuesta(respuesta, usuario, pregunta)) {
			cambiarPantalla(4, 5);
		} else {
			System.out.println("Datos incorrectos");
		}
	}

	public void comprobarUsuarioRegistroAdmin() {
		String nick = ((_03_RegistroAdmin) misVistas[3]).getUsuario();
		if (miModelo.comprobarUsuarioRegistro(nick)) {
			agregarAdmin();
		} else {
			System.out.println("Usuario ya registrado.");
		}
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
		String usuario = ((_02_Registro2) misVistas[2]).getUsuario();
		String nombre = datosRegistro[0];
		String apellidos = datosRegistro[1];
		String cp = datosRegistro[2];
		String pwd = ((_02_Registro2) misVistas[2]).getPwd();
		int pregunta = ((_02_Registro2) misVistas[2]).getPregunta();
		String admin = ((_02_Registro2) misVistas[2]).getAdmin();
		String respuesta = ((_02_Registro2) misVistas[2]).getRespuesta();
		if (miModelo.agregarUsuario(usuario, nombre, apellidos, pwd, pregunta, admin, pregunta, respuesta)) {
			System.out.println("Usuario registrado con éxito.");
			cambiarPantalla(2, 0);
			return true;
		} else {
			System.out.println("Error al registrar usuario");
			return false;
		}
	}

	public boolean establecerPwd() {
		String pwd = ((_05_RecuperarPwd2) misVistas[5]).getPwd();
		String nick = ((_04_RecuperarPwd) misVistas[4]).getNick();
		if (miModelo.establecerPwd(nick, pwd)) {
			cambiarPantalla(5, 0);
			return true;
		} else {
			System.out.println("Error al cambiar la contraseña");
			return false;
		}
	}

	public void agregarAdmin() {
		String usuario = ((_03_RegistroAdmin) misVistas[3]).getUsuario();
		String nombre = datosRegistro[0];
		String apellidos = datosRegistro[1];
		String cp = datosRegistro[2];
		String pwd = ((_03_RegistroAdmin) misVistas[3]).getPwd();
		int pregunta = ((_03_RegistroAdmin) misVistas[3]).getPregunta();
		String admin = ((_03_RegistroAdmin) misVistas[3]).getAdmin();
		String respuesta = ((_03_RegistroAdmin) misVistas[3]).getRespuesta();
		String pwdAdmin = ((_03_RegistroAdmin) misVistas[3]).getPwdAdmin();
		if (miModelo.comprobarPwdAdmin(pwdAdmin)
				&& miModelo.agregarUsuario(usuario, nombre, apellidos, pwd, pregunta, admin, pregunta, respuesta)) {
			System.out.println("Admin registrado con éxito.");
			cambiarPantalla(3, 0);
		} else {
			((_03_RegistroAdmin) misVistas[3]).setError("Los datos son incorrectos");
			System.out.println("Error al registrarse");
		}
	}

	public void agregarPublicacion() {
	 int cp = Integer.parseInt(((_08_PublicarDenuncia) misVistas[8]).getCp());
	 String provincia = ((_08_PublicarDenuncia) misVistas[8]).getProvincia();
	 String ciudad = ((_08_PublicarDenuncia) misVistas[8]).getCiudad();
	 String calle = ((_08_PublicarDenuncia) misVistas[8]).getCalle();
	 String direccion = String.join(", ", calle, provincia, ciudad);
	 String descripcion = ((_08_PublicarDenuncia) misVistas[8]).getDescripcion();
	 int categoria = ((_08_PublicarDenuncia) misVistas[8]).getCategoria();
     int codigo = miModelo.ultimoCodigo() + 1;
     if(miModelo.agregarDenuncia(direccion, codigo, null, nick, categoria, cp, descripcion)) {
    	 if(comprobarAdmin()) {
    		 cambiarPantalla(8, 7);
    	 } else {
    		 cambiarPantalla(8, 6);
    	 }
     } else {
    	 ((_03_RegistroAdmin) misVistas[8]).setError("Datos incorrectos");
     }
	}
}
