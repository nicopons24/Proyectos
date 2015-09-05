package tanytrans.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import tanytrans.config.Calculos;
import tanytrans.model.Viaje;

public class ViajesTableModel extends AbstractTableModel {

	private final String[] TITULOS = {"Ref", "Matricula", "Precio", "Iva", "Importe"};
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	
	@Override
	public int getColumnCount() {
		return TITULOS.length;
	}

	@Override
	public int getRowCount() {
		return viajes.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object o = null;
		Viaje v = viajes.get(row);
		switch (col) {
		case 0:
			o = v.getRefViaje();
			break;
		case 1:
			o = v.getMatricula();
			break;
		case 2:
			o = v.getPrecio();
			break;
		case 3:
			o = v.getIva();
			break;
		case 4:
			o = Calculos.getInstance().calculaImporte(v);
			break;
		}
		return o;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return TITULOS[columnIndex];
	}
	
	public void deleteRow (int idex) {
		viajes.remove(idex);
		fireTableRowsDeleted(idex, idex);
	}
	
	public void addRow (Viaje v) {
		viajes.add(v);
		fireTableRowsInserted(getRowCount(), getRowCount());
	}

	public Viaje getViajeAt(int pos) {
		return viajes.get(pos);
	}
	
	public void setViajeAt(int pos, Viaje v) {
		viajes.set(pos, v);
		fireTableDataChanged();
	}
	
	public void removeAllData() {
		viajes = new ArrayList<Viaje>();
		fireTableDataChanged();
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(ArrayList<Viaje> viajes) {
		this.viajes = viajes;
		fireTableDataChanged();
	}

}
