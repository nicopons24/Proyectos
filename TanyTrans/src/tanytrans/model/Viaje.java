package tanytrans.model;

public class Viaje {

	private int refViaje, iva;
	private double precio;
	private String matricula;
	
	public Viaje() {
		
	}

	public Viaje(int refViaje, int iva, double precio, String matricula) {
		this.refViaje = refViaje;
		this.iva = iva;
		this.precio = precio;
		this.matricula = matricula;
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
	
}
