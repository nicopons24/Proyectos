import tanytrans.controller.MainController;
import tanytrans.model.ConexionBD;


public class Main {

	public static void main(String[] args) {
		ConexionBD conection = ConexionBD.getInstance();
		if(conection.getConnection() != null) {
			MainController.getInstance();
		}
	}

}
