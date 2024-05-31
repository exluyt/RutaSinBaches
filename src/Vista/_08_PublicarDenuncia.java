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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	private JTextField txtProvincia, txtCp, txtCiudad, txtCalle;
	private Component frame;
	private Controlador miControlador;
	private Modelo miModelo;
	private JTextArea txtDescripcion;
	private JLabel lblUpload;
	private JLabel lblCamino;
	private JLabel lblPersona;
	private JComboBox comboBox;
	private JLabel lblVacio;

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
		lblPersona.setBounds(79, 369, 253, 204);
		contentPane.add(lblPersona);
		ImageIcon sizePersona = new ImageIcon(imagePersona.getImage().getScaledInstance(lblPersona.getWidth(),
				lblPersona.getHeight(), Image.SCALE_SMOOTH));
		lblPersona.setIcon(sizePersona);

		lblUpload = new JLabel("");
		lblUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				miControlador.abrirActualizarFotoDenuncia();
			}
		});
		lblUpload.setBounds(32, 308, 50, 43);
		ImageIcon sizeUpload = new ImageIcon(imageUpload.getImage().getScaledInstance(lblUpload.getWidth(),
				lblUpload.getHeight(), Image.SCALE_SMOOTH));
		lblUpload.setIcon(sizeUpload);
		lblUpload.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
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
		lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		/**
		 * Event handler for the lblLogo label. It calls the cambiarPantalla method of
		 * the controller to change the screen to the main page (depending on whether it
		 * is an admin user or not).
		 * 
		 * @param e The mouse event
		 */
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				miControlador.comprobarUsuarioPagina();
				cerrarVentana();
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

		txtProvincia = new JTextField();
		txtProvincia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtProvincia.setBounds(666, 266, 253, 28);
		contentPane.add(txtProvincia);
		txtProvincia.setColumns(10);

		txtCp = new JTextField();
		txtCp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCp.setColumns(10);
		txtCp.setBounds(666, 219, 253, 28);
		contentPane.add(txtCp);

		txtCiudad = new JTextField();
		txtCiudad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCiudad.setColumns(10);
		txtCiudad.setBounds(666, 312, 253, 28);
		contentPane.add(txtCiudad);

		txtCalle = new JTextField();
		txtCalle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCalle.setColumns(10);
		txtCalle.setBounds(666, 360, 253, 28);
		contentPane.add(txtCalle);

		JButton btnPublicar = new JButton("Publicar denuncia");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (miControlador.comprobarDenunciaSimilar()) {
					int response = JOptionPane.showConfirmDialog(frame,
							"Se ha encontrado una denuncia parecida. ¿Desea publicarla igualemente?",
							"Coincidencia encontrada", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {
						// Logic for when "Yes" is clicked
						miControlador.cambiarPantalla(8, 6);
						camposVacios();
					} else if (response == JOptionPane.NO_OPTION) {
						lblVacio.setText("Denuncia no publicada");
					}
				} else {
					camposVacios();
					
				}
			}
		});
		btnPublicar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPublicar.setBounds(581, 579, 230, 28);
		contentPane.add(btnPublicar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(666, 461, 253, 69);
		contentPane.add(scrollPane);

		txtDescripcion = new JTextArea();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(txtDescripcion);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Iluminación", "Edificios", "Pavimiento", "Naturaleza", "Limpieza"}));
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

		lblVacio = new JLabel("");
		lblVacio.setForeground(new Color(255, 0, 0));
		lblVacio.setBounds(666, 190, 253, 14);
		contentPane.add(lblVacio);

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

	public String getCp() {
		return txtCp.getText();
	}

	public String getProvincia() {
		return txtProvincia.getText();
	}

	public String getCiudad() {
		return txtCiudad.getText();
	}

	public String getCalle() {
		return txtCalle.getText();
	}

	public int getCategoria() {
		return comboBox.getSelectedIndex() + 1;
	}

	public String getDescripcion() {
		return txtDescripcion.getText();
	}

	public void setError(String mensaje) {
		lblVacio.setText(mensaje);
	}

	public void camposVacios() {
		String cp = getCp();
		miControlador.setCp(getCp());
		String provincia = getProvincia();
		String ciudad = getCiudad();
		String calle = getCalle();
		String descripcion = getDescripcion();
		if (cp.isEmpty() || provincia.isEmpty() || ciudad.isEmpty() || calle.isEmpty() || descripcion.isEmpty()) {
			lblVacio.setText("Rellena todos los campos");
		} else {
			try {
				if (5 == cp.length()) {
					Integer.parseInt(cp);
					lblVacio.setText("");
					miControlador.agregarPublicacion();
					cerrarVentana();
				} else {
					lblVacio.setText("Introduzca un codigo postal valido");
				}
			} catch (NumberFormatException e) {
				System.out.print("Los datos no son validos");
				lblVacio.setText("Los datos no son validos");
			}
		}
	}

	public void actualizarFotoPerfil(ImageIcon imageIcon) {
		Image image = imageIcon.getImage().getScaledInstance(lblFotoPerfil.getWidth(), lblFotoPerfil.getHeight(),
				Image.SCALE_SMOOTH);
		lblFotoPerfil.setIcon(new ImageIcon(image));
	}
	public void cerrarVentana() {
		txtCp.setText("");
		txtProvincia.setText("");
		txtCiudad.setText("");
		txtCalle.setText("");
		txtDescripcion.setText("");
		lblVacio.setText("");
		ImageIcon imageFotoPerfil = new ImageIcon(getClass().getResource("img/Imagen.jpg"));
		ImageIcon sizeFotoPerfil = new ImageIcon(imageFotoPerfil.getImage().getScaledInstance(lblFotoPerfil.getWidth(),
				lblFotoPerfil.getHeight(), Image.SCALE_SMOOTH));
		lblFotoPerfil.setIcon(sizeFotoPerfil);
	}
}