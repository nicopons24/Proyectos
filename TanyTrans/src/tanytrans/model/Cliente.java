package tanytrans.model;

public class Cliente {

	private int idCliente, cp, iban;
	private String nombre, direccion, localidad, pais, num, banco;
	
	public Cliente() {
		
	}

	public Cliente(int idCliente, int cp, int iban, String nombre,
			String direccion, String localidad, String pais, String num,
			String banco) {
		this.idCliente = idCliente;
		this.cp = cp;
		this.iban = iban;
		this.nombre = nombre;
		this.direccion = direccion;
		this.localidad = localidad;
		this.pais = pais;
		this.num = num;
		this.banco = banco;
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

	public int getIban() {
		return iban;
	}

	public void setIban(int iban) {
		this.iban = iban;
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

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}
	
}
