package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tanytrans.model.Camion;
import tanytrans.tablemodel.ModeloTablaCamion;

public class PanelListaCamion extends JPanel {
	
	private Insets insets;
	private JButton editar, eliminar;
	private JTable tabla;

	public PanelListaCamion(){
		
		insets = new Insets(10, 10, 10, 10);
		setName("ListaCamion");
		setLayout(new GridBagLayout());
		
		colocaComponentes();
		
	}

	private void colocaComponentes() {
		
		JLabel l = new JLabel("Lista camiones:");
		GridBagConstraints gbc_l = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(l, gbc_l);
		
		editar = new JButton("Editar");
		GridBagConstraints gbc_editar = new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(editar, gbc_editar);
		
		eliminar = new JButton("Eliminar");
		GridBagConstraints gbc_eliminar = new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(eliminar, gbc_eliminar);
		
		tabla = new JTable();
		tabla.setName("Camion");
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		GridBagConstraints gbc_tabla = new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll, gbc_tabla);
		
	}
	
	public void setTableModel(ArrayList<Camion> camiones) {
		tabla.setModel(new ModeloTablaCamion(camiones));
	}
}
