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
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kukabuka.config.Idioma;
import kukabuka.controller.ControladorPrincipal;
import kukabuka.font.FuenteKukabuka;
import kukabuka.other.BotonKukabuka;
import kukabuka.other.JFrameKukabuka;
import kukabuka.other.PanelKukabuka;
import kukabuka.twitter.TwitterController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class VentanaTweet extends JFrameKukabuka {
	
	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	private static final Color COLOR_PANEL = new Color(240 , 0, 0, 230);
	
	private int x,y,ancho, largo;
	private Idioma idioma = Idioma.getInstance();
	private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	private Twitter twitter;
	private JPanel panel;
	private BotonKukabuka btnTwittear;
	private JLabel lblimgPerfil;
	private JTextArea textArea;
	private String nomReceta;
	
	public VentanaTweet(String t) {
		
		this.twitter = ControladorPrincipal.getInstance().getcTwitter().getTwitter();
		this.nomReceta = t;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/kukabuka/images/logoKukabuka.png")));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize((int) (size.getWidth()/2.5), (int) (size.getHeight()/2.5));
		PanelKukabuka p = new PanelKukabuka(getSize(), this);
		setContentPane(p);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		this.x = (int) (size.getWidth()/65);
		this.y = (int) (size.getHeight()/20);
		this.ancho = (int) (getWidth()-(x*2));
		this.largo = (int) (getHeight()/1.3);
		
		colocaComponentes();
		setImagePerfil();
		setTitles();
		
		setVisible(true);
	}
	
	private void colocaComponentes() {
		
		Insets insets = new Insets(10, 10, 10, 10);
		
		panel = new JPanel();
		panel.setBackground(COLOR_PANEL);
		GridBagLayout gbl = new GridBagLayout();
		panel.setLayout(gbl);
		panel.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 3, true), null, TitledBorder.CENTER, TitledBorder.TOP, FONT, Color.BLACK));
		panel.setBounds(x, y, ancho, largo);
		getContentPane().add(panel);
		
		btnTwittear = new BotonKukabuka("Tweet");
		btnTwittear.setFont(FONT);
		btnTwittear.setForeground(Color.BLACK);
		GridBagConstraints posBtn = new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panel.add(btnTwittear, posBtn);
		btnTwittear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().getcTwitter().escribirTweet();
			}
		});
		
		lblimgPerfil = new JLabel();
		lblimgPerfil.setFont(FONT);
		lblimgPerfil.setForeground(Color.BLACK);
		GridBagConstraints posImg = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panel.add(lblimgPerfil, posImg);
		
		textArea = new JTextArea();
		textArea.setFont(FONT);
		textArea.setForeground(Color.BLACK);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints posTxt = new GridBagConstraints(1, 0, 2, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		panel.add(scroll, posTxt);
	}
	
	private void setImagePerfil () {
		User usuario;
		URL urlImg = null;
		try {
			usuario = this.twitter.showUser(this.twitter.getId());
			urlImg = new URL(usuario.getProfileImageURL());
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (TwitterException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        ImageIcon img = new ImageIcon(urlImg);
        lblimgPerfil.setIcon(img);
	}
	
	public void setTitles() {
		setTexto();
		
		TitledBorder tb = (TitledBorder) panel.getBorder();
		tb.setTitle(idioma.getProperty("enviarTweet"));
		
		repaint();
	}
	
	private void setTexto(){
		String s = idioma.getProperty("tweet1")+nomReceta+idioma.getProperty("tweet2")+"http://kukabuka.esy.es @Kukabuka_ #KukabukaApp";
		textArea.setText(s);
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}
