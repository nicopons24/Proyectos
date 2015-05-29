package tanytrans.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import tanytrans.model.Camion;

public class ModeloTablaCamion extends AbstractTableModel {

private final String[] CAMIONES = {"Marca","Modelo","Matrícula","Nº Chasis"};
	
	private ArrayList<Camion> camiones;
	
	public ModeloTablaCamion(ArrayList<Camion> c) {
		camiones = c;
	}
	
	public void addRow(Camion c) {
		camiones.add(c);
		fireTableRowsInserted(getRowCount(), getRowCount());
	}
	
	public void deleteRow(int rowIndex) {
		camiones.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public void updateRow(Camion c, int rowIndex) {
		camiones.set(rowIndex, c);
		fireTableRowsUpdated(rowIndex, rowIndex);
	}
	
	@Override
	public int getRowCount() {
		return camiones.size();
	}

	@Override
	public int getColumnCount() {
		return CAMIONES.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return CAMIONES[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object o = null;
		Camion c = camiones.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			o = c.getMarca();
			break;
		case 1:
			o = c.getModelo();
			break;
		case 2:
			o = c.getMatricula();
			break;
		case 3:
			o = c.getChasis();
			break;
		}
		return o;
	}

	public ArrayList<Camion> getCamiones() {
		return camiones;
	}

}
