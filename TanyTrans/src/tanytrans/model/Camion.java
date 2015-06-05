package tanytrans.model;

public class Camion {

	private int idCamion;
	private String marca, modelo, matricula, chasis;
	
	public Camion() {
		idCamion = -1;
		marca = "";
		modelo = "";
		matricula = "";
		chasis = "";
	}
	
	public Camion(int idCamion, String marca, String modelo, String matricula,
			String chasis) {
		super();
		this.idCamion = idCamion;
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.chasis = chasis;
	}
	
	@Override
	public String toString() {
		return marca+" - "+matricula;
	}

	public int getIdCamion() {
		return idCamion;
	}
	public void setIdCamion(int idCamion) {
		this.idCamion = idCamion;
	}

	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getChasis() {
		return chasis;
	}
	public void setChasis(String chasis) {
		this.chasis = chasis;
	}
	
	
}
