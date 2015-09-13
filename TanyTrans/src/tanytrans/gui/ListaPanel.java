package tanytrans.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import tanytrans.config.Data;
import tanytrans.controller.MainController;
import tanytrans.model.Cliente;
import tanytrans.model.Factura;
import tanytrans.tablemodel.ListaFacturasTableModel;

import com.toedter.calendar.JDateChooser;

public class ListaPanel extends JPanel {
	
	public static final String NAME = "ListaPanel";
	private static final Insets insets = new Insets(10,10,10,10);
	
	private JPanel mesPanel, diaPanel, listaPanel, resumePanel, panelFac;
	private JLabel lblMes, lblInicio, lblFin, lblYear, lblCant, lblTotal;
	private JTextField cantidad, total;
	private JComboBox<String> meses;
	private JSpinner year;
	private JButton buscarMes, buscarDia, edit, delete, print, back, printFac;
	private JDateChooser fechaInicio, fechaFin;
	private JTable table;

	public ListaPanel() {
		setLayout(new GridBagLayout());
		setMesPanel();
		setDiaPanel();
		setListaPanel();
		setResumePanel();
	}
	
	public void setData(ArrayList<Factura> facturas) {
		ListaFacturasTableModel model = ((ListaFacturasTableModel) table.getModel());
		model.getFacturas().clear();
		model.setFacturas(facturas);
	}
	
	private void resetData() {
		meses.setSelectedIndex(Data.MONTH-1);
		year.setValue(Data.YEAR);
		fechaInicio.setDate(new Date((long) (Calendar.getInstance().getTime().getTime() - 2629750000f)));
		fechaFin.setDate(Calendar.getInstance().getTime());
		((ListaFacturasTableModel) table.getModel()).removeAllData();
		
	}

	private void setMesPanel() {
		mesPanel = new JPanel(new GridBagLayout());
		add(mesPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		lblMes = new JLabel("Mes:");
		mesPanel.add(lblMes, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		
		meses = new JComboBox<String>();
		mesPanel.add(meses, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		for (String s: Data.MESES) {
			meses.addItem(s);
		}
		meses.setSelectedIndex(Data.MONTH-1);
		
		lblYear = new JLabel("Año:");
		mesPanel.add(lblYear, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		year = new JSpinner();
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(Data.YEAR, 2000, 3000, 1);
		year.setModel(spinnerModel);
		mesPanel.add(year, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		buscarMes = new JButton("Buscar");
		mesPanel.add(buscarMes, new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		buscarMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int m = meses.getSelectedIndex() + 1;
				int a = (int) year.getModel().getValue();
				MainController.getInstance().buscarFacturaMes(m, a);
			}
		});
	}
	
	private void setDiaPanel() {
		diaPanel = new JPanel(new GridBagLayout());
		add(diaPanel, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		lblInicio = new JLabel("Desde:");
		diaPanel.add(lblInicio, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		
		fechaInicio = new JDateChooser(new Date((long) (Calendar.getInstance().getTime().getTime() - 2629750000f)));
		diaPanel.add(fechaInicio, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblFin = new JLabel("hasta:");
		diaPanel.add(lblFin, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		
		fechaFin = new JDateChooser(Calendar.getInstance().getTime());
		diaPanel.add(fechaFin, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		buscarDia = new JButton("Buscar");
		diaPanel.add(buscarDia, new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		buscarDia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date inicio = fechaInicio.getDate();
				Date fin = fechaFin.getDate();
				MainController.getInstance().buscarFacturaDia(inicio, fin);
			}
		});
	}
	
	private void setListaPanel() {
		listaPanel = new JPanel(new BorderLayout(10, 10));
		add(listaPanel, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		panelFac = new JPanel(new GridBagLayout());
		panelFac.setVisible(false);
		listaPanel.add(panelFac, BorderLayout.EAST);
		
		printFac = new JButton("Factura PDF");
		panelFac.add(printFac, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		printFac.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Factura f = ((ListaFacturasTableModel)table.getModel()).getFacturaAt(table.getSelectedRow());
				Cliente c = null;
				for (Cliente cl:Data.clientes) {
					if (f.getIdCliente() == cl.getIdCliente()) {
						c = cl;
						break;
					}
				}
				MainController.getInstance().printFactura(f, c);
			}
		});
		edit = new JButton("Editar");
		panelFac.add(edit, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Factura f = ((ListaFacturasTableModel) table.getModel()).getFacturaAt(table.getSelectedRow());
				MainController.getInstance().showFactura(f, table.getSelectedRow());
			}
		});
		
		delete = new JButton("Eliminar");
		panelFac.add(delete, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Factura f = ((ListaFacturasTableModel) table.getModel()).getFacturaAt(table.getSelectedRow());
				MainController.getInstance().deleteFactura(f, table.getSelectedRow());
			}
		});
		
		table = new JTable(new ListaFacturasTableModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listaPanel.add(scrollPane, BorderLayout.CENTER);
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				double t = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					Factura f = ((ListaFacturasTableModel) table.getModel()).getFacturaAt(i);
					t += f.getImporte();
				}
				cantidad.setText(table.getRowCount()+"");
				total.setText(String.format("%.2f", t));
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int rowSelected = table.getSelectedRow();
				if (rowSelected > -1) {
					panelFac.setVisible(true);
				}
				else {
					panelFac.setVisible(false);
				}
			}
		});
	}

	private void setResumePanel() {
		resumePanel = new JPanel(new GridBagLayout());
		add(resumePanel, new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		lblCant = new JLabel("Cant. Facturas:");
		resumePanel.add(lblCant, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		
		cantidad = new JTextField();
		cantidad.setEditable(false);
		resumePanel.add(cantidad, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblTotal = new JLabel("Total:");
		resumePanel.add(lblTotal, new GridBagConstraints(2, 2, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		
		total = new JTextField();
		total.setEditable(false);
		resumePanel.add(total, new GridBagConstraints(3, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		print = new JButton("Recuento PDF");
		print.setEnabled(false);
		resumePanel.add(print, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		back = new JButton("Atras");
		resumePanel.add(back, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetData();
				MainController.getInstance().showHome();
			}
		});
		
		JPanel panel = new JPanel();
		resumePanel.add(panel, new GridBagConstraints(1, 0, 1, 3, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
	}
	
	public ListaFacturasTableModel getTableModel() {
		return (ListaFacturasTableModel) table.getModel();
	}
	
}
