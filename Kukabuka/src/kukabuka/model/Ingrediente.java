package kukabuka.model;


public class Ingrediente {

	private int idIngrediente;
	private String nombre;
	private String tipo;
	
	public Ingrediente(int idIngrediente, String nombre, String tipo) {
		this.idIngrediente = idIngrediente;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public Ingrediente(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public int getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return  nombre;
	}
	
	
}
