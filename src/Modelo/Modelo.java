package Modelo;

import java.io.File;
import java.sql.Blob;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

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

	private String nick;
	private String nombre;
	private String apellido;
	private int cp;
	private String pass;
	private int pregunta;
	private String respuesta;

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

	public void mostrarDatosUsuario(String nick) {
		String query = "SELECT * FROM `usuario` WHERE nick = ?;";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nick);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				this.nick = rs.getString("nick");
				this.nombre = rs.getString("nombre");
				this.apellido = rs.getString("apellido");
				this.cp = rs.getInt("cp");
				this.pass = rs.getString("pwd");
				this.pregunta = rs.getInt("pregunta_codigo");
				this.respuesta = rs.getString("respuesta_seguridad");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean actualizarDatosUsuario(String nick, String nombre, String apellido, int cp, String pass,
			int pregunta, String respuesta) {
		String query = "{CALL modificarUsuario(?, ?, ?, ?, ?, ?, ?)}";
		try {
			CallableStatement ctmt = conexion.prepareCall(query);
			ctmt.setString(1, nick);
			ctmt.setString(2, nombre);
			ctmt.setString(3, apellido);
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

	// Getters para acceder a los datos del usuario
	public String getNick() {
		return nick;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getCp() {
		return cp;
	}

	public String getPass() {
		return pass;
	}

	public int getPregunta() {
		return pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public List<Object[]> establecerTablas(String usuario, int fav) {
		List<Object[]> tabla = new ArrayList<Object[]>();
		String query = null;
		switch (fav) {
		case 1:
			query = "SELECT \r\n" + "    d.codigo, \r\n" + "    d.usuario_nick, \r\n" + "    d.estado, \r\n"
					+ "    CONCAT(d.direccion, ' ', d.cp) AS direccion_cp, \r\n" + "    d.fecha, \r\n"
					+ "    c.nombre AS categoria_nombre, \r\n" + "    d.descripcion, \r\n" + "    v.favorito\r\n"
					+ "FROM \r\n" + "    denuncia d\r\n" + "JOIN \r\n"
					+ "    votar v ON d.codigo = v.denuncia_codigo\r\n" + "JOIN \r\n"
					+ "    categoria c ON d.categoria_codigo = c.codigo \r\n" + "WHERE \r\n"
					+ "    v.usuario_nick = ? AND v.favorito = 'Y'\r\n" + "ORDER BY \r\n" + "    d.fecha DESC;\r\n";
			break;
		case 2:
			query = "SELECT \r\n" + "    d.codigo, \r\n" + "    d.usuario_nick, \r\n" + "    d.estado, \r\n"
					+ "    CONCAT(d.direccion, ' ', d.cp) AS direccion_cp, \r\n" + "    d.fecha, \r\n"
					+ "    c.nombre AS categoria_nombre, \r\n" + "    d.descripcion, \r\n"
					+ "    COALESCE((SELECT v.favorito FROM votar v WHERE v.denuncia_codigo = d.codigo AND v.usuario_nick = ? AND v.favorito = 'Y'), 'N') AS favorito\r\n"
					+ "FROM \r\n" + "    denuncia d \r\n" + "JOIN \r\n"
					+ "    categoria c ON d.categoria_codigo = c.codigo \r\n" + "ORDER BY \r\n"
					+ "    d.fecha DESC;\r\n";
			break;
		case 3:
			query = "SELECT \r\n" + "    d.codigo, \r\n" + "    d.usuario_nick, \r\n" + "    d.estado, \r\n"
					+ "    CONCAT(d.direccion, ' ', d.cp) AS direccion_cp, \r\n" + "    d.fecha, \r\n"
					+ "    c.nombre AS categoria_nombre, \r\n" + "    d.descripcion, \r\n" + "    COALESCE(\r\n"
					+ "        (SELECT v.favorito FROM votar v WHERE v.usuario_nick = ? AND d.codigo = v.denuncia_codigo), \r\n"
					+ "        'N'\r\n" + "    ) AS favorito\r\n" + "FROM \r\n" + "    denuncia d\r\n" + "JOIN \r\n"
					+ "    categoria c ON d.categoria_codigo = c.codigo \r\n" + "WHERE \r\n"
					+ "    d.usuario_nick = ? \r\n" + "ORDER BY \r\n" + "    d.fecha DESC;";
			break;
		case 4:
			query = "SELECT \r\n" + "    d.codigo, \r\n" + "    d.usuario_nick, \r\n" + "    d.estado, \r\n"
					+ "    CONCAT(d.direccion, ' ', d.cp) AS direccion_cp, \r\n" + "    d.fecha, \r\n"
					+ "    c.nombre AS categoria_nombre, \r\n" + "    d.descripcion, \r\n"
					+ "    COALESCE((SELECT v.favorito FROM votar v WHERE v.denuncia_codigo = d.codigo AND v.usuario_nick = ? AND v.favorito = 'Y'), 'N') AS favorito\r\n"
					+ "FROM \r\n" + "    denuncia d \r\n" + "JOIN \r\n"
					+ "    categoria c ON d.categoria_codigo = c.codigo \r\n" + "ORDER BY \r\n"
					+ "    d.codigo DESC;\r\n";
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + fav);
		}
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			switch (fav) {
			case 1:
				pstmt.setString(1, usuario);
				break;
			case 2:
				pstmt.setString(1, usuario);
				break;
			case 3:
				pstmt.setString(1, usuario);
				pstmt.setString(2, usuario);
				break;
			case 4:
				pstmt.setString(1, usuario);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + fav);
			}
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int codigo = resultSet.getInt("codigo");
				String usr = resultSet.getString("usuario_nick");
				String estado = resultSet.getString("estado");
				String direccionCp = resultSet.getString("direccion_cp");
				Date fecha = resultSet.getDate("fecha");
				String categoriaNombre = resultSet.getString("categoria_nombre");
				String descripcion = resultSet.getString("descripcion");
				String favorito = resultSet.getString("favorito");
				tabla.add(new Object[] { codigo, usr, estado, direccionCp, fecha, categoriaNombre, descripcion,
						favorito });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tabla;
	};

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

	public boolean agregarDenuncia(String direccion, int codigo, String img, String nick, int categoria, int cp,
			String descripcion) {
		String query = "{CALL agregarDenuncia(?, ?, ?, ?, ?, ?, ?, ?)}";
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

	public void actualizarFotoPerfil(File ficheroActual, String nick2) {
		ImageIcon imageIcon = new ImageIcon(ficheroActual.getAbsolutePath());
		((_10_InfoPersonal) misVistas[10]).actualizarFotoPerfil(imageIcon);
	}
	
	public void actualizarFotoDenuncia(File ficheroActual) {
		ImageIcon imageIcon = new ImageIcon(ficheroActual.getAbsolutePath());
		((_08_PublicarDenuncia) misVistas[8]).actualizarFotoPerfil(imageIcon);
	}

	public void actualizarFotoPerfilBD(byte[] imageBytes, String nick) {
		String sql = "UPDATE usuario SET foto = ? WHERE nick = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			pstmt.setBytes(1, imageBytes);
			pstmt.setString(2, nick);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarFotoDenunciaBD(byte[] imageBytes, String codigo) {
		String sql = "UPDATE denuncia SET imagen = ? WHERE codigo = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			pstmt.setBytes(1, imageBytes);
			pstmt.setString(2, codigo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public byte[] getImagen(String nick) {
		byte[] imageData = null;
		try {
			String sql = "SELECT foto FROM usuario WHERE nick = ?";
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, nick);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				// Obtener los datos de la imagen
				Blob blob = resultSet.getBlob("foto");
				if (blob != null) {
					imageData = blob.getBytes(1, (int) blob.length());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Manejar la excepción apropiadamente
		}
		return imageData;
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
