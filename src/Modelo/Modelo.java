package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import Controlador.Controlador;
import Vista.*;

/**
 * Modelo class that manages database connections and operations.
 * 
 * @author Kiss, Henry, Nacho, Mario
 */
public class Modelo {
	private Vista[] misVistas; // Array of views

	private Connection conexion; // Database connection
	private Properties datos;
	private InputStream entrada;
	private OutputStream salida;
	private File miFichero;
	private final String FILE = "Datos.txt";
	private final String BBDD = "Conexion_BBDD.txt";
	private Properties prop = new Properties();

	private Controlador miControlador;

	/**
	 * Sets the views for the model.
	 * 
	 * @param misVistas the array of views to be set
	 */
	public void setControlador(Vista[] misVistas) {
		this.misVistas = misVistas;
	}
	
	public String getUrl() {
		try (FileInputStream input = new FileInputStream(BBDD)) {
            prop.load(input);
            return prop.getProperty("url");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return null;
	}
	
	public String getLogin() {
		try (FileInputStream input = new FileInputStream(BBDD)) {
            prop.load(input);
            return prop.getProperty("login");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return null;
	}
	
	public String getPwd() {
		try (FileInputStream input = new FileInputStream(BBDD)) {
            prop.load(input);
            return prop.getProperty("pwd");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return null;
	}

	/**
	 * Constructor of the Modelo class that establishes the connection to the
	 * database.
	 */
	public Modelo() {
		try {
			conexion = DriverManager.getConnection(getUrl(), getLogin(), getPwd());
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
		
		datos = new Properties();
		try {
			miFichero = new File(FILE);
			if (miFichero.exists()) {
				entrada = new FileInputStream(miFichero);
				datos.load(entrada);
			} else {
				System.err.println("Fichero no encontrado");
				System.exit(1);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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
				return true;
			} else {
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


	
	
	
	public boolean comprobarUsuarioRegistro(String nick) {
		String query = "SELECT * FROM `usuario` WHERE nick = ?;";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nick);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				throw new SQLException("Usuario ya registrado.");
			} else {
				return true;
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
	public boolean establecerPwd(String nick, String pwd) {
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

	public boolean comprobarUsuarioAdmin(String nick) {
		String query = "SELECT * FROM `usuario` WHERE nick = ?;";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nick);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("admin").equalsIgnoreCase("si")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean comprobarPwdAdmin(String pwd) {
		String query = "SELECT * FROM `configuracion` WHERE valor = ?;";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, pwd);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				throw new SQLException("Los datos no coinciden.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean actualizarDatosUsuario(String nick, String nombre, String apellidos, int cp, String pass,
			int pregunta, String respuesta) {
		String query = "{CALL modificarUsuario(?, ?, ?, ?, ?, ?, ?)}";
		try {
			CallableStatement ctmt = conexion.prepareCall(query);
			ctmt.setString(1, nick);
			ctmt.setString(2, nombre);
			ctmt.setString(3, apellidos);
			ctmt.setString(4, pass);
			ctmt.setInt(5, cp);
			ctmt.setInt(6, pregunta);
			ctmt.setString(7, respuesta);

			ctmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public int ultimoCodigo() {
		String query = "SELECT * FROM `denuncia` ORDER BY codigo DESC LIMIT 1;";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String valorColumna = rs.getString("codigo");
				return Integer.parseInt(valorColumna);
			} else {
				throw new SQLException("Error.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean agregarDenuncia(String direccion, int codigo, String img,
			String nick, int categoria, int cp, String descripcion) {
		String query = "{CALL agregarDenuncia(?, ?, ?, ?, ?, ?, ?)}";
		try {
			CallableStatement ctmt = conexion.prepareCall(query);
			ctmt.setString(1, direccion);
			ctmt.setInt(2, codigo);
			ctmt.setString(3, img);
			ctmt.setString(4, nick);
			ctmt.setInt(5, categoria);
			ctmt.setInt(6, cp);
			ctmt.setString(7, descripcion);
			boolean filasModificadas = ctmt.execute();
			if (filasModificadas) {
				System.out.println("Insertado correctamente");
				return true;
			} else {
				throw new SQLException("Error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void guardarUsuario(String nick) {
		try {
			datos.setProperty("User", nick);
			salida = new FileOutputStream(miFichero);
			datos.store(salida, "El nickname se ha guardado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean comprobarSimulitud(int cp, int categoria) {
		String query = "SELECT * FROM `denuncia` WHERE cp = ? AND categoria_codigo = ?;";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setInt(1, cp);
			pstmt.setInt(2, categoria);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				throw new SQLException("Error.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void guardarCp(String cp) {
		try {
			datos.setProperty("Cp", cp);
			salida = new FileOutputStream(miFichero);
			datos.store(salida, "El codigo postal se ha guardado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
