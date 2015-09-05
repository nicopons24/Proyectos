package tanytrans.model;

public class Cliente {

	private int idCliente, cp;
	private String nombre, direccion, localidad, pais, num;
	
	public Cliente() {
		
	}

	public Cliente(int idCliente, int cp, String nombre,
			String direccion, String localidad, String pais, String num) {
		this.idCliente = idCliente;
		this.cp = cp;
		this.nombre = nombre;
		this.direccion = direccion;
		this.localidad = localidad;
		this.pais = pais;
		this.num = num;
	}
	
	@Override
	public String toString() {
		return nombre;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
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

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
}
