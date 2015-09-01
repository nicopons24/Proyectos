package tanytrans.controller;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;

import tanytrans.gui.DatosEmpresaFrame;
import tanytrans.gui.FacturaPanel;
import tanytrans.gui.ListaPanel;
import tanytrans.gui.MainFrame;
import tanytrans.gui.ViajeFrame;
import tanytrans.model.Factura;
import tanytrans.model.FacturaModel;
import tanytrans.model.Viaje;
import tanytrans.tablemodel.ViajesTableModel;

public class MainController {

	private static MainController instance = null;
	private static FacturaModel modeloFactura;
	
	private final MainFrame ventanaPrincipal;
	
	private MainController(){
		modeloFactura = FacturaModel.getInstance();
		ventanaPrincipal = new MainFrame(Toolkit.getDefaultToolkit().getScreenSize());
	}
	
	public void buscarFacturaMes(int mes, int year) {
		ArrayList<Factura> facturas = modeloFactura.consultaFacturasMes(mes, year);
		ventanaPrincipal.getPanelLista().setData(facturas);
	}
	
	public void buscarFacturaDia(Date inicio, Date fin) {
		ArrayList<Factura> facturas = modeloFactura.consultaFacturasDia(inicio, fin);
		ventanaPrincipal.getPanelLista().setData(facturas);
	}
	
	public void showList() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), ListaPanel.NAME);
	}
	
	public void showFactura() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), FacturaPanel.NAME);
	}
	
	public void showFactura(Factura f) {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), FacturaPanel.NAME);
		ventanaPrincipal.getPanelFactura().setData(f);		
	}
	
	public void newViaje() {
		new ViajeFrame(ventanaPrincipal.getSize(), ventanaPrincipal);
	}
	
	public void editViaje(int pos, Viaje v) {
		new ViajeFrame(ventanaPrincipal.getSize(), ventanaPrincipal, v, pos);
	}
	
	public void addViajeFactura(Viaje v) {
		ViajesTableModel model = ventanaPrincipal.getPanelFactura().getTableModel();
		model.addRow(v);
	}
	
	public void editViajeFactura(int pos, Viaje v) {
		ViajesTableModel model = ventanaPrincipal.getPanelFactura().getTableModel();
		model.setViajeAt(pos, v);
	}
	
	public void deleteViajeFactura(int pos) {
		ViajesTableModel model = ventanaPrincipal.getPanelFactura().getTableModel();
		model.deleteRow(pos);
	}
	
	public void showDatosEmpresa() {
		new DatosEmpresaFrame(ventanaPrincipal.getSize(), ventanaPrincipal);
	}
	
	public static MainController getInstance() {
		if (instance == null) { instance = new MainController();}
		return instance;
	}
}
