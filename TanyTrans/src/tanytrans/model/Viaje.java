package tanytrans.model;

import tanytrans.config.Calculos;

public class Viaje {

	private int id = -1, numFactura;
	private double precio, iva;
	private String matricula, refViaje;
	
	public Viaje() {
		
	}

	public Viaje(int id, String refViaje, double iva, int numFactura, double precio, String matricula) {
		this.id = id;
		this.refViaje = refViaje;
		this.iva = iva;
		this.numFactura = numFactura;
		this.precio = precio;
		this.matricula = matricula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRefViaje() {
		return refViaje;
	}

	public void setRefViaje(String refViaje) {
		this.refViaje = refViaje;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double d) {
		this.iva = d;
	}
	
	public String getIvaString() {
		return Calculos.priceFormat(iva);
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getPrecioString() {
		return Calculos.priceFormat(precio);
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(int numFactura) {
		this.numFactura = numFactura;
	}
	
}
