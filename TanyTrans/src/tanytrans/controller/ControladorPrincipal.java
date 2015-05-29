package tanytrans.controller;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import tanytrans.gui.VentanaPrincipal;
import tanytrans.model.Camion;
import tanytrans.model.Cliente;
import tanytrans.model.Empleado;
import tanytrans.model.ModeloCamion;
import tanytrans.model.ModeloCliente;
import tanytrans.model.ModeloEmpleado;

public class ControladorPrincipal {

	private static ControladorPrincipal instance;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private ModeloCamion modeloCamion;
	private ModeloCliente modeloCliente;
	private ModeloEmpleado modeloEmpleado;
	private VentanaPrincipal vPrincipal;

	private ControladorPrincipal() {
		dimension = new Dimension((int) dimension.getWidth() / 2,
				(int) dimension.getHeight() / 2);
		modeloEmpleado = ModeloEmpleado.getInstance();
		modeloCliente = ModeloCliente.getInstance();
		modeloCamion = ModeloCamion.getInstance();
		vPrincipal = new VentanaPrincipal(dimension);
		
		try {
			cargaCamiones();
			cargaEmpleados();
			cargaClientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cambiaPanel(String panel) {
		CardLayout layout = (CardLayout) vPrincipal.getContentPane().getLayout();
		layout.show(vPrincipal.getContentPane(), panel);
	}

	public void cambiaMenu(String panel) {
		CardLayout layout = (CardLayout) vPrincipal.getPanelPrincipal().getEleccion().getLayout();
		layout.show(vPrincipal.getPanelPrincipal().getEleccion(), panel);
	}
	
	private void cargaCamiones() throws SQLException {
		ArrayList<Camion> camiones = modeloCamion.consultaCamiones();
		vPrincipal.getPanelListaCamiones().setTableModel(camiones);
	}
	
	private void cargaEmpleados() throws SQLException {
		ArrayList<Empleado> empleados = modeloEmpleado.consultaEmpleados();
		vPrincipal.getPanelListaEmpleados().setTableModel(empleados);
	}
	
	private void cargaClientes() throws SQLException {
		ArrayList<Cliente> clientes = modeloCliente.consultaClientes();
		vPrincipal.getPanelListaClientes().setTableModel(clientes);
	}

	public static ControladorPrincipal getInstance() {
		if (instance == null) instance = new ControladorPrincipal();
		return instance;
	}

	public VentanaPrincipal getvPrincipal() {
		return vPrincipal;
	}
}
