package tanytrans.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import tanytrans.controller.ControladorPrincipal;
import tanytrans.custom.TPanel;

public class VentanaPrincipal extends JFrame {
	
	private JMenuBar barraMenu;
	private JMenu menuHome, menuNuevo, menuLista;
	private JMenuItem nuevoViaje, listaViaje, nuevoCliente, listaCliente, nuevoEmpleado, listaEmpleado, nuevoCamion, listaCamion;
	private PanelListaEmpleado panelListaEmpleados;
	private PanelListaCliente panelListaClientes;
	private PanelListaCamion panelListaCamiones;
	private PanelNuevoEmpleado panelNuevoEmpleado;
	private PanelNuevoCliente panelNuevoCliente;
	private PanelNuevoCamion panelNuevoCamion;
	private PanelPrincipal panelPrincipal;
	
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
		
		panelPrincipal = new PanelPrincipal();
		getContentPane().add(panelPrincipal.getName(), panelPrincipal);
		
		panelListaClientes = new PanelListaCliente();
		getContentPane().add(panelListaClientes.getName(), panelListaClientes);
		
		panelListaEmpleados = new PanelListaEmpleado();
		getContentPane().add(panelListaEmpleados.getName(), panelListaEmpleados);
		
		panelListaCamiones = new PanelListaCamion();
		getContentPane().add(panelListaCamiones.getName(), panelListaCamiones);
		
		panelNuevoCliente = new PanelNuevoCliente();
		getContentPane().add(panelNuevoCliente.getName(), panelNuevoCliente);
		
		panelNuevoEmpleado = new PanelNuevoEmpleado();
		getContentPane().add(panelNuevoEmpleado.getName(), panelNuevoEmpleado);
		
		panelNuevoCamion = new PanelNuevoCamion();
		getContentPane().add(panelNuevoCamion.getName(), panelNuevoCamion);
	}
	
	private void colocaMenuBar() {
		
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		menuHome = new JMenu("Menu");
		barraMenu.add(menuHome);
		
		menuNuevo = new JMenu("Nuevo");
		barraMenu.add(menuNuevo);
		
		nuevoViaje = new JMenuItem("Viaje >");
		menuNuevo.add(nuevoViaje);
		nuevoViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ControladorPrincipal.getInstance().cambiaPanel(panelNuevoViaje.getName());
			}
		});
		
		nuevoCliente = new JMenuItem("Cliente >");
		menuNuevo.add(nuevoCliente);
		nuevoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().cambiaPanel(panelNuevoCliente.getName());
			}
		});
		
		nuevoCamion = new JMenuItem("Camión >");
		menuNuevo.add(nuevoCamion);
		nuevoCamion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().cambiaPanel(panelNuevoCamion.getName());
			}
		});
		
		nuevoEmpleado = new JMenuItem("Empleado >");
		menuNuevo.add(nuevoEmpleado);
		nuevoEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().cambiaPanel(panelNuevoEmpleado.getName());				
			}
		});
		
		menuLista = new JMenu("Listas");
		barraMenu.add(menuLista);
		
		listaViaje = new JMenuItem("Viajes >");
		menuLista.add(listaViaje);
		listaViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ControladorPrincipal.getInstance().cambiaPanel(panelListaViajes.getName());
			}
		});
		
		listaCliente = new JMenuItem("Clientes >");
		menuLista.add(listaCliente);
		listaCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().cambiaPanel(panelListaClientes.getName());
			}
		});
		
		listaEmpleado = new JMenuItem("Empleados >");
		menuLista.add(listaEmpleado);
		listaEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().cambiaPanel(panelListaEmpleados.getName());
			}
		});
		
		listaCamion = new JMenuItem("Camiones >");
		menuLista.add(listaCamion);
		listaCamion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().cambiaPanel(panelListaCamiones.getName());
			}
		});
		
	}

}
