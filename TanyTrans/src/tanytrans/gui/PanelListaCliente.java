package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tanytrans.model.Cliente;
import tanytrans.tablemodel.ModeloTablaClientes;

public class PanelListaCliente extends JPanel {

	private Insets insets;
	private JButton editar, eliminar, guardar, cancelar;
	private JTable tabla;
	private JPanel panelEditar;
	private JTextField nombre, direccion, telefono1, telefono2;

	public PanelListaCliente(){
		
		insets = new Insets(10, 10, 10, 10);
		setName("ListaClientes");
		setLayout(new GridBagLayout());
		
		colocaComponentes();
		
	}

	private void colocaComponentes() {
		
		JLabel l = new JLabel("Lista clientes:");
		GridBagConstraints gbc_l = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(l, gbc_l);
		
		editar = new JButton("Editar");
		editar.setEnabled(false);
		GridBagConstraints gbc_editar = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		add(editar, gbc_editar);
		editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelEditar.setVisible(true);
				guardar.setVisible(true);
				cancelar.setVisible(true);
			}
		});
		
		eliminar = new JButton("Eliminar");
		eliminar.setEnabled(false);
		GridBagConstraints gbc_eliminar = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(eliminar, gbc_eliminar);
		
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
				panelEditar.setVisible(false);
				guardar.setVisible(false);
				cancelar.setVisible(false);
				editar.setEnabled(true);
				int rowSelected = tabla.getSelectedRow();
			}
		});
		
		panelEditar = new JPanel();
		panelEditar.setLayout(new GridBagLayout());
		panelEditar.setVisible(false);
		GridBagConstraints gbc_panelEditar = new GridBagConstraints(0, 2, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		add(panelEditar, gbc_panelEditar);
		
		JLabel nom = new JLabel("Nombre:");
		GridBagConstraints gbc_nom = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		panelEditar.add(nom, gbc_nom);
		
		JLabel dir = new JLabel("Dirección:");
		GridBagConstraints gbc_dir = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		panelEditar.add(dir, gbc_dir);
		
		JLabel tel1 = new JLabel("Teléfono 1:");
		GridBagConstraints gbc_tel1 = new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		panelEditar.add(tel1, gbc_tel1);
		
		JLabel tel2 = new JLabel("Teléfono 2:");
		GridBagConstraints gbc_tel2 = new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		panelEditar.add(tel2, gbc_tel2);
		
		nombre = new JTextField();
		GridBagConstraints gbc_nombre = new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(nombre, gbc_nombre);
		
		direccion = new JTextField();
		GridBagConstraints gbc_direccion = new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(direccion, gbc_direccion);
		
		telefono1 = new JTextField();
		GridBagConstraints gbc_telefono1 = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(telefono1, gbc_telefono1);
		
		telefono2 = new JTextField();
		GridBagConstraints gbc_telefono2 = new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(telefono2, gbc_telefono2);
		
		cancelar = new JButton("Cancelar");
		cancelar.setVisible(false);
		GridBagConstraints gbc_cancelar = new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		add(cancelar, gbc_cancelar);
		
		guardar = new JButton("Guardar");
		guardar.setVisible(false);
		GridBagConstraints gbc_guardar = new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		add(guardar, gbc_guardar);
		
	}
	
	public void setTableModel(ArrayList<Cliente> clientes) {
		tabla.setModel(new ModeloTablaClientes(clientes));
	}
	
	public ModeloTablaClientes getTableModel() {
		return (ModeloTablaClientes) tabla.getModel();
	}
	
}
