package tanytrans.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {
	
	private Insets insets = new Insets(10, 10, 10, 10);
	private ImageIcon logo = new ImageIcon(getClass().getResource("/tanytrans/images/TanytransLogo.png"));
	
	public PanelPrincipal() {
		
		setName("MenuPrincipal");
		setLayout(new GridBagLayout());
	
		redimensionaLogo();
		colocaComponentes();
		
	}
	
	private void colocaComponentes() {
		
		JLabel icono = new JLabel(logo);
		GridBagConstraints gbc_icono = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(icono, gbc_icono);
		
	}
	
	private void redimensionaLogo(){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		d.setSize(d.getWidth()/2, d.getWidth()/8);
		Image i = logo.getImage();
		i = i.getScaledInstance((int) d.getWidth()-100, (int) d.getHeight(), Image.SCALE_SMOOTH);
		logo.setImage(i);
	}

}