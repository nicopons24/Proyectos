package tanytrans.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tanytrans.custom.TPanel;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Color;

public class Frame extends JFrame {
	
	public Frame() {
		TPanel contentPane = new TPanel(new ImageIcon(getClass().getResource("/tanytrans/images/Carretera.jpeg")));
		setContentPane(contentPane);
		
		setSize(800, 600);
		setTitle("TANYTRANS");
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout cl = new CardLayout(20, 20);
		setLayout(cl);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);
		
		PEmpleados empleados = new PEmpleados();
		getContentPane().add(empleados,"EMPLEADOS");
		
		ItemMenu(menuBar);
		
		setVisible(true);
	}
	
	private void ItemMenu(JMenuBar menuBar) {
		
		JMenuItem mntmEmpleados = new JMenuItem("Empleados");
		menuBar.add(mntmEmpleados);
		
		JMenuItem mntmCamiones = new JMenuItem("Camiones");
		menuBar.add(mntmCamiones);
		
		JMenuItem mntmViajes = new JMenuItem("Viajes");
		menuBar.add(mntmViajes);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		menuBar.add(mntmClientes);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		menuBar.add(mntmBuscar);
	}

}
