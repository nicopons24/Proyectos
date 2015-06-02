package kukabuka.other;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import kukabuka.config.Idioma;

public class BotonIdioma extends JButton implements MouseListener{
	
	private static final int ES = 0;
	private static final int EN = 1;
	private ImageIcon esp, espSel, eng, engSel;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Idioma idioma = Idioma.getInstance();
	private JFrameKukabuka frame;

	public BotonIdioma (JFrameKukabuka frame) {
		super();
		this.frame = frame;
		esp = new ImageIcon((getClass().getResource("/kukabuka/images/esp.png")));
		esp = redimensionar((int) pantalla.getHeight()/50,(int) pantalla.getHeight()/50, this.esp);
		eng = new ImageIcon((getClass().getResource("/kukabuka/images/eng.png")));
		eng = redimensionar((int) pantalla.getHeight()/50,(int) pantalla.getHeight()/50, this.eng);
		espSel = new ImageIcon((getClass().getResource("/kukabuka/images/espWhite.png")));
		espSel = redimensionar((int) pantalla.getHeight()/50,(int) pantalla.getHeight()/50, this.espSel);
		engSel = new ImageIcon((getClass().getResource("/kukabuka/images/engWhite.png")));
		engSel = redimensionar((int) pantalla.getHeight()/50,(int) pantalla.getHeight()/50, this.engSel);
		
		imagenBoton();
		
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setVisible(true);
		addMouseListener(this);
	}
	
	private void imagenBoton() {
		
		switch (idioma.getIdioma()) {
			default:
			case ES:
				setIcon(esp);
				break;
			case EN:
				setIcon(eng);
				break;
		}
		
		setSize(getIcon().getIconWidth()+10, getIcon().getIconHeight()+10);
		
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

	@Override
	public void mouseClicked(MouseEvent e) {
		int id = idioma.getIdioma();
		try {
			switch (id) {
			case ES:
				idioma.setIdioma(EN);
				break;
			case EN:
				idioma.setIdioma(ES);
				break;
			}
			frame.setTitles();
			imagenBoton();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		switch (this.idioma.getIdioma()) {
		case ES:
			setIcon(espSel);
			break;
		case EN:
			setIcon(engSel);
			break;
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		switch (this.idioma.getIdioma()) {
		case ES:
			setIcon(esp);
			break;
		case EN:
			setIcon(eng);
			break;
		}
		
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
