package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.demo.PieChartDemo1;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;

import Controlador.Controlador;
import Modelo.Modelo;

import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

import Modelo.*;
import Controlador.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class represents a GUI for a statistics page. It extends JFrame and
 * implements the Vista interface. It contains various Swing components like
 * JLabel, JPanel.
 * 
 * @author Henry Illescas
 */
public class _09_Estadisticas extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblRSB;
	private JLabel lblFotoPerfil;
	private JPanel panel_1;

	private Controlador miControlador;
	private Modelo miModelo;

	/**
	 * Constructor for the _09_Estadisticas class. Initializes the form and its
	 * components.
	 */
	public _09_Estadisticas() {
		setIconImage(new ImageIcon(getClass().getResource("img/Logo_peque.png")).getImage());
		setTitle("PaginaPrincipal");
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
			public void mouseReleased(MouseEvent e) {
				miControlador.cambiarPantalla(9, 7);
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
		lblFotoPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				miControlador.cambiarPantalla(9, 10);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblFotoPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblFotoPerfil.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFotoPerfil.setBounds(984, 21, 60, 60);
		panel.add(lblFotoPerfil);
		ImageIcon sizeFotoPerfil = new ImageIcon(imageFotoPerfil.getImage().getScaledInstance(lblFotoPerfil.getWidth(),
				lblFotoPerfil.getHeight(), Image.SCALE_SMOOTH));
		lblFotoPerfil.setIcon(sizeFotoPerfil);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(36, 113, 61));
		panel_1.setBounds(10, 122, 1054, 538);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Estadisticas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(447, 8, 180, 41);
		panel_1.add(lblNewLabel);
		
		JPanel panel_Categorias = new JPanel();
		panel_Categorias.setBounds(20, 60, 472, 228);
		 panel_Categorias.setLayout(new BorderLayout()); // Usa BorderLayout para que el gráfico se ajuste al tamaño del panel

		panel_1.add(panel_Categorias);
		
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        dataset.addValue(10, "Año 2024", "Iluminación");
	        dataset.addValue(20, "Año 2024", "Edificios");
	        dataset.addValue(30, "Año 2024", "Pavimento");
	        dataset.addValue(15, "Año 2024", "Naturaleza");
	        dataset.addValue(25, "Año 2024", "Limpieza");


	        // Crear el gráfico
	        JFreeChart chart = ChartFactory.createBarChart(
	                "Numero de denuncias por categoría",
	                "Categoria",
	                "Denuncias",
	                dataset
	        );
	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setMouseWheelEnabled(true);
	        chartPanel.setMinimumDrawWidth(200);
	        chartPanel.setMaximumDrawWidth(500);
	        chartPanel.setMaximumDrawHeight(500);

	        panel_Categorias.add(chartPanel,BorderLayout.CENTER);
			JPanel panel_DenunciasTotalesdelMes = new JPanel();
			panel_DenunciasTotalesdelMes.setBounds(550, 60, 472, 228);
			 panel_DenunciasTotalesdelMes.setLayout(new BorderLayout()); // Usa BorderLayout para que el gráfico se ajuste al tamaño del panel

			panel_1.add(panel_DenunciasTotalesdelMes);
			
			 DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		        dataset2.addValue(10, "Enero", "");
		        dataset2.addValue(20, "Febrero", "");
		        dataset2.addValue(30, "Marzo", "");
		        dataset2.addValue(15, "Abril", "");
		        dataset2.addValue(3, "Mayo", "");
		        dataset2.addValue(17, "Junio", "");
		        dataset2.addValue(7, "Julio", "");
		        dataset2.addValue(15, "Agosto", "");
		        dataset2.addValue(5, "Septiembre", "");
		        dataset2.addValue(20, "Octubre", "");
		        dataset2.addValue(50, "Noviembre", "");
		        dataset2.addValue(15, "Diciembre", "");


		        // Crear el gráfico
		        JFreeChart chart2 = ChartFactory.createBarChart(
		                "Numero de denuncias Mensuales",
		                "Mes",
		                "Denuncias",
		                dataset2
		        );
		        ChartPanel chartPanel2 = new ChartPanel(chart2);
		        chartPanel2.setMouseWheelEnabled(true);
		        chartPanel2.setMinimumDrawWidth(200);
		        chartPanel2.setMaximumDrawWidth(500);
		        chartPanel2.setMaximumDrawHeight(500);

		        panel_DenunciasTotalesdelMes.add(chartPanel2,BorderLayout.CENTER);
		        
		        JPanel panel_Montaña = new JPanel();
		        panel_Montaña.setBounds(20, 299, 1004, 215);
		        panel_Montaña.setLayout(new BorderLayout());
		        panel_1.add(panel_Montaña);
		        


		        // Crear el conjunto de datos para el histograma
		        HistogramDataset histogramDataset = new HistogramDataset();
		        double[] values = {50, 100, 50, 15, 15}; // Aquí deberías usar tus datos reales
		        histogramDataset.addSeries("Incidencias", values, 5);
		        // Crear el gráfico de histograma
		        JFreeChart histogramChart = ChartFactory.createHistogram(
		                "Histograma de incidencias",
		                "Frecuencia",
		                "Incidencias",
		                histogramDataset,
		                PlotOrientation.HORIZONTAL,
		                true, // Include legend
		                true,
		                false
		        );
		        ChartPanel histogramChartPanel = new ChartPanel(histogramChart);
		        histogramChartPanel.setHorizontalAxisTrace(true);
		        histogramChartPanel.setMouseZoomable(false);

		        // Añadir los gráficos al panel
		        panel_Montaña.add(histogramChartPanel, BorderLayout.CENTER);
	}

	/**
	 * This method sets the controller for the view.
	 * 
	 * @param miControlador The Controlador to be set.
	 */
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 * This method sets the model for the view.
	 * 
	 * @param @param miModelo The Modelo to be set.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}
}