package kukabuka.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Token {

	private static final String TOKENS_ROUTE = "/kukabuka/config/token.properties";
	private static Token instance = new Token();
	private Properties propiedades = new Properties();
	private InputStream entrada;
	
	private Token() {
		entrada = Token.class.getResourceAsStream(TOKENS_ROUTE);
		try {
			propiedades.load(entrada);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String id) {
		return propiedades.getProperty(id);
	}
	
	public static Token getInstance() {
		return instance;
	}
}
