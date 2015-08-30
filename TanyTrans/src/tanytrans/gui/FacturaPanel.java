package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import tanytrans.controller.MainController;

import com.toedter.calendar.JDateChooser;

public class FacturaPanel extends JPanel {
	
	public static final String NAME = "FacturaPanel";
	private static final Insets insets = new Insets(10, 10, 10, 10);
	
	private JPanel datosPanel, viajesPanel, resumenPanel, pagoPanel;
	private JLabel lblNumFactura, lblFecha, lblLocalidad, lblTotal, lblPago, lblIban, lblBanco;
	private JComboBox<String> pago;
	private JTextField numFactura, localidad, total, iban, banco;
	private JDateChooser fecha;
	private JButton addViaje, editViaje, delViaje, save, print, delete;
	private JTable table;

	public FacturaPanel() {
		setLayout(new GridBagLayout());
		setDatosPanel();
		setViajesPanel();
		setResumePanel();
	}
	
	public void setData(Object o) {
		
	}
	
	private void setDatosPanel() {
		datosPanel = new JPanel(new GridBagLayout());
		add(datosPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
				
		lblNumFactura = new JLabel("Numero de Factura:");
		datosPanel.add(lblNumFactura, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		numFactura = new JTextField();
		datosPanel.add(numFactura, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblFecha = new JLabel("Fecha:");
		datosPanel.add(lblFecha, new GridBagConstraints(2, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		fecha = new JDateChooser();
		datosPanel.add(fecha, new GridBagConstraints(3, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblLocalidad = new JLabel("Localidad");
		datosPanel.add(lblLocalidad, new GridBagConstraints(4, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		localidad = new JTextField();
		datosPanel.add(localidad, new GridBagConstraints(5, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	}
	
	private void setViajesPanel() {
		viajesPanel = new JPanel(new GridBagLayout());
		add(viajesPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		addViaje = new JButton("Añadir");
		viajesPanel.add(addViaje, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		addViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().newViaje();
			}
		});
		
		editViaje = new JButton("Editar");
		viajesPanel.add(editViaje, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		
		delViaje = new JButton("Eliminar");
		viajesPanel.add(delViaje, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		
		table = new JTable();
		JScrollPane scrollPane=  new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		viajesPanel.add(scrollPane, new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
	}
	
	private void setResumePanel() {
		resumenPanel = new JPanel(new GridBagLayout());
		add(resumenPanel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		save = new JButton("Guardar");
		resumenPanel.add(save, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		print = new JButton("Imprimir");
		resumenPanel.add(print, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		delete = new JButton("Cancelar");
		resumenPanel.add(delete, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		lblTotal = new JLabel("TOTAL:");
		resumenPanel.add(lblTotal, new GridBagConstraints(2, 2, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.CENTER, insets, 0, 0));
		
		total = new JTextField();
		resumenPanel.add(total, new GridBagConstraints(3, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		pagoPanel = new JPanel(new GridBagLayout());
		resumenPanel.add(pagoPanel, new GridBagConstraints(1, 0, 1, 3, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		lblPago = new JLabel("Metodo de pago");
		pagoPanel.add(lblPago, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		pago = new JComboBox<String>();
		pagoPanel.add(pago, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblIban = new JLabel("IBAN:");
		pagoPanel.add(lblIban, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		iban = new JTextField();
		pagoPanel.add(iban, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblBanco = new JLabel("Banco:");
		pagoPanel.add(lblBanco, new GridBagConstraints(0, 2, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		banco = new JTextField();
		pagoPanel.add(banco, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	}
	
}
