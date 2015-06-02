package kukabuka.model;

public class Puntuacion {

	private int idUsuario;
	private int idReceta;
	private double puntuacion;
	
	public Puntuacion(int idUsuario, int idReceta, double puntuacion) {
		this.idUsuario = idUsuario;
		this.idReceta = idReceta;
		this.puntuacion = puntuacion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
	
	
}
