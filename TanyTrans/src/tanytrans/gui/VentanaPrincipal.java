package tanytrans.gui;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import tanytrans.custom.TPanel;

public class VentanaPrincipal extends JFrame {
	
	private JMenuBar barraMenu;
	private JMenu menuHome, menuViajes, menuClientes, menuEmpleados, menuCamiones;
	private JMenuItem nuevoViaje, listaViaje, nuevoCliente, listaCliente, nuevoEmpleado, listaEmpleado, nuevoCamion, listaCamion;
	private PanelListaEmpleado panelListaEmpleados;
	private PanelNuevoEmpleado panelNuevoEmpleado;
	
	public VentanaPrincipal(Dimension d) {
		
		setTitle("TANYTRANS");
		setResizable(true);
		setSize((int) d.getWidth(),(int) d.getHeight());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new TPanel());
		setLayout(new CardLayout(20, 20));
		
		colocaMenuBar();
		colocaComponentes();
		
		setVisible(true);
	}
	
	private void colocaComponentes() {
		
		panelListaEmpleados = new PanelListaEmpleado();
		getContentPane().add(panelListaEmpleados.getName(), panelListaEmpleados);
		
		panelNuevoEmpleado = new PanelNuevoEmpleado();
		getContentPane().add(panelNuevoEmpleado.getName(), panelNuevoEmpleado);
	}
	
	private void colocaMenuBar() {
		
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		menuHome = new JMenu("Principal");
		barraMenu.add(menuHome);
		
		menuViajes = new JMenu("Viajes");
		barraMenu.add(menuViajes);
		
		nuevoViaje = new JMenuItem("Nuevo viaje >");
		menuViajes.add(nuevoViaje);
		
		listaViaje = new JMenuItem("Lista viajes >");
		menuViajes.add(listaViaje);
		
		menuClientes = new JMenu("Clientes");
		barraMenu.add(menuClientes);
		
		nuevoCliente = new JMenuItem("Nuevo cliente >");
		menuClientes.add(nuevoCliente);
		
		listaCliente = new JMenuItem("Lista clientes >");
		menuClientes.add(listaCliente);
		
		menuEmpleados = new JMenu("Empleados");
		barraMenu.add(menuEmpleados);
		
		nuevoEmpleado = new JMenuItem("Nuevo empleado >");
		menuEmpleados.add(nuevoEmpleado);
		
		listaEmpleado = new JMenuItem("Lista empleados >");
		menuEmpleados.add(listaEmpleado);
		
		menuCamiones = new JMenu("Camiones");
		barraMenu.add(menuCamiones);
		
		nuevoCamion = new JMenuItem("Nuevo camión >");
		menuCamiones.add(nuevoCamion);
		
		listaCamion = new JMenuItem("Lista camiones >");
		menuCamiones.add(listaCamion);
		
	}

}
