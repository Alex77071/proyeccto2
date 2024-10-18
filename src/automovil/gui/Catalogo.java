/*Pr�ctica 1
Paradigmas de Programaci�n II
Iv�n Alexander Cort�s P�rez
Grupo 512*/

package automovil.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import automovil.utilerias.MiFocusTraversalPolicy;

public class Catalogo extends JDialog implements ItemListener {
	private static final long serialVersionUID = 1L;
	private VentanaPrincipal ventanaPrincipal;
	private JMenuBar menuBarra;
	private JMenu operacionesMenu;
	private JMenuItem nuevoOperaciones;
	private JMenuItem modificarOperaciones;
	private JMenuItem guardarOperaciones;
	private JMenuItem eliminarOperaciones;
	private JMenuItem cancelarOperaciones;
	private JButton botonNuevo;
	private JButton botonModificar;
	private JButton botonGuardar;
	private JButton botonEliminar;
	private JButton botonCancelar;
	private JComboBox<String> comboNombreAutomovil;

	// ENTIDAD

	private JTextField kilometrajeAuto;
	private JTextField precioAuto;
	private JTextField modeloAuto;
	private JTextField numSerie;
	private JTextField fechaFabricacion;
	private JComboBox<String> marcaAuto;
	private JRadioButton radioGasolina;
	private JRadioButton radioElectrico;
	private JRadioButton radioDiesel;
	private JCheckBox frontales;
	private JCheckBox laterales;
	private JCheckBox cortina;
	private JList<String> listaAccesorios;
	private JComboBox<String> comboAccesorios;

	public Catalogo(JFrame principal) {
		super(principal, "Cat�logo de Autom�viles", true);
		this.setIconImage(
				Toolkit.getDefaultToolkit().getImage(getClass().getResource("/automovil/imagenes/icono.png")));
		ventanaPrincipal = (VentanaPrincipal) principal;
		this.setSize(1280, 720);
		this.setLocationRelativeTo(ventanaPrincipal);

		// OPERACIONES (Men�)
		operacionesMenu = new JMenu("Operaciones");
		operacionesMenu.setIcon(new ImageIcon(getClass().getResource("/automovil/imagenes/operaciones.png")));
		operacionesMenu.setToolTipText("Operaciones del cat�logo...");
		operacionesMenu.setMnemonic(KeyEvent.VK_P);

		// NUEVO
		Action actionNuevo = new AbstractAction("Nuevo",
				new ImageIcon(getClass().getResource("/automovil/imagenes/nuevo.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoNuevo();

			}
		};
		actionNuevo.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		actionNuevo.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		actionNuevo.putValue(Action.SHORT_DESCRIPTION, "Comienza con la personalizaci�n");
		nuevoOperaciones = new JMenuItem(actionNuevo);

		// MODIFICAR
		Action actionModificar = new AbstractAction("Modificar",
				new ImageIcon(getClass().getResource("/automovil/imagenes/modificar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoModificar();
			}
		};
		actionModificar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		actionModificar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		actionModificar.putValue(Action.SHORT_DESCRIPTION, "Realiza modificaciones al cat�logo");
		modificarOperaciones = new JMenuItem(actionModificar);

		// GUARDAR
		Action actionGuardar = new AbstractAction("Guardar",
				new ImageIcon(getClass().getResource("/automovil/imagenes/guardar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoGuardar();
			}
		};
		actionGuardar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		actionGuardar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_G);
		actionGuardar.putValue(Action.SHORT_DESCRIPTION, "Guarda los cambios realizados");
		guardarOperaciones = new JMenuItem(actionGuardar);

		// ELIMINAR
		Action actionEliminar = new AbstractAction("Eliminar",
				new ImageIcon(getClass().getResource("/automovil/imagenes/eliminar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoEliminar();
			}
		};
		actionEliminar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		actionEliminar.putValue(Action.SHORT_DESCRIPTION, "Elimina el dato ingresado");
		eliminarOperaciones = new JMenuItem(actionEliminar);

		// CANCELAR
		Action actionCancelar = new AbstractAction("Cancelar",
				new ImageIcon(getClass().getResource("/automovil/imagenes/cancelar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoCancelar();
			}
		};
		actionCancelar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		actionCancelar.putValue(Action.SHORT_DESCRIPTION, "Cancela la operaci�n");
		cancelarOperaciones = new JMenuItem(actionCancelar);

		menuBarra = new JMenuBar();
		menuBarra.add(operacionesMenu);
		operacionesMenu.add(nuevoOperaciones);
		operacionesMenu.add(modificarOperaciones);
		operacionesMenu.add(guardarOperaciones);
		operacionesMenu.add(eliminarOperaciones);
		operacionesMenu.add(cancelarOperaciones);
		this.setJMenuBar(menuBarra);

		// DISTRIBUCI�N DE PANELES

		getContentPane().setLayout(new BorderLayout());
		JPanel panelNorte = new JPanel();
		JPanel panelEste = new JPanel();
		JPanel panelOeste = new JPanel();
		JPanel panelCentro = new JPanel();
		JPanel panelAux = new JPanel();

		// DISTRIBUCI�N LADO OESTE

		// FILAS Y COLUMNAS
		panelOeste.setLayout(new GridLayout(6, 2));
		panelOeste.setPreferredSize(new Dimension(450, 0));

		JLabel precio = new JLabel("Precio");
		panelAux = new JPanel();
		precio.setDisplayedMnemonic(KeyEvent.VK_P);
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		panelAux.add(precio);
		panelOeste.add(panelAux);
		// CAMPO TEXTO
		precioAuto = new JTextField("");
		precioAuto.setToolTipText("Precio del Autom�vil");
		precioAuto.setPreferredSize(new Dimension(110, 20));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(precioAuto);
		panelOeste.add(panelAux);

		JLabel km = new JLabel("Kilometraje");
		panelAux = new JPanel();
		km.setDisplayedMnemonic(KeyEvent.VK_K);
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 0));
		panelAux.add(km);
		panelOeste.add(panelAux);
		// CAMPO TEXTO (km)
		kilometrajeAuto = new JTextField("");
		kilometrajeAuto.setPreferredSize(new Dimension(100, 20));
		kilometrajeAuto.setToolTipText("Kilometraje del Autom�vil");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(kilometrajeAuto);
		panelOeste.add(panelAux);

		JLabel fecha = new JLabel("Fecha Fabricaci�n");
		panelAux = new JPanel();
		fecha.setDisplayedMnemonic(KeyEvent.VK_F);
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER, 126, 0));
		panelAux.add(fecha);
		panelOeste.add(panelAux);
		// CAMPO TEXTO (Fecha)
		fechaFabricacion = new JTextField("");
		fechaFabricacion.setToolTipText("Fecha de fabricaci�n del Autom�vil");
		fechaFabricacion.setPreferredSize(new Dimension(100, 20));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(fechaFabricacion);
		panelOeste.add(panelAux);

		JLabel marca = new JLabel("Marca");
		panelAux = new JPanel();
		marca.setDisplayedMnemonic(KeyEvent.VK_R);
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER, 126, 0));
		panelAux.add(marca);
		panelOeste.add(panelAux);
		// OPCIONES MARCA
		marcaAuto = new JComboBox<String>();
		marcaAuto.setToolTipText("Marca del Autom�vil");
		marcaAuto.setEditable(true);
		marcaAuto.setPreferredSize(new Dimension(192, 20));

		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(marcaAuto);
		panelOeste.add(panelAux);

		JLabel modelo = new JLabel("Modelo");
		panelAux = new JPanel();
		modelo.setDisplayedMnemonic(KeyEvent.VK_D);
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 0));
		panelAux.add(modelo);
		panelOeste.add(panelAux);
		// CAMPO TEXTO (Modelo)
		modeloAuto = new JTextField("");
		modeloAuto.setToolTipText("Modelo del Autom�vil");
		modeloAuto.setPreferredSize(new Dimension(100, 20));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(modeloAuto);
		panelOeste.add(panelAux);

		JLabel combustible = new JLabel("Tipo Combustible");
		panelAux = new JPanel();
		combustible.setDisplayedMnemonic(KeyEvent.VK_C);
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 0));
		panelAux.add(combustible);
		panelOeste.add(panelAux);
		// RADIOS TIPO DE COMBUSTIBLE
		radioGasolina = new JRadioButton("Gasolina");
		radioGasolina.setToolTipText("Autom�vil a gasolina");
		radioElectrico = new JRadioButton("El�ctrico");
		radioElectrico.setToolTipText("Autom�vil el�ctrico");
		radioDiesel = new JRadioButton("Diesel");
		radioDiesel.setToolTipText("Autom�vil a diesel");
		combustible.setLabelFor(radioGasolina);
		ButtonGroup group = new ButtonGroup();
		group.add(radioGasolina);
		group.add(radioElectrico);
		group.add(radioDiesel);
		radioGasolina.setSelected(true);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(radioGasolina);
		panelAux.add(radioElectrico);
		panelAux.add(radioDiesel);
		panelOeste.add(panelAux);

		// DISTRIBUCI�N LADO CENTRO
		panelCentro.setLayout(new GridLayout(6, 2));

		JLabel seguridad = new JLabel("Sistema Seguridad");
		panelAux = new JPanel();
		seguridad.setDisplayedMnemonic(KeyEvent.VK_I);
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		panelAux.add(seguridad);
		panelCentro.add(panelAux);
		// COMBOBOX (Seguridad)
		frontales = new JCheckBox("Frontales");
		frontales.setToolTipText("Bolsas de aire frontales");
		laterales = new JCheckBox("Laterales");
		laterales.setToolTipText("Bolsas de aire laterales");
		cortina = new JCheckBox("Cortina");
		cortina.setToolTipText("Bolsas de aire tipo cortina");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(frontales);
		panelAux.add(laterales);
		panelAux.add(cortina);
		panelCentro.add(panelAux);

		JLabel serie = new JLabel("N�mero Serie");
		panelAux = new JPanel();
		serie.setDisplayedMnemonic(KeyEvent.VK_E);
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 0));
		panelAux.add(serie);
		panelCentro.add(panelAux);
		// CAMPO TEXTO (Serie)
		numSerie = new JTextField("");
		numSerie.setToolTipText("Serie del Autom�vil");
		numSerie.setPreferredSize(new Dimension(100, 20));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(numSerie);
		panelCentro.add(panelAux);

		JLabel caracteristicas = new JLabel("Caracter�stica Opcional");
		panelAux = new JPanel();
		caracteristicas.setDisplayedMnemonic(KeyEvent.VK_T);
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
		panelAux.add(caracteristicas);
		panelCentro.add(panelAux);
		//
		comboAccesorios = new JComboBox<String>();
		comboAccesorios.setEditable(true);
		comboAccesorios.setToolTipText("Caracteristicas opcionales del Autom�vil");
		comboAccesorios.setPreferredSize(new Dimension(192, 20));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(comboAccesorios);
		panelCentro.add(panelAux);

		JLabel espacioAccesorios = new JLabel(" ");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(espacioAccesorios);
		panelCentro.add(panelAux);
		// Agregar accesorios
		DefaultListModel<String> auto = new DefaultListModel<>();
		listaAccesorios = new JList<>(auto);
		JScrollPane scrollPanel2 = new JScrollPane(listaAccesorios);
		scrollPanel2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel2.setPreferredSize(new Dimension(192, 70));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(scrollPanel2);
		panelCentro.add(panelAux);

		JLabel espacioBotones = new JLabel(" ");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(espacioBotones);
		panelCentro.add(panelAux);

		// Bot�n Agregar
		Action actionAgregar = new AbstractAction("Agregar",
				new ImageIcon(getClass().getResource("/automovil/imagenes/agregar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoAgregar();
			}
		};
		actionAgregar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		actionAgregar.putValue(Action.SHORT_DESCRIPTION, "Agrega accesorios al cat�logo");
		JButton botonAgregar = new JButton(actionAgregar);

		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(botonAgregar);

		// Bot�n Quitar
		Action actionQuitar = new AbstractAction("Quitar",
				new ImageIcon(getClass().getResource("/automovil/imagenes/quitar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoQuitar();
			}
		};
		actionQuitar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_T);
		actionQuitar.putValue(Action.SHORT_DESCRIPTION, "Quita accesorios del cat�logo");
		JButton botonQuitar = new JButton(actionQuitar);

		panelAux.add(botonQuitar);
		panelCentro.add(panelAux);

		JLabel imagen = new JLabel("Imagen");
		panelAux = new JPanel();
		imagen.setDisplayedMnemonic(KeyEvent.VK_M);
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 0));
		panelAux.add(imagen);
		panelCentro.add(panelAux);
		JLabel imagenn = new JLabel("Imagen");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(imagenn);
		panelCentro.add(panelAux);

		// Bot�n Seleccionar
		Action actionselec = new AbstractAction("Seleccionar",
				new ImageIcon(getClass().getResource("/automovil/imagenes/seleccionar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoSeleccionar();
			}
		};
		actionselec.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
		actionselec.putValue(Action.SHORT_DESCRIPTION, "Selecci�n de la imagen");
		JButton seleccionar = new JButton(actionselec);
		panelAux.add(seleccionar);

		/// DISTRIBUCI�N LADO NORTE

		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));

		JLabel nombreEntidad = new JLabel("Autom�vil");
		nombreEntidad.setDisplayedMnemonic(KeyEvent.VK_A);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(nombreEntidad);
		panelNorte.add(panelAux);

		comboNombreAutomovil = new JComboBox<String>();
		comboNombreAutomovil.setPreferredSize(new Dimension(500, 20));
		comboNombreAutomovil.setToolTipText("Despliega lista de autom�viles");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(comboNombreAutomovil);
		panelNorte.add(panelAux);

		// DISTRIBUCI�N LADO ESTE

		panelEste.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelEste.setLayout(new GridLayout(5, 2));

		// Bot�n Nuevo
		botonNuevo = new JButton(actionNuevo);
		botonNuevo.getActionMap().put("bot�nNue", actionNuevo);
		botonNuevo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) actionNuevo.getValue(Action.ACCELERATOR_KEY), "botonNue");
		botonNuevo.setPreferredSize(new Dimension(130, 35));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(botonNuevo);
		panelEste.add(panelAux);

		// Bot�n Modificar
		botonModificar = new JButton(actionModificar);
		botonModificar.getActionMap().put("bot�nMod", actionModificar);
		botonModificar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) actionModificar.getValue(Action.ACCELERATOR_KEY), "bot�nMod");
		botonModificar.setPreferredSize(new Dimension(130, 35));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(botonModificar);
		panelEste.add(panelAux);

		// Bot�n Guardar
		botonGuardar = new JButton(actionGuardar);
		botonGuardar.getActionMap().put("bot�nGuar", actionGuardar);
		botonGuardar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) actionGuardar.getValue(Action.ACCELERATOR_KEY), "bot�nGuar");
		botonGuardar.setPreferredSize(new Dimension(130, 35));
		panelAux = new JPanel();
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(botonGuardar);
		panelEste.add(panelAux);

		// Bot�n Eliminar
		botonEliminar = new JButton(actionEliminar);
		botonEliminar.setPreferredSize(new Dimension(130, 35));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAux.add(botonEliminar);
		panelEste.add(panelAux);

		// Bot�n Cancelar
		botonCancelar = new JButton(actionCancelar);
		botonCancelar.setPreferredSize(new Dimension(130, 35));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(botonCancelar);
		panelEste.add(panelAux);

		getContentPane().add(panelNorte, BorderLayout.NORTH);
		getContentPane().add(panelEste, BorderLayout.EAST);
		getContentPane().add(panelOeste, BorderLayout.WEST);
		getContentPane().add(panelCentro, BorderLayout.CENTER);

		establecerPoliticaFoco();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}

	// CAMBIAR CAMPO
	private void establecerPoliticaFoco() {

		Vector<Component> componentes = new Vector<Component>();
		componentes.add(botonNuevo);
		componentes.add(marcaAuto);
		componentes.add(modeloAuto);
		componentes.add(precioAuto);
		componentes.add(kilometrajeAuto);
		componentes.add(fechaFabricacion);
		componentes.add(numSerie);
		componentes.add(frontales);
		componentes.add(laterales);
		componentes.add(cortina);
		componentes.add(radioGasolina);
		componentes.add(radioDiesel);
		componentes.add(radioElectrico);
		componentes.add(listaAccesorios);
		componentes.add(botonGuardar);

		MiFocusTraversalPolicy politicaFoco = new MiFocusTraversalPolicy(componentes);
		this.setFocusTraversalPolicy(politicaFoco);

	}

	// NUEVO
	private void metodoNuevo() {
		habilitarCampos(true);
	    limpiarCampos();
	    
	    botonGuardar.setEnabled(true);
	    guardarOperaciones.setEnabled(true);
	    botonCancelar.setEnabled(true);
	    cancelarOperaciones.setEnabled(true);
	    
	    botonNuevo.setEnabled(false);
	    nuevoOperaciones.setEnabled(false);
	    botonModificar.setEnabled(false);
	    modificarOperaciones.setEnabled(false);
	    botonEliminar.setEnabled(false);
	    eliminarOperaciones.setEnabled(false);

	    comboNombreAutomovil.setEnabled(false);

		JOptionPane.showMessageDialog(this, "Nuevo", "Nuevo", JOptionPane.INFORMATION_MESSAGE);
	}

	// MODIFICAR
	private void metodoModificar() {
		JOptionPane.showMessageDialog(this, "Modificar", "Modificar", JOptionPane.INFORMATION_MESSAGE);
	}

	// GUARDAR
	private void metodoGuardar() {
		JOptionPane.showMessageDialog(this, "Guardar", "Guardar", JOptionPane.INFORMATION_MESSAGE);

	}

	// ELIMINAR
	private void metodoEliminar() {
		JOptionPane.showMessageDialog(this, "Eliminar", "Eliminar", JOptionPane.INFORMATION_MESSAGE);

	}

	// CANCELAR
	private void metodoCancelar() {
		habilitarCampos(false);
	    limpiarCampos();
	    
	    botonNuevo.setEnabled(true);
	    nuevoOperaciones.setEnabled(true);
	    
	    botonGuardar.setEnabled(false);
	    guardarOperaciones.setEnabled(false);
	    botonCancelar.setEnabled(false);
	    cancelarOperaciones.setEnabled(false);
	    
	    verificarLista();
	 
	    
		JOptionPane.showMessageDialog(this, "Cancelar", "Cancelar", JOptionPane.INFORMATION_MESSAGE);

	}

	// AGREGAR
	private void metodoAgregar() {
		JOptionPane.showMessageDialog(this, "Agregar", "Agregar", JOptionPane.INFORMATION_MESSAGE);

	}

	// QUITAR
	private void metodoQuitar() {
		JOptionPane.showMessageDialog(this, "Quitar", "Quitar", JOptionPane.INFORMATION_MESSAGE);

	}

	// SELECCIONAR
	private void metodoSeleccionar() {
		JOptionPane.showMessageDialog(this, "Seleccionar", "Seleccionar", JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}

	// Limpiar campos (13)
	/*
	 * Limpiar� el contenido de los campos de texto del di�logo y seleccionar� el
	 * valor por defecto en los radios / listas desplegables con opciones fijas.
	 */
	private void limpiarCampos() {
		// Limpir campos de texto
		kilometrajeAuto.setText("");
		precioAuto.setText("");
		modeloAuto.setText("");
		numSerie.setText("");
		fechaFabricacion.setText("");

		// Limpiar los JComboBox
		marcaAuto.setSelectedIndex(0);
		comboAccesorios.setSelectedIndex(0);
		comboNombreAutomovil.setSelectedIndex(0);

		// Limpiar los JRadioButton
		radioGasolina.setSelected(true);
		radioElectrico.setSelected(false);
		radioDiesel.setSelected(true);

		// Limpiar los JChecbox
		frontales.setSelected(false);
		laterales.setSelected(false);
		cortina.setSelected(false);

	}

	// Habilitar campos (14).
	/*
	 * Habilitar� o deshabilitar� la escritura de los campos de texto y habilitar� o
	 * deshabilitar� al resto de los componentes gr�ficos del �rea de trabajo, seg�n
	 * el valor boolean recibido.
	 */
	private void habilitarCampos(boolean habilitar) {
		// Campos de texto
		kilometrajeAuto.setEnabled(habilitar);
		precioAuto.setEnabled(habilitar);
		modeloAuto.setEnabled(habilitar);
		numSerie.setEnabled(habilitar);
		fechaFabricacion.setEnabled(habilitar);

		// Campos JComboBox
		marcaAuto.setEnabled(habilitar);
		comboAccesorios.setEnabled(habilitar);

		// Campos JRadioButton
		radioGasolina.setEnabled(habilitar);
		radioElectrico.setEnabled(habilitar);
		radioDiesel.setEnabled(habilitar);

		// Campos JChecBox
		frontales.setEnabled(habilitar);
		laterales.setEnabled(habilitar);
		cortina.setEnabled(habilitar);

	}

	// Verificar lista (15).
	/*
	 * Verificar� si la lista desplegable principal tiene elementos. Si los tiene,
	 * habilitar� a los botones y men�s �Modificar� y �Eliminar�,
	 */
	private void verificarLista() {
		if (comboNombreAutomovil.getItemCount() > 0) {
			botonModificar.setEnabled(true);
			botonEliminar.setEnabled(true);
			modificarOperaciones.setEnabled(true);
			eliminarOperaciones.setEnabled(true);
			comboNombreAutomovil.setEnabled(true);
		} else {
			botonModificar.setEnabled(false);
			botonEliminar.setEnabled(false);
			modificarOperaciones.setEnabled(false);
			eliminarOperaciones.setEnabled(false);
			comboNombreAutomovil.setEnabled(false);
		}
	}

	// Inicializar (16)
	/*
	 * Es llamado para inicializar el di�logo, deshabilitar� a los elementos
	 * gr�ficos, habilitar� al bot�n y men� �Nuevo� y deshabilitar� a los botones y
	 * men�s �Guardar� y �Cancelar�.
	 */
	private void inicializar() {
		habilitarCampos(false);

		botonNuevo.setEnabled(true);
		nuevoOperaciones.setEnabled(true);

		botonGuardar.setEnabled(true);
		guardarOperaciones.setEnabled(true);
		botonCancelar.setEnabled(false);
		cancelarOperaciones.setEnabled(false);
	}
	
	

}
