package tanytrans.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import tanytrans.config.Calculos;
import tanytrans.model.Viaje;

public class ViajesTableModel extends AbstractTableModel {

	private final String[] TITULOS = {"Ref", "Matricula", "Precio", "Iva", "Importe"};
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>(), delViajes, newViajes, editViajes;
	
	public ViajesTableModel() {
		delViajes = new ArrayList<Viaje>();
		editViajes = new ArrayList<Viaje>();
		newViajes = new ArrayList<Viaje>();
	}
	
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
			o = v.getPrecioString();
			break;
		case 3:
			o = v.getIvaString();
			break;
		case 4:
			double d = Calculos.calculaImporte(v);
			o = Calculos.priceFormat(d);
			break;
		}
		return o;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return TITULOS[columnIndex];
	}
	
	public void deleteRow (int idex) {
		delViajes.add(getViajeAt(idex));
		viajes.remove(idex);
		fireTableRowsDeleted(idex, idex);
	}
	
	public void addRow (Viaje v) {
		viajes.add(v);
		newViajes.add(v);
		fireTableRowsInserted(getRowCount(), getRowCount());
	}

	public Viaje getViajeAt(int pos) {
		return viajes.get(pos);
	}
	
	public void setViajeAt(int pos, Viaje v) {
		viajes.set(pos, v);
		if (v.getId() != -1) {
			editViajes.add(v);
		}
		else {
			newViajes.add(v);
		}
		fireTableDataChanged();
	}
	
	public void removeAllData() {
		viajes = new ArrayList<Viaje>();
		delViajes = new ArrayList<Viaje>();
		editViajes = new ArrayList<Viaje>();
		newViajes = new ArrayList<Viaje>();
		fireTableDataChanged();
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(ArrayList<Viaje> viajes) {
		this.viajes = viajes;
		fireTableDataChanged();
	}

	public ArrayList<Viaje> getDelViajes() {
		return delViajes;
	}

	public ArrayList<Viaje> getNewViajes() {
		return newViajes;
	}

	public ArrayList<Viaje> getEditViajes() {
		return editViajes;
	}

}
