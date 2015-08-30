package tanytrans.controller;

import java.awt.CardLayout;
import java.awt.Toolkit;

import tanytrans.gui.ViajeFrame;
import tanytrans.gui.FacturaPanel;
import tanytrans.gui.ListaPanel;
import tanytrans.gui.MainFrame;

public class MainController {

	private static MainController instance = null;
	
	private final MainFrame ventanaPrincipal;
	
	private MainController(){
		ventanaPrincipal = new MainFrame(Toolkit.getDefaultToolkit().getScreenSize());
	}
	
	public void showList() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), ListaPanel.NAME);
	}
	
	public void showFactura() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), FacturaPanel.NAME);
	}
	
	public void showFactura(Object o) {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), FacturaPanel.NAME);
		ventanaPrincipal.getPanelFactura().setData(o);		
	}
	
	public void newViaje() {
		new ViajeFrame(ventanaPrincipal.getSize(), ventanaPrincipal);
	}
	
	public void editViaje(Object o) {
		ViajeFrame viajeFrame = new ViajeFrame(ventanaPrincipal.getSize(), ventanaPrincipal);
		viajeFrame.setData(o);
	}
	
	public static MainController getInstance() {
		if (instance == null) { instance = new MainController();}
		return instance;
	}
}
