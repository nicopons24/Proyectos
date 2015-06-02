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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kukabuka.config.Idioma;
import kukabuka.controller.ControladorPrincipal;
import kukabuka.font.FuenteKukabuka;
import kukabuka.other.BotonKukabuka;
import kukabuka.other.JFrameKukabuka;
import kukabuka.other.PanelKukabuka;

public class VentanaPin extends JFrameKukabuka {

	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	private static final Color COLOR_PANEL = new Color(240 , 0, 0, 230);
	
	private Idioma idioma = Idioma.getInstance();
	private int x,y,ancho, largo;
	private JTextField txtPin;
	private JLabel lblPin, lblRuta, lblInfoArchivo;
	private BotonKukabuka btnGuardar;
	private JPanel panel;
	private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	
	public VentanaPin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/kukabuka/images/logoKukabuka.png")));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize((int) (size.getHeight()/2.5), (int) (size.getHeight()/3));
		PanelKukabuka p = new PanelKukabuka(getSize(), this);
		setContentPane(p);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		this.x = (int) (size.getWidth()/65);
		this.y = (int) (size.getHeight()/20);
		this.ancho = (int) (getWidth()-(x*2));
		this.largo = (int) (getHeight()/1.3);
		
		colocaComponentes();
		setTitles();
		
		setVisible(true);
	}
	
	public void colocaComponentes(){
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panel = new JPanel();
		panel.setBackground(COLOR_PANEL);
		GridBagLayout gbl = new GridBagLayout();
		panel.setLayout(gbl);
		panel.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panel.setBounds(x, y, ancho, largo);
		getContentPane().add(panel);
		
		this.lblPin = new JLabel();
		this.lblPin.setFont(FONT);
		this.lblPin.setForeground(Color.BLACK);
		GridBagConstraints posPinInfo = new GridBagConstraints(0, 0, 3, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panel.add(this.lblPin, posPinInfo);
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setFont(FONT);
		lblPin.setForeground(Color.BLACK);
		GridBagConstraints posPinlbl = new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		panel.add(lblPin, posPinlbl);
		
		txtPin = new JTextField();
		txtPin.setFont(FONT);
		txtPin.setForeground(Color.BLACK);
		GridBagConstraints posPin = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panel.add(txtPin, posPin);
		
		btnGuardar = new BotonKukabuka(null);
		GridBagConstraints posbtn = new GridBagConstraints(2, 1, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panel.add(btnGuardar, posbtn);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getAutTwitter().compruebaPin();
			}
		});
		
		lblInfoArchivo = new JLabel();
		lblInfoArchivo.setFont(FONT);
		lblInfoArchivo.setForeground(Color.BLACK);
		GridBagConstraints posInfo = new GridBagConstraints(0, 2, 3, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panel.add(lblInfoArchivo, posInfo);
		
		lblRuta = new JLabel();
		lblRuta.setText(System.getProperty("user.home"));
		lblRuta.setFont(FONT);
		lblRuta.setForeground(Color.BLACK);
		GridBagConstraints posRuta = new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panel.add(lblRuta, posRuta);
		
	}
	
	public void setTitles() {
		lblPin.setText(idioma.getProperty("introPin"));
		lblInfoArchivo.setText(idioma.getProperty("infoArchivo"));
		btnGuardar.setText(idioma.getProperty("guardar"));
		
		TitledBorder tb = (TitledBorder) panel.getBorder();
		tb.setTitle(idioma.getProperty("conectar"));
		
		repaint();
	}

	public JTextField getTxtPin() {
		return txtPin;
	}
}
