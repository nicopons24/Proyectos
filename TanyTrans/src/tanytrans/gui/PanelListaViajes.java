package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelListaViajes extends JPanel {
	
	private Insets insets;
	private JButton editar, eliminar, guardar, cancelar;
	private JTable tabla;

	public PanelListaViajes() {
		
		insets = new Insets(10, 10, 10, 10);
		setName("ListaViajes");
		setLayout(new GridBagLayout());
		
		colocaComponentes();
		
	}

	private void colocaComponentes() {
		
		JLabel l = new JLabel("Lista viajes:");
		GridBagConstraints gbc_l = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(l, gbc_l);
		
		editar = new JButton("Editar");
		editar.setEnabled(false);
		GridBagConstraints gbc_editar = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		add(editar, gbc_editar);
		editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					
				}
			}
		});
		
		eliminar = new JButton("Eliminar");
		eliminar.setEnabled(false);
		GridBagConstraints gbc_eliminar = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(eliminar, gbc_eliminar);
		eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					
				}
			}
		});
		
		tabla = new JTable();
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		GridBagConstraints gbc_tabla = new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll, gbc_tabla);
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					editar.setEnabled(true);
					eliminar.setEnabled(true);
				}
				else {
					editar.setEnabled(false);
					eliminar.setEnabled(false);
				}
				guardar.setVisible(false);
				cancelar.setVisible(false);
			}
		});
		
		
		
		
	
}
