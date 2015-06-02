package kukabuka.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FuenteKukabuka {

	private String fuente;
	private Font f;
	
	public FuenteKukabuka() {
		fuente = "KeepCalm-Medium.ttf";
		try {
			InputStream input = getClass().getResource("/kukabuka/font/"+fuente).openStream();
            f = Font.createFont(Font.TRUETYPE_FONT, input);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Font setFontType (int style, float size) {
		Font font = f.deriveFont(style, size);
		return font;
	}
}
