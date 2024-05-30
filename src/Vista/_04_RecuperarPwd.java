package Vista;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;
import Modelo.Modelo;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class represents a GUI for a password recovery form. It extends `JFrame`
 * and implements the `Vista` interface. It contains various Swing components
 * like `JLabel`, `JTextField`, `JButton`, and `JComboBox`.
 *
 * @author Nacho
 */
public class _04_RecuperarPwd extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo, lblFondo, lblRSB, lblVacio;
	private JTextField txtUsuario, txtRespuesta;
	private Controlador miControlador;
	private Modelo miModelo;
	private JButton btnAtrs, btnSiguiente;
	private JComboBox comboBox;
	private boolean esUsuarioClicado, esRespuestaClicado;

	/**
	 * Constructor for the _04_RecuperarPwd class. Initializes the form and its
	 * components.
	 */
	public _04_RecuperarPwd() {
		setIconImage(new ImageIcon(getClass().getResource("img/Logo_peque.png")).getImage());
		setTitle("Recuperar Contraseña");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 200, 1090, 710);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		lblLogo = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("img/logo_peque.png"));
		contentPane.setLayout(null);
		lblLogo.setBounds(171, 250, 158, 147);
		contentPane.add(lblLogo);
		ImageIcon imageIcon1 = new ImageIcon(
				imageIcon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
		lblLogo.setIcon(imageIcon1);

		lblFondo = new JLabel("");
		ImageIcon imageFondo = new ImageIcon(getClass().getResource("img/fondooo.png"));
		lblFondo.setBounds(0, 0, 507, 671);
		contentPane.add(lblFondo);
		ImageIcon sizeFondo = new ImageIcon(
				imageFondo.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH));
		lblFondo.setIcon(sizeFondo);

		lblRSB = new JLabel("");
		ImageIcon imageRSB = new ImageIcon(getClass().getResource("img/LogoGrande.png"));
		lblRSB.setBounds(677, 117, 222, 51);
		contentPane.add(lblRSB);
		ImageIcon sizeRSB = new ImageIcon(
				imageRSB.getImage().getScaledInstance(lblRSB.getWidth(), lblRSB.getHeight(), Image.SCALE_SMOOTH));
		lblRSB.setIcon(sizeRSB);

		/**
		 * This event handler is triggered when the user press a key on the `txtUsuario`
		 * field. It clears the text in the field if it is the default text.
		 * 
		 * @param e The key event
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
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsuario.setText("Usuario");
		txtUsuario.setBounds(685, 249, 222, 31);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(685, 220, 58, 18);
		contentPane.add(lblUsuario);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "¿Cuál es tu nombre de pila?",
				"¿Cuál es tu primera escuela?", "¿Cuál es tu primera mascota?" }));
		comboBox.setBounds(685, 383, 222, 31);
		contentPane.add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("Selecciona la pregunta de seguridad:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(677, 358, 252, 14);
		contentPane.add(lblNewLabel_2);

		/**
		 * This event handler is triggered when the user press a key on the
		 * `txtRespuesta` field. It clears the text in the field if it is the default
		 * text.
		 * 
		 * @param e The key event
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
		txtRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRespuesta.setText("Respuesta");
		txtRespuesta.setBounds(685, 432, 222, 31);
		contentPane.add(txtRespuesta);
		txtRespuesta.setColumns(10);

		/**
		 * This event handler is triggered when the user clicks the `btnSiguiente`
		 * button. It calls the `camposVacios` method.
		 * 
		 * @param e The action event
		 */
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camposVacios();
			}
		});
		btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSiguiente.setBounds(685, 508, 151, 35);
		contentPane.add(btnSiguiente);

		lblVacio = new JLabel("");
		lblVacio.setForeground(Color.RED);
		lblVacio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVacio.setBounds(687, 186, 158, 23);
		contentPane.add(lblVacio);

		/**
		 * This event handler is triggered when the user clicks the `btnAtrs` button. It
		 * changes the screen to the login screen.
		 * 
		 * @param e The action event
		 */
		btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarPantalla(4, 0);
			}
		});
		btnAtrs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtrs.setBounds(561, 507, 67, 40);
		contentPane.add(btnAtrs);
	}

	/**
	 * Overrides the `setVisible` method from `JFrame` to control the visibility of
	 * the frame.
	 * 
	 * @param b A boolean that determines whether the frame should be visible or
	 *          not.
	 */
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
	}

	/**
	 * Returns the text entered in the `txtUsuario` field.
	 * 
	 * @return A String containing the text entered in the `txtUsuario` field.
	 */
	public String getUsuario() {
		return txtUsuario.getText();
	}

	/**
	 * Returns the selected index of the `comboBox` plus one.
	 * 
	 * @return An integer representing the selected index of the `comboBox` plus
	 *         one.
	 */
	private int getComboBox() {
		return comboBox.getSelectedIndex() + 1;
	}

	/**
	 * Checks if all the required fields are filled. If not, it displays an error
	 * message. If all fields are filled, it checks if the user exists and if the
	 * answer to the security question is correct. If everything is correct, it sets
	 * the user for password recovery and changes the screen. If not, it displays an
	 * error message.
	 */
	public void camposVacios() {
		String usuario = txtUsuario.getText().trim();
		String respuesta = txtRespuesta.getText().trim();
		if (usuario.isEmpty() || respuesta.isEmpty() || usuario.equals("Usuario") || respuesta.equals("Respuesta")) {
			// Mostrar un mensaje de error al usuario
			lblVacio.setText("Rellene todos los campos");
		} else {
				miControlador.comprobarUsuarioRecuperar();
				miControlador.setNick(usuario);
				miControlador.cambiarPantalla(4, 5);
				lblVacio.setText("");
			}
		}

	

	/**
	 * Sets the Controlador for this class.
	 * 
	 * @param miControlador The Controlador to be set.
	 */
	@Override
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 * Sets the Modelo for this class.
	 * 
	 * @param miModelo The Modelo to be set.
	 */
	@Override
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public String getNick() {
		return txtUsuario.getText();
	}
	
	public int getPregunta() {
		return comboBox.getSelectedIndex() + 1;
	}
	
	public String getRespuesta() {
		return txtRespuesta.getText();
	}
	

}
