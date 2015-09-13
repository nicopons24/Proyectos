package tanytrans.model;

import java.util.ArrayList;

import tanytrans.config.Calculos;


public class Factura {
	
	private int id, numFactura, idCliente;
	private double importe;
	private String pago, fecha, localidad;
	private ArrayList<Viaje> viajes, delViajes, editViajes, newViajes;

	public Factura() {
		
	}

	public Factura(int id, int numFactura, int idCliente, double importe,
			String pago, String fecha, String localidad) {
		this.id = id;
		this.numFactura = numFactura;
		this.idCliente = idCliente;
		this.importe = importe;
		this.pago = pago;
		this.fecha = fecha;
		this.localidad = localidad;
	}

	public int getId() {
		return id;
	}
	
	public String getNumFacturaString() {
		return Calculos.numFacturaFormat(numFactura);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(int numFactura) {
		this.numFactura = numFactura;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	public String getImporteString() {
		return Calculos.priceFormat(importe);
	}

	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(ArrayList<Viaje> viajes) {
		this.viajes = viajes;
	}

	public ArrayList<Viaje> getDelViajes() {
		return delViajes;
	}

	public void setDelViajes(ArrayList<Viaje> delViajes) {
		this.delViajes = delViajes;
	}

	public ArrayList<Viaje> getEditViajes() {
		return editViajes;
	}

	public void setEditViajes(ArrayList<Viaje> editViajes) {
		this.editViajes = editViajes;
	}

	public ArrayList<Viaje> getNewViajes() {
		return newViajes;
	}

	public void setNewViajes(ArrayList<Viaje> newViajes) {
		this.newViajes = newViajes;
	}
}