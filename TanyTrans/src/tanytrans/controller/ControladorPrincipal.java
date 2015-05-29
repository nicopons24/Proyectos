package tanytrans.controller;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import tanytrans.gui.VentanaPrincipal;

public class ControladorPrincipal {
	
	private static ControladorPrincipal instance;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private VentanaPrincipal vPrincipal;

	private ControladorPrincipal() {
		dimension = new Dimension((int) dimension.getWidth()/2,(int) dimension.getHeight()/2);
		vPrincipal = new VentanaPrincipal(dimension);
	}
	
	public void cambiaPanel(String panel) {
		CardLayout layout = (CardLayout) vPrincipal.getContentPane().getLayout();
		layout.show(vPrincipal.getContentPane(), panel);
	}

	public static ControladorPrincipal getInstance() {
		if (instance == null) instance = new ControladorPrincipal();
		return instance;
	}
}
