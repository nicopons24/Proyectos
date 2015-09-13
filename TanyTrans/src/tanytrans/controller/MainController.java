package tanytrans.controller;

import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import tanytrans.config.Calculos;
import tanytrans.config.Data;
import tanytrans.gui.ClientePanel;
import tanytrans.gui.DatosEmpresaFrame;
import tanytrans.gui.FacturaPanel;
import tanytrans.gui.ListaPanel;
import tanytrans.gui.MainFrame;
import tanytrans.gui.MainPanel;
import tanytrans.gui.ViajeFrame;
import tanytrans.model.Cliente;
import tanytrans.model.ClienteModel;
import tanytrans.model.ConexionBD;
import tanytrans.model.Factura;
import tanytrans.model.FacturaModel;
import tanytrans.model.MiEmpresa;
import tanytrans.model.Viaje;
import tanytrans.model.ViajeModel;
import tanytrans.reports.model.FacturaDatasource;
import tanytrans.tablemodel.ClientesTableModel;
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
		consultaClientes();
		consulta10Facturas();
	}
	
	private void consulta10Facturas() {
		try {
			ArrayList<Factura> facturas = modeloFactura.consultaFacturas();
			ventanaPrincipal.getPanelPrincipal().loadFacturas(facturas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void consultaClientes() {
		Data.clientes = modeloCliente.consultaClientes();
		ventanaPrincipal.getPanelFactura().loadData();
		ventanaPrincipal.getPanelCliente().loadData();
	}
	
	public void buscarFacturaMes(int mes, int year) {
		ArrayList<Factura> facturas;
		try {
			facturas = modeloFactura.consultaFacturasMes(mes, year);
			ventanaPrincipal.getPanelLista().setData(facturas);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal, e.getMessage(), ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void buscarFacturaDia(Date inicio, Date fin) {
		try {
			ArrayList<Factura> facturas = modeloFactura.consultaFacturasDia(inicio, fin);
			ventanaPrincipal.getPanelLista().setData(facturas);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal, e.getMessage(), ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
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
		int o = JOptionPane.showConfirmDialog(ventanaPrincipal, "Exportar factura a pdf?", ventanaPrincipal.getTitle(), JOptionPane.YES_NO_OPTION);
		switch (o) {
		case 0:
			Cliente c = null;
			for (Cliente cl:Data.clientes) {
				if (f.getIdCliente() == cl.getIdCliente()) {
					c = cl;
					break;
				}
			}
			MainController.getInstance().printFactura(f, c);
			break;
		case 1:
			break;
		}
	}
	
	public void updateFactura(Factura f, int row) {
		int o = JOptionPane.showConfirmDialog(ventanaPrincipal, "Deseas guardar la factura?", ventanaPrincipal.getTitle(), JOptionPane.YES_NO_OPTION);
		switch (o) {
		case 0:
			try{
				for (Viaje v: f.getNewViajes()) {
					v.setNumFactura(f.getNumFactura());
					modeloViajes.insertaViajes(v);
				}
				modeloFactura.updateFactura(f);
				for (Viaje v: f.getEditViajes()) {
					v.setNumFactura(f.getNumFactura());
					modeloViajes.updateViajes(v);
				}
				for (Viaje v: f.getDelViajes()) {
					modeloViajes.deleteViajes(v);
				}
				ConexionBD.getInstance().getConnection().commit();
				((ListaFacturasTableModel) ventanaPrincipal.getPanelLista().getTableModel()).setFacturaAt(row, f);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(ventanaPrincipal, e.getMessage(), ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
			o = JOptionPane.showConfirmDialog(ventanaPrincipal, "Guardar Factura"+f.getNumFacturaString()+" como pdf?", ventanaPrincipal.getTitle(), JOptionPane.YES_NO_OPTION);
			switch (o) {
			case 0:
				Cliente c = null;
				for (Cliente cl:Data.clientes) {
					if (f.getIdCliente() == cl.getIdCliente()) {
						c = cl;
						break;
					}
				}
				MainController.getInstance().printFactura(f, c);
				break;
			case 1:
				break;
			}
			break;
		case 1:
			break;
		}
		showList();
	}
	
	public void deleteFactura(Factura f, int row) {
		int o = JOptionPane.showConfirmDialog(ventanaPrincipal, "Deseas eliminar la factura?", ventanaPrincipal.getTitle(), JOptionPane.YES_NO_OPTION);
		switch (o) {
		case 0:
			try {
				for (Viaje v: f.getViajes()) {
					modeloViajes.deleteViajes(v);
				}
				modeloFactura.deleteFactura(f);
				ConexionBD.getInstance().getConnection().commit();
				JOptionPane.showMessageDialog(ventanaPrincipal, "Factura eliminada correctamente", ventanaPrincipal.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				((ListaFacturasTableModel) ventanaPrincipal.getPanelLista().getTableModel()).deleteRow(row);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(ventanaPrincipal, e.getMessage(), ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
			break;
		case 1:
			break;
		}
	}
	
	public void saveCliente(Cliente c) {
		try {
			modeloCliente.insertaCliente(c);
			ConexionBD.getInstance().getConnection().commit();
			JOptionPane.showMessageDialog(ventanaPrincipal, "Cliente guardado correctamente", ventanaPrincipal.getTitle(), JOptionPane.INFORMATION_MESSAGE);
			consultaClientes();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal, e.getMessage(), ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateCliente(Cliente c, int row) {
		try {
			modeloCliente.updateCliente(c);
			ConexionBD.getInstance().getConnection().commit();
			JOptionPane.showMessageDialog(ventanaPrincipal, "Cliente guardado correctamente", ventanaPrincipal.getTitle(), JOptionPane.INFORMATION_MESSAGE);
			((ClientesTableModel) ventanaPrincipal.getPanelCliente().getTableModel()).setClienteaAt(row, c);
			Data.setClientes(((ClientesTableModel) ventanaPrincipal.getPanelCliente().getTableModel()).getClientes());
			ventanaPrincipal.getPanelFactura().loadData();
			ventanaPrincipal.getPanelCliente().loadData();
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
		ventanaPrincipal.getPanelFactura().newData();
	}
	
	public void showHome() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), MainPanel.NAME);
		consulta10Facturas();
	}
	
	public void showClientes() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), ClientePanel.NAME);
		ventanaPrincipal.getPanelCliente().ocultaPanelDatos();
	}
	
	public void editCliente(Cliente c, int row) {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), ClientePanel.NAME);
		ventanaPrincipal.getPanelCliente().setData(c, row);
	}
	
	public void newCliente() {
		((CardLayout) ventanaPrincipal.getContentPane().getLayout()).show(ventanaPrincipal.getContentPane(), ClientePanel.NAME);
		ventanaPrincipal.getPanelCliente().newData();
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
		int o = JOptionPane.showConfirmDialog(ventanaPrincipal, "Deseas eliminar el viaje?", ventanaPrincipal.getTitle(), JOptionPane.YES_NO_OPTION);
		switch (o) {
		case 0:
			ViajesTableModel model = ventanaPrincipal.getPanelFactura().getTableModel();
			model.deleteRow(pos);
			break;
		case 1:
			break;
		}
	}
	
	public void printFactura(Factura f, Cliente c) {
		InputStream template = getClass().getResourceAsStream("/tanytrans/reports/gui/TanytransFactura.jasper");
		FacturaDatasource dataSource = new FacturaDatasource(f, c);
	    JFileChooser chooser = openFileChooser("Factura"+f.getNumFacturaString());
	    int selection = chooser.showSaveDialog(ventanaPrincipal);
        switch (selection) {
        case JFileChooser.APPROVE_OPTION:
        	File file = chooser.getSelectedFile();
        	String path = file.getAbsolutePath();
        	if (!path.endsWith(".pdf")) {
        		file = new File(path+".pdf");
        	}
        	if (file.exists()) {
        		int o = JOptionPane.showConfirmDialog(ventanaPrincipal, file.getName()+" ya existe. \n\r Deseas reepmlazarlo?", ventanaPrincipal.getTitle(), JOptionPane.YES_NO_OPTION);
        		switch (o) {
        		case 0:
					printReport(dataSource, template, file);
        			break;
        		case 1:
        			break;
        		}
        	}
        	else {
				printReport(dataSource, template, file);	
        	}
        	break;
        case JFileChooser.CANCEL_OPTION:
        	break;
        case JFileChooser.ERROR_OPTION:
        	JOptionPane.showMessageDialog(ventanaPrincipal, "Error al guardar", ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
        	break;
        }
	}
	
	@SuppressWarnings({ "deprecation" })
	private void printReport (JRDataSource dataSource, InputStream template, File file) {
		try {
			JasperReport report = (JasperReport) JRLoader.loadObject(template);
			JasperPrint print = JasperFillManager.fillReport(report, null, dataSource);
			@SuppressWarnings("rawtypes")
			JRExporter exporter = new JRPdfExporter();  
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
	        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file); 
	        exporter.exportReport();
	        JOptionPane.showMessageDialog(ventanaPrincipal, file.getName()+" guardado correctamente", ventanaPrincipal.getTitle(), JOptionPane.INFORMATION_MESSAGE);
	        Desktop.getDesktop().open(file);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal, "No se pudo crear el pdf", ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal, "No se pudo abrir "+file.getName(), ventanaPrincipal.getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JFileChooser openFileChooser(String fileName) {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogTitle("Guardar en...");
        try {
			Method fileNameString = chooser.getUI().getClass().getMethod("setFileName", String.class);
			fileNameString.invoke(chooser.getUI(), fileName);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
        chooser.addChoosableFileFilter(new FileFilter() {
        	static final String FILTER = "PDF (*.pdf)";
			@Override
			public String getDescription() {
				return FILTER;
			}
			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".pdf");
			}
		});
        return chooser;
	}
	
	public void openCalc() {
		String os = System.getProperty("os.name").toLowerCase();
		Runtime r = Runtime.getRuntime();
		try {
			if (Calculos.isUnix(os)) { 
				r.exec("/usr/bin/gnome-calculator");
			}
			if (Calculos.isWindows(os)) {
				r.exec("C:\\Windows\\System32\\calc.exe");
			}
			if (Calculos.isMac(os)){
				r.exec("/Applications/Calculator.app");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
