package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Controlador.Controlador;
import Vista.*;

/**
 * Modelo class that manages database connections and operations.
 * 
 * @author Kiss, Henry, Nacho, Mario
 */
public class Modelo {
	private Vista[] misVistas; // Array of views

	private String bd; // Database name
	private String login; // Database login
	private String pwd; // Database password
	private String url; // Database URL
	private Connection conexion; // Database connection

	private Controlador miControlador;

	/**
	 * Sets the views for the model.
	 * 
	 * @param misVistas the array of views to be set
	 */
	public void setControlador(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

	/**
	 * Constructor of the Modelo class that establishes the connection to the
	 * database.
	 */
	public Modelo() {
		conexionSQL();
		try {
			conexion = DriverManager.getConnection(url, login, pwd);
			if (conexion != null) {
				System.out.println("¡Conexión exitosa a la base de datos!");
			}
		} catch (SQLException e) {
			System.err.println("Error BD: " + e.getMessage());
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Error General: " + e.getMessage());
			System.exit(2);
		}
		// agregarUsuario("jua123123", "juan1234", "gonzalez", "1234", 12345, "Si", 1);
	}

	/**
	 * Sets the database connection parameters.
	 */
	public void conexionSQL() {
		url = "jdbc:mysql://52.47.208.71/ruta_sin_baches";
		login = "root";
		pwd = "root.!";
	}

	/**
	 * Adds a user to the database.
	 * 
	 * @param nick           the user's nickname
	 * @param nombre         the user's first name
	 * @param apellido       the user's last name
	 * @param pwd            the user's password
	 * @param cp             the user's postal code
	 * @param admin          the user's admin status
	 * @param preguntaCodigo the user's security question code
	 * @param respuesta      the user's security question answer
	 * @return true if the user was added successfully, false otherwise
	 */
	public boolean agregarUsuario(String nick, String nombre, String apellido, String pwd, int cp, String admin,
			int preguntaCodigo, String respuesta) {

		String query = "{CALL agregarUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		int rs = 0;
		try {
			CallableStatement ctmt = conexion.prepareCall(query);
			ctmt.setString(1, nick);
			ctmt.setString(2, nombre);
			ctmt.setString(3, apellido);
			ctmt.setString(4, pwd);
			ctmt.setInt(5, cp);
			ctmt.setString(6, admin);
			ctmt.setInt(7, preguntaCodigo + 1);
			ctmt.setString(8, respuesta);
			ctmt.registerOutParameter(9, java.sql.Types.INTEGER);
			ctmt.execute();
			rs = ctmt.getInt(9);
			if (rs == 1) {
				System.out.println("Usuario registrado con éxito.");
				return true;
			} else {
				System.out.println("Error al registrar usuario.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if a user exists in the database with the provided credentials.
	 * 
	 * @param nick the user's nickname
	 * @param pwd  the user's password
	 * @return true if the user exists and the password is correct, false otherwise
	 */
	public String comprobarUsuario(String nick, String pwd) {
		String query = "SELECT * FROM `usuario` WHERE nick = ? AND pwd = ?;";
		String queryAdmin = "SELECT * FROM `usuario` WHERE nick = ?;";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nick);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();

			PreparedStatement pstmtAd = conexion.prepareStatement(queryAdmin);
			pstmtAd.setString(1, nick);
			ResultSet rsAd = pstmtAd.executeQuery();
			if (rs.next()) {
				System.out.println("Usuario ya registrado.");
				if (rsAd.next()) {
					if (rsAd.getString("admin").equalsIgnoreCase("si")) {
						return "trueAdm";
					} else {
						return "trueUsr";
					}
				}
				return "falso";
			} else {
				return "falso";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "falso";
		}
	}

	private int fallos = 0;

	/**
	 * Increments the number of failed login attempts and exits the program if there
	 * are too many.
	 */
	public void incrementarFallos() {
		fallos++;
		if (fallos >= 3) {
			System.out.println("Demasiados intentos fallidos.");
			System.exit(0);
		}
	}

	/**
	 * Checks if a user exists in the database with the provided nickname.
	 * 
	 * @param nick the user's nickname
	 * @return true if the user exists, false otherwise
	 */
	public boolean comprobarUsuarioRecuperar(String nick) {
		String queryAdmin = "SELECT * FROM `usuario` WHERE nick = ?;";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(queryAdmin);
			pstmt.setString(1, nick);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("Admin.");
				return true;
			} else {
				System.out.println("Usuario.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if the provided security answer is correct for the given user and
	 * question.
	 * 
	 * @param respuesta the user's security answer
	 * @param nick      the user's nickname
	 * @param pregunta  the security question code
	 * @return true if the answer is correct, false otherwise
	 */
	public boolean comprobarRespuesta(String respuesta, String nick, int pregunta) {
		String query = "SELECT * FROM `usuario` WHERE respuesta_seguridad = ? && nick = ? && pregunta_codigo = ?;";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, respuesta);
			pstmt.setString(2, nick);
			pstmt.setInt(3, pregunta);
			ResultSet rs = pstmt.executeQuery();

			// Verify if the ResultSet has any results
			if (rs.next()) {
				System.out.println("Respuesta correcta.");
				return true;
			} else {
				System.out.println("Respuesta incorrecta o pregunta incorrecta.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates the user's password in the database.
	 * 
	 * @param pwd  the new password
	 * @param nick the user's nickname
	 * @return true if the password was updated successfully, false otherwise
	 */
	public boolean establecerPwd(String pwd, String nick) {
		String query = "UPDATE usuario SET pwd = ? WHERE nick = ?;";
		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, pwd);
			pstmt.setString(2, nick);

			int rs = pstmt.executeUpdate();

			// Verify if the number of updated rows is greater than zero
			if (rs > 0) {
				System.out.println("Contraseña cambiada.");
				return true;
			} else {
				System.out.println("No se ha cambiado la contraseña.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
