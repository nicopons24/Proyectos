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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import tanytrans.config.Calculos;
import tanytrans.config.Data;
import tanytrans.controller.MainController;
import tanytrans.model.Cliente;
import tanytrans.tablemodel.ClientesTableModel;

public class ClientePanel extends JPanel {

	public static final String NAME = "panelClientes"; 
	private static final Insets insets = new Insets(10, 10, 10, 10);
	
	private JPanel panelLista, panelDatos;
	private JLabel lblClientes, lblNom, lblDir, lblCp, lblLoc, lblPais, lblNum, lblTel1, lblTel2;
	private JButton add, edit, save, cancel, back;
	private JTable table;
	private JTextField nombre, direccion, cp, localidad, pais, nif, tel1, tel2;
	private boolean isNew, editable;
	private int id, row;
	
	public ClientePanel() {
		setLayout(new GridBagLayout());
		setListaPanel();
		setDatosPanel();
		isNew = false;
		editable = false;
	}
	
	public void setData(Cliente c, int row) {
		editable = true;
		id = c.getIdCliente();
		this.row = row;
		panelDatos.setVisible(true);
		add.setEnabled(false);
		nombre.setText(c.getNombre());
		direccion.setText(c.getDireccion());
		cp.setText(c.getCp()+"");
		localidad.setText(c.getLocalidad());
		pais.setText(c.getPais());
		nif.setText(c.getNum());
		tel1.setText(c.getTel1());
		tel2.setText(c.getTel2());
	}
	
	public void newData() {
		isNew = true;
		editable = false;
		panelDatos.setVisible(true);
		edit.setEnabled(false);
		table.clearSelection();
		nombre.setText("");
		direccion.setText("");
		cp.setText("");
		localidad.setText("");
		pais.setText("");
		nif.setText("");
		tel1.setText("");
		tel2.setText("");
	}
	
	public void loadData() {
		((ClientesTableModel) table.getModel()).removeAllData();
		((ClientesTableModel) table.getModel()).setClientes(Data.clientes);
	}
	
	private void setListaPanel() {
		panelLista = new JPanel(new GridBagLayout());
		add(panelLista, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		lblClientes = new JLabel("Clientes");
		panelLista.add(lblClientes, new GridBagConstraints(0, 0, 3, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		edit = new JButton("Editar");
		edit.setEnabled(false);
		panelLista.add(edit, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente c = ((ClientesTableModel) table.getModel()).getClienteAt(table.getSelectedRow());
				MainController.getInstance().editCliente(c, table.getSelectedRow());
			}
		});
		
		add = new JButton("Nuevo cliente");
		panelLista.add(add, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newData();
			}
		});
		
		table = new JTable(new ClientesTableModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelLista.add(scrollPane, new GridBagConstraints(0, 2, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (isNew) {
					edit.setEnabled(false);
				}
				else {
					int rowSelected = table.getSelectedRow();
					if (rowSelected > -1) {
						edit.setEnabled(true);
					}
					else {
						edit.setEnabled(false);
					}
				}
			}
		});
		
		back = new JButton("Atras");
		panelLista.add(back, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().showHome();
			}
		});
	}
	
	private void setDatosPanel() {
		panelDatos = new JPanel(new GridBagLayout());
		panelDatos.setVisible(false);
		add(panelDatos, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		JPanel panel = new JPanel(new GridBagLayout());
		panelDatos.add(panel, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblNom = new JLabel("Nombre:");
		panel.add(lblNom, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		nombre = new JTextField();
		panel.add(nombre, new GridBagConstraints(1, 0, 6, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblDir = new JLabel("Dirección:");
		panel.add(lblDir, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		direccion = new JTextField();
		panel.add(direccion, new GridBagConstraints(1, 1, 6, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblCp = new JLabel("CP:");
		panel.add(lblCp, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		cp = new JTextField();
		panel.add(cp, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblLoc = new JLabel("Localidad:");
		panel.add(lblLoc, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		localidad = new JTextField();
		panel.add(localidad, new GridBagConstraints(3, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblPais = new JLabel("Pais:");
		panel.add(lblPais, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		pais = new JTextField();
		panel.add(pais, new GridBagConstraints(5, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblNum = new JLabel("NIF/CIF:");
		panel.add(lblNum, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		nif = new JTextField();
		panel.add(nif, new GridBagConstraints(1, 3, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblTel1 = new JLabel("Telefono 1:");
		panel.add(lblTel1, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
		
		tel1 = new JTextField();
		panel.add(tel1, new GridBagConstraints(1, 4, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblTel2 = new JLabel("Telefono 2");
		panel.add(lblTel2, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		
		tel2 = new JTextField();
		panel.add(tel2, new GridBagConstraints(4, 4, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		cancel = new JButton("Cancelar");
		panelDatos.add(cancel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ocultaPanelDatos();
			}
		});
		
		save = new JButton("Guardar");
		panelDatos.add(save, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente c = new Cliente();
				c.setNombre(nombre.getText());
				c.setDireccion(direccion.getText());
				c.setLocalidad(localidad.getText());
				c.setPais(pais.getText());
				c.setNum(nif.getText());
				c.setTel1(tel1.getText());
				c.setTel2(tel2.getText());
				if (Calculos.isNumeric(cp.getText())) {c.setCp(Integer.parseInt(cp.getText()));}
				if (editable) {
					c.setIdCliente(id);
					MainController.getInstance().updateCliente(c, row);
				}
				else {
					MainController.getInstance().saveCliente(c);
				}
				ocultaPanelDatos();
			}
		});
	}
	
	public void ocultaPanelDatos() {
		isNew = false;
		panelDatos.setVisible(false);
		add.setEnabled(true);
		table.clearSelection();
	}
	
	public TableModel getTableModel() {
		return table.getModel();
	}
}
