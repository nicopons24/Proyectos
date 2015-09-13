package tanytrans.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import tanytrans.config.Data;
import tanytrans.model.Cliente;

public class ClientesTableModel extends AbstractTableModel {

	private final String[] TITULOS = {"NIF/CIF", "Nombre", "Telefono"};
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public ClientesTableModel() {
		
	}
	
	public ClientesTableModel(ArrayList<Cliente> c) {
		clientes = c;
	}
	
	@Override
	public int getColumnCount() {
		return TITULOS.length;
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object o = null;
		Cliente c = clientes.get(row);
		switch (col) {
		case 0:
			o = c.getNum();
			break;
		case 1:
			o = c.getNombre();
			break;
		case 2:
			o = c.getTel1() + " / " +c.getTel2();
			break;
		}
		return o;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return TITULOS[columnIndex];
	}

	public Cliente getClienteAt(int pos) {
		return clientes.get(pos);
	}
	
	public void addRow (Cliente c) {
		clientes.add(c);
		fireTableRowsInserted(getRowCount(), getRowCount());
	}
	
	public void removeAllData() {
		clientes = new ArrayList<Cliente>();
		fireTableDataChanged();
	}
	
	public void setClienteaAt(int pos, Cliente c) {
		clientes.set(pos, c);
		Data.clientes = clientes;
		fireTableDataChanged();
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
		fireTableDataChanged();
	}

}
