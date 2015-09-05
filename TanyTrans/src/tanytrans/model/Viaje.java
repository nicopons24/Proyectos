package tanytrans.model;

public class Viaje {

	private int id, refViaje, iva, numFactura;
	private double precio;
	private String matricula;
	
	public Viaje() {
		
	}

	public Viaje(int id, int refViaje, int iva, int numFactura, double precio, String matricula) {
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

	public int getRefViaje() {
		return refViaje;
	}

	public void setRefViaje(int refViaje) {
		this.refViaje = refViaje;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
