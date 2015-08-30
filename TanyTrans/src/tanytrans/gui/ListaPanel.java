package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;

public class ListaPanel extends JPanel {
	
	public static final String NAME = "ListaPanel";
	private static final Insets insets = new Insets(10,10,10,10);
	
	private JPanel mesPanel, diaPanel, listaPanel;
	private JLabel lblMes, lblInicio, lblFin;
	private JComboBox<String> meses;
	private JButton buscarMes, buscarDia, edit, delete;
	private JDateChooser fechaInicio, fechaFin;
	private JTable table;

	public ListaPanel() {
		setLayout(new GridBagLayout());
		setMesPanel();
		setDiaPanel();
		setListaPanel();
	}

	private void setMesPanel() {
		mesPanel = new JPanel(new GridBagLayout());
		add(mesPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		lblMes = new JLabel("Mes:");
		mesPanel.add(lblMes, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		
		meses = new JComboBox<String>();
		mesPanel.add(meses, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		buscarMes = new JButton("Buscar");
		mesPanel.add(buscarMes, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
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
	}
	
	private void setListaPanel() {
		listaPanel = new JPanel(new GridBagLayout());
		add(listaPanel, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		edit = new JButton("Editar");
		listaPanel.add(edit, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		
		delete = new JButton("Eliminar");
		listaPanel.add(delete, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listaPanel.add(scrollPane, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
	}
	
}
