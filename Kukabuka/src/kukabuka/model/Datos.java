package kukabuka.model;

import kukabuka.config.Idioma;

public class Datos {
	
	private static Datos instance = new Datos();

	public static final String[] TIPO = {"","Carne","Pescado","Pasta","Verduras","Fruta","Otros"};
	public static final String[] DIFICULTAD = {"","Facil","Medio","Dificil"};
	public static final String[] CATEGORIA = {"","Primer plato","Segundo plato", "Entrantes", "Postre"};
	
	private static final String[] TIPO_EN = {"","Meat","Fish","Pasta","Vegeables","Fruit","Other"};
	private static final String[] DIFICULTAD_EN = {"","Easy","Medium","Hard"};
	private static final String[] CATEGORIA_EN = {"","Starter","Main course", "Appetizer", "Dessert"};
	
	private int idioma;
	
	private Datos() {
		this.idioma = Idioma.getInstance().getIdioma();
	}
	
	public String[] getTipo() {
		switch (idioma) {
			default:
			case 0:
				return TIPO;
			case 1:
				return TIPO_EN;
		}
	}
	
	public String[] getDificultad() {
		switch (idioma) {
			default:
			case 0:
				return DIFICULTAD;
			case 1:
				return DIFICULTAD_EN;
		}
	}
	
	public String[] getCategoria() {
		switch (idioma) {
			default:
			case 0:
				return CATEGORIA;
			case 1:
				return CATEGORIA_EN;
		}
	}

	public int getIdioma() {
		return idioma;
	}

	public void setIdioma(int idioma) {
		this.idioma = idioma;
	}

	public static Datos getInstance() {
		return instance;
	}
}
