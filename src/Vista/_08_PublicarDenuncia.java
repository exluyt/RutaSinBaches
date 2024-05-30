package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controlador.Controlador;
import Modelo.Modelo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class represents a GUI for a form to publish a complaint. It extends
 * `JFrame` and implements the `Vista` interface. It contains various Swing
 * components like JLabel, JTextField, JButton, and JComboBo`.
 * 
 * @author Nacho
 */
public class _08_PublicarDenuncia extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo, lblRSB, lblFotoPerfil, lblInfo, lblCdigoPostal, lblContrasea, lblPreguntasDeSeguridad;
	private JTextField textNombre, textUsuario, textApellidos, textCP;
	private Component frame;
	private Controlador miControlador;
	private Modelo miModelo;
	private JTextArea textArea;
	private JLabel lblUpload;
	private JLabel lblCamino;
	private JLabel lblPersona;

	/**
	 * Constructor for the _08_PublicarDenuncia class. Initializes the form and its
	 * components.
	 */
	public _08_PublicarDenuncia() {
		setIconImage(new ImageIcon(getClass().getResource("img/Logo_peque.png")).getImage());
		setTitle("Publicar denuncia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 200, 1090, 710);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon imageIcon = new ImageIcon(getClass().getResource("img/logo_peque.png"));
		ImageIcon imageRSB = new ImageIcon(getClass().getResource("img/LogoGrande.png"));
		ImageIcon imageFotoPerfil = new ImageIcon(getClass().getResource("img/Imagen.jpg"));
		ImageIcon imageUpload = new ImageIcon(getClass().getResource("img/Drawing.png"));
		ImageIcon imageCamino = new ImageIcon(getClass().getResource("img/Camino.png"));
		ImageIcon imagePersona = new ImageIcon(getClass().getResource("img/personas caminando.png"));

		lblCamino = new JLabel("");
		lblCamino.setBounds(-260, 491, 439, 275);
		contentPane.add(lblCamino);
		ImageIcon sizeCamino = new ImageIcon(imageCamino.getImage().getScaledInstance(lblCamino.getWidth(),
				lblCamino.getHeight(), Image.SCALE_SMOOTH));
		lblCamino.setIcon(sizeCamino);

		lblPersona = new JLabel("");
		lblPersona.setBounds(80, 370, 253, 204);
		contentPane.add(lblPersona);
		ImageIcon sizePersona = new ImageIcon(imagePersona.getImage().getScaledInstance(lblPersona.getWidth(),
				lblPersona.getHeight(), Image.SCALE_SMOOTH));
		lblPersona.setIcon(sizePersona);

		lblUpload = new JLabel("");
		lblUpload.setBounds(32, 308, 50, 43);
		ImageIcon sizeUpload = new ImageIcon(imageUpload.getImage().getScaledInstance(lblUpload.getWidth(),
				lblUpload.getHeight(), Image.SCALE_SMOOTH));
		lblUpload.setIcon(sizeUpload);
		lblUpload.setCursor(new Cursor(Cursor.HAND_CURSOR)); // IMPORTANTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
		contentPane.add(lblUpload);

		lblInfo = new JLabel("Publicar una");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(new Color(0, 0, 0));
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblInfo.setBounds(485, 136, 275, 49);
		contentPane.add(lblInfo);

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
			public void mouseEntered(MouseEvent e) {
				lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblLogo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			/**
			 * Event handler for the lblLogo label. It calls the cambiarPantalla method of
			 * the controller to change the screen to the main page (depending on whether it
			 * is an admin user or not).
			 * 
			 * @param e The mouse event
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				miControlador.comprobarUsuarioPagina();
			}
		});
		lblLogo.setBounds(10, 11, 77, 78);
		panel.add(lblLogo);
		ImageIcon imageIcon1 = new ImageIcon(
				imageIcon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
		lblLogo.setIcon(imageIcon1);

		lblFotoPerfil = new JLabel("");
		lblFotoPerfil.setBounds(36, 136, 253, 204);
		contentPane.add(lblFotoPerfil);
		ImageIcon sizeFotoPerfil = new ImageIcon(imageFotoPerfil.getImage().getScaledInstance(lblFotoPerfil.getWidth(),
				lblFotoPerfil.getHeight(), Image.SCALE_SMOOTH));
		lblFotoPerfil.setIcon(sizeFotoPerfil);

		JLabel lblUsuario = new JLabel("Codigo postal:");
		lblUsuario.setForeground(new Color(58, 182, 98));
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(476, 214, 100, 37);
		contentPane.add(lblUsuario);

		JLabel lblUsuario_1 = new JLabel("Provincia:");
		lblUsuario_1.setForeground(new Color(58, 182, 98));
		lblUsuario_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario_1.setBounds(476, 261, 87, 37);
		contentPane.add(lblUsuario_1);

		JLabel lblUsuario_2 = new JLabel("Ciudad:");
		lblUsuario_2.setForeground(new Color(58, 182, 98));
		lblUsuario_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario_2.setBounds(476, 308, 69, 37);
		contentPane.add(lblUsuario_2);

		lblCdigoPostal = new JLabel("Calle:");
		lblCdigoPostal.setForeground(new Color(58, 182, 98));
		lblCdigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCdigoPostal.setBounds(476, 355, 100, 37);
		contentPane.add(lblCdigoPostal);

		lblContrasea = new JLabel("Categoria:");
		lblContrasea.setForeground(new Color(58, 182, 98));
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setBounds(476, 402, 87, 37);
		contentPane.add(lblContrasea);

		lblPreguntasDeSeguridad = new JLabel("Descripción:");
		lblPreguntasDeSeguridad.setForeground(new Color(58, 182, 98));
		lblPreguntasDeSeguridad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPreguntasDeSeguridad.setBounds(476, 461, 158, 37);
		contentPane.add(lblPreguntasDeSeguridad);

		textNombre = new JTextField();
		textNombre.setBounds(666, 266, 253, 28);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		textUsuario.setBounds(666, 219, 253, 28);
		contentPane.add(textUsuario);

		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(666, 312, 253, 28);
		contentPane.add(textApellidos);

		textCP = new JTextField();
		textCP.setColumns(10);
		textCP.setBounds(666, 360, 253, 28);
		contentPane.add(textCP);

		JButton btnPublicar = new JButton("Publicar denuncia");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(frame,
						"Se ha encontrado una denuncia parecida. ¿Desea publicarla igualemente?",
						"Coincidencia encontrada", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Logic for when "Yes" is clicked
					miControlador.cambiarPantalla(8, 6);
					// Add your specific actions here
				} else if (response == JOptionPane.NO_OPTION) {
					// Logic for when "No" is clicked (optional)
					System.out.println("No button clicked");
				}
			}
		});
		btnPublicar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPublicar.setBounds(581, 579, 230, 28);
		contentPane.add(btnPublicar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(666, 461, 253, 69);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Iluminacion", "Alumbrado", "..." }));
		comboBox.setBounds(666, 407, 253, 28);
		contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("denuncia");
		lblNewLabel.setForeground(new Color(58, 182, 98));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(718, 143, 127, 34);
		contentPane.add(lblNewLabel);

		JLabel lblFotoPerfil_1 = new JLabel("");
		lblFotoPerfil_1.setBounds(52, 416, 253, 204);
		contentPane.add(lblFotoPerfil_1);

	}

	/**
	 * Sets the controller for the view.
	 * 
	 * @param miControlador The Controller to be set.
	 */
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 * Sets the model for the view.
	 * 
	 * @param miModelo The Model to be set.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}
}