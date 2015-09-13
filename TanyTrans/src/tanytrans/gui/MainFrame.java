package tanytrans.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import tanytrans.controller.MainController;
import tanytrans.custom.CustomPanel;

public class MainFrame extends JFrame {
	
	private Dimension size;
	private JMenuBar menuBar;
	private JMenu menu1, menu2, menu3, menu4;
	private JMenuItem item11, item12, item21, item22, item31, item41, item42;
	private CustomPanel contentPane;
	private MainPanel panelPrincipal;
	private FacturaPanel panelFactura;
	private ListaPanel panelLista;
	private ClientePanel panelCliente;
	
	public MainFrame(Dimension screensize) {
		size = new Dimension((int) screensize.getWidth()*2/3, (int) screensize.getHeight()*2/3);
		contentPane = new CustomPanel();
		
		setTitle("TanyTrans");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tanytrans/images/CamionLogo.png")));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(size);
		setMinimumSize(new Dimension(1280, 720));
		setMaximumSize(screensize);
		setResizable(true);
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		getContentPane().setLayout(new CardLayout(getWidth()/35, getHeight()/35));
		setMenu();
		setPanels();
		
		setVisible(true);
	}
	
	private void setPanels() {
		panelPrincipal = new MainPanel();
		getContentPane().add(MainPanel.NAME, panelPrincipal);
		
		panelFactura = new FacturaPanel();
		getContentPane().add(FacturaPanel.NAME, panelFactura);
		
		panelLista = new ListaPanel();
		getContentPane().add(ListaPanel.NAME, panelLista);
		
		panelCliente = new ClientePanel();
		getContentPane().add(ClientePanel.NAME, panelCliente);
	}
	
	private void setMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu1 = new JMenu("Archivo");
		menuBar.add(menu1);
		
		item11 = new JMenuItem("Nueva factura");
		menu1.add(item11);
		item11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().showFactura();
			}
		});
		
		item12 = new JMenuItem("Nuevo cliente");
		menu1.add(item12);
		item12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().newCliente();
			}
		});
		
		menu2 = new JMenu("Buscar");
		menuBar.add(menu2);
		
		item21 = new JMenuItem("Facturas");
		menu2.add(item21);
		item21.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().showList();
			}
		});
		
		item22 = new JMenuItem("Clientes");
		menu2.add(item22);
		item22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().showClientes();
			}
		});
		
		menu3 = new JMenu("Herramientas");
		menuBar.add(menu3);
		
		item31 = new JMenuItem("Calculadora");
		menu3.add(item31);
		item31.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().openCalc();
			}
		});
		
		menu4 = new JMenu("Ajustes");
		menuBar.add(menu4);
		
		item41 = new JMenuItem("Datos empresa");
		menu4.add(item41);
		item41.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().showDatosEmpresa();
			}
		});
		
		item42 = new JMenuItem("Idioma");
		menu4.add(item42);
	}

	public MainPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public FacturaPanel getPanelFactura() {
		return panelFactura;
	}

	public ListaPanel getPanelLista() {
		return panelLista;
	}

	public ClientePanel getPanelCliente() {
		return panelCliente;
	}

}
