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
		dimension = new Dimension((int) dimension.getWidth() * 2 / 3,(int) dimension.getHeight() * 3 / 4);
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
	
	public void datosCamion(int selectedIndex) {
		Camion c = vPrincipal.getPanelListaCamiones().getTableModel().getCamiones().get(selectedIndex);
		vPrincipal.getPanelListaCamiones().setDatos(c);
	}
	
	public void datosCliente(int selectedIndex) {
		Cliente c = vPrincipal.getPanelListaClientes().getTableModel().getClientes().get(selectedIndex);
		vPrincipal.getPanelListaClientes().setDatos(c);
	}
	
	public void datosEmpleado(int selectedIndex) {
		Empleado e = vPrincipal.getPanelListaEmpleados().getTableModel().getEmpleados().get(selectedIndex);
		vPrincipal.getPanelListaEmpleados().setDatos(e);
	}
	
	public void actualizaCamion(int selectedIndex) {
		Camion c = vPrincipal.getPanelListaCamiones().getTableModel().getCamiones().get(selectedIndex);
		Camion nuevo = vPrincipal.getPanelListaCamiones().getDatos();
		c.setMarca(nuevo.getMarca());
		c.setModelo(nuevo.getModelo());
		c.setMatricula(nuevo.getMatricula());
		c.setChasis(nuevo.getChasis());
		vPrincipal.getPanelListaCamiones().getTableModel().updateRow(c, selectedIndex);
		ArrayList<Camion> camiones = vPrincipal.getPanelListaCamiones().getTableModel().getCamiones();
		vPrincipal.getPanelNuevoViaje().cargaCamiones(camiones);
	}
	
	public void actualizaCliente(int selectedIndex) {
		Cliente c = vPrincipal.getPanelListaClientes().getTableModel().getClientes().get(selectedIndex);
		Cliente nuevo = vPrincipal.getPanelListaClientes().getDatos();
		c.setNombre(nuevo.getNombre());
		c.setDireccion(nuevo.getDireccion());
		c.setTelefono1(nuevo.getTelefono1());
		c.setTelefono2(nuevo.getTelefono2());
		vPrincipal.getPanelListaClientes().getTableModel().updateRow(c, selectedIndex);
	}
	
	public void actualizaEmpleado(int selectedIndex) {
		Empleado e = vPrincipal.getPanelListaEmpleados().getTableModel().getEmpleados().get(selectedIndex);
		Empleado nuevo = vPrincipal.getPanelListaEmpleados().getDatos();
		e.setNombre(nuevo.getNombre());
		e.setApellidos(nuevo.getApellidos());
		e.setDni(nuevo.getDni());
		e.setTelefono(nuevo.getTelefono());
		vPrincipal.getPanelListaEmpleados().getTableModel().updateRow(e, selectedIndex);
	}
	
	public void eliminaCamion(int selectedIndex) {
		vPrincipal.getPanelListaCamiones().getTableModel().deleteRow(selectedIndex);
		ArrayList<Camion> camiones = vPrincipal.getPanelListaCamiones().getTableModel().getCamiones();
		vPrincipal.getPanelNuevoViaje().cargaCamiones(camiones);
	}
	
	public void eliminaCliente(int selectedIndex) {
		vPrincipal.getPanelListaClientes().getTableModel().deleteRow(selectedIndex);
		ArrayList<Cliente> clientes = vPrincipal.getPanelListaClientes().getTableModel().getClientes();
		vPrincipal.getPanelNuevoViaje().cargaClientes(clientes);
	}
	
	public void eliminaEmpleado(int selectedIndex) {
		vPrincipal.getPanelListaEmpleados().getTableModel().deleteRow(selectedIndex);
		ArrayList<Empleado> empleados = vPrincipal.getPanelListaEmpleados().getTableModel().getEmpleados();
		vPrincipal.getPanelNuevoViaje().cargaEmpleados(empleados);
	}
	
	private void cargaCamiones() throws SQLException {
		ArrayList<Camion> camiones = modeloCamion.consultaCamiones();
		vPrincipal.getPanelListaCamiones().setTableModel(camiones);
		vPrincipal.getPanelNuevoViaje().cargaCamiones(camiones);
	}
	
	private void cargaEmpleados() throws SQLException {
		ArrayList<Empleado> empleados = modeloEmpleado.consultaEmpleados();
		vPrincipal.getPanelListaEmpleados().setTableModel(empleados);
		vPrincipal.getPanelNuevoViaje().cargaEmpleados(empleados);
	}
	
	private void cargaClientes() throws SQLException {
		ArrayList<Cliente> clientes = modeloCliente.consultaClientes();
		vPrincipal.getPanelListaClientes().setTableModel(clientes);
		vPrincipal.getPanelNuevoViaje().cargaClientes(clientes);
	}
	

	public static ControladorPrincipal getInstance() {
		if (instance == null) instance = new ControladorPrincipal();
		return instance;
	}

	public VentanaPrincipal getvPrincipal() {
		return vPrincipal;
	}
}
