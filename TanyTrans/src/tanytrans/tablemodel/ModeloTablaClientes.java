package tanytrans.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import tanytrans.model.Cliente;

public class ModeloTablaClientes extends AbstractTableModel {
	
	private final String[] CLIENTES = {"Nombre","Dirección","Teléfono 1","Teléfono 2"};
	
	private ArrayList<Cliente> clientes;
	
	public ModeloTablaClientes(ArrayList<Cliente> c) {
		clientes = c;
	}
	
	public void addRow(Cliente c) {
		clientes.add(c);
		fireTableRowsInserted(getRowCount(), getRowCount());
	}
	
	public void deleteRow(int rowIndex) {
		clientes.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		return CLIENTES.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return CLIENTES[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object o = null;
		Cliente c = clientes.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			o = c.getNombre();
			break;
		case 1:
			o = c.getDireccion();
			break;
		case 2:
			o = c.getTelefono1();
			break;
		case 3:
			o = c.getTelefono2();
			break;
		}
		return o;
	}

}
