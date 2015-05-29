package tanytrans.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import tanytrans.model.Empleado;

public class ModeloTablaEmpleados extends AbstractTableModel {
	
	private final String[] EMPLEADOS = {"Nombre","Apellidos","Dni","Teléfono"};
	
	private ArrayList<Empleado> empleados;

	public ModeloTablaEmpleados(ArrayList<Empleado> e) {
		empleados = e;
	}
	
	@Override
	public int getRowCount() {
		return empleados.size();
	}

	@Override
	public int getColumnCount() {
		return EMPLEADOS.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return EMPLEADOS[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object o = null;
		Empleado e = empleados.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			o = e.getNombre();
			break;
		case 1:
			o = e.getApellidos();
			break;
		case 2:
			o = e.getDni();
			break;
		case 3:
			o = e.getTelefono();
			break;
		}
		return o;
	}

}
