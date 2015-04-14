package tanytrans.custom;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TPanel extends JPanel {

private ImageIcon img;
	
	public TPanel(ImageIcon i) {
		super();
		img = i;
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Dimension size = getSize();
		g.drawImage(img.getImage(), 0, 0, size.width, size.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
