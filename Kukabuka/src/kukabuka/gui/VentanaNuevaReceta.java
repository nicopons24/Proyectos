package kukabuka.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kukabuka.config.Idioma;
import kukabuka.controller.ControladorPrincipal;
import kukabuka.font.FuenteKukabuka;
import kukabuka.model.Datos;
import kukabuka.model.Ingrediente;
import kukabuka.model.Usuario;
import kukabuka.other.BotonKukabuka;
import kukabuka.other.JFrameKukabuka;
import kukabuka.other.PanelKukabuka;


public class VentanaNuevaReceta extends JFrameKukabuka {
	
	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	private static final Color COLOR_PANEL = new Color(240 , 0, 0, 230);
	
	private Idioma idioma = Idioma.getInstance();
	private Datos datos = Datos.getInstance();
	private int x, y, largo, ancho;
	private Dimension size;
	private Usuario user;
	private JPanel panelDatos, panelIngredientes, panelPreparacion, panelMultimedia;
	private JTextField txtTitulo, txtPais, txtAutor, txtIngPrincipal, txtNombreIng, txtTipoIng;
	private JTextArea txtDescripcion, txtPreparacion, txtConsejos;
	private JComboBox<String> cboxTipo, cboxCategoria, cboxDificultad;
	private JComboBox<Ingrediente> cboxIngrediente;
	private JSpinner spnDuracion;
	private JList<Ingrediente> listIngredientes;
	private JList<String> listMultimedia;
	private BotonKukabuka btnAddVideo, btnAddImagen, btnGuardar, btnBorrarDatos, btnAdd, btnBorrarIngredientes, btnBorrarPreparacion, btnBorrarMultimedia, btnQuitarIng, btnQuitarMultimedia, btnCrearIng;
	private DefaultListModel<Ingrediente> modeloIngredientes;
	private DefaultListModel<String> modeloMultimedia;
	private SpinnerNumberModel spnModeloDuracion;
	private JLabel lblTitulo, lblDescripcion, lblPais, lblTipo, lblCategoria, lblDificultad, lblDuracion, lblMin, lblConsejos, lblAutor, lblIngredientePrincipal, lblIngrediente, lblListaDeIngredientes, lblNuevoIngrediente, lblNombre, lblTipoIng, lblModoPreparacion, lblListaMultimedia;
	
	public VentanaNuevaReceta(Dimension d, Usuario u) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/kukabuka/images/logoKukabuka.png")));
		
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.size = d;
		this.user = u;
		this.x = (int) (size.getWidth()/65);
		this.y = (int) (size.getHeight()/20);
		this.ancho = (int) (size.getWidth()/4.4);
		this.largo = (int) (size.getHeight()/1.1);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(size);
		setLocationRelativeTo(null);
		setResizable(false);
		PanelKukabuka p = new PanelKukabuka(getSize(), this);
		setContentPane(p);
		
		datos();
		ingredientes();
		preparacion();
		multimedia();
		
		btnGuardar = new BotonKukabuka(null);
		btnGuardar.setBounds(x,(int) (size.getHeight()/1.3)+y*2, ancho,(int) size.getHeight()/45);
		getContentPane().add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().guardarReceta();				
			}
		});
		
		btnGuardar.setForeground(Color.BLACK);
		
		setTitles();
		
		setVisible(true);
	}

	private void multimedia() {
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelMultimedia = new JPanel();
		panelMultimedia.setBackground(COLOR_PANEL);
		GridBagLayout gbl = new GridBagLayout();
		panelMultimedia.setLayout(gbl);
		panelMultimedia.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panelMultimedia.setBounds(x, y, ancho, (int) (size.getHeight()/1.3));
		getContentPane().add(panelMultimedia);
		
		lblListaMultimedia = new JLabel();
		lblListaMultimedia.setFont(FONT);
		lblListaMultimedia.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblListaMultimedia = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelMultimedia.add(lblListaMultimedia, gbc_lblListaMultimedia);
		
		btnQuitarMultimedia = new BotonKukabuka(null);
		GridBagConstraints posQuitar = new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelMultimedia.add(btnQuitarMultimedia, posQuitar);
		btnQuitarMultimedia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().quitarMultimedia();
				
			}
		});
		
		listMultimedia = new JList<String>();
		modeloMultimedia = new DefaultListModel<String>();
		listMultimedia.setFont(FONT);
		listMultimedia.setForeground(Color.BLACK);
		listMultimedia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMultimedia.setModel(modeloMultimedia);
		GridBagConstraints posLista = new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		JScrollPane scrollMultimedia = new JScrollPane(listMultimedia, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelMultimedia.add(scrollMultimedia, posLista);
		
		btnAddVideo = new BotonKukabuka(null);
		GridBagConstraints posVideo = new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelMultimedia.add(btnAddVideo, posVideo);
		btnAddVideo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().agregarVideo();
			}
		});
		
		btnAddImagen = new BotonKukabuka(null);
		GridBagConstraints posImagenes = new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelMultimedia.add(btnAddImagen, posImagenes);
		btnAddImagen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().agregarImagen();				
			}

		});
		
		btnBorrarMultimedia = new BotonKukabuka(null);
		GridBagConstraints posBorrar = new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelMultimedia.add(btnBorrarMultimedia, posBorrar);
		btnBorrarMultimedia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().borrarMultimedia();
				
			}
		});
		
	}

	private void preparacion() {
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelPreparacion = new JPanel();
		panelPreparacion.setBackground(COLOR_PANEL);
		GridBagLayout gbl = new GridBagLayout();
		panelPreparacion.setLayout(gbl);
		panelPreparacion.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panelPreparacion.setBounds(x, y, ancho, largo);
		getContentPane().add(panelPreparacion);
		
		this.x = (int) (x+ancho+(size.getWidth()/65));
		
		lblModoPreparacion = new JLabel();
		lblModoPreparacion.setFont(FONT);
		lblModoPreparacion.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblModoPreparacion = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelPreparacion.add(lblModoPreparacion, gbc_lblModoPreparacion);
		
		txtPreparacion = new JTextArea();
		txtPreparacion.setFont(FONT);
		txtPreparacion.setForeground(Color.BLACK);
		GridBagConstraints posPreparacion = new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		txtPreparacion.setLineWrap(true);
		txtPreparacion.setWrapStyleWord(true);
		JScrollPane scrollPreparacion = new JScrollPane(txtPreparacion, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelPreparacion.add(scrollPreparacion, posPreparacion);
		
		btnBorrarPreparacion = new BotonKukabuka(null);
		GridBagConstraints posBorrar = new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelPreparacion.add(btnBorrarPreparacion, posBorrar);
		btnBorrarPreparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().borrarPreparacion();
			}
		});
		
	}

	private void ingredientes() {
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelIngredientes = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelIngredientes.setLayout(gbl);
		panelIngredientes.setBackground(COLOR_PANEL);
		panelIngredientes.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panelIngredientes.setBounds(x, y, ancho, largo);
		getContentPane().add(panelIngredientes);
		
		this.x = (int) (x+ancho+(size.getWidth()/65));
		
		lblIngredientePrincipal = new JLabel();
		lblIngredientePrincipal.setFont(FONT);
		lblIngredientePrincipal.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblIngredientePrincipal = new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(lblIngredientePrincipal, gbc_lblIngredientePrincipal);
		
		txtIngPrincipal = new JTextField();
		txtIngPrincipal.setFont(FONT);
		txtIngPrincipal.setForeground(Color.BLACK);
		GridBagConstraints posIngPrincipalTxt = new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(txtIngPrincipal, posIngPrincipalTxt);
		
		lblIngrediente = new JLabel();
		lblIngrediente.setFont(FONT);
		lblIngrediente.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblIngrediente = new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(lblIngrediente, gbc_lblIngrediente);
		
		cboxIngrediente = new JComboBox<Ingrediente>();
		cboxIngrediente.setFont(FONT);
		cboxIngrediente.setForeground(Color.BLACK);
		cboxIngrediente.addItem(null);
		GridBagConstraints posIngredienteBox = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(cboxIngrediente, posIngredienteBox);
		
		btnAdd = new BotonKukabuka(null);
		GridBagConstraints posAdd = new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(btnAdd, posAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().agregarIngredienteExist();
				
			}
		});
		
		btnQuitarIng = new BotonKukabuka(null);
		GridBagConstraints posQuitar = new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(btnQuitarIng, posQuitar);
		btnQuitarIng.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().quitarIngrediente();
				
			}
		});
		
		lblListaDeIngredientes = new JLabel();
		lblListaDeIngredientes.setFont(FONT);
		lblListaDeIngredientes.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblListaDeIngredientes = new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(lblListaDeIngredientes, gbc_lblListaDeIngredientes);
		
		listIngredientes = new JList<Ingrediente>();
		modeloIngredientes = new DefaultListModel<Ingrediente>();
		listIngredientes.setFont(FONT);
		listIngredientes.setForeground(Color.BLACK);
		listIngredientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listIngredientes.setModel(modeloIngredientes);
		GridBagConstraints posListaIng = new GridBagConstraints(0, 5, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		JScrollPane scrollIngredientes = new JScrollPane(listIngredientes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelIngredientes.add(scrollIngredientes, posListaIng);
		
		lblNuevoIngrediente = new JLabel();
		lblNuevoIngrediente.setFont(FONT);
		lblNuevoIngrediente.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblNuevoIngrediente = new GridBagConstraints(0, 6, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(lblNuevoIngrediente, gbc_lblNuevoIngrediente);
		
		lblNombre = new JLabel();
		lblNombre.setFont(FONT);
		lblNombre.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints(0, 7, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(lblNombre, gbc_lblNombre);
		
		txtNombreIng = new JTextField();
		txtNombreIng.setFont(FONT);
		txtNombreIng.setForeground(Color.BLACK);
		GridBagConstraints posNombreTxt = new GridBagConstraints(1, 7, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(txtNombreIng, posNombreTxt);
		
		lblTipoIng = new JLabel();
		lblTipoIng.setFont(FONT);
		lblTipoIng.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblTipoIng = new GridBagConstraints(0, 8, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(lblTipoIng, gbc_lblTipoIng);
		
		txtTipoIng = new JTextField();
		txtTipoIng.setFont(FONT);
		txtTipoIng.setForeground(Color.BLACK);
		GridBagConstraints posTipoTxt = new GridBagConstraints(1, 8, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(txtTipoIng, posTipoTxt);
		
		btnCrearIng = new BotonKukabuka(null);
		GridBagConstraints posCrear = new GridBagConstraints(1, 6, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelIngredientes.add(btnCrearIng, posCrear);
		btnCrearIng.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().agregaNuevoIngrediente();				
			}
		});
		
		btnBorrarIngredientes = new BotonKukabuka(null);
		GridBagConstraints posBorrar = new GridBagConstraints(0, 9, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelIngredientes.add(btnBorrarIngredientes, posBorrar);
		btnBorrarIngredientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().borrarIngredientes();				
			}
		});
		
		
	}

	private void datos() {
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelDatos = new JPanel();
		panelDatos.setBackground(COLOR_PANEL);
		panelDatos.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panelDatos.setBounds(x, y,ancho, largo);
		GridBagLayout gbl = new GridBagLayout();
		panelDatos.setLayout(gbl);
		getContentPane().add(panelDatos);
		
		this.x = x+ancho+x;
		
		lblTitulo = new JLabel();
		lblTitulo.setFont(FONT);
		lblTitulo.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints(0, 0, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblTitulo, gbc_lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setFont(FONT);
		txtTitulo.setForeground(Color.BLACK);
		GridBagConstraints posTituloTxt = new GridBagConstraints(0, 1, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(txtTitulo, posTituloTxt);
		
		lblDescripcion = new JLabel();
		lblDescripcion.setFont(FONT);
		lblDescripcion.setForeground(Color.BLACK);
		GridBagConstraints	gbc_lblDescripcion = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblDescripcion, gbc_lblDescripcion);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setFont(FONT);
		txtDescripcion.setForeground(Color.BLACK);
		GridBagConstraints posDescTxt = new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane scrollDesc = new JScrollPane(txtDescripcion, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelDatos.add(scrollDesc,posDescTxt);
		
		lblPais = new JLabel();
		lblPais.setFont(FONT);
		lblPais.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblPais = new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblPais, gbc_lblPais);
		
		txtPais = new JTextField();
		txtPais.setFont(FONT);
		txtPais.setForeground(Color.BLACK);
		GridBagConstraints posPaisTxt = new GridBagConstraints(0, 5, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(txtPais, posPaisTxt);
		
		lblTipo = new JLabel();
		lblTipo.setFont(FONT);
		lblTipo.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblTipo = new GridBagConstraints(0, 6, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblTipo, gbc_lblTipo);
		
		cboxTipo = new JComboBox<String>();
		cboxTipo.setFont(FONT);
		cboxTipo.setForeground(Color.BLACK);
		GridBagConstraints posTipoBox = new GridBagConstraints(1, 6, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(cboxTipo, posTipoBox);
		
		lblCategoria = new JLabel();
		lblCategoria.setFont(FONT);
		lblCategoria.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints(0, 7, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblCategoria, gbc_lblCategoria);
		
		cboxCategoria = new JComboBox<String>();
		cboxCategoria.setFont(FONT);
		cboxCategoria.setForeground(Color.BLACK);
		GridBagConstraints posCatBox = new GridBagConstraints(1, 7, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(cboxCategoria, posCatBox);
		
		lblDificultad = new JLabel();
		lblDificultad.setFont(FONT);
		lblDificultad.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblDificultad = new GridBagConstraints(0, 8, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblDificultad, gbc_lblDificultad);
		
		cboxDificultad = new JComboBox<String>();
		cboxDificultad.setFont(FONT);
		cboxDificultad.setForeground(Color.BLACK);
		GridBagConstraints posDificultadBox = new GridBagConstraints(1, 8, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(cboxDificultad, posDificultadBox);
		
		lblDuracion = new JLabel();
		lblDuracion.setFont(FONT);
		lblDuracion.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints(0, 9, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblDuracion, gbc_lblDuracion);
		
		spnDuracion = new JSpinner();
		spnModeloDuracion = new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1));
		spnDuracion.setModel(spnModeloDuracion);
		spnDuracion.setFont(FONT);
		Component c = spnDuracion.getEditor().getComponent(0);
		c.setForeground(Color.BLACK);
		GridBagConstraints posDuracionNum = new GridBagConstraints(1, 9, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(spnDuracion, posDuracionNum);
		
		lblMin = new JLabel();
		lblMin.setFont(FONT);
		lblMin.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblMin = new GridBagConstraints(2, 9, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblMin,gbc_lblMin);
		
		lblConsejos = new JLabel();
		lblConsejos.setFont(FONT);
		lblConsejos.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblConsejos = new GridBagConstraints(0, 10, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblConsejos, gbc_lblConsejos);
		
		txtConsejos = new JTextArea();
		txtConsejos.setFont(FONT);
		txtConsejos.setForeground(Color.BLACK);
		txtConsejos.setLineWrap(true);
		txtConsejos.setWrapStyleWord(true);
		GridBagConstraints posConsejosTxt = new GridBagConstraints(0, 11, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		JScrollPane scrollConsejos = new JScrollPane(txtConsejos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelDatos.add(scrollConsejos, posConsejosTxt);
		
		lblAutor = new JLabel();
		lblAutor.setFont(FONT);
		lblAutor.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblAutor = new GridBagConstraints(0, 12, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(lblAutor, gbc_lblAutor);
		
		txtAutor = new JTextField();
		txtAutor.setFont(FONT);
		txtAutor.setForeground(Color.BLACK);
		txtAutor.setText(user.getNomUsuario());
		GridBagConstraints posAutorTxt = new GridBagConstraints(0, 13, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelDatos.add(txtAutor, posAutorTxt);
		
		btnBorrarDatos = new BotonKukabuka(null);
		GridBagConstraints posBorrar = new GridBagConstraints(0, 14, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelDatos.add(btnBorrarDatos, posBorrar);
		btnBorrarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcNuevaReceta().borrarDatos();
			}
		});
		
		
	}
	
	@Override
	public void setTitles() {
		lblAutor.setText(idioma.getProperty("autor"));
		lblCategoria.setText(idioma.getProperty("categoria"));
		lblConsejos.setText(idioma.getProperty("consejo"));
		lblDescripcion.setText(idioma.getProperty("descripcion"));
		lblDificultad.setText(idioma.getProperty("dificultad"));
		lblDuracion.setText(idioma.getProperty("duracion"));
		lblIngrediente.setText(idioma.getProperty("ingrediente"));
		lblIngredientePrincipal.setText(idioma.getProperty("ingPrinc"));
		lblListaDeIngredientes.setText(idioma.getProperty("listaIngredientes"));
		lblListaMultimedia.setText(idioma.getProperty("listaMultimedia"));
		lblMin.setText(idioma.getProperty("min"));
		lblModoPreparacion.setText(idioma.getProperty("modoPreparacion"));
		lblNombre.setText(idioma.getProperty("nombre"));
		lblNuevoIngrediente.setText(idioma.getProperty("newIngrediente"));
		lblPais.setText(idioma.getProperty("pais"));
		lblTipo.setText(idioma.getProperty("tipo"));
		lblTipoIng.setText(idioma.getProperty("tipo"));
		lblTitulo.setText(idioma.getProperty("titulo"));
		
		btnAdd.setText(idioma.getProperty("add"));
		btnAddImagen.setText(idioma.getProperty("addImagen"));
		btnAddVideo.setText(idioma.getProperty("addVideo"));
		btnBorrarDatos.setText(idioma.getProperty("borrar"));
		btnBorrarIngredientes.setText(idioma.getProperty("borrar"));
		btnBorrarMultimedia.setText(idioma.getProperty("borrar"));
		btnBorrarPreparacion.setText(idioma.getProperty("borrar"));
		btnCrearIng.setText(idioma.getProperty("crear"));
		btnGuardar.setText(idioma.getProperty("guardar"));
		btnQuitarIng.setText(idioma.getProperty("quitar"));
		btnQuitarMultimedia.setText(idioma.getProperty("quitar"));
		
		TitledBorder tpm = (TitledBorder) panelMultimedia.getBorder();
		tpm.setTitle(idioma.getProperty("multimedia"));
		
		TitledBorder tpp = (TitledBorder) panelPreparacion.getBorder();
		tpp.setTitle(idioma.getProperty("preparacion"));
		
		TitledBorder tpi = (TitledBorder) panelIngredientes.getBorder();
		tpi.setTitle(idioma.getProperty("ingredientes"));
		
		TitledBorder tpd = (TitledBorder) panelDatos.getBorder();
		tpd.setTitle(idioma.getProperty("datosPrincipales"));
		
		String[] insert = null;
		
		cboxTipo.removeAllItems();
		insert = datos.getTipo();
		for (int i = 0; i < insert.length; i++) {
			cboxTipo.addItem(insert[i]);
		}
		cboxCategoria.removeAllItems();
		insert = datos.getCategoria();
		for (int i = 0; i < insert.length; i++) {
			cboxCategoria.addItem(insert[i]);
		}
		cboxDificultad.removeAllItems();
		insert =datos.getDificultad();
		for (int i = 0; i < insert.length; i++) {
			cboxDificultad.addItem(insert[i]);
		}
		
		repaint();
	}
	
	public void cargaIngredientes(ArrayList<Ingrediente> ingredientes){
		for (int i = 0; i < ingredientes.size(); i++) {
			cboxIngrediente.addItem(ingredientes.get(i));
		}
	}

	public JTextField getTxtTitulo() {
		return txtTitulo;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public JTextField getTxtPais() {
		return txtPais;
	}

	public JTextField getTxtAutor() {
		return txtAutor;
	}

	public JTextField getTxtIngPrincipal() {
		return txtIngPrincipal;
	}

	public JTextArea getTxtPreparacion() {
		return txtPreparacion;
	}

	public JTextArea getTxtConsejos() {
		return txtConsejos;
	}

	public JTextField getTxtNombreIng() {
		return txtNombreIng;
	}

	public JTextField getTxtTipoIng() {
		return txtTipoIng;
	}

	public JComboBox<String> getCboxTipo() {
		return cboxTipo;
	}

	public JComboBox<String> getCboxCategoria() {
		return cboxCategoria;
	}

	public JComboBox<String> getCboxDificultad() {
		return cboxDificultad;
	}

	public JComboBox<Ingrediente> getCboxIngrediente() {
		return cboxIngrediente;
	}

	public JSpinner getSpnDuracion() {
		return spnDuracion;
	}

	public JList<Ingrediente> getListIngredientes() {
		return listIngredientes;
	}

	public JList<String> getListMultimedia() {
		return listMultimedia;
	}

	public DefaultListModel<Ingrediente> getModeloIngredientes() {
		return modeloIngredientes;
	}

	public DefaultListModel<String> getModeloMultimedia() {
		return modeloMultimedia;
	}

	public SpinnerNumberModel getSpnModeloDuracion() {
		return spnModeloDuracion;
	}

	public Usuario getUser() {
		return user;
	}
	
}