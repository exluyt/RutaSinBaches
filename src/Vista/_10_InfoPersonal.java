package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controlador.Controlador;
import Modelo.Modelo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import Controlador.*;
import Modelo.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

/**
 * This class represents a GUI for a form to publish a complaint. It extends
 * `JFrame` and implements the `Vista` interface. It contains various Swing
 * components like JLabel, JTextField, JButton, and JComboBo.
 * 
 * @author Arpad Kiss
 */
public class _10_InfoPersonal extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo, lblRSB, lblFotoPerfil, lblCambia, lblInfo, lblCdigoPostal, lblContrasea,
			lblPreguntasDeSeguridad;
	private JPasswordField passwordField;
	private JTextField textNombre, textUsuario, textApellidos, textCP, textRespuesta;
	private JLabel lblMontana;
	private JLabel lblCiudad;
	private JLabel lblCamino;
	private JLabel lblCamino1;

	private Controlador miControlador;
	private Modelo miModelo;
	private JLabel lblUpload;
	private JLabel lblUsuarioPerfil;
	private JLabel lblNombreapellido;
	private JPanel panel_1;

	/**
	 * Constructor for the _10_InfoPersonal class. Initializes the form and its
	 * components.
	 * 
	 * @param miControlador The controller for the view.
	 */
	public _10_InfoPersonal() {
		setIconImage(new ImageIcon(getClass().getResource("img/Logo_peque.png")).getImage());
		setTitle("Informacion personal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 200, 1090, 710);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon imageIcon = new ImageIcon(getClass().getResource("img/logo_peque.png"));
		ImageIcon imageRSB = new ImageIcon(getClass().getResource("img/LogoGrande.png"));
		ImageIcon imageFotoPerfil = new ImageIcon(getClass().getResource("img/fotoPerfil.png"));
		ImageIcon imageRuta = new ImageIcon(getClass().getResource("img/ruta.png"));
		ImageIcon imageCamino = new ImageIcon(getClass().getResource("img/Camino.png"));
		ImageIcon imageCiudad = new ImageIcon(getClass().getResource("img/Ciudad.png"));
		ImageIcon imageMontana = new ImageIcon(getClass().getResource("img/montana2.png"));
		ImageIcon imageUpload = new ImageIcon(getClass().getResource("img/Drawing.png"));

		lblUpload = new JLabel("");
		lblUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});
		lblUpload.setBounds(948, 266, 50, 50);
		contentPane.add(lblUpload);
		ImageIcon sizeUpload = new ImageIcon(imageUpload.getImage().getScaledInstance(lblUpload.getWidth(),
				lblUpload.getHeight(), Image.SCALE_SMOOTH));
		lblUpload.setIcon(sizeUpload);

		lblMontana = new JLabel("");
		lblMontana.setBounds(0, 94, 358, 232);
		contentPane.add(lblMontana);
		ImageIcon sizeMontana = new ImageIcon(imageMontana.getImage().getScaledInstance(lblMontana.getWidth(),
				lblMontana.getHeight(), Image.SCALE_SMOOTH));
		lblMontana.setIcon(sizeMontana);

		lblCiudad = new JLabel("");
		lblCiudad.setBounds(843, 466, 203, 155);
		contentPane.add(lblCiudad);
		ImageIcon sizeCiudad = new ImageIcon(imageCiudad.getImage().getScaledInstance(lblCiudad.getWidth(),
				lblCiudad.getHeight(), Image.SCALE_SMOOTH));
		lblCiudad.setIcon(sizeCiudad);

		lblCamino = new JLabel("");
		lblCamino.setBounds(835, 311, 334, 237);
		contentPane.add(lblCamino);
		ImageIcon sizeCamino = new ImageIcon(imageCamino.getImage().getScaledInstance(lblCamino.getWidth(),
				lblCamino.getHeight(), Image.SCALE_SMOOTH));
		lblCamino.setIcon(sizeCamino);

		lblCamino1 = new JLabel("");
		lblCamino1.setBounds(-186, 225, 320, 237);
		contentPane.add(lblCamino1);
		ImageIcon sizeCamino1 = new ImageIcon(imageCamino.getImage().getScaledInstance(lblCamino1.getWidth(),
				lblCamino1.getHeight(), Image.SCALE_SMOOTH));
		lblCamino1.setIcon(sizeCamino1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(10, 11, 1054, 100);
		contentPane.add(panel);
		panel.setLayout(null);

		lblRSB = new JLabel("");
		lblRSB.setBounds(379, 21, 296, 68);
		panel.add(lblRSB);
		ImageIcon sizeRSB = new ImageIcon(
				imageRSB.getImage().getScaledInstance(lblRSB.getWidth(), lblRSB.getHeight(), Image.SCALE_SMOOTH));
		lblRSB.setIcon(sizeRSB);

		lblLogo = new JLabel("");
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				miControlador.comprobarUsuarioPaginaInfo();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblLogo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLogo.setBounds(10, 11, 77, 78);
		panel.add(lblLogo);
		ImageIcon imageIcon1 = new ImageIcon(
				imageIcon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
		lblLogo.setIcon(imageIcon1);

		lblFotoPerfil = new JLabel("");
		lblFotoPerfil.setBounds(806, 133, 206, 198);
		contentPane.add(lblFotoPerfil);
		ImageIcon sizeFotoPerfil = new ImageIcon(imageFotoPerfil.getImage().getScaledInstance(lblFotoPerfil.getWidth(),
				lblFotoPerfil.getHeight(), Image.SCALE_SMOOTH));
		lblFotoPerfil.setIcon(sizeFotoPerfil);

		lblUsuarioPerfil = new JLabel("@Usuario");
		lblUsuarioPerfil.setForeground(new Color(0, 0, 0));
		lblUsuarioPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuarioPerfil.setBounds(806, 369, 182, 37);
		contentPane.add(lblUsuarioPerfil);

		lblNombreapellido = new JLabel("Pedro Camacho");
		lblNombreapellido.setForeground(Color.BLACK);
		lblNombreapellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreapellido.setBounds(806, 327, 182, 37);
		contentPane.add(lblNombreapellido);

		panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(305, 108, 444, 597);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnModificarPreguntas = new JButton("Modificar");
		btnModificarPreguntas.setBounds(139, 467, 141, 37);
		panel_1.add(btnModificarPreguntas);
		btnModificarPreguntas.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblRespuesta = new JLabel("Respuesta:");
		lblRespuesta.setBounds(29, 390, 158, 37);
		panel_1.add(lblRespuesta);
		lblRespuesta.setForeground(new Color(58, 182, 98));
		lblRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textRespuesta = new JTextField();
		textRespuesta.setBounds(219, 400, 182, 20);
		panel_1.add(textRespuesta);
		textRespuesta.setColumns(10);

		lblPreguntasDeSeguridad = new JLabel("Pregunta de seguridad:");
		lblPreguntasDeSeguridad.setBounds(29, 342, 193, 37);
		panel_1.add(lblPreguntasDeSeguridad);
		lblPreguntasDeSeguridad.setForeground(new Color(58, 182, 98));
		lblPreguntasDeSeguridad.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(219, 351, 182, 22);
		panel_1.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "¿Cuál tu nombre de pila?",
				"¿Cuál es tu primera escuela?", "¿Cuál es tu primera mascota?" }));

		lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(29, 294, 87, 37);
		panel_1.add(lblContrasea);
		lblContrasea.setForeground(new Color(58, 182, 98));
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));

		passwordField = new JPasswordField();
		passwordField.setBounds(219, 304, 100, 20);
		panel_1.add(passwordField);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblCdigoPostal = new JLabel("Código postal:");
		lblCdigoPostal.setBounds(29, 242, 100, 37);
		panel_1.add(lblCdigoPostal);
		lblCdigoPostal.setForeground(new Color(58, 182, 98));
		lblCdigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textCP = new JTextField();
		textCP.setBounds(219, 252, 100, 20);
		panel_1.add(textCP);
		textCP.setColumns(10);

		textApellidos = new JTextField();
		textApellidos.setBounds(219, 204, 100, 20);
		panel_1.add(textApellidos);
		textApellidos.setColumns(10);

		JLabel lblUsuario_2 = new JLabel("Apellidos:");
		lblUsuario_2.setBounds(29, 194, 69, 37);
		panel_1.add(lblUsuario_2);
		lblUsuario_2.setForeground(new Color(58, 182, 98));
		lblUsuario_2.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblUsuario_1 = new JLabel("Nombre:");
		lblUsuario_1.setBounds(29, 149, 64, 37);
		panel_1.add(lblUsuario_1);
		lblUsuario_1.setForeground(new Color(58, 182, 98));
		lblUsuario_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textNombre = new JTextField();
		textNombre.setBounds(219, 159, 100, 20);
		panel_1.add(textNombre);
		textNombre.setColumns(10);

		textUsuario = new JTextField();
		textUsuario.setBounds(219, 111, 100, 20);
		panel_1.add(textUsuario);
		textUsuario.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(29, 101, 64, 37);
		panel_1.add(lblUsuario);
		lblUsuario.setForeground(new Color(58, 182, 98));
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblCambia = new JLabel("Cambia tu");
		lblCambia.setBounds(10, 37, 175, 37);
		panel_1.add(lblCambia);
		lblCambia.setFont(new Font("Tahoma", Font.PLAIN, 30));

		lblInfo = new JLabel("información personal");
		lblInfo.setBounds(150, 30, 284, 49);
		panel_1.add(lblInfo);
		lblInfo.setForeground(new Color(58, 182, 98));
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 30));

	}

	/**
	 * This method sets the controller for the view.
	 * 
	 * @param miControlador The Controller to be set.
	 */
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 * This method sets the model for the view.
	 * 
	 * @param miModelo The Model to be set.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}
}
