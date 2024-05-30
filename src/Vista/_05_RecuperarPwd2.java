
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
 * This class represents a GUI for a second step of password recovery form. It
 * extends JFrame and implements the Vista interface. It contains various Swing
 * components like JLabel, JTextField, JButton.
 *
 * @author Nacho
 */
public class _05_RecuperarPwd2 extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo, lblFondo, lblRSB, lblVacio, lblPwd;
	private JTextField txtPwd, txtRepetirPwd;
	private Modelo miModelo;
	private Controlador miControlador;
	private JButton btnCambiarPwd;
	private boolean esPwdClicado, esRepetirPwdClicado;

	/**
	 * Constructor for the _05_RecuperarPwd2 class. Initializes the form and its
	 * components.
	 */
	public _05_RecuperarPwd2() {
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
		 * Event handler for the btnCambiarPwd button. It calls the camposVacios method.
		 * When a key is pressed, it clears the text field.
		 * 
		 * @param e The key event.
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
		txtPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPwd.setText("Contraseña");
		txtPwd.setBounds(698, 268, 222, 31);
		contentPane.add(txtPwd);
		txtPwd.setColumns(10);

		lblPwd = new JLabel("Nueva contraseña:");
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPwd.setBounds(698, 239, 139, 18);
		contentPane.add(lblPwd);

		JLabel lblRepetirPwd = new JLabel("Repetir contraseña:");
		lblRepetirPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRepetirPwd.setBounds(698, 339, 252, 14);
		contentPane.add(lblRepetirPwd);

		/**
		 * Event handler for the txtRepetirPwd field. When a key is pressed, it clears
		 * the text field.
		 * 
		 * @param e The key event.
		 */
		txtRepetirPwd = new JTextField();
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
		txtRepetirPwd.setBounds(698, 364, 222, 31);
		contentPane.add(txtRepetirPwd);
		txtRepetirPwd.setColumns(10);

		/**
		 * Event handler for the btnCambiarPwd button. It calls the camposVacios method.
		 * 
		 * @param e The action event.
		 */
		btnCambiarPwd = new JButton("Cambiar contraseña");
		btnCambiarPwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camposVacios();
			}
		});
		btnCambiarPwd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCambiarPwd.setBounds(698, 451, 151, 35);
		contentPane.add(btnCambiarPwd);

		lblVacio = new JLabel("");
		lblVacio.setForeground(Color.RED);
		lblVacio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVacio.setBounds(698, 198, 191, 14);
		contentPane.add(lblVacio);

	}

	/**
	 * Checks if all the required fields are filled. If not, it displays an error
	 * message. If all fields are filled, it checks if the passwords match. If
	 * everything is correct, it sets the new password for the user. If not, it
	 * displays an error message.
	 */
	public void camposVacios() {
		String pwd = txtPwd.getText().trim();
		String repetirPwd = txtRepetirPwd.getText().trim();
		if (pwd.isEmpty() || repetirPwd.isEmpty() || pwd.equals("Contraseña")
				|| repetirPwd.equals("Repetir contraseña")) {
			// Mostrar un mensaje de error al usuario
			lblVacio.setText("Rellene todos los campos");
		} else {
			comprobarPwdIguales();
			lblVacio.setText("");
		}
	}

	/**
	 * Checks if the passwords entered in the txtPwd and txtRepetirPwd fields match.
	 * If they match, it sets the new password for the user. If not, it displays an
	 * error message.
	 */
	private void comprobarPwdIguales() {
	    String pwd = txtPwd.getText().trim();
	    String repetirPwd = txtRepetirPwd.getText().trim();
	    if (pwd.equals(repetirPwd)) {
	        lblVacio.setText("");
	        if (miControlador.establecerPwd()) {
	            System.out.println("Cambio de contraseña correcto");
	        } else {
	            System.out.println("Cambio de contraseña incorrecto");
	        }
	    } else {
	        lblVacio.setText("Las contraseñas no coinciden");
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

	public String getPwd() {
		return txtPwd.getText();
	}
}
