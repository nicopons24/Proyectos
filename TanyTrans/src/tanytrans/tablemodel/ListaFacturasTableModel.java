package tanytrans.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import tanytrans.model.Factura;

public class ListaFacturasTableModel extends AbstractTableModel {

	private final String[] TITULOS = {"Num factura", "Fecha", "Cliente", "Importe"};
	private ArrayList<Factura> facturas = new ArrayList<Factura>();
	
	public ListaFacturasTableModel() {
		
	}
	
	public ListaFacturasTableModel(ArrayList<Factura> f) {
		facturas = f;
	}
	
	@Override
	public int getColumnCount() {
		return TITULOS.length;
	}

	@Override
	public int getRowCount() {
		return facturas.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object o = null;
		Factura f = facturas.get(row);
		switch (col) {
		case 0:
			o = f.getNumFactura();
			break;
		case 1:
			o = f.getFecha();
			break;
		case 2:
			o = f.getIdCliente();
			break;
		case 3:
			o = f.getImporte();
			break;
		}
		return o;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return TITULOS[columnIndex];
	}
	
	public void deleteRow (int idex) {
		facturas.remove(idex);
		fireTableRowsDeleted(idex, idex);
	}

	public Factura getFacturaAt(int pos) {
		return facturas.get(pos);
	}

	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}

}
