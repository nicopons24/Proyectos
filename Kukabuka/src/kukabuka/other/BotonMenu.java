package kukabuka.other;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotonMenu extends JButton implements MouseListener{

	private ImageIcon icono, iconoSel;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	
	public BotonMenu (ImageIcon img, ImageIcon imgSel){
		super();
		this.icono = img;
		this.iconoSel = imgSel;
		setIcon(icono);
		setSize(icono.getIconWidth()+10, icono.getIconHeight()+10);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setVisible(true);
		addMouseListener(this);
	}
	
	public ImageIcon redimensionar(int width, int height, ImageIcon img){
		if(width == 0 || height == 0){
			width++;
			height++;
		}
		Image img2 = img.getImage();
		Image img3 = img2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img3);
		return icon;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setIcon(iconoSel);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setIcon(icono);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
