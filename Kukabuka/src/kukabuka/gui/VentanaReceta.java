package kukabuka.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kukabuka.config.Idioma;
import kukabuka.controller.ControladorPrincipal;
import kukabuka.font.FuenteKukabuka;
import kukabuka.model.Receta;
import kukabuka.other.BotonKukabuka;
import kukabuka.other.JFrameKukabuka;
import kukabuka.other.PanelKukabuka;


public class VentanaReceta extends JFrameKukabuka{
	
	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	private static final Color COLOR_PANEL = new Color(240 , 0, 0, 240);
	
	private Idioma idioma = Idioma.getInstance();
	private int x, y, largo, ancho;
	private Dimension size;
	private JList<String> txtIngredientes;
	private DefaultListModel<String> modeloIng;
	private JTextArea txtPreparacion, txtConsejo;
	private JLabel lblImagen, lblDescripcion, lblTitulo, lblPuntuacion, lblNumPuntuacion, lblAutor, lblNomAutor, lblPuntuar, lblCategoria, lblNomCategoria, lblTipo, lblNomTipo, lblDificultad, lblNomDificultad, lblPais, lblNomPais, lblIngPrinc, lblNomIngPrinc, lblTPrep, lblNumTPrep, lblMin;
	private JButton btnFavorito, btnCompartir, btnImprimir, p1, p2, p3, p4, p5, btnVideo, btnSig, btnAnt;
	private JPanel panelIngredientes, panelPreparacion, panelMultimedia, panelReceta, panelMenu, panelConsejo;
		
	public VentanaReceta(Dimension d) {
		
		this.setExtendedState(MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaReceta.class.getResource("/kukabuka/images/logoKukabuka.png")));
		
		this.size = d;
		this.x = (int) (size.getWidth()/65);
		this.y = (int) (size.getHeight()/20);
		this.ancho = (int) (size.getWidth())-x*2;
		this.largo = (int) (size.getHeight()/1.2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(size);
		setLocationRelativeTo(null);
		setResizable(false);
		
		PanelKukabuka p = new PanelKukabuka(getSize(), this);
		setContentPane(p);
		
		receta();
		menu();
		
		setTitles();
		
		setVisible(true);
	}

	private void receta(){
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelReceta = new JPanel();
		panelReceta.setSize(ancho, largo);
		panelReceta.setLocation(x, y);
		panelReceta.setBackground(COLOR_PANEL);
		panelReceta.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), "", TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		GridBagLayout gbl = new GridBagLayout();
		panelReceta.setLayout(gbl);
		getContentPane().add(panelReceta);
		
		y = y+largo+(y/2);
		
		lblTitulo = new JLabel();
		lblTitulo.setFont(FONT.deriveFont(Font.BOLD, 20.0f));
		lblTitulo.setForeground(Color.WHITE);
		GridBagConstraints posTitulo = new GridBagConstraints(0, 0, 5, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblTitulo, posTitulo);
		
		lblDescripcion = new JLabel();
		lblDescripcion.setFont(FONT);
		lblDescripcion.setForeground(Color.WHITE);
		GridBagConstraints posDesc = new GridBagConstraints(0, 1, 5, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblDescripcion, posDesc);
		
		//CATEGORIA
		lblCategoria = new JLabel();
		lblCategoria.setFont(FONT);
		lblCategoria.setForeground(Color.BLACK);
		GridBagConstraints posCat = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblCategoria, posCat);
		
		lblNomCategoria = new JLabel();
		lblNomCategoria.setFont(FONT);
		lblNomCategoria.setForeground(Color.WHITE);
		GridBagConstraints posNomCat = new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblNomCategoria, posNomCat);
		
		//TIPO
		lblTipo = new JLabel();
		lblTipo.setFont(FONT);
		lblTipo.setForeground(Color.BLACK);
		GridBagConstraints posTipo = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblTipo, posTipo);
		
		lblNomTipo = new JLabel();
		lblNomTipo.setFont(FONT);
		lblNomTipo.setForeground(Color.WHITE);
		GridBagConstraints posNomTipo = new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblNomTipo, posNomTipo);
		
		//INGREDIENTE PRINCIPAL
		lblIngPrinc = new JLabel();
		lblIngPrinc.setFont(FONT);
		lblIngPrinc.setForeground(Color.BLACK);
		GridBagConstraints posIngPrin = new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblIngPrinc, posIngPrin);
		
		lblNomIngPrinc = new JLabel();
		lblNomIngPrinc.setFont(FONT);
		lblNomIngPrinc.setForeground(Color.WHITE);
		GridBagConstraints posNomIngPrin = new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblNomIngPrinc, posNomIngPrin);
		
		//PAIS
		lblPais = new JLabel();
		lblPais.setFont(FONT);
		lblPais.setForeground(Color.BLACK);
		GridBagConstraints posPais = new GridBagConstraints(2, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblPais, posPais);
		
		lblNomPais = new JLabel();
		lblNomPais.setFont(FONT);
		lblNomPais.setForeground(Color.WHITE);
		GridBagConstraints posNomPais = new GridBagConstraints(3, 2, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblNomPais, posNomPais);
		
		//DIFICULTAD
		lblDificultad = new JLabel();
		lblDificultad.setFont(FONT);
		lblDificultad.setForeground(Color.BLACK);
		GridBagConstraints posDif = new GridBagConstraints(2, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblDificultad, posDif);
		
		lblNomDificultad = new JLabel();
		lblNomDificultad.setFont(FONT);
		lblNomDificultad.setForeground(Color.WHITE);
		GridBagConstraints posNomDif = new GridBagConstraints(3, 3, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblNomDificultad, posNomDif);
		
		//TIEMPO PREPARACION
		lblTPrep = new JLabel();
		lblTPrep.setFont(FONT);
		lblTPrep.setForeground(Color.BLACK);
		GridBagConstraints posTPrep = new GridBagConstraints(2, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblTPrep, posTPrep);
		
		lblNumTPrep = new JLabel();
		lblNumTPrep.setFont(FONT);
		lblNumTPrep.setForeground(Color.WHITE);
		GridBagConstraints posNumPrep = new GridBagConstraints(3, 4, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblNumTPrep, posNumPrep);
		
		lblMin = new JLabel();
		lblMin.setFont(FONT);
		lblMin.setForeground(Color.WHITE);
		GridBagConstraints posMin = new GridBagConstraints(4, 4, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblMin, posMin);
		
		//AUTOR
		lblAutor = new JLabel();
		lblAutor.setFont(FONT);
		lblAutor.setForeground(Color.BLACK);
		GridBagConstraints posAutor = new GridBagConstraints(5, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblAutor, posAutor);
		
		lblNomAutor = new JLabel();
		lblNomAutor.setFont(FONT);
		lblNomAutor.setForeground(Color.WHITE);
		GridBagConstraints posNomAutor = new GridBagConstraints(6, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblNomAutor, posNomAutor);
		
		//PUNTUACION
		lblPuntuacion = new JLabel();
		lblPuntuacion.setFont(FONT);
		lblPuntuacion.setForeground(Color.BLACK);
		GridBagConstraints posPunt = new GridBagConstraints(5, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblPuntuacion, posPunt);
		
		lblNumPuntuacion = new JLabel();
		lblNumPuntuacion.setFont(FONT);
		lblNumPuntuacion.setForeground(Color.WHITE);
		GridBagConstraints posNumPunt = new GridBagConstraints(6, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelReceta.add(lblNumPuntuacion, posNumPunt);
		
		ingredientes();
		preparacion();
		consejo();
		multimedia();
	}
	
	private void ingredientes(){
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelIngredientes = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelIngredientes.setOpaque(false);
		panelIngredientes.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), "", TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panelIngredientes.setLayout(gbl);
		GridBagConstraints posPanel = new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		panelReceta.add(panelIngredientes, posPanel);
		
		//LISTA INGREDIENTES
		txtIngredientes = new JList<String>();
		modeloIng = new DefaultListModel<String>();
		txtIngredientes.setModel(modeloIng);
		txtIngredientes.setFont(FONT);
		txtIngredientes.setForeground(Color.BLACK);
		GridBagConstraints posIng = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, getInsets(), 0, 0);
		JScrollPane scrollIng = new JScrollPane(txtIngredientes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelIngredientes.add(scrollIng, posIng);
	}
	
	private void preparacion(){
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelPreparacion = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelPreparacion.setOpaque(false);
		panelPreparacion.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), "", TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panelPreparacion.setLayout(gbl);
		GridBagConstraints posPanel = new GridBagConstraints(1, 5, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		panelReceta.add(panelPreparacion, posPanel);
		
		//TEXTO PREPARACION
		txtPreparacion = new JTextArea();
		txtPreparacion.setFont(FONT);
		txtPreparacion.setForeground(Color.BLACK);
		txtPreparacion.setLineWrap(true);
		txtPreparacion.setWrapStyleWord(true);
		txtPreparacion.setEditable(false);
		GridBagConstraints posPrep = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, getInsets(), 0, 0);
		JScrollPane scrollPrep = new JScrollPane(txtPreparacion, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelPreparacion.add(scrollPrep, posPrep);
	}
	
	private void consejo(){
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelConsejo = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelConsejo.setOpaque(false);
		panelConsejo.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), "", TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panelConsejo.setLayout(gbl);
		GridBagConstraints posPanel = new GridBagConstraints(5, 2, 2, 3, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		panelReceta.add(panelConsejo, posPanel);
		
		//TEXTO CONSEJO
		txtConsejo = new JTextArea();
		txtConsejo.setFont(FONT);
		txtConsejo.setForeground(Color.BLACK);
		txtConsejo.setLineWrap(true);
		txtConsejo.setWrapStyleWord(true);
		txtConsejo.setEditable(false);
		GridBagConstraints posCons = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, getInsets(), 0, 0);
		JScrollPane scrollCons = new JScrollPane(txtConsejo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelConsejo.add(scrollCons, posCons);
	}
	
	private void multimedia(){
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panelMultimedia = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panelMultimedia.setBackground(new Color(240, 0, 0));
		panelMultimedia.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panelMultimedia.setLayout(gbl);
		GridBagConstraints posPanel = new GridBagConstraints(3, 5, 4, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		panelReceta.add(panelMultimedia, posPanel);
		
		// IMAGEN
		btnAnt = new BotonKukabuka("<");
		btnAnt.setEnabled(false);
		GridBagConstraints posAnt = new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		panelMultimedia.add(btnAnt, posAnt);
		btnAnt.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			ControladorPrincipal.getInstance().getcReceta().anterior();
		}
			
		});
		
		lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints posImg = new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		lblImagen.setBorder(new LineBorder(Color.BLACK, 3, true));
		panelMultimedia.add(lblImagen, posImg);
		
		btnSig = new BotonKukabuka(">");
		GridBagConstraints posSig = new GridBagConstraints(2, 0, 1, 1, 0.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		panelMultimedia.add(btnSig, posSig);
		btnSig.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcReceta().siguiente();
			}
				
			});
		
		// VIDEO
		btnVideo = new BotonKukabuka(null);
		GridBagConstraints posBotonVideo = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelMultimedia.add(btnVideo, posBotonVideo);
		btnVideo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcReceta().mostrarVideo();
				
			}
				
			});
	}

	private void menu() {
		
		Insets insets = new Insets(0, 10, 0, 10);
		
		panelMenu = new JPanel();
		panelMenu.setSize(ancho,(int) (size.getHeight()/15));
		panelMenu.setLocation(x, y);
		panelMenu.setBackground(COLOR_PANEL);
		panelMenu.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), "", TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		GridBagLayout gbl = new GridBagLayout();
		panelMenu.setLayout(gbl);
		getContentPane().add(panelMenu);
		
		//PUNTUACION
		lblPuntuar = new JLabel();
		lblPuntuar.setFont(FONT);
		lblPuntuar.setForeground(Color.BLACK);
		GridBagConstraints posPuntuar =  new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelMenu.add(lblPuntuar, posPuntuar);
		
		p1 = new BotonKukabuka("1");
		GridBagConstraints pos1 = new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelMenu.add(p1, pos1);
		p1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ControladorPrincipal.getInstance().getcReceta().puntuar(1);
				
			}
				
			});
		
		p2 = new BotonKukabuka("2");
		GridBagConstraints pos2 = new GridBagConstraints(2, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelMenu.add(p2, pos2);
		p2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ControladorPrincipal.getInstance().getcReceta().puntuar(2);
				
			}
				
			});
		
		p3 = new BotonKukabuka("3");
		GridBagConstraints pos3 = new GridBagConstraints(3, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelMenu.add(p3, pos3);
		p3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ControladorPrincipal.getInstance().getcReceta().puntuar(3);
				
			}
				
			});
		
		p4 = new BotonKukabuka("4");
		GridBagConstraints pos4 = new GridBagConstraints(4, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelMenu.add(p4, pos4);
	p4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ControladorPrincipal.getInstance().getcReceta().puntuar(4);
				
			}
				
			});
		
		p5 = new BotonKukabuka("5");
		GridBagConstraints pos5 = new GridBagConstraints(5, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelMenu.add(p5, pos5);
		p5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ControladorPrincipal.getInstance().getcReceta().puntuar(5);
				
			}
				
			});
		
		//BOTONES
		btnFavorito = new BotonKukabuka(null);
		GridBagConstraints posFav = new GridBagConstraints(6, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelMenu.add(btnFavorito, posFav);
		btnFavorito.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcReceta().marcarFavorito();
			}
				
			});

		btnCompartir = new BotonKukabuka(null);
		GridBagConstraints posComp = new GridBagConstraints(7, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelMenu.add(btnCompartir, posComp);
		btnCompartir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcReceta().abrirVentanaCompartir();
			}
				
			});
		
		btnImprimir = new BotonKukabuka(null);
		btnImprimir.setEnabled(false);
		GridBagConstraints posImp = new GridBagConstraints(8, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelMenu.add(btnImprimir, posImp);
		btnImprimir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
				
			});
	}
	
	public void setTitles(){
		lblAutor.setText(idioma.getProperty("autor"));
		lblCategoria.setText(idioma.getProperty("categoria"));
		lblDificultad.setText(idioma.getProperty("dificultad"));
		lblIngPrinc.setText(idioma.getProperty("ingPrinc"));
		lblMin.setText(idioma.getProperty("min"));
		lblPais.setText(idioma.getProperty("pais"));
		lblPuntuacion.setText(idioma.getProperty("puntuacion"));
		lblPuntuar.setText(idioma.getProperty("puntuacion"));
		lblTipo.setText(idioma.getProperty("tipo"));
		lblTPrep.setText(idioma.getProperty("duracion"));
		
		btnCompartir.setText(idioma.getProperty("compartir"));
		btnImprimir.setText(idioma.getProperty("imprimir"));
		btnVideo.setText(idioma.getProperty("video"));
		btnFavorito.setText(idioma.getProperty("addFavorito"));
		
		TitledBorder tpc = (TitledBorder) panelConsejo.getBorder();
		tpc.setTitle(idioma.getProperty("consejo"));
		
		TitledBorder tpi = (TitledBorder) panelIngredientes.getBorder();
		tpi.setTitle(idioma.getProperty("ingrediente"));
		
		TitledBorder tpm = (TitledBorder) panelMultimedia.getBorder();
		tpm.setTitle(idioma.getProperty("multimedia"));
		
		TitledBorder tpmn = (TitledBorder) panelMenu.getBorder();
		tpmn.setTitle(idioma.getProperty("menu"));
		
		TitledBorder tpp = (TitledBorder) panelPreparacion.getBorder();
		tpp.setTitle(idioma.getProperty("preparacion"));
		
		repaint();
	}
	
	public void cargaDatos(Receta r, String cat, String aut) {
		lblTitulo.setText(r.getNombre());
		lblDescripcion.setText(r.getDescripcion());
		lblNomCategoria.setText(cat);
		lblNomTipo.setText(r.getTipo());
		lblNomIngPrinc.setText(r.getTipoIngPrincipal());
		lblNomPais.setText(r.getPais());;
		lblNomDificultad.setText(r.getDificultad());
		lblNumTPrep.setText(""+r.gettPrepaacion());
		lblNumPuntuacion.setText(""+r.getPuntuacion());
		lblNomAutor.setText(aut);
		txtConsejo.setText(r.getConsejo());
		txtPreparacion.setText(r.getPreparacion());
		for (int i = 0; i < r.getIngredientes().size(); i++) {
			modeloIng.addElement(r.getIngredientes().get(i).getNombre());
		}
	}
	
	public void asignarPuntuacion(double i){
		lblNumPuntuacion.setText(""+i);
	}
	
	public void asignarImagen(ImageIcon img){
		lblImagen.setIcon(img);
	}

	public JButton getBtnFavorito() {
		return btnFavorito;
	}

	public JButton getBtnVideo() {
		return btnVideo;
	}

	public JLabel getLblImagen() {
		return lblImagen;
	}
	
	public JButton getBtnSig() {
		return btnSig;
	}

	public JButton getBtnAnt() {
		return btnAnt;
	}

	public JButton getP1() {
		return p1;
	}

	public JButton getP2() {
		return p2;
	}

	public JButton getP3() {
		return p3;
	}

	public JButton getP4() {
		return p4;
	}

	public JButton getP5() {
		return p5;
	}	
}