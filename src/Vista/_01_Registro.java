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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.Color;
import java.awt.Cursor;

import Controlador.Controlador;
import Modelo.Modelo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class represents the Registration screen of the application. It extends
 * JFrame and implements the Vista interface.
 *
 * @author Mario Seoane, Arpad Kiss
 */

public class _01_Registro extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblInicioSesion2, lblInicioSesion1, lblNombre, lblApellidos, lblCdigoPostal, lblLogo, lblFondo,
			lblRSB;
	private JTextField txtNombre;

	private JTextField txtApellidos;
	private JTextField txtCodigoPostal;
	private JCheckBox chckbxTerminos, chckbxNotificaciones, chckbxAdmin, chckbxSoyMayorDe;
	private JButton btn1de2;
	private Controlador miControlador;
	private Modelo miModelo;
	private boolean esNombreClicado = false;
	private boolean esApellidosClicado = false;
	private boolean esCodigoPostalClicado = false;
	private JLabel lblVacio;
	private JLabel lblAsteriscoAcepto;
	private JLabel lblAsteriscoMayor;

	/**
	 * Constructor for the Registration screen. Initializes the components and sets
	 * up the event handlers.
	 */
	public _01_Registro() {
		setIconImage(new ImageIcon(getClass().getResource("img/Logo_peque.png")).getImage());
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 200, 1090, 710);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAsteriscoMayor = new JLabel("");
		lblAsteriscoMayor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAsteriscoMayor.setForeground(new Color(255, 0, 0));
		lblAsteriscoMayor.setBounds(185, 464, 46, 14);
		contentPane.add(lblAsteriscoMayor);

		lblAsteriscoAcepto = new JLabel("");
		lblAsteriscoAcepto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAsteriscoAcepto.setForeground(new Color(255, 0, 0));
		lblAsteriscoAcepto.setBounds(185, 413, 46, 14);
		contentPane.add(lblAsteriscoAcepto);

		lblInicioSesion1 = new JLabel("¿Ya tienes cuenta?");
		lblInicioSesion1.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblInicioSesion1.setBounds(166, 27, 137, 23);
		contentPane.add(lblInicioSesion1);

		/**
		 * Event handler for the "Inicia Sesión" label. When clicked, it changes the
		 * screen to the Login screen. When the mouse enters the label, it changes to a
		 * hand cursor. When the mouse exits the label, it changes back to the default
		 * cursor.
		 * 
		 * @param e The mouse event.
		 */
		lblInicioSesion2 = new JLabel("Inicia Sesión");
		lblInicioSesion2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				miControlador.cambiarPantalla(1, 0);
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
		Font subrayadoInicio = lblInicioSesion2.getFont();
		Map<TextAttribute, Object> attributesInicio = (Map<TextAttribute, Object>) subrayadoInicio.getAttributes();
		attributesInicio.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblInicioSesion2.setFont(subrayadoInicio.deriveFont(attributesInicio));
		lblInicioSesion2.setForeground(Color.BLUE);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(166, 134, 81, 23);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellidos.setBounds(166, 220, 65, 23);
		contentPane.add(lblApellidos);

		/**
		 * Event handler for the name field. When clicked or press a key, it clears the
		 * field.
		 * 
		 * @param e The Key event.
		 * @param e The Mouse event.
		 */
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!esNombreClicado) {
					txtNombre.setText("");
					esNombreClicado = true;
				}
			}
		});

		txtNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!esNombreClicado) {
					txtNombre.setText("");
					esNombreClicado = true;
				}
			}
		});
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNombre.setText("Nombre");
		txtNombre.setBounds(166, 168, 198, 29);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		/**
		 * Event handler for the surname field. When clicked or press a key, it clears
		 * the field.
		 * 
		 * @param e The Key event.
		 * @param e The Mouse event.
		 */
		txtApellidos = new JTextField();
		txtApellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!esApellidosClicado) {
					txtApellidos.setText("");
					esApellidosClicado = true;
				}
			}
		});
		txtApellidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!esApellidosClicado) {
					txtApellidos.setText("");
					esApellidosClicado = true;
				}
			}
		});
		txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtApellidos.setText("Apellidos");
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(166, 252, 198, 29);
		contentPane.add(txtApellidos);

		chckbxTerminos = new JCheckBox("  Acepto los Términos y Condiciones");
		chckbxTerminos.setFont(new Font("Dialog", Font.PLAIN, 13));
		chckbxTerminos.setBounds(166, 408, 239, 23);
		contentPane.add(chckbxTerminos);

		/**
		 * Event handler for the "1/2" button. When clicked, it call the camposVacios()
		 * method. When the mouse enters the button, it changes to a hand cursor. When
		 * the mouse exits the button, it changes back to the default cursor.
		 * 
		 * @param e The Action event.
		 * @param e The Mouse event.
		 */
		btn1de2 = new JButton("1/2");
		btn1de2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camposVacios();
			}
		});
		btn1de2.addMouseListener(new MouseAdapter() {
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
		btn1de2.setBounds(440, 539, 55, 35);
		contentPane.add(btn1de2);

		/**
		 * Event handler for the postal code field. When clicked or the mouse enters the
		 * field it, clears the field.
		 * 
		 * @param e The Key event.
		 * @param e The Mouse event.
		 */
		txtCodigoPostal = new JTextField();
		txtCodigoPostal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!esCodigoPostalClicado) {
					txtCodigoPostal.setText("");
					esCodigoPostalClicado = true;
				}
			}
		});
		txtCodigoPostal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!esCodigoPostalClicado) {
					txtCodigoPostal.setText("");
					esCodigoPostalClicado = true;
				}
			}
		});
		txtCodigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCodigoPostal.setText("Código Postal");
		txtCodigoPostal.setColumns(10);
		txtCodigoPostal.setBounds(166, 337, 198, 29);
		contentPane.add(txtCodigoPostal);

		lblCdigoPostal = new JLabel("Código Postal:");
		lblCdigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCdigoPostal.setBounds(166, 303, 125, 23);
		contentPane.add(lblCdigoPostal);

		chckbxNotificaciones = new JCheckBox("  Deseo recibir notificaciones");
		chckbxNotificaciones.setSelected(true);
		chckbxNotificaciones.setFont(new Font("Dialog", Font.PLAIN, 13));
		chckbxNotificaciones.setBounds(166, 434, 208, 23);
		contentPane.add(chckbxNotificaciones);

		chckbxAdmin = new JCheckBox("  Soy Administrador");
		chckbxAdmin.setFont(new Font("Dialog", Font.PLAIN, 13));
		chckbxAdmin.setBounds(166, 486, 208, 23);
		contentPane.add(chckbxAdmin);

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

		lblRSB = new JLabel("");
		lblRSB.setBounds(153, 72, 222, 51);
		ImageIcon imageRSB = new ImageIcon(getClass().getResource("img/LogoGrande.png"));
		ImageIcon sizeRSB = new ImageIcon(
				imageRSB.getImage().getScaledInstance(lblRSB.getWidth(), lblRSB.getHeight(), Image.SCALE_SMOOTH));
		lblRSB.setIcon(sizeRSB);
		contentPane.add(lblRSB);

		chckbxSoyMayorDe = new JCheckBox("  Soy mayor de 14 años");
		chckbxSoyMayorDe.setFont(new Font("Dialog", Font.PLAIN, 13));
		chckbxSoyMayorDe.setBounds(166, 460, 208, 23);
		contentPane.add(chckbxSoyMayorDe);

		lblVacio = new JLabel("");
		lblVacio.setForeground(Color.RED);
		lblVacio.setBounds(166, 122, 152, 14);
		contentPane.add(lblVacio);
	}

	/**
	 * Checks if the name, surname and postal code fields are empty. If they are, it
	 * displays an error message. If they are not, it checks if the user exists in
	 * the system.
	 */
	private void camposVacios() {
		String nombre = txtNombre.getText().trim();
		String apellidos = txtApellidos.getText().trim();
		String codigoPostal = txtCodigoPostal.getText().trim();
		if (nombre.isEmpty() || apellidos.isEmpty() || codigoPostal.isEmpty() || nombre.equals("Nombre")
				|| apellidos.equals("Apellidos") || codigoPostal.equals("Código Postal")) {
			// Mostrar un mensaje de error al usuario
			lblVacio.setText("Rellene todos los campos");
		} else if (!chckbxTerminos.isSelected()) {
			lblAsteriscoAcepto.setText("*");
		} else if (!chckbxSoyMayorDe.isSelected()) {
			lblAsteriscoMayor.setText("*");
		} else {
			if (chckbxAdmin.isSelected()) {
				mandarDatos();
				miControlador.cambiarPantalla(1, 3);
				lblVacio.setText("");
			} else {
				mandarDatos();
				miControlador.cambiarPantalla(1, 2);
				lblVacio.setText("");
			}
		}
	}

	/**
	 * Returns the text in the name field.
	 * 
	 * @return The text in the name field.
	 */
	public String getTxtNombre() {
		return txtNombre.getText();
	}

	/**
	 * Returns the text in the surname field.
	 * 
	 * @return The text in the surname field.
	 */
	public String getTxtApellidos() {
		return txtApellidos.getText();
	}

	/**
	 * Returns the text in the postal code field.
	 * 
	 * @return The text in the postal code field.
	 */
	public String getTxtCp() {
		return txtCodigoPostal.getText();
	}

	/**
	 * Sends the data from the registration form to the controller.
	 */
	public void mandarDatos() {
		miControlador.registro2Pantalla(getTxtNombre(), getTxtApellidos(), getTxtCp());
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
}
