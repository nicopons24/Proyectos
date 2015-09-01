package tanytrans.gui;

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
import javax.swing.SpinnerNumberModel;

import tanytrans.controller.MainController;
import tanytrans.model.Factura;
import tanytrans.tablemodel.ListaFacturasTableModel;

import com.toedter.calendar.JDateChooser;

public class ListaPanel extends JPanel {
	
	public static final String NAME = "ListaPanel";
	private static final Insets insets = new Insets(10,10,10,10);
	
	private int currYear;
	private JPanel mesPanel, diaPanel, listaPanel;
	private JLabel lblMes, lblInicio, lblFin, lblYear;
	private JComboBox<String> meses;
	private JSpinner year;
	private JButton buscarMes, buscarDia, edit, delete;
	private JDateChooser fechaInicio, fechaFin;
	private JTable table;

	public ListaPanel() {
		currYear = Calendar.getInstance().get(Calendar.YEAR);
		setLayout(new GridBagLayout());
		setMesPanel();
		setDiaPanel();
		setListaPanel();
	}
	
	public void setData(ArrayList<Factura> facturas) {
		ListaFacturasTableModel model = ((ListaFacturasTableModel) table.getModel());
		model.getFacturas().clear();
		model.setFacturas(facturas);
	}

	private void setMesPanel() {
		mesPanel = new JPanel(new GridBagLayout());
		add(mesPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		lblMes = new JLabel("Mes:");
		mesPanel.add(lblMes, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		
		meses = new JComboBox<String>();
		mesPanel.add(meses, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblYear = new JLabel("Año:");
		mesPanel.add(lblYear, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		year = new JSpinner();
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(currYear, 2000, 3000, 1);
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
		
		fechaInicio = new JDateChooser();
		diaPanel.add(fechaInicio, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblFin = new JLabel("hasta:");
		diaPanel.add(lblFin, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		
		fechaFin = new JDateChooser();
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
		listaPanel = new JPanel(new GridBagLayout());
		add(listaPanel, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		edit = new JButton("Editar");
		listaPanel.add(edit, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		
		delete = new JButton("Eliminar");
		listaPanel.add(delete, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		
		table = new JTable(new ListaFacturasTableModel());
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listaPanel.add(scrollPane, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
	}
	
}
