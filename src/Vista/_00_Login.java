package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Controlador.*;
import Modelo.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

/**
 * This class represents the Login screen of the application. It extends JFrame
 * and implements the Vista interface.
 *
 * @author Mario Seoane, Arpad Kiss
 */

public class _00_Login extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblRegistrarse2, lblRegistrarse1, lblUsuario, lblPwd, lblhasOlvidadoTu, lblRecordar, lblLogo,
			lblFondo, lblRSB;
	private JTextField txtUsuario;
	private JCheckBox chckbxCredenciales;
	private boolean esUsrClicado = false;
	private boolean esPwdClicado = false;

	private Controlador miControlador;
	private Modelo miModelo;
	private JLabel lblVacio;
	private JPasswordField txtPwd;

	/**
	 * Constructor for the Login screen. Initializes the components and sets up the
	 * event handlers.
	 */
	public _00_Login() {
		setIconImage(new ImageIcon(getClass().getResource("img/Logo_peque.png")).getImage());
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 200, 1090, 710);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblRegistrarse1 = new JLabel("¿Todavía no tienes cuenta?");
		lblRegistrarse1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegistrarse1.setBounds(677, 506, 162, 29);
		contentPane.add(lblRegistrarse1);

		/**
		 * Event handler for the "Registrarse" label. When clicked, it changes the
		 * screen to the registration screen. When the mouse enters the label, the
		 * cursor changes to a hand cursor. When the mouse exits the label, the cursor
		 * changes to the default cursor.
		 * 
		 * @param e The mouse event.
		 */
		lblRegistrarse2 = new JLabel("Registrate");
		lblRegistrarse2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblRegistrarse2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				miControlador.cambiarPantalla(0, 1);
				lblVacio.setText("");
			}
		});

		lblRegistrarse2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegistrarse2.setForeground(Color.BLUE);
		lblRegistrarse2.setBounds(845, 506, 65, 29);
		contentPane.add(lblRegistrarse2);
		Font subrayadoRegistro = lblRegistrarse2.getFont();
		Map<TextAttribute, Object> attributesRegistro = (Map<TextAttribute, Object>) subrayadoRegistro.getAttributes();
		attributesRegistro.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblRegistrarse2.setFont(subrayadoRegistro.deriveFont(attributesRegistro));
		lblRegistrarse2.setForeground(Color.BLUE);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblUsuario.setBounds(677, 200, 75, 24);
		contentPane.add(lblUsuario);

		lblPwd = new JLabel("Contraseña:");
		lblPwd.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPwd.setBounds(677, 287, 90, 29);
		contentPane.add(lblPwd);

		/**
		 * Event handler for the "Usuario" text field. When press a key or clicked, it
		 * clears the text field.
		 * 
		 * @param e The key event.
		 * @param e The mouse event.
		 */
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!esUsrClicado) {
					txtUsuario.setText("");
					esUsrClicado = true;
				}
			}
		});
		txtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!esUsrClicado) {
					txtUsuario.setText("");
					esUsrClicado = true;
				}
			}
		});
		txtUsuario.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtUsuario.setText("Nombre de Usuario");
		txtUsuario.setBounds(677, 235, 226, 29);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		chckbxCredenciales = new JCheckBox("Recordar credenciales");
		chckbxCredenciales.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxCredenciales.setBounds(677, 361, 162, 23);
		contentPane.add(chckbxCredenciales);

		lblhasOlvidadoTu = new JLabel("¿Has olvidado tu contraseña?");
		lblhasOlvidadoTu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblhasOlvidadoTu.setBounds(677, 481, 171, 14);
		contentPane.add(lblhasOlvidadoTu);

		/**
		 * Event handler for the "Recordar" label. When clicked it changes the screen to
		 * the password recovery screen. When the mouse enters the label, the cursor
		 * changes to a hand cursor. When the mouse exits the label, the cursor changes
		 * to the default cursor.
		 * 
		 * @param e The mouse event.
		 * @param e The mouse event.
		 */
		lblRecordar = new JLabel("Recordar");
		lblRecordar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblRecordar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				miControlador.cambiarPantalla(0, 4);
				lblVacio.setText("");
			}
		});
		lblRecordar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRecordar.setForeground(Color.BLUE);
		lblRecordar.setBounds(851, 481, 59, 14);
		contentPane.add(lblRecordar);
		Font subrayadoRecordar = lblRecordar.getFont();
		Map<TextAttribute, Object> attributesRecordar = (Map<TextAttribute, Object>) subrayadoRecordar.getAttributes();
		attributesRecordar.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblRecordar.setFont(subrayadoRecordar.deriveFont(attributesRecordar));
		lblRecordar.setForeground(Color.BLUE);

		/**
		 * Event handler for the "INICIAR SESIÓN" button. When clicked it calls the
		 * camposVacios method.
		 * 
		 * @param e The action event.
		 */
		JButton btnNewButton = new JButton("INICIAR SESIÓN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camposVacios();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(679, 405, 149, 35);
		contentPane.add(btnNewButton);

		lblLogo = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("img/logo_peque.png"));
		lblLogo.setBounds(178, 267, 158, 147);
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

		lblVacio = new JLabel("");
		lblVacio.setForeground(new Color(255, 0, 0));
		lblVacio.setBounds(677, 179, 171, 14);
		contentPane.add(lblVacio);

		JCheckBox checkBox = new JCheckBox("New check box");
		checkBox.setBounds(274, 443, 97, 23);
		contentPane.add(checkBox);

		txtPwd = new JPasswordField();
		txtPwd.setToolTipText("");
		txtPwd.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtPwd.setBounds(677, 318, 226, 29);
		contentPane.add(txtPwd);
	}

	/**
	 * Checks if the username and password fields are empty. If they are, it
	 * displays an error message. If they are not, it checks if the user exists in
	 * the system.
	 */
	public void camposVacios() {
		String username = txtUsuario.getText().trim();
		String password = new String(txtPwd.getPassword());
		if (username.isEmpty() || password.isEmpty() || username.equals("Nombre de Usuario")
				|| password.equals("Contraseña")) {
			lblVacio.setText("Rellene todos los campos");
		} else {
			if (!miControlador.comprobarUsuario()) {
				lblVacio.setText("Datos incorrectos");
			}
		}
	}

	/**
	 *
	 * Sets the controller for this view.
	 * 
	 * @param miControlador The controller to be set.
	 */

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 *
	 * Sets the model for this view.
	 * 
	 * @param miModelo The model to be set.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public String getNick() {
		return txtUsuario.getText();
	}

	public String getPassword() {
		return txtPwd.getText();
	}
}