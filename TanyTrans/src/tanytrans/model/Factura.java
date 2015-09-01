package tanytrans.model;


public class Factura {
	
	private int numFactura, refViaje, idCliente;
	private double importe;
	private String pago;
	private String fecha;

	public Factura() {
		
	}

	public Factura(int numFactura, int refViaje, int idCliente, double importe,
			String pago, String fecha) {
		this.numFactura = numFactura;
		this.refViaje = refViaje;
		this.idCliente = idCliente;
		this.importe = importe;
		this.pago = pago;
		this.fecha = fecha;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(int numFactura) {
		this.numFactura = numFactura;
	}

	public int getRefViaje() {
		return refViaje;
	}

	public void setRefViaje(int refViaje) {
		this.refViaje = refViaje;
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
}