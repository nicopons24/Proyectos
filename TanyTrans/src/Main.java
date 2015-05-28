import java.awt.Dimension;
import java.awt.Toolkit;

import tanytrans.gui.VentanaPrincipal;


public class Main {

	public static void main(String[] args) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		d.setSize(d.getWidth()/2, d.getHeight()/2);
		new VentanaPrincipal(d);
	}

}
