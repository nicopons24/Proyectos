package tanytrans.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tanytrans.controller.MainController;
import tanytrans.model.MiEmpresa;
import tanytrans.tablemodel.ListaFacturasTableModel;

public class MainPanel extends JPanel {
	
	public static final String NAME = "PanelPrincipal";
	private static final Insets insets = new Insets(10, 10, 10, 10);
	
	private JPanel left, right, center;
	private JLabel lblArchivo, lblBuscar, lblHerr, lblDatosEmp, nombre, dir, lblCont, lblTel, tel, lblFax, fax, lblEmail, email, lblReciente;
	private JButton newFactura, newCliente, buscarFactura, buscarCliente, calc, edit, es, bg;
	private JTable table;
	
	public MainPanel() {
		setLayout(new BorderLayout());
		setLogo();
		setLeftPanel();
		setCenterPanel();
		setRightPanel();
	}
	
	public void refreshDatosEmpresa() {
		MiEmpresa e = MiEmpresa.getInstance();
		
		nombre.setText(e.getNombre());
		dir.setText(e.getDireccion()+" - "+e.getCp()+" - "+e.getLocalidad()+" - "+e.getPais());
		tel.setText("+"+e.getTel1()+" / +"+e.getTel2());
		fax.setText("+"+e.getFax());
		email.setText(e.getEmail());
	}
	
	private void setCenterPanel() {
		right = new JPanel(new GridBagLayout());
		add(right, BorderLayout.CENTER);
		
		lblReciente = new JLabel("Facturas Recientes");
		right.add(lblReciente, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		table = new JTable(new ListaFacturasTableModel());
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		right.add(scroll, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
	}
	
	private void setRightPanel() {
		MiEmpresa e = MiEmpresa.getInstance();
		
		center = new JPanel(new GridBagLayout());
		add(center, BorderLayout.EAST);
		
		lblDatosEmp = new JLabel("Datos Empresa");
		center.add(lblDatosEmp, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		nombre = new JLabel(e.getNombre());
		center.add(nombre, new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		
		dir = new JLabel(e.getDireccion()+" - "+e.getCp()+" - "+e.getLocalidad()+" - "+e.getPais());
		center.add(dir, new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, insets, 0, 0));
		
		lblCont = new JLabel("Contacto");
		center.add(lblCont, new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblTel = new JLabel("Telefono:");
		center.add(lblTel, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		tel = new JLabel("+"+e.getTel1()+" / +"+e.getTel2());
		center.add(tel, new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblFax = new JLabel("Fax:");
		center.add(lblFax, new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		fax = new JLabel("+"+e.getFax());
		center.add(fax, new GridBagConstraints(1, 5, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		lblEmail = new JLabel("Email:");
		center.add(lblEmail, new GridBagConstraints(0, 6, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		email = new JLabel(e.getEmail());
		center.add(email, new GridBagConstraints(1, 6, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		edit = new JButton("Editar");
		center.add(edit, new GridBagConstraints(0, 7, 2, 1, 1.0, 1.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, insets, 0, 0));
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().showDatosEmpresa();
			}
		});
	}
	
	private void setLeftPanel() {
		left = new JPanel(new GridBagLayout());
		add(left, BorderLayout.WEST);
		
		lblArchivo = new JLabel("Arhivo");
		left.add(lblArchivo, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		newFactura = new JButton("Nueva Factura");
		left.add(newFactura, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		newFactura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainController.getInstance().showFactura();
			}
		});
		
		newCliente = new JButton("Nuevo Cliente");
		left.add(newCliente, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		lblBuscar = new JLabel("Buscar");
		left.add(lblBuscar, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		buscarFactura = new JButton("Buscar Factura");
		left.add(buscarFactura, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		buscarFactura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().showList();
			}
		});
		
		buscarCliente = new JButton("Buscar Cliente");
		left.add(buscarCliente, new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		lblHerr = new JLabel("Herramientas");
		left.add(lblHerr, new GridBagConstraints(0, 6, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		calc = new JButton("Calculadora");
		left.add(calc, new GridBagConstraints(0, 7, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		es = new JButton("ES");
		left.add(es, new GridBagConstraints(0, 8, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, insets, 0, 0));
		
		bg = new JButton("BG");
		left.add(bg, new GridBagConstraints(1, 8, 1, 1, 0.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, insets, 0, 0));
	}
	
	private void setLogo() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/tanytrans/images/Tanytrans.png"));
		JLabel logo = new JLabel(icon);
		add(logo, BorderLayout.NORTH);
	}

}
