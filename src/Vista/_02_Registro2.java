package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;
import Modelo.Modelo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Controlador.*;
import Modelo.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class represents the second Registration screen of the application. It
 * extends JFrame and implements the Vista interface.
 *
 * @author Mario Seoane, Arpad Kiss
 */

public class _02_Registro2 extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblInicioSesion2, lblInicioSesion1, lblUsuario, lblPwd, lblRepetirPwd, lblLogo, lblFondo,
			lblPregunta, lblRobot;
	private JTextField txtUsuario, txtPwd, txtRepetirPwd, txtRespuesta;
	private JButton btnRegistro;
	private JComboBox comboBoxPreguntas;
	private boolean esUsuarioClicado = false;
	private boolean esPwdClicado = false;
	private boolean esRepetirPwdClicado = false;
	private boolean esRespuestaClicado = false;

	private Controlador miControlador;
	private Modelo miModelo;
	private JLabel lblVacio;
	private JCheckBox chckbxRobot;
	private JLabel lblAsterisco;
	private JLabel lblVacio1;

	/**
	 * Constructor for the second Registration screen. Initializes the components
	 * and sets up the event handlers.
	 */
	public _02_Registro2() {
		setIconImage(new ImageIcon(getClass().getResource("img/Logo_peque.png")).getImage());
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 200, 1090, 710);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				lblVacio = new JLabel("");
				lblVacio.setForeground(Color.RED);
				lblVacio.setBounds(166, 116, 152, 23);
				contentPane.add(lblVacio);

		lblAsterisco = new JLabel("");
		lblAsterisco.setForeground(new Color(255, 0, 0));
		lblAsterisco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAsterisco.setBounds(185, 503, 46, 14);
		contentPane.add(lblAsterisco);

		lblInicioSesion1 = new JLabel("¿Ya tienes cuenta?");
		lblInicioSesion1.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblInicioSesion1.setBounds(166, 27, 137, 23);
		contentPane.add(lblInicioSesion1);

		/**
		 * Event handler for the "Inicia Sesión" label. Changes the screen to the Login
		 * screen. When the mouse enters the label area, the cursor changes to a hand.
		 * When the mouse exits the label area, the cursor changes to the default
		 * 
		 * @param e The mouse event.
		 */
		lblInicioSesion2 = new JLabel("Inicia Sesión");
		lblInicioSesion2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				miControlador.cambiarPantalla(2, 0);
				lblVacio.setText("");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblInicioSesion2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblInicioSesion2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblInicioSesion2.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblInicioSesion2.setForeground(Color.BLUE);
		lblInicioSesion2.setBounds(292, 27, 87, 23);
		contentPane.add(lblInicioSesion2);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(166, 134, 81, 23);
		contentPane.add(lblUsuario);

		lblPwd = new JLabel("Contraseña:");
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPwd.setBounds(166, 220, 97, 23);
		contentPane.add(lblPwd);

		/**
		 * Event handler for the "Usuario" text field. When the mouse clicks on the text
		 * field, the text is cleared. When the text field is selected, the text is
		 * cleared.
		 * 
		 * @param e The key event.
		 * @param e The mouse event.
		 */
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!esUsuarioClicado) {
					txtUsuario.setText("");
					esUsuarioClicado = true;
				}
			}
		});
		txtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!esUsuarioClicado) {
					txtUsuario.setText("");
					esUsuarioClicado = true;
				}
			}
		});
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsuario.setText("Usuario");
		txtUsuario.setBounds(166, 168, 198, 29);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		/**
		 * Event handler for the "Pwd" text field. When the mouse clicks on the text
		 * field, the text is cleared. When the text field is selected, the text is
		 * cleared.
		 * 
		 * @param e The key event.
		 * @param e The mouse event.
		 */
		txtPwd = new JTextField();
		txtPwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!esPwdClicado) {
					txtPwd.setText("");
					esPwdClicado = true;
				}
			}
		});
		txtPwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!esPwdClicado) {
					txtPwd.setText("");
					esPwdClicado = true;
				}
			}
		});
		txtPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPwd.setText("Contraseña");
		txtPwd.setColumns(10);
		txtPwd.setBounds(166, 252, 198, 29);
		contentPane.add(txtPwd);

		/**
		 * Event handler for the "Registrarse" button. Calls the "camposVacios" method.
		 * 
		 * @param e The action event.
		 */
		btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camposVacios();
			}
		});
		btnRegistro.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnRegistro.setBounds(166, 540, 147, 35);
		contentPane.add(btnRegistro);
		Font subrayadoInicio = lblInicioSesion2.getFont();
		Map<TextAttribute, Object> attributesInicio = (Map<TextAttribute, Object>) subrayadoInicio.getAttributes();
		attributesInicio.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblInicioSesion2.setFont(subrayadoInicio.deriveFont(attributesInicio));
		lblInicioSesion2.setForeground(Color.BLUE);

		/**
		 * Event handler for the "RepetirPwd" text field. When the mouse clicks on the
		 * text field, the text is cleared. When the text field is selected, the text is
		 * cleared.
		 * 
		 * @param e The mouse event.
		 * @param e The key event.
		 */
		txtRepetirPwd = new JTextField();
		txtRepetirPwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!esRepetirPwdClicado) {
					txtRepetirPwd.setText("");
					esRepetirPwdClicado = true;
				}
			}
		});
		txtRepetirPwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!esRepetirPwdClicado) {
					txtRepetirPwd.setText("");
					esRepetirPwdClicado = true;
				}
			}
		});
		txtRepetirPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRepetirPwd.setText("Repetir contraseña");
		txtRepetirPwd.setColumns(10);
		txtRepetirPwd.setBounds(166, 324, 198, 29);
		contentPane.add(txtRepetirPwd);

		lblRepetirPwd = new JLabel("Repetir contraseña:");
		lblRepetirPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRepetirPwd.setBounds(166, 290, 152, 23);
		contentPane.add(lblRepetirPwd);

		lblLogo = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("img/logo_peque.png"));
		lblLogo.setBounds(750, 276, 158, 147);
		contentPane.add(lblLogo);
		ImageIcon imageIcon1 = new ImageIcon(
				imageIcon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
		lblLogo.setIcon(imageIcon1);

		lblFondo = new JLabel("");
		ImageIcon imageIcoon = new ImageIcon(getClass().getResource("img/fondooo.png"));
		lblFondo.setBounds(567, 0, 507, 671);
		contentPane.add(lblFondo);
		ImageIcon imageIcoon1 = new ImageIcon(
				imageIcoon.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH));
		lblFondo.setIcon(imageIcoon1);

		lblPregunta = new JLabel("Selecciona la pregunta de seguridad:");
		lblPregunta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPregunta.setBounds(166, 377, 237, 23);
		contentPane.add(lblPregunta);

		/**
		 * Event handler for the "Respuesta" text field. When the mouse clicks on the
		 * text field, the text is cleared. When the text field is selected, the text is
		 * cleared.
		 * 
		 * @param e The key event.
		 * @param e The mouse event.
		 */
		txtRespuesta = new JTextField();
		txtRespuesta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!esRespuestaClicado) {
					txtRespuesta.setText("");
					esRespuestaClicado = true;
				}
			}
		});
		txtRespuesta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!esRespuestaClicado) {
					txtRespuesta.setText("");
					esRespuestaClicado = true;
				}
			}
		});
		txtRespuesta.setText("Respuesta");
		txtRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRespuesta.setColumns(10);
		txtRespuesta.setBounds(166, 450, 237, 29);
		contentPane.add(txtRespuesta);

		comboBoxPreguntas = new JComboBox();
		comboBoxPreguntas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxPreguntas.setModel(new DefaultComboBoxModel(new String[] { "¿Cuál es tu nombre de pila?",
				"¿Cuál es tu primera escuela?", "¿Cuál es tu primera mascota?" }));
		comboBoxPreguntas.setBounds(166, 411, 237, 28);
		contentPane.add(comboBoxPreguntas);

		lblRobot = new JLabel("");
		lblRobot.setBounds(153, 72, 222, 51);
		ImageIcon imageRSB = new ImageIcon(getClass().getResource("img/LogoGrande.png"));
		ImageIcon sizeRSB = new ImageIcon(
				imageRSB.getImage().getScaledInstance(lblRobot.getWidth(), lblRobot.getHeight(), Image.SCALE_SMOOTH));
		lblRobot.setIcon(sizeRSB);
		contentPane.add(lblRobot);

		/**
		 * Event handler for the "1/2" button. Change the screen to the first
		 * Registration screen. When clicked, the "camposVacios" method is called. When
		 * the mouse enters the button area, the cursor changes to a hand. When the
		 * mouse exits the button area, the cursor changes to the default.
		 * 
		 * @param e The action event.
		 * @param e The mouse event.
		 */
		JButton btn1de2 = new JButton("1/2");
		btn1de2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarPantalla(2, 1);
				lblVacio.setText("");
			}
		});
		btn1de2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				camposVacios();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn1de2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn1de2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btn1de2.setFont(new Font("Dialog", Font.PLAIN, 13));
		btn1de2.setBounds(54, 540, 55, 35);
		contentPane.add(btn1de2);

		chckbxRobot = new JCheckBox("  No soy un robot");
		chckbxRobot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxRobot.setBounds(166, 498, 137, 23);
		contentPane.add(chckbxRobot);

		lblRobot = new JLabel("");
		lblRobot.setBounds(309, 498, 30, 23);
		ImageIcon imageRobot = new ImageIcon(getClass().getResource("img/google.png"));
		ImageIcon sizeRobot = new ImageIcon(
				imageRobot.getImage().getScaledInstance(lblRobot.getWidth(), lblRobot.getHeight(), Image.SCALE_SMOOTH));
		lblRobot.setIcon(sizeRobot);
		contentPane.add(lblRobot);

		lblVacio1 = new JLabel("");
		lblVacio1.setForeground(Color.RED);
		lblVacio1.setBounds(166, 360, 198, 14);
		contentPane.add(lblVacio1);
	}

	/**
	 * Checks if the user, password, repeat password, and answer fields are empty.
	 * If they are, it displays an error message. If they are not, it checks if the
	 * user exists in the system.
	 */
	public void camposVacios() {
		String usuario = txtUsuario.getText().trim();
		String pwd = txtPwd.getText().trim();
		String repetirPwd = txtRepetirPwd.getText().trim();
		String respuesta = txtRespuesta.getText().trim();
		if (usuario.isEmpty() || pwd.isEmpty() || repetirPwd.isEmpty() || respuesta.isEmpty()
				|| usuario.equals("Usuario") || pwd.equals("Contraseña") || repetirPwd.equals("Repetir contraseña")
				|| respuesta.equals("Respuesta")) {
			lblVacio.setText("Rellena todos los campos");
		} else if (!chckbxRobot.isSelected()) {
			lblAsterisco.setText("*");
		} else {
			comprobarPwdIguales();
		}
	}

	/**
	 * Checks if the entered passwords match. If they do, it sends the registration
	 * data to the model. If they don't, it displays an error message.
	 */
	public void comprobarPwdIguales() {
		String[] datosRegistro = miControlador.getDatosRegistro();
		String nombre = datosRegistro[0];
		String apellidos = datosRegistro[1];
		String codigoPostal = datosRegistro[2];
		String pwd = txtPwd.getText().trim();
		String repetirPwd = txtRepetirPwd.getText().trim();
		String respuesta = txtRespuesta.getText().trim();
		String usuario = txtUsuario.getText().trim();
		String admin = "no";
		miControlador.getNick();
		int pregunta = comboBoxPreguntas.getSelectedIndex() + 1;
		if (pwd.equals(repetirPwd)) {
			if (!miControlador.comprobarUsuarioRegistro()) {
				lblVacio.setText("Error en el registro");
			}
		} else {
			lblVacio1.setText("Las contraseñas no coinciden");
		}
	}

	/**
	 * Sets the controller for this view.
	 * 
	 * @param miControlador The controller to be set.
	 */
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 * Sets the model for this view.
	 * 
	 * @param miModelo The model to be set.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public String getUsuario() {
		return txtUsuario.getText();
	}

	public String getPwd() {
		return txtUsuario.getText();
	}

	public int getPregunta() {
		return comboBoxPreguntas.getSelectedIndex();
	}

	public String getAdmin() {
		return "no";
	}

	public String getRespuesta() {
		return txtRespuesta.getText();
	}

	public void setlblVacio(String string) {
		lblVacio.setText(string);
	}

}
