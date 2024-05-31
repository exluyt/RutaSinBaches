package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.Controlador;
import Modelo.Modelo;

import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Modelo.*;
import Controlador.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class represents the main page of the application. It extends JFrame and
 * implements the Vista interface. It contains various Swing components like
 * JLabel, JTextField, JButton, JTable, JScrollPane, JComboBox, and JTabbedPane.
 *
 * @author Henry Illescas
 */
public class _06_PaginaPrincipal extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JButton btnPublicarDenuncia, btnNewButton_1, btnNewButton_2, btnResuelta, btnNewButton_3;
	private JPanel contentPane, paraTi, denunciasFavoritas, misDenuncias;
	private JLabel lblLogo, lblRSB, lblFotoPerfil, lblNewLabel;
	private JScrollPane scrollPane, scrollPane2, scrollPane3;
	private boolean esCodigoPostalClicado = false;
	private JComboBox comboBox_1, comboBox;
	private JTable table, table2, table3;
	private JTextField txtCodigoPostal;
	private JTabbedPane pestañas;

	private Modelo miModelo;
	private Controlador miControlador;
	private JButton btnFav;

	private boolean añadido=false;
	/**
	 * Constructor for the _06_PaginaPrincipal class. Initializes the form and its
	 * components.
	 **/
	public _06_PaginaPrincipal() {
		setIconImage(new ImageIcon(getClass().getResource("img/Logo_peque.png")).getImage());
		setTitle("Pagina principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 200, 1090, 710);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("img/logo_peque.png"));
		ImageIcon imageRSB = new ImageIcon(getClass().getResource("img/LogoGrande.png"));
		ImageIcon imageFotoPerfil = new ImageIcon(getClass().getResource("img/fotoPerfil.png"));
		ImageIcon imageBusquedaLupa = new ImageIcon(getClass().getResource("img/Busquedapng.png"));

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent evt) {
				byte[] imageData = miModelo.getImagen(miControlador.recuperarUsuario());
				ImageIcon imageIcon = new ImageIcon(imageData);
				Image image = imageIcon.getImage();
				Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

				// Mostrar la imagen en un JLabel
				lblFotoPerfil.setIcon(new ImageIcon(scaledImage));
			}
		});

		btnResuelta = new JButton("Resuelta");
		btnResuelta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnNewButton_3 = new JButton("Ver imagen");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(549, 575, 101, 32);
		contentPane.add(btnNewButton_3);
		btnResuelta.setForeground(Color.BLACK);
		btnResuelta.setEnabled(false);
		btnResuelta.setBackground(Color.WHITE);
		btnResuelta.setBounds(658, 575, 101, 32);
		contentPane.add(btnResuelta);

		btnNewButton_2 = new JButton("");
		
		btnNewButton_2.setBounds(480, 182, 42, 28);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setOpaque(false);

		ImageIcon sizeBusquedaLupa = new ImageIcon(imageBusquedaLupa.getImage()
				.getScaledInstance(btnNewButton_2.getWidth(), btnNewButton_2.getHeight(), Image.SCALE_SMOOTH));
		btnNewButton_2.setIcon(sizeBusquedaLupa);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(238, 182, 110, 28);
		contentPane.add(comboBox_1);
		comboBox_1.setModel(
				new DefaultComboBoxModel(new String[] {"--Categoria--", "Iluminacion", "Edificios", "Pavimento", "Naturaleza", "Limpieza"}));

		comboBox = new JComboBox();
		comboBox.setBounds(358, 182, 112, 28);
		contentPane.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--Estado--", "Publicado", "En proceso", "Finalizada"}));

		txtCodigoPostal = new JTextField();
		txtCodigoPostal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!esCodigoPostalClicado) {
					txtCodigoPostal.setText("");
					esCodigoPostalClicado = true;
				}
			}
		});
		txtCodigoPostal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!esCodigoPostalClicado) {
					txtCodigoPostal.setText("");
					esCodigoPostalClicado = true;
				}
			}
		});

		txtCodigoPostal.setBounds(30, 182, 180, 28);
		contentPane.add(txtCodigoPostal);
		txtCodigoPostal.setText("Codigo Postal");
		txtCodigoPostal.setColumns(10);

		lblNewLabel = new JLabel("Busqueda");
		lblNewLabel.setBounds(30, 162, 95, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.WHITE);

		btnPublicarDenuncia = new JButton("Publicar denuncia");
		btnPublicarDenuncia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarPantalla(6, 8);
			}
		});
		btnPublicarDenuncia.setForeground(Color.BLACK);
		btnPublicarDenuncia.setBackground(Color.WHITE);
		btnPublicarDenuncia.setBounds(883, 575, 154, 32);
		contentPane.add(btnPublicarDenuncia);

		btnFav = new JButton("Favorito");

		btnFav.setEnabled(false);
		btnFav.setBackground(Color.WHITE);
		btnFav.setForeground(Color.BLACK);
		btnFav.setBounds(772, 575, 101, 32);
		contentPane.add(btnFav);

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

		lblLogo.setBounds(10, 11, 77, 78);
		panel.add(lblLogo);
		ImageIcon imageIcon1 = new ImageIcon(
				imageIcon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
		lblLogo.setIcon(imageIcon1);

		lblFotoPerfil = new JLabel("");
		lblFotoPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblFotoPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				miControlador.cambiarPantalla(6, 10);
			}
		});

		lblFotoPerfil.setBounds(984, 21, 60, 60);
		panel.add(lblFotoPerfil);
		ImageIcon sizeFotoPerfil = new ImageIcon(imageFotoPerfil.getImage().getScaledInstance(lblFotoPerfil.getWidth(),
				lblFotoPerfil.getHeight(), Image.SCALE_SMOOTH));
		lblFotoPerfil.setIcon(sizeFotoPerfil);

		pestañas = new JTabbedPane();
		pestañas.setBorder(null);
		pestañas.setLocation(20, 122);
		pestañas.setSize(1044, 500);
		pestañas.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int selectPestaña = pestañas.getSelectedIndex();
				if (selectPestaña==2 || selectPestaña==3 &&!añadido) {
					comboBox.addItem("Nueva");
					comboBox.addItem("Rechazada");
					añadido=true;
					}
				if(selectPestaña==0 || selectPestaña==1 && añadido) {
					comboBox.removeItem("Nueva");
					comboBox.removeItem("Rechazada");	
					añadido=false;
				}
			}
		});
		denunciasFavoritas = new JPanel();
		denunciasFavoritas.setBackground(new Color(36, 113, 61));
		denunciasFavoritas.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		pestañas.addTab("Denuncias Favoritas", denunciasFavoritas);
		denunciasFavoritas.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 1019, 330);
		denunciasFavoritas.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel modeloTabla1 = new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, },
				new String[] { "C\u00F3digo", "Usuario", "Estado", "Direcci\u00F3n", "Fecha de publicaci\u00F3n",
						"Categoria", "Descripci\u00F3n", "\u2605" });
		table.setModel(modeloTabla1);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                btnFav.setEnabled(table.getSelectedRow() != -1);
            }
        });
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setMinWidth(150);
		table.getColumnModel().getColumn(3).setMaxWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(125);
		table.getColumnModel().getColumn(4).setMinWidth(125);
		table.getColumnModel().getColumn(4).setMaxWidth(125);
		table.getColumnModel().getColumn(5).setPreferredWidth(101);
		table.getColumnModel().getColumn(7).setPreferredWidth(25);
		table.getColumnModel().getColumn(7).setMinWidth(25);
		table.getColumnModel().getColumn(7).setMaxWidth(25);

		scrollPane.setViewportView(table);

		paraTi = new JPanel();
		paraTi.setBackground(new Color(36, 113, 61));
		pestañas.addTab("Para Ti", paraTi);
		paraTi.setLayout(null);
		paraTi.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 75, 1019, 330);
		paraTi.add(scrollPane2);

		table2 = new JTable();
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel modeloTabla2 = new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, },
				new String[] { "C\u00F3digo", "Usuario", "Estado", "Direcci\u00F3n", "Fecha de publicaci\u00F3n",
						"Categoria", "Descripci\u00F3n", "\u2605" });
		table2.setModel(modeloTabla2);
		table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                btnFav.setEnabled(table2.getSelectedRow() != -1);
                
            }
        });
		table2.getColumnModel().getColumn(0).setPreferredWidth(50);
		table2.getColumnModel().getColumn(0).setMinWidth(50);
		table2.getColumnModel().getColumn(0).setMaxWidth(50);
		table2.getColumnModel().getColumn(1).setMaxWidth(75);
		table2.getColumnModel().getColumn(2).setPreferredWidth(100);
		table2.getColumnModel().getColumn(2).setMinWidth(100);
		table2.getColumnModel().getColumn(2).setMaxWidth(100);
		table2.getColumnModel().getColumn(3).setPreferredWidth(150);
		table2.getColumnModel().getColumn(3).setMinWidth(150);
		table2.getColumnModel().getColumn(3).setMaxWidth(150);
		table2.getColumnModel().getColumn(4).setPreferredWidth(125);
		table2.getColumnModel().getColumn(4).setMinWidth(125);
		table2.getColumnModel().getColumn(4).setMaxWidth(125);
		table2.getColumnModel().getColumn(5).setPreferredWidth(101);
		table2.getColumnModel().getColumn(7).setPreferredWidth(25);
		table2.getColumnModel().getColumn(7).setMinWidth(25);
		table2.getColumnModel().getColumn(7).setMaxWidth(25);
		scrollPane2.setViewportView(table2);

		misDenuncias = new JPanel();
		misDenuncias.setBackground(new Color(36, 113, 61));
		pestañas.addTab("Mis Denuncias", misDenuncias);
		misDenuncias.setLayout(null);
		misDenuncias.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(10, 75, 1019, 330);
		misDenuncias.add(scrollPane3);


		table3 = new JTable();
		table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel modeloTabla3 = new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, },
				new String[] { "C\u00F3digo", "Usuario", "Estado", "Direcci\u00F3n", "Fecha de publicaci\u00F3n",
						"Categoria", "Descripci\u00F3n", "\u2605" });
		table3.setModel(modeloTabla3);
		table3.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                btnFav.setEnabled(table3.getSelectedRow() != -1);
            }
        });
		table3.getColumnModel().getColumn(0).setPreferredWidth(50);
		table3.getColumnModel().getColumn(0).setMinWidth(50);
		table3.getColumnModel().getColumn(0).setMaxWidth(50);
		table3.getColumnModel().getColumn(1).setMaxWidth(75);
		table3.getColumnModel().getColumn(2).setPreferredWidth(100);
		table3.getColumnModel().getColumn(2).setMinWidth(100);
		table3.getColumnModel().getColumn(2).setMaxWidth(100);
		table3.getColumnModel().getColumn(3).setPreferredWidth(150);
		table3.getColumnModel().getColumn(3).setMinWidth(150);
		table3.getColumnModel().getColumn(3).setMaxWidth(150);
		table3.getColumnModel().getColumn(4).setPreferredWidth(125);
		table3.getColumnModel().getColumn(4).setMinWidth(125);
		table3.getColumnModel().getColumn(4).setMaxWidth(125);
		table3.getColumnModel().getColumn(5).setPreferredWidth(101);
		table3.getColumnModel().getColumn(7).setPreferredWidth(25);
		table3.getColumnModel().getColumn(7).setMinWidth(25);
		table3.getColumnModel().getColumn(7).setMaxWidth(25);
		scrollPane3.setViewportView(table3);

		btnNewButton_1 = new JButton("Eliminar denuncia");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(369, 420, 150, 32);
		misDenuncias.add(btnNewButton_1);

		getContentPane().add(pestañas);
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent evt) {
				table.setModel(miControlador.crearTablaFav(modeloTabla1, 1));
				table2.setModel(miControlador.crearTablaFav(modeloTabla2, 2));
				table3.setModel(miControlador.crearTablaFav(modeloTabla3, 3));
			}
		});
		
		btnFav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filaSelect();
				table.setModel(miControlador.crearTablaFav(modeloTabla1, 1));
				table2.setModel(miControlador.crearTablaFav(modeloTabla2, 2));
				table3.setModel(miControlador.crearTablaFav(modeloTabla3, 3));
				
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String cp=txtCodigoPostal.getText().trim();

				int categoria = comboBox_1.getSelectedIndex();
				int estado = comboBox.getSelectedIndex();
				int tablaActu = pestañas.getSelectedIndex();
				
				switch (tablaActu) {
				case 0: 
					table.setModel(miControlador.filtrarTablas(modeloTabla1 ,cp, categoria, estado,tablaActu));
					break;
				case 1: 
					table2.setModel(miControlador.filtrarTablas(modeloTabla2,cp, categoria, estado,tablaActu));
					break;
				case 2: 
					table3.setModel(miControlador.filtrarTablas(modeloTabla3, cp, categoria, estado,tablaActu));
					break;
				}
			}
		});
	}

	/**
	 * Sets the Controlador for this class.
	 * 
	 * @param miControlador The Controlador to be set.
	 **/
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 * Sets the Modelo for this class.
	 * 
	 * @param miModelo The Modelo to be set.
	 **/
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public DefaultTableModel getTableModel(DefaultTableModel dtm) {
		return dtm;

	};
	
	public void filaSelect() {
	    int filaSeleccionadaT1 = table.getSelectedRow();
	    int filaSeleccionadaT2 = table2.getSelectedRow();
	    int filaSeleccionadaT3 = table3.getSelectedRow();
	    
	    if (filaSeleccionadaT1 != -1) {
	        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	        Object dato = modelo.getValueAt(filaSeleccionadaT1, 0);
	        Object dato1 = modelo.getValueAt(filaSeleccionadaT1, 7); 
	        miControlador.obtenerFav(dato, dato1);
	    } else if (filaSeleccionadaT2 != -1) {
	        DefaultTableModel modelo = (DefaultTableModel) table2.getModel();
	        Object dato = modelo.getValueAt(filaSeleccionadaT2, 0);
	        Object dato1 = modelo.getValueAt(filaSeleccionadaT2, 7);
	        miControlador.obtenerFav(dato, dato1);
	    } else if (filaSeleccionadaT3 != -1) {
	        DefaultTableModel modelo = (DefaultTableModel) table3.getModel();
	        Object dato = modelo.getValueAt(filaSeleccionadaT3, 0);
	        Object dato1 = modelo.getValueAt(filaSeleccionadaT3, 7);
	        miControlador.obtenerFav(dato, dato1);
	    } else {
	        System.out.println("No se ha seleccionado ninguna fila en la tabla.");
	    }
	}

	
	

}