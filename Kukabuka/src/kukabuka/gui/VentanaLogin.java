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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import kukabuka.other.BotonKukabuka;
import kukabuka.other.JFrameKukabuka;
import kukabuka.other.PanelKukabuka;

public class VentanaLogin extends JFrameKukabuka{

	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	private static final Color COLOR_PANEL = new Color(240 , 0, 0, 230);

	private int x, y, largo, ancho;
	private JPanel panelLogin, panelRegistro;
	private JTextField txtUserRegistro;
	private JTextField txtCorreoRegistro;
	private JTextField txtUsuarioLogin;
	private JPasswordField passRegistro;
	private JPasswordField passLogin;
	private BotonKukabuka btnRegistro, btnEntrar;
	private Dimension size;
	private Idioma idioma = Idioma.getInstance();
	private JLabel lblNewUser, lblNewPass, lblEmail, lblUsuarioLogin, lblPassLogin, lblLinkPass;

	public VentanaLogin(Dimension d) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/kukabuka/images/logoKukabuka.png")));

		this.size = d;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((int) size.getWidth()/2, (int) size.getHeight()/2);
		PanelKukabuka p = new PanelKukabuka(getSize(),this);
		setContentPane(p);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.ancho = (int) (getWidth()/2.5);
		this.x = (int) (getWidth()/2)-ancho-(getWidth()/40);
		this.y = (int) (size.getHeight()/20);
		this.largo = (int) (getHeight()/1.2);

		signin();
		login();
		
		setTitles();

		setVisible(true);
	}

	public void setTitles(){
		lblNewUser.setText(idioma.getProperty("nuevoUsuario"));
		lblNewPass.setText(idioma.getProperty("nuevaPass"));
		lblEmail.setText(idioma.getProperty("correo"));
		btnRegistro.setText(idioma.getProperty("registro"));
		lblUsuarioLogin.setText(idioma.getProperty("usuario"));
		lblPassLogin.setText(idioma.getProperty("contrasena"));
		lblLinkPass.setText(idioma.getProperty("olvidarPass"));
		btnEntrar.setText(idioma.getProperty("entrar"));
		TitledBorder tl = (TitledBorder) panelRegistro.getBorder();
		tl.setTitle(idioma.getProperty("registro2"));
		TitledBorder tl2 = (TitledBorder) panelLogin.getBorder();
		tl2.setTitle(idioma.getProperty("inicioSesion"));
		repaint();
	}

	private void signin() {

		panelRegistro = new JPanel();
		panelRegistro.setBackground(COLOR_PANEL);
		panelRegistro.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, new Color(0, 0, 0)));
		panelRegistro.setBounds(x, y, ancho, largo);
		getContentPane().add(panelRegistro);
		panelRegistro.setLayout(new GridBagLayout());

		this.x = (int) (getWidth()/2)+(getWidth()/40);

		Insets insets = new Insets(10, 35, 10, 35);

		lblNewUser = new JLabel();
		lblNewUser.setFont(FONT);
		lblNewUser.setForeground(Color.BLACK);
		GridBagConstraints posNewUser = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelRegistro.add(lblNewUser, posNewUser);

		txtUserRegistro = new JTextField();
		txtUserRegistro.setFont(FONT);
		txtUserRegistro.setForeground(Color.BLACK);
		GridBagConstraints posUsuario = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelRegistro.add(txtUserRegistro, posUsuario);

		lblNewPass = new JLabel();
		lblNewPass.setFont(FONT);
		lblNewPass.setForeground(Color.BLACK);
		GridBagConstraints posNewPass = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelRegistro.add(lblNewPass, posNewPass);

		passRegistro = new JPasswordField();
		passRegistro.setFont(FONT);
		passRegistro.setForeground(Color.BLACK);
		GridBagConstraints posRegistro = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelRegistro.add(passRegistro, posRegistro);

		lblEmail = new JLabel();
		GridBagConstraints posNewEmail = new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		lblEmail.setFont(FONT);
		lblEmail.setForeground(Color.BLACK);
		panelRegistro.add(lblEmail, posNewEmail);

		txtCorreoRegistro = new JTextField();
		txtCorreoRegistro.setFont(FONT);
		txtCorreoRegistro.setForeground(Color.BLACK);
		GridBagConstraints posCorreo = new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelRegistro.add(txtCorreoRegistro, posCorreo);

		btnRegistro = new BotonKukabuka(null);
		GridBagConstraints posBoton = new GridBagConstraints(0, 6, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 10, 10, 10), 0, 0);
		panelRegistro.add(btnRegistro, posBoton);
		btnRegistro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcLogin().signin();	
			}
		});

	}

	private void login() {

		panelLogin = new JPanel();
		panelLogin.setBackground(COLOR_PANEL);
		panelLogin.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, new Color(0, 0, 0)));
		panelLogin.setBounds(x, y, ancho, largo);
		getContentPane().add(panelLogin);
		panelLogin.setLayout(new GridBagLayout());

		Insets insets = new Insets(10, 35, 10, 35);

		lblUsuarioLogin = new JLabel();
		lblUsuarioLogin.setFont(FONT);
		lblUsuarioLogin.setForeground(Color.BLACK);
		GridBagConstraints posUsuarioLog = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelLogin.add(lblUsuarioLogin, posUsuarioLog);

		txtUsuarioLogin = new JTextField();
		txtUsuarioLogin.setFont(FONT);
		txtUsuarioLogin.setForeground(Color.BLACK);
		GridBagConstraints posUsuario = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelLogin.add(txtUsuarioLogin, posUsuario);

		lblPassLogin = new JLabel();
		lblPassLogin.setFont(FONT);
		lblPassLogin.setForeground(Color.BLACK);
		GridBagConstraints posPassLog = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelLogin.add(lblPassLogin, posPassLog);

		passLogin = new JPasswordField();
		passLogin.setFont(FONT);
		passLogin.setForeground(Color.BLACK);
		GridBagConstraints posPass = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelLogin.add(passLogin, posPass);

		lblLinkPass = new JLabel();
		lblLinkPass.setFont(FONT);
		lblLinkPass.setForeground(Color.BLACK);
		GridBagConstraints posLink = new GridBagConstraints(0, 4, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelLogin.add(lblLinkPass, posLink);
		lblLinkPass.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblLinkPass.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblLinkPass.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ControladorPrincipal.getInstance().getcLogin().cambioContrasena();
			}
		});	

		btnEntrar = new BotonKukabuka(null);
		GridBagConstraints posBoton = new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(35, 10, 10, 10), 0, 0);
		panelLogin.add(btnEntrar, posBoton);
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcLogin().login();
			}
		});

	}
	
	public JTextField getTxtUserRegistro() {
		return txtUserRegistro;
	}

	public JTextField getTxtCorreoRegistro() {
		return txtCorreoRegistro;
	}

	public JPasswordField getPassRegistro() {
		return passRegistro;
	}

	public JTextField getTxtUsuarioLogin() {
		return txtUsuarioLogin;
	}

	public JPasswordField getPassLogin() {
		return passLogin;
	}

	public Dimension getDimension() {
		return size;
	}
}
