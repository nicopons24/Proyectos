import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

import tanytrans.controller.ControladorPrincipal;
import tanytrans.gui.VentanaPrincipal;
import tanytrans.model.ConexionDB;


public class Main {

	public static void main(String[] args) {
		boolean isConected = ConexionDB.getInstance().connectDB();
		if (isConected) ControladorPrincipal.getInstance();
		else JOptionPane.showMessageDialog(null, "Error de conexión", "Tanytrans", JOptionPane.ERROR_MESSAGE);
	}

}
