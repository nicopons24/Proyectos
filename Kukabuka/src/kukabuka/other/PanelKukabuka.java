package kukabuka.other;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import kukabuka.config.Idioma;
import kukabuka.controller.ControladorPrincipal;
import kukabuka.font.FuenteKukabuka;
import kukabuka.gui.VentanaPerfil;
import kukabuka.gui.VentanaReceta;

public class PanelKukabuka extends JPanel {

	private ImageIcon img = new ImageIcon(this.getClass().getResource("/kukabuka/images/Background.jpeg"));
	private ImageIcon cerrar, minimizar, cerrarSel, minimizarSel;
	private JFrameKukabuka frame;
	private Dimension size;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	static Point mouseDownCompCoords;

	
	public PanelKukabuka(Dimension d, JFrameKukabuka frame) {
		super();
		this.size = d;
		this.frame = frame;
		this.cerrar = new ImageIcon(getClass().getResource("/kukabuka/images/close33.png"));
		this.cerrar = redimensionar((int) pantalla.getHeight()/50,(int) pantalla.getHeight()/50, this.cerrar);
		this.minimizar = new ImageIcon((getClass().getResource("/kukabuka/images/minus65.png")));
		this.minimizar = redimensionar((int) pantalla.getHeight()/50,(int) pantalla.getHeight()/50, this.minimizar);
		this.cerrarSel = new ImageIcon(getClass().getResource("/kukabuka/images/close33white.png"));
		this.cerrarSel = redimensionar((int) pantalla.getHeight()/50,(int) pantalla.getHeight()/50, this.cerrarSel);
		this.minimizarSel = new ImageIcon((getClass().getResource("/kukabuka/images/minus65white.png")));
		this.minimizarSel = redimensionar((int) pantalla.getHeight()/50,(int) pantalla.getHeight()/50, this.minimizarSel);
		
		setLayout(null);
		
		botones();
		barra();
		
		this.setVisible(true);
	}
	
	private void barra(){
		
		int x =(int) (pantalla.getHeight()/45);
		
		ImageIcon img = new ImageIcon(this.getClass().getResource("/kukabuka/images/logoKukabuka.png"));
		img = redimensionar((int) pantalla.getHeight()/45,(int) pantalla.getHeight()/45, img);
		
		JLabel texto = new JLabel("KUKABUKA");
		texto.setForeground(Color.BLACK);
		texto.setFont(FONT);
		texto.setIcon(img);
		texto.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		texto.setSize((int) size.getWidth(), img.getIconHeight()+10);
		texto.setLocation((int) (x+(size.getWidth()/70)), 0);
		add(texto);
		
		JLabel barra = new JLabel();
		barra.setBackground(new Color(240, 0, 0, 230));
		barra.setBounds(0, 0, (int) (size.getWidth()), (int) (pantalla.getHeight()/32));
		barra.setOpaque(true);
		barra.addMouseListener(new MouseListener(){
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }
        });
        barra.addMouseMotionListener(new MouseMotionListener(){
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
		
		add(barra);
	}
	
	private void botones(){
		int x =(int) (size.getWidth()-size.getWidth()/30);
		
		BotonMenu cerrar = new BotonMenu(this.cerrar, this.cerrarSel);
		cerrar.setLocation((int) x-cerrar.getWidth(), 0);
		add(cerrar);
		cerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		x = x-cerrar.getWidth();
		
		BotonMenu minimizar = new BotonMenu(this.minimizar, this.minimizarSel);
		minimizar.setLocation((int) x-minimizar.getWidth(), 0);
		add(minimizar);
		minimizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		
		x = x-minimizar.getWidth();
		
		BotonIdioma idioma = new BotonIdioma(frame);
		idioma.setLocation((int) x-idioma.getWidth(), 0);
		add(idioma);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Dimension size = getSize();
		g.drawImage(img.getImage(), 0, 0, size.width, size.height, null);
		setOpaque(false);
		super.paintComponent(g);
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
}
