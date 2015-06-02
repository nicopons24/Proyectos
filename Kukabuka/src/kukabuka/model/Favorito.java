package kukabuka.model;

public class Favorito {
	
	private int idFavorito;
	private int idReceta;
	private String nomReceta;
	
	
	public Favorito(int idReceta, String nomReceta) {
		super();
		this.idReceta = idReceta;
		this.nomReceta = nomReceta;
	}

	public Favorito(int idFavorito, int idReceta, String nomReceta) {
		super();
		this.idFavorito = idFavorito;
		this.idReceta = idReceta;
		this.nomReceta = nomReceta;
	}

	public int getIdFavorito() {
		return idFavorito;
	}

	public void setIdFavorito(int idFavorito) {
		this.idFavorito = idFavorito;
	}

	public String getNomReceta() {
		return nomReceta;
	}

	public void setNomReceta(String nomReceta) {
		this.nomReceta = nomReceta;
	}
	
	public int getIdReceta() {
		return idReceta;
	}
	
	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}
	
	

}
