package tanytrans.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tanytrans.custom.TPanel;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
	
	private CardLayout layout;
	private JMenuBar menu;
	private PEmpleados empleados;
	
	public Frame() {
		TPanel contentPane = new TPanel(new ImageIcon(getClass().getResource("/tanytrans/images/Carretera.jpeg")));
		setContentPane(contentPane);
		
		setSize(800, 600);
		setTitle("TANYTRANS");
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout = new CardLayout(20, 20);
		setLayout(layout);
		
		menu = new JMenuBar();
		menu.setBorderPainted(false);
		setJMenuBar(menu);
		
		JPanel inicio = new JPanel();
		JLabel img = new JLabel(new ImageIcon(getClass().getResource("/tanytrans/images/TanytransLogo.png")));
		inicio.add(img);
		inicio.setOpaque(false);
		getContentPane().add(inicio, "INICIO");
		
		empleados = new PEmpleados();
		getContentPane().add(empleados,"EMPLEADOS");
		
		ItemMenu(menu);
		
		setVisible(true);
	}
	
	private void ItemMenu(JMenuBar menuBar) {
		
		JMenuItem mntmEmpleados = new JMenuItem("Empleados");
		menuBar.add(mntmEmpleados);
		mntmEmpleados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "EMPLEADOS");
			}
		});
		
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
