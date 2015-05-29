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

}
