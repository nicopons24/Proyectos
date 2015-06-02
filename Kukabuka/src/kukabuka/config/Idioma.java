package kukabuka.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import kukabuka.model.Datos;

public class Idioma {
	
	private static final int ES = 0;
	private static final int EN = 1;
	private static final String ES_LANG = "/kukabuka/config/es_lang.properties";
	private static final String EN_LANG = "/kukabuka/config/en_lang.properties";
	private static Idioma instance = new Idioma(ES);
	
	private Properties propiedades = new Properties();
	private InputStream entrada;
	private int idioma;

	private Idioma (int idioma) {
		this.idioma = idioma;
		
		switch (this.idioma) {
		default:
		case ES:
			entrada = Idioma.class.getResourceAsStream(ES_LANG);
			break;
		case EN:
			entrada = Idioma.class.getResourceAsStream(EN_LANG);
			break;
		}
		
		try {
			propiedades.load(entrada);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
	public int getIdioma() {
		return idioma;
	}

	public void setIdioma(int idioma) throws IOException {
		this.idioma = idioma;
		Datos.getInstance().setIdioma(idioma);
		switch (this.idioma) {
		default:
		case ES:
			entrada = Idioma.class.getResourceAsStream(ES_LANG);
			break;
		case EN:
			entrada = Idioma.class.getResourceAsStream(EN_LANG);
			break;
		}
		
		propiedades.load(entrada);
	}



	public String getProperty(String id) {
		return propiedades.getProperty(id);
	}
	
	public static Idioma getInstance() {
		return instance;
	}
	
}
