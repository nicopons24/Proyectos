package kukabuka.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import kukabuka.config.Idioma;
import kukabuka.controller.ControladorPrincipal;
import kukabuka.font.FuenteKukabuka;
import kukabuka.model.Datos;
import kukabuka.model.Receta;
import kukabuka.model.RecetaRunnable;
import kukabuka.model.Usuario;
import kukabuka.other.BotonKukabuka;
import kukabuka.other.BotonReceta;
import kukabuka.other.JFrameKukabuka;
import kukabuka.other.PanelKukabuka;


public class VentanaInicio extends JFrameKukabuka {
	
	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	private static final Color COLOR_PANEL = new Color(240 , 0, 0, 230);
	
	private int x, y, ancho, largo;
	private JPanel panelPrincipal, panelMenu, panelInicio, panelPerfil, panelSugerencias, panelBuscar, panelFavoritos, panelRecetas, panelRecetasFavoritas, panelRecetasSugerencias, panelFiltroBusqueda;
	private BotonKukabuka btnBuscarMenu, btnFavoritos, btnNuevaReceta, btnAtras, btnBuscar, btnEditar, btnTwitter, btnCerrarSesion;
	private JTextField txtBuscar;
	private JComboBox<String> cboxCategoria, cboxTipo, cboxDificultad;
	private JLabel lblBuscar, lblTitleUsuario, lblTitleCorreo, lblRedesSociales;
	private Usuario user;
	private Dimension size;
	private Idioma idioma = Idioma.getInstance();
	private Datos datos = Datos.getInstance();
	
	public VentanaInicio(Dimension d, Usuario u) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/kukabuka/images/logoKukabuka.png")));
		
		this.setExtendedState(MAXIMIZED_BOTH);
		this.size = d;
		this.user = u;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(this.size);
		PanelKukabuka p = new PanelKukabuka(getSize(),this);
		setContentPane(p);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);
		this.x = (int) (size.getWidth()/65);
		this.y = (int) (size.getHeight()/20);
		this.ancho = (int) (getWidth()/5.2);
		this.largo = (int) (size.getHeight()/1.1);
		
		menu();
		principal();
		perfil();
		sugerencias();
		
		setTitles();
		
		setVisible(true);
	}
	
	private void principal() {
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(x, y, ancho, largo);
		panelPrincipal.setLayout(new CardLayout(0, 0));
		
		this.x = x+ancho+(int) (size.getWidth()/65);
		this.ancho = (int) (getWidth()/5.2);
		this.largo = (largo/2)-(this.y/4);
		
		inicio();
		favoritos();
		buscar();
				
		getContentPane().add(panelPrincipal);
		
	}

	private void favoritos(){
		
		panelFavoritos = new JPanel();
		panelFavoritos.setBackground(COLOR_PANEL);
		panelFavoritos.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, new Color(0, 0, 0)));
		panelFavoritos.setBounds(0, 0, panelPrincipal.getWidth(), panelPrincipal.getHeight());
		panelFavoritos.setLayout(new GridLayout(1, 1, 10, 10));
		panelPrincipal.add(panelFavoritos, "FAVORITOS");
		
		panelRecetasFavoritas = new JPanel();
		panelRecetasFavoritas.setLayout(new GridBagLayout());
		panelRecetasFavoritas.setBackground(new Color(240,0,0));
		JScrollPane scroll = new JScrollPane(panelRecetasFavoritas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelFavoritos.add(scroll);
		
		
	}
	
	private void buscar(){
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelBuscar = new JPanel();
		panelBuscar.setBackground(COLOR_PANEL);
		panelBuscar.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, new Color(0, 0, 0)));
		panelBuscar.setBounds(0, 0, panelPrincipal.getWidth(), panelPrincipal.getHeight());
		panelPrincipal.add(panelBuscar, "BUSCAR");
		panelBuscar.setLayout(new GridBagLayout());
		
		lblBuscar = new JLabel();
		lblBuscar.setFont(FONT);
		lblBuscar.setForeground(Color.BLACK);
		GridBagConstraints posBuscar = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelBuscar.add(lblBuscar, posBuscar);
		
		txtBuscar = new JTextField();
		txtBuscar.setFont(FONT);
		txtBuscar.setForeground(Color.BLACK);
		GridBagConstraints posTxtBuscar = new GridBagConstraints(1, 0, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelBuscar.add(txtBuscar, posTxtBuscar);
		
		btnBuscar = new BotonKukabuka(null);
		GridBagConstraints posBtnBuscar = new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelBuscar.add(btnBuscar, posBtnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControladorPrincipal.getInstance().getcInicio().recetasBuscador();
			}
		});
		
		cboxCategoria = new JComboBox<String>();
		cboxCategoria.setFont(FONT);
		cboxCategoria.setForeground(Color.BLACK);
		GridBagConstraints posCat = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		for (int i = 0; i < Datos.CATEGORIA.length; i++) {
			cboxCategoria.addItem(Datos.CATEGORIA[i]);
		}
		panelBuscar.add(cboxCategoria, posCat);
		
		cboxDificultad = new JComboBox<String>();
		cboxDificultad.setFont(FONT);
		cboxDificultad.setForeground(Color.BLACK);
		GridBagConstraints posDif = new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		for (int i = 0; i < Datos.DIFICULTAD.length; i++) {
			cboxDificultad.addItem(Datos.DIFICULTAD[i]);
		}
		panelBuscar.add(cboxDificultad, posDif);
		
		cboxTipo = new JComboBox<String>();
		cboxTipo.setFont(FONT);
		cboxTipo.setForeground(Color.BLACK);
		GridBagConstraints posTipo = new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		for (int i = 0; i < Datos.TIPO.length; i++) {
			cboxTipo.addItem(Datos.TIPO[i]);
		}
		panelBuscar.add(cboxTipo,posTipo);
		
		panelFiltroBusqueda = new JPanel();
		panelFiltroBusqueda.setLayout(new GridBagLayout());
		panelFiltroBusqueda.setBackground(new Color(240,0,0));
		JScrollPane scroll = new JScrollPane(panelFiltroBusqueda, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints posPanel = new GridBagConstraints(0, 2, 5, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		panelBuscar.add(scroll, posPanel);
	}
	
	private void inicio() {
		
		panelInicio = new JPanel();
		panelInicio.setBackground(COLOR_PANEL);
		panelInicio.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3, true), null, TitledBorder.LEADING, TitledBorder.TOP, FONT, new Color(0, 0, 0)));
		panelInicio.setBounds(0, 0, panelPrincipal.getWidth(), panelPrincipal.getHeight());
		panelPrincipal.add(panelInicio, "INICIO");
		GridLayout gl = new GridLayout(1,1,10,10);
		panelInicio.setLayout(gl);
		
		panelRecetas = new JPanel();
		panelRecetas.setLayout(new GridBagLayout());
		panelRecetas.setBackground(new Color(240,0,0));
		JScrollPane scroll = new JScrollPane(panelRecetas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelInicio.add(scroll);
		
	}

	private void sugerencias() {
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelSugerencias = new JPanel();
		panelSugerencias.setBackground(COLOR_PANEL);
		panelSugerencias.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, new Color(0, 0, 0)));
		panelSugerencias.setBounds(x,y,ancho, largo);
		getContentPane().add(panelSugerencias);
		panelSugerencias.setLayout(new GridBagLayout());
		
		panelRecetasSugerencias = new JPanel();
		panelRecetasSugerencias.setOpaque(false);
		panelRecetasSugerencias.setLayout(new GridBagLayout());
		GridBagConstraints posPanel = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		panelSugerencias.add(panelRecetasSugerencias, posPanel);
		
	}

	private void perfil() {
		
		Insets insets = new Insets(10, 10, 10, 10);

		panelPerfil = new JPanel();
		panelPerfil.setBackground(COLOR_PANEL);
		panelPerfil.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, new Color(0, 0, 0)));
		panelPerfil.setBounds(x,y,ancho,largo);
		getContentPane().add(panelPerfil);
		GridBagLayout gbl = new GridBagLayout();
		panelPerfil.setLayout(gbl);
		
		this.y = this.y+this.largo+(this.y/2);
		
		lblTitleUsuario = new JLabel();
		lblTitleUsuario.setFont(FONT);
		lblTitleUsuario.setForeground(Color.BLACK);
		GridBagConstraints posUsuario = new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelPerfil.add(lblTitleUsuario, posUsuario);
		
		JLabel lblNombreUsuario = new JLabel();
		lblNombreUsuario.setFont(FONT);
		lblNombreUsuario.setText(user.getNomUsuario());
		lblNombreUsuario.setForeground(Color.WHITE);
		GridBagConstraints posNombre = new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelPerfil.add(lblNombreUsuario, posNombre);
		
		lblTitleCorreo = new JLabel();
		lblTitleCorreo.setFont(FONT);
		lblTitleCorreo.setForeground(Color.BLACK);
		GridBagConstraints posCorreo = new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelPerfil.add(lblTitleCorreo, posCorreo);
		
		JLabel lblCorreoUsuario = new JLabel();
		lblCorreoUsuario.setFont(FONT);
		lblCorreoUsuario.setText(user.getCorreo());
		lblCorreoUsuario.setForeground(Color.WHITE);
		GridBagConstraints posEmail = new GridBagConstraints(0, 3, 2, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelPerfil.add(lblCorreoUsuario, posEmail);
		
		lblRedesSociales = new JLabel();
		lblRedesSociales.setFont(FONT);
		lblRedesSociales.setForeground(Color.BLACK);
		GridBagConstraints posSocial = new GridBagConstraints(0, 4, 2, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelPerfil.add(lblRedesSociales, posSocial);
		
		JPanel panelRedesSociales = new JPanel();
		panelRedesSociales.setOpaque(false);
		GridBagConstraints posPanelSocial = new GridBagConstraints(0, 5, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		panelPerfil.add(panelRedesSociales, posPanelSocial);
		
		btnTwitter = new BotonKukabuka("Twitter");
		//GridBagConstraints posTwitter = new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelRedesSociales.add(btnTwitter);
		btnTwitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().autorizarTwitter();
			}
		});
		
		btnEditar = new BotonKukabuka(null);
		GridBagConstraints posEditar = new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelPerfil.add(btnEditar, posEditar);
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().ventanaPerfil();
			}
		});
		
		btnCerrarSesion = new BotonKukabuka(null);
		GridBagConstraints posSesion = new GridBagConstraints(1, 5, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelPerfil.add(btnCerrarSesion, posSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcInicio().cerrarSesion();
			}
		});
		
	}

	private void menu() {
		
		Insets insets = new Insets(20, 50, 20, 50);
		
		this.panelMenu = new JPanel();
		panelMenu.setBackground(COLOR_PANEL);
		panelMenu.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, new Color(0, 0, 0)));
		this.panelMenu.setBounds(x, y, ancho, largo);
		GridBagLayout gbl = new GridBagLayout();
		this.panelMenu.setLayout(gbl);
		getContentPane().add(this.panelMenu);
		
		this.x = x+x+ancho;
		this.ancho = (int) (getWidth()/1.8);
		
		JLabel icono = new JLabel();
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/kukabuka/images/logoKukabuka.png"));
		int tam = panelMenu.getWidth()-insets.left-insets.right-FONT.getSize()-3;
		logo = redimensionar(tam, tam, logo);
		icono.setIcon(logo);
		GridBagConstraints posIcono = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		panelMenu.add(icono, posIcono);
				
		this.btnBuscarMenu = new BotonKukabuka(null);
		GridBagConstraints posBuscar = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		this.panelMenu.add(this.btnBuscarMenu, posBuscar);
		this.btnBuscarMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcInicio().mostrarBuscador();
				
			}
		});
		
		this.btnFavoritos = new BotonKukabuka(null);
		GridBagConstraints posFav = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		this.panelMenu.add(this.btnFavoritos, posFav);
		this.btnFavoritos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcInicio().mostrarFavoritos();
			}
			
		});
		
		this.btnNuevaReceta = new BotonKukabuka(null);
		GridBagConstraints posNReceta = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		this.panelMenu.add(this.btnNuevaReceta, posNReceta);
		this.btnNuevaReceta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().ventanaNuevaReceta();
			}
		});
		
		this.btnAtras = new BotonKukabuka(null);
		GridBagConstraints posAtras = new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		this.panelMenu.add(this.btnAtras, posAtras);
		this.btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcInicio().mostrarInicio();
			}
		});
		
		}
	
	public void cargaRecetas(ArrayList<Receta> recetas){
		Insets insets = new Insets(15, 15, 15, 15);
		int x = 0;
		int y = 0;
		panelRecetas.removeAll();
		for (int i = 0; i < recetas.size(); i++){
			if (x >= 3) {
				x = 0;
				y++;
			}
			BotonReceta btnReceta = new BotonReceta(recetas.get(i));
			Thread cargaImg = new Thread(new RecetaRunnable(btnReceta, 200), recetas.get(i).getNombre());
			GridBagConstraints posBtn = new GridBagConstraints(x, y, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
			panelRecetas.add(btnReceta, posBtn);
			x++;
			cargaImg.start();
		}
		panelRecetas.repaint();
	}
	
	public void muestraBusqueda(ArrayList<Receta> recetas) {
		Insets insets = new Insets(15, 15, 15, 15);
		int x = 0;
		int y = 0;
		panelFiltroBusqueda.removeAll();
		for (int i = 0; i < recetas.size(); i++){
			if (x >= 3) {
				x = 0;
				y++;
			}
			BotonReceta btnReceta = new BotonReceta(recetas.get(i));
			Thread cargaImg = new Thread(new RecetaRunnable(btnReceta, 200), recetas.get(i).getNombre());
			GridBagConstraints posBtn = new GridBagConstraints(x, y, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
			panelFiltroBusqueda.add(btnReceta, posBtn);
			x++;
			cargaImg.start();
		}
		panelFiltroBusqueda.repaint();
	}
	
	public void cargaFavoritos(ArrayList<Receta> recetas) {
		Insets insets = new Insets(15, 15, 15, 15);
		int x = 0;
		int y = 0;
		panelRecetasFavoritas.removeAll();
		for (int i = 0; i < recetas.size(); i++){
			if (x >= 3) {
				x = 0;
				y++;
			}
			BotonReceta btnReceta = new BotonReceta(recetas.get(i));
			Thread cargaImg = new Thread(new RecetaRunnable(btnReceta, 200), recetas.get(i).getNombre());
			GridBagConstraints posBtn = new GridBagConstraints(x, y, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
			panelRecetasFavoritas.add(btnReceta, posBtn);
			x++;
			cargaImg.start();
		}
		panelRecetasFavoritas.repaint();
		
	}
	
	public void cargaSugerencias(Receta[] recetas) {
		Insets insets = new Insets(5, 5, 5, 5);
		int x = 0;
		int y = 0;
		panelRecetasSugerencias.removeAll();
		for (int i = 0; i < recetas.length; i++){
			if (x >= 1) {
				x = 0;
				y++;
			}
			BotonReceta btnReceta = new BotonReceta(recetas[i]);
			Thread cargaImg = new Thread(new RecetaRunnable(btnReceta, 150), recetas[i].getNombre());
			GridBagConstraints posBtn = new GridBagConstraints(x, y, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
			panelRecetasSugerencias.add(btnReceta, posBtn);
			x++;
			cargaImg.start();
		}
		panelRecetasSugerencias.repaint();
		
	}
	
	
	public void setTitles() {
		btnBuscarMenu.setText(idioma.getProperty("buscar"));
		btnFavoritos.setText(idioma.getProperty("favorito"));
		btnNuevaReceta.setText(idioma.getProperty("nuevaReceta"));
		btnAtras.setText(idioma.getProperty("atras"));
		btnBuscar.setText(idioma.getProperty("buscar"));
		btnEditar.setText(idioma.getProperty("editar"));
		btnCerrarSesion.setText(idioma.getProperty("cerrarSesion"));
		
		lblBuscar.setText(idioma.getProperty("buscar"));
		lblTitleUsuario.setText(idioma.getProperty("usuario"));
		lblTitleCorreo.setText(idioma.getProperty("correo"));
		lblRedesSociales.setText(idioma.getProperty("redesSociales"));
		
		TitledBorder tl = (TitledBorder) panelFavoritos.getBorder();
		tl.setTitle(idioma.getProperty("favorito"));
		
		TitledBorder tl1 = (TitledBorder) panelBuscar.getBorder();
		tl1.setTitle(idioma.getProperty("buscar"));
		
		TitledBorder tl2 = (TitledBorder) panelInicio.getBorder();
		tl2.setTitle(idioma.getProperty("inicio"));
		
		TitledBorder tl3 = (TitledBorder) panelSugerencias.getBorder();
		tl3.setTitle(idioma.getProperty("sugerencias"));
		
		TitledBorder tl4 = (TitledBorder) panelPerfil.getBorder();
		tl4.setTitle(idioma.getProperty("perfil"));
		
		TitledBorder tl5 = (TitledBorder) panelMenu.getBorder();
		tl5.setTitle(idioma.getProperty("menu"));
		
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

	private ImageIcon redimensionar(int width, int height, ImageIcon img){
		if(width == 0 || height == 0){
			width++;
			height++;
		}
		Image img2 = img.getImage();
		Image img3 = img2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img3);
		return icon;
	}
	
	public BotonKukabuka getBtnBuscarMenu() {
		return btnBuscarMenu;
	}

	public BotonKukabuka getBtnFavoritos() {
		return btnFavoritos;
	}

	public BotonKukabuka getBtnAtras() {
		return btnAtras;
	}
	
	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public JComboBox<String> getCboxCategoria() {
		return cboxCategoria;
	}

	public void setCboxCategoria(JComboBox<String> cboxCategoria) {
		this.cboxCategoria = cboxCategoria;
	}

	public JComboBox<String> getCboxTipo() {
		return cboxTipo;
	}

	public JComboBox<String> getCboxDificultad() {
		return cboxDificultad;
	}

	public JTextField getTxtBuscar() {
		return txtBuscar;
	}
}
