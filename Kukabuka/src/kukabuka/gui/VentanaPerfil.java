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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kukabuka.config.Idioma;
import kukabuka.controller.ControladorPrincipal;
import kukabuka.font.FuenteKukabuka;
import kukabuka.model.Usuario;
import kukabuka.other.BotonKukabuka;
import kukabuka.other.JFrameKukabuka;
import kukabuka.other.PanelKukabuka;

public class VentanaPerfil extends JFrameKukabuka{
	
	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 14.0f);
	private static final Color COLOR_PANEL = new Color(240 , 0, 0, 230);
	
	private int x, y, ancho, largo;
	private Dimension size;
	private JPanel panelPerfil;
	private JTextField txtCorreo;
	private JLabel lblContrasea, lblRepitaContrasea, lblCorreo;
	private JPasswordField passContrasea, passRepitaContrasea;
	private BotonKukabuka btnGuardar;
	private Usuario user;
	private Idioma idioma = Idioma.getInstance();

	public VentanaPerfil(Dimension d, Usuario u) {
		
		size = d;
		
		this.user = u;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/kukabuka/images/logoKukabuka.png")));		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setSize((int)(size.getWidth()/3), (int)(size.getHeight()/3));
		PanelKukabuka p = new PanelKukabuka(getSize(), this);
		setContentPane(p);
		setResizable(false);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.x = getWidth()/65;
		this.y = (int) (size.getHeight()/20);
		this.ancho = (int) (getWidth()-(x*2));
		this.largo = (int) (getHeight()/1.3);
		
		perfil();
		setTitles();
		
		setVisible(true);
	}
	
	public void setTitles(){
		
		TitledBorder tl = (TitledBorder) panelPerfil.getBorder();
		tl.setTitle(idioma.getProperty("perfil"));
		
		lblContrasea.setText(idioma.getProperty("contrasena"));
		lblRepitaContrasea.setText(idioma.getProperty("repitaContrasena"));
		btnGuardar.setText(idioma.getProperty("guardar"));
		lblCorreo.setText(idioma.getProperty("correo"));
		
		repaint();
	}

	private void perfil() {
		
		Insets insets = new Insets(10,10,10,10);
		
		panelPerfil = new JPanel();
		panelPerfil.setBackground(COLOR_PANEL);
		panelPerfil.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panelPerfil.setBounds(x,y,ancho,largo);
		getContentPane().add(panelPerfil);
		panelPerfil.setLayout(new GridBagLayout());
		
		
		lblContrasea = new JLabel();
		GridBagConstraints posCon = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		lblContrasea.setFont(FONT);
		lblContrasea.setForeground(Color.BLACK);
		panelPerfil.add(lblContrasea, posCon);
		
		txtCorreo = new JTextField();
		GridBagConstraints posCorreoTxt = new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		txtCorreo.setFont(FONT);
		txtCorreo.setForeground(Color.BLACK);
		panelPerfil.add(txtCorreo, posCorreoTxt);
		
		lblRepitaContrasea = new JLabel();
		GridBagConstraints posConRep = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		lblRepitaContrasea.setFont(FONT);
		lblRepitaContrasea.setForeground(Color.BLACK);
		panelPerfil.add(lblRepitaContrasea, posConRep);
		
		lblCorreo = new JLabel();
		GridBagConstraints posCorreo = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		lblCorreo.setFont(FONT);
		lblCorreo.setForeground(Color.BLACK);
		panelPerfil.add(lblCorreo, posCorreo);
		
		passContrasea = new JPasswordField();
		GridBagConstraints posConTxt = new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		passContrasea.setFont(FONT);
		passContrasea.setForeground(Color.BLACK);
		panelPerfil.add(passContrasea, posConTxt);
		
		passRepitaContrasea = new JPasswordField();
		GridBagConstraints posConRepTxt = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		passRepitaContrasea.setFont(FONT);
		passRepitaContrasea.setForeground(Color.BLACK);
		panelPerfil.add(passRepitaContrasea, posConRepTxt);
		
		btnGuardar = new BotonKukabuka(null);
		GridBagConstraints posBtn = new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelPerfil.add(btnGuardar, posBtn);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ControladorPrincipal.getInstance().getcPerfil().guardarDatos();
			}
		});
	}

	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	public void setTxtCorreo(JTextField txtCorreo) {
		this.txtCorreo = txtCorreo;
	}

	public JPasswordField getPassContrasea() {
		return passContrasea;
	}

	public void setPassContrasea(JPasswordField passContrasea) {
		this.passContrasea = passContrasea;
	}

	public JPasswordField getPassRepitaContrasea() {
		return passRepitaContrasea;
	}

	public void setPassRepitaContrasea(JPasswordField passRepitaContrasea) {
		this.passRepitaContrasea = passRepitaContrasea;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(BotonKukabuka btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
	

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
}
