package kukabuka.other;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import kukabuka.controller.ControladorPrincipal;
import kukabuka.font.FuenteKukabuka;
import kukabuka.gui.VentanaReceta;
import kukabuka.model.ConexionDB;
import kukabuka.model.Imagen;
import kukabuka.model.Ingrediente;
import kukabuka.model.Receta;
import kukabuka.model.Usuario;

public class BotonReceta extends JButton implements MouseListener{

	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	private Receta receta;
	private ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/kukabuka/images/logoKukabuka.png")));
	
	public BotonReceta(Receta receta){
		
		this.receta = receta;
		this.img = redimensionar(150, 150, img);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setHorizontalTextPosition(CENTER);
		setVerticalTextPosition(BOTTOM);
		setBorder(new LineBorder(Color.BLACK, 3, true));
		setForeground(Color.BLACK);
		setFont(FONT);
		setText(this.receta.getNombre());
		colocaImagen(img);
		
		setVisible(true);
		addMouseListener(this);
	}
	
	public void colocaImagen(ImageIcon i) {
		setIcon(i);
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

	public Receta getReceta() {
		return receta;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ControladorPrincipal.getInstance().ventanaReceta(receta);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBorder(new LineBorder(Color.WHITE, 3, true));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBorder(new LineBorder(Color.BLACK, 3, true));
		setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		setForeground(Color.WHITE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
