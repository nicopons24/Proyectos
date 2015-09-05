package tanytrans.controller;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import tanytrans.config.Data;
import tanytrans.gui.DatosEmpresaFrame;
import tanytrans.gui.FacturaPanel;
import tanytrans.gui.ListaPanel;
import tanytrans.gui.MainFrame;
import tanytrans.gui.MainPanel;
import tanytrans.gui.ViajeFrame;
import tanytrans.model.ClienteModel;
import tanytrans.model.ConexionBD;
import tanytrans.model.Factura;
import tanytrans.model.FacturaModel;
import tanytrans.model.MiEmpresa;
import tanytrans.model.Viaje;
import tanytrans.model.ViajeModel;
import tanytrans.tablemodel.ListaFacturasTableModel;
import tanytrans.tablemodel.ViajesTableModel;

public class MainController {

	private static MainController instance = null;
	private static FacturaModel modeloFactura;
	private static ClienteModel modeloCliente;
	private static ViajeModel modeloViajes;
	
	private final MainFrame ventanaPrincipal;
	
	private MainController(){
		modeloFactura = FacturaModel.getInstance();
		modeloCliente = ClienteModel.getInstance();
		modeloViajes = ViajeModel.getInstance();
		ventanaPrincipal = new MainFrame(Toolkit.getDefaultToolkit().getScreenSize());
	}
	
	public void buscarFacturaMes(int mes, int year) {
		ArrayList<Factura> facturas;
		try {
			facturas = modeloFactura.consultaFacturasMes(mes, year);
			ventanaPrincipal.getPanelLista().setData(facturas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void buscarFacturaDia(Date inicio, Date fin) {
		try {
			ArrayList<Factura> facturas = modeloFactura.consultaFacturasDia(inicio, fin);
			ventanaPrincipal.getPanelLista().setData(facturas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveFactura(Factura f) {
		try {
			modeloFactura.insertFactura(f);
			for (Viaje v: f.getViajes()) {
				v.setNumFactura(f.getNumFactura());
				modeloViajes.insertaViajes(v);
			}
			ConexionBD.getInstance().getConnection().commit();
			JOptionPane.showMessageDialog(ventanaPrincipal, "Factura guardada correctamente", ventanaPrincipal.getTitle(), JOptionPane.INFORMATION_MESSAGE);
			Data.refreshNumFactura();
			ventanaPrincipal.getPanelFactura().newData();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal, e.getMessage(), ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateFactura(Factura f, int row) {
		try{
			modeloFactura.updateFactura(f);
			ConexionBD.getInstance().getConnection().commit();
			for (Viaje v: f.getViajes()) {
				v.setNumFactura(f.getNumFactura());
				modeloViajes.updateViajes(v);
				ConexionBD.getInstance().getConnection().commit();
			}
			JOptionPane.showMessageDialog(ventanaPrincipal, "Factura actualizada correctamente", ventanaPrincipal.getTitle(), JOptionPane.INFORMATION_MESSAGE);
			((ListaFacturasTableModel) ventanaPrincipal.getPanelLista().getTableModel()).setFacturaAt(row, f);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal, e.getMessage(), ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateDatosEmpresa() {
		try {
			MiEmpresa.getInstance().updateEmpresa();
			ConexionBD.getInstance().getConnection().commit();
			JOptionPane.showMessageDialog(ventanaPrincipal, "Datos de la empresa actualizados correctamente", ventanaPrincipal.getTitle(), JOptionPane.INFORMATION_MESSAGE);
			ventanaPrincipal.getPanelPrincipal().refreshDatosEmpresa();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal, e.getMessage(), ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void showList() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), ListaPanel.NAME);
	}
	
	public void showFactura() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), FacturaPanel.NAME);
	}
	
	public void showHome() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), MainPanel.NAME);
	}
	
	public void showFactura(Factura f, int row) {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), FacturaPanel.NAME);
		ventanaPrincipal.getPanelFactura().setData(f, row);		
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

	public MainFrame getVentanaPrincipal() {
		return ventanaPrincipal;
	}
}
