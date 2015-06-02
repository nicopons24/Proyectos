
import kukabuka.controller.ControladorPrincipal;
import kukabuka.gui.VentanaCarga;

public class Main {

	public static void main(String[] args) {
		ControladorPrincipal controlador = ControladorPrincipal.getInstance();
		VentanaCarga vc = new VentanaCarga(6000);
		vc.showSplashAndExit();
	}
}
