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

import tanytrans.controller.ControladorPrincipal;
import tanytrans.model.Camion;
import tanytrans.model.Empleado;
import tanytrans.tablemodel.ModeloTablaEmpleados;

public class PanelListaEmpleado extends JPanel{
	
	private Insets insets;
	private JButton editar, eliminar, guardar, cancelar;
	private JTable tabla;
	private JPanel panelEditar;
	private JTextField nombre, dni, telefono, apellidos;

	public PanelListaEmpleado(){
		
		insets = new Insets(10, 10, 10, 10);
		setName("ListaEmpleados");
		setLayout(new GridBagLayout());
		
		colocaComponentes();
		
	}

	private void colocaComponentes() {
		
		JLabel l = new JLabel("Lista empleados:");
		GridBagConstraints gbc_l = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
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
					ControladorPrincipal.getInstance().datosEmpleado(rowSelected);
					panelEditar.setVisible(true);
					guardar.setVisible(true);
					cancelar.setVisible(true);
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
					ControladorPrincipal.getInstance().eliminaEmpleado(rowSelected);
					tabla.clearSelection();
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
				panelEditar.setVisible(false);
				guardar.setVisible(false);
				cancelar.setVisible(false);
			}
		});
		
		panelEditar = new JPanel();
		panelEditar.setLayout(new GridBagLayout());
		panelEditar.setVisible(false);
		GridBagConstraints gbc_panelEditar = new GridBagConstraints(0, 2, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		add(panelEditar, gbc_panelEditar);
		
		JLabel nom = new JLabel("Nombre:");
		GridBagConstraints gbc_nom = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(nom, gbc_nom);
		
		JLabel ape = new JLabel("Apellidos:");
		GridBagConstraints gbc_ape = new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(ape, gbc_ape);
		
		JLabel ide = new JLabel("Dni:");
		GridBagConstraints gbc_ide = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(ide, gbc_ide);
		
		JLabel tel = new JLabel("Teléfono:");
		GridBagConstraints gbc_tel = new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(tel, gbc_tel);
		
		nombre = new JTextField();
		GridBagConstraints gbc_nombre = new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(nombre, gbc_nombre);
		
		apellidos = new JTextField();
		GridBagConstraints gbc_apellidos = new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(apellidos, gbc_apellidos);
		
		dni = new JTextField();
		GridBagConstraints gbc_dni = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(dni, gbc_dni);
		
		telefono = new JTextField();
		GridBagConstraints gbc_telefono = new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(telefono, gbc_telefono);
		
		cancelar = new JButton("Cancelar");
		cancelar.setVisible(false);
		GridBagConstraints gbc_cancelar = new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		add(cancelar, gbc_cancelar);
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					panelEditar.setVisible(false);
					cancelar.setVisible(false);
					guardar.setVisible(false);
					tabla.clearSelection();
				}
			}
		});
		
		guardar = new JButton("Guardar");
		guardar.setVisible(false);
		GridBagConstraints gbc_guardar = new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		add(guardar, gbc_guardar);
		guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					ControladorPrincipal.getInstance().actualizaEmpleado(rowSelected);
					panelEditar.setVisible(false);
					cancelar.setVisible(false);
					guardar.setVisible(false);
					tabla.clearSelection();
				}
			}
		});
	
	}
	
	public void setDatos(Empleado e) {
		nombre.setText(e.getNombre());
		apellidos.setText(e.getApellidos());
		dni.setText(e.getDni());
		telefono.setText(e.getTelefono()+"");
	}
	
	public Empleado getDatos() {
		String nom = nombre.getText();
		String ape = apellidos.getText();
		String ide = dni.getText();
		String tel = telefono.getText();
		return new Empleado(-1, nom, ape, ide, Integer.parseInt(tel));
	}
	
	public void setTableModel(ArrayList<Empleado> empleados) {
		tabla.setModel(new ModeloTablaEmpleados(empleados));
	}
	
	public ModeloTablaEmpleados getTableModel() {
		return (ModeloTablaEmpleados) tabla.getModel();
	}
}
