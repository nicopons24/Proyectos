package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import net.sf.jasperreports.engine.xml.JRExpressionFactory.StringExpressionFactory;
import tanytrans.config.Calculos;
import tanytrans.config.Data;
import tanytrans.controller.MainController;
import tanytrans.model.Cliente;
import tanytrans.model.Factura;
import tanytrans.model.MiEmpresa;
import tanytrans.model.Viaje;
import tanytrans.tablemodel.ViajesTableModel;

import com.toedter.calendar.JDateChooser;

public class FacturaPanel extends JPanel {
	
	public static final String NAME = "FacturaPanel";
	private static final Insets insets = new Insets(10, 10, 10, 10);
	
	private boolean editable;
	int id, pos;
	private JPanel datosPanel, viajesPanel, resumenPanel, pagoPanel;
	private JLabel lblNumFactura, lblFecha, lblLocalidad, lblTotal, lblPago, lblIban, lblBanco, lblCliente;
	private JComboBox<String> pago;
	private JComboBox<Cliente> cliente;
	private JTextField numFactura, localidad, total, iban, banco;
	private JDateChooser fecha;
	private JButton addViaje, editViaje, delViaje, save, delete;
	private JTable table;

	public FacturaPanel() {
		editable = false;
		setLayout(new GridBagLayout());
		setDatosPanel();
		setViajesPanel();
		setResumePanel();
	}
	
	public void loadData() {
		cliente.removeAllItems();
		for (Cliente c: Data.clientes) {
			cliente.addItem(c);
		}
		banco.setText(MiEmpresa.getInstance().getBanco());
		iban.setText(MiEmpresa.getInstance().getIban());
	}
	
	public void setData(Factura f, int row) {
		editable = true;
		id = f.getId();
		pos = row;
		String i[] = Calculos.separateDate(f.getFecha());
		Calendar c = new GregorianCalendar(Integer.parseInt(i[0]), Integer.parseInt(i[1]), Integer.parseInt(i[2]));
		
		numFactura.setText(f.getNumFacturaString());
		fecha.setDate(c.getTime());
		localidad.setText(f.getLocalidad());
		for (Cliente cl: Data.clientes){
			if (cl.getIdCliente() == f.getIdCliente()) {
				cliente.setSelectedItem(cl);
				break;
			}
		}
		((ViajesTableModel) table.getModel()).removeAllData();
		((ViajesTableModel) table.getModel()).setViajes(f.getViajes());
		for (int j = 0; j < Data.PAGOBD.length; j++){
			if (f.getPago().equals(Data.PAGOBD[j])){
				pago.setSelectedIndex(j);
				break;
			}
		}
		banco.setText(MiEmpresa.getInstance().getBanco());
		iban.setText(MiEmpresa.getInstance().getIban());
	}
	
	public void newData() {
		editable = false;
		numFactura.setText(Calculos.numFacturaFormat(Data.getLastnumFactura()));
		localidad.setText(Data.LOCALIDAD_DEFAULT);
		fecha.setDate(Calendar.getInstance().getTime());
		((ViajesTableModel) table.getModel()).removeAllData();
		banco.setText(MiEmpresa.getInstance().getBanco());
		iban.setText(MiEmpresa.getInstance().getIban());
	}
	
	private void setDatosPanel() {
		datosPanel = new JPanel(new GridBagLayout());
		add(datosPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
				
		lblNumFactura = new JLabel("Numero de Factura:");
		datosPanel.add(lblNumFactura, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		numFactura = new JTextField(Data.getLastnumFactura()+"");
		datosPanel.add(numFactura, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblFecha = new JLabel("Fecha:");
		datosPanel.add(lblFecha, new GridBagConstraints(2, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		fecha = new JDateChooser(Calendar.getInstance().getTime());
		datosPanel.add(fecha, new GridBagConstraints(3, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblLocalidad = new JLabel("Localidad");
		datosPanel.add(lblLocalidad, new GridBagConstraints(4, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		localidad = new JTextField(Data.LOCALIDAD_DEFAULT);
		datosPanel.add(localidad, new GridBagConstraints(5, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		lblCliente = new JLabel("Cliente");
		datosPanel.add(lblCliente, new GridBagConstraints(6, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		cliente = new JComboBox<Cliente>();
		datosPanel.add(cliente, new GridBagConstraints(7, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
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
		editViaje.setEnabled(false);
		viajesPanel.add(editViaje, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		editViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Viaje v = ((ViajesTableModel) table.getModel()).getViajeAt(table.getSelectedRow());
				MainController.getInstance().editViaje(table.getSelectedRow(), v);
			}
		});
		
		delViaje = new JButton("Eliminar");
		delViaje.setEnabled(false);
		viajesPanel.add(delViaje, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		delViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().deleteViajeFactura(table.getSelectedRow());
			}
		});
		
		table = new JTable(new ViajesTableModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane=  new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		viajesPanel.add(scrollPane, new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				double t = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					Viaje v = ((ViajesTableModel) table.getModel()).getViajeAt(i);
					t += Calculos.calculaImporte(v);
				}
				total.setText(String.format("%.2f", t));
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int rowSelected = table.getSelectedRow();
				if (rowSelected > -1) {
					editViaje.setEnabled(true);
					delViaje.setEnabled(true);
				}
				else {
					editViaje.setEnabled(false);
					delViaje.setEnabled(false);
				}
			}
		});
	}
	
	private void setResumePanel() {
		resumenPanel = new JPanel(new GridBagLayout());
		add(resumenPanel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		save = new JButton("Guardar");
		resumenPanel.add(save, new GridBagConstraints(0, 0, 1, 2, 0.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, insets, 0, 0));
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Factura f = new Factura();
				f.setId(id);
				if (Calculos.isNumeric(numFactura.getText())) {f.setNumFactura(Integer.parseInt(numFactura.getText()));}
				f.setFecha(Calculos.toSqlDate(fecha.getDate()));
				f.setIdCliente(((Cliente) cliente.getSelectedItem()).getIdCliente());
				f.setPago(Data.PAGOBD[pago.getSelectedIndex()]);
				f.setImporte(Double.parseDouble(total.getText().replace(",", ".")));
				f.setLocalidad(localidad.getText());
				if (editable) {
					f.setViajes(((ViajesTableModel) table.getModel()).getViajes());
					f.setDelViajes(((ViajesTableModel) table.getModel()).getDelViajes());
					f.setEditViajes(((ViajesTableModel) table.getModel()).getEditViajes());
					f.setNewViajes(((ViajesTableModel) table.getModel()).getNewViajes());
					MainController.getInstance().updateFactura(f, pos);
				}
				else {
					f.setViajes(((ViajesTableModel) table.getModel()).getViajes());
					MainController.getInstance().saveFactura(f);
				}
			}
		});
		
		delete = new JButton("Cancelar");
		resumenPanel.add(delete, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editable) {
					MainController.getInstance().showList();
				}
				else {
					MainController.getInstance().showHome();
				}
			}
		});
		
		lblTotal = new JLabel("TOTAL:");
		resumenPanel.add(lblTotal, new GridBagConstraints(2, 2, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.CENTER, insets, 0, 0));
		
		total = new JTextField(String.format("%.2f", 0.0));
		total.setEditable(false);
		resumenPanel.add(total, new GridBagConstraints(3, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		pagoPanel = new JPanel(new GridBagLayout());
		resumenPanel.add(pagoPanel, new GridBagConstraints(1, 0, 1, 3, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		lblPago = new JLabel("Metodo de pago");
		pagoPanel.add(lblPago, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		pago = new JComboBox<String>();
		pagoPanel.add(pago, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		for (int i = 0; i < Data.PAGO.length; i++) {
			pago.addItem(Data.PAGO[i]);
		}
		pago.setSelectedIndex(0);
		
		lblIban = new JLabel("IBAN:");
		pagoPanel.add(lblIban, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		iban = new JTextField(MiEmpresa.getInstance().getIban());
		iban.setEditable(false);
		pagoPanel.add(iban, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblBanco = new JLabel("Banco:");
		pagoPanel.add(lblBanco, new GridBagConstraints(0, 2, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		banco = new JTextField(MiEmpresa.getInstance().getBanco());
		banco.setEditable(false);
		pagoPanel.add(banco, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	}
	
	public ViajesTableModel getTableModel() {
		return ((ViajesTableModel) table.getModel());
	}
	
}
