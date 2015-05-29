package tanytrans.model;

public class Cliente {
	
	private int idCliente, telefono1, telefono2;
	private String nombre, direccion;
	
	public Cliente() {
		idCliente = -1;
		telefono1 = -1;
		telefono2 = -1;
		nombre = "";
		direccion = "";
	}
	
	public Cliente(int idCliente, String nombre, 
			int telefono1, int telefono2, String direccion) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		return nombre+" - "+direccion;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(int telefono1) {
		this.telefono1 = telefono1;
	}

	public int getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(int telefono2) {
		this.telefono2 = telefono2;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
