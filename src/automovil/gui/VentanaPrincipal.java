/*Práctica 1
Paradigmas de Programación II
Iván Alexander Cortés Pérez
Grupo 512*/

package automovil.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class VentanaPrincipal extends JFrame {
	private JMenuBar menuBarra;
	private JMenu archivoMenu;
	private JMenu operacionesMenu;
	private JMenu ayudaMenu;
	private JMenuItem abrirArchivo;
	private JMenuItem guardarArchivo;
	private JMenuItem salirArchivo;
	private JMenuItem catalogoOperaciones;
	private JMenuItem consultaOperaciones;
	private JMenuItem acercadeAyuda;

	private static final long serialVersionUID = 1L;

	public VentanaPrincipal() {
		super("  Catálogo de Automóviles");

		this.setIconImage(
				Toolkit.getDefaultToolkit().getImage(getClass().getResource("/automovil/imagenes/icono.png")));

		// Menú (Opciones)

		archivoMenu = new JMenu("Archivo");
		archivoMenu.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/archivo.png")));
		archivoMenu.setMnemonic(KeyEvent.VK_A);
		archivoMenu.setToolTipText("Opciones de Archivo.");

		abrirArchivo = new JMenuItem("Abrir");
		abrirArchivo.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/abrir.png")));
		abrirArchivo.setMnemonic(KeyEvent.VK_B);
		abrirArchivo.setToolTipText("Abre el archivo.");
		abrirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		abrirArchivo.addActionListener(new ManejoEventos());

		guardarArchivo = new JMenuItem("Guardar");
		guardarArchivo.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/guardar.png")));
		guardarArchivo.setMnemonic(KeyEvent.VK_G);
		guardarArchivo.setToolTipText("Guarda los cambios realizados.");
		guardarArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		guardarArchivo.addActionListener(new ManejoEventos());

		salirArchivo = new JMenuItem("Salir");
		salirArchivo.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/salir.png")));
		salirArchivo.setMnemonic(KeyEvent.VK_S);
		salirArchivo.setToolTipText("Cierra la aplicación.");
		salirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		salirArchivo.addActionListener(new ManejoEventos());

		operacionesMenu = new JMenu("Operaciones");
		operacionesMenu.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/operaciones.png")));
		operacionesMenu.setMnemonic(KeyEvent.VK_O);
		operacionesMenu.setToolTipText("Opciones de operaciones.");

		consultaOperaciones = new JMenuItem("Consultas");
		consultaOperaciones.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/consultar.png")));
		consultaOperaciones.setMnemonic(KeyEvent.VK_N);
		consultaOperaciones.setToolTipText("Muestra la información extraída del archivo.");
		consultaOperaciones.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.SHIFT_DOWN_MASK));
		consultaOperaciones.addActionListener(new ManejoEventos());

		ayudaMenu = new JMenu("Ayuda");
		ayudaMenu.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/ayuda.png")));
		ayudaMenu.setMnemonic(KeyEvent.VK_Y);
		ayudaMenu.setToolTipText("Opciones de ayuda al usuario.");

		archivoMenu.add(abrirArchivo);
		archivoMenu.add(guardarArchivo);
		archivoMenu.addSeparator();
		archivoMenu.add(salirArchivo);

		catalogoOperaciones = new JMenuItem("Catálogo");
		catalogoOperaciones.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/catalogo.png")));
		catalogoOperaciones.setMnemonic(KeyEvent.VK_C);
		catalogoOperaciones.setToolTipText("Abre el catálogo para mostrar las opciones.");
		catalogoOperaciones.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.SHIFT_DOWN_MASK));
		catalogoOperaciones.addActionListener(new ManejoEventos());

		operacionesMenu.add(catalogoOperaciones);
		operacionesMenu.addSeparator();
		operacionesMenu.add(consultaOperaciones);

		ayudaMenu = new JMenu("Ayuda");
		ayudaMenu.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/ayuda.png")));
		ayudaMenu.setMnemonic(KeyEvent.VK_Y);
		ayudaMenu.setToolTipText("Ayuda al usuario.");

		acercadeAyuda = new JMenuItem("Acerca de...");
		acercadeAyuda.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/acercaDe.png")));
		acercadeAyuda.setMnemonic(KeyEvent.VK_E);
		acercadeAyuda.setToolTipText("Muestra información del sistema.");
		acercadeAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		acercadeAyuda.addActionListener(new ManejoEventos());

		menuBarra = new JMenuBar();
		menuBarra.add(archivoMenu);
		menuBarra.add(operacionesMenu);
		menuBarra.add(ayudaMenu);
		this.setJMenuBar(menuBarra);

		ayudaMenu.add(acercadeAyuda);

		// Clase anónima
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				metodoSalir();
			}
		});

		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);

		this.getContentPane().setLayout(new FlowLayout());
		JLabel fondo = new JLabel();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/automovil/imagenes/auto2.jpg"));
		Image imagenEscalada = imagenFondo.getImage().getScaledInstance(-1, getSize().height - 100, Image.SCALE_SMOOTH);
		fondo.setIcon(new ImageIcon(imagenEscalada));
		this.getContentPane().add(fondo);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);

	}

	private void metodoAbrir() {

	}

	private void metodoGuardar() {

	}

	private void metodoSalir() {
		dispose();
	}

	private void metodoCatalogo() {
		new Catalogo(this);
	}

	private void metodoConsultas() {

	}

	private void metodoAcercade() {
		JOptionPane.showMessageDialog(this,
				"Catálogo de Automóviles" + "\n\n" + "Realizado por:" + "\nIván Alexander Cortés Pérez" + "\n\n"
						+ "Derechos reservados UMAR " + '\u00A9' + " 2024",
				"Acerca de - Catálogo de Automóviles", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getResource("/automovil/Imagenes/auto.jpg")));
	}

	// Clase interna
	private class ManejoEventos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(abrirArchivo)) {
				metodoAbrir();
			} else if (e.getSource().equals(guardarArchivo)) {
				metodoGuardar();
			} else if (e.getSource().equals(salirArchivo)) {
				metodoSalir();
			} else if (e.getSource().equals(catalogoOperaciones)) {
				metodoCatalogo();
			} else if (e.getSource().equals(consultaOperaciones)) {
				metodoConsultas();
			} else if (e.getSource().equals(acercadeAyuda)) {
				metodoAcercade();
			}
		}
	}

}
