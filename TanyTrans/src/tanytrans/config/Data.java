package tanytrans.config;

import java.util.ArrayList;
import java.util.Calendar;

import tanytrans.model.Cliente;
import tanytrans.model.ClienteModel;
import tanytrans.model.FacturaModel;

public class Data {

	public static final int DAY = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	public static final int MONTH = Calendar.getInstance().get(Calendar.MONTH)+1;
	public static final int YEAR = Calendar.getInstance().get(Calendar.YEAR);
	
	public static final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	public static final String[] PAGO = {"Transferencia bancaria", "Plazos"};
	public static final String[] PAGOBD = {"TF", "PL"};
	public static final String LOCALIDAD_DEFAULT = "ESPAÑA";
	
	public static ArrayList<Cliente> clientes = ClienteModel.getInstance().consultaClientes();
	private static int lastnumFactura = FacturaModel.getInstance().lastNumFactura();

	public static void refreshNumFactura() {
		lastnumFactura = FacturaModel.getInstance().lastNumFactura();
	}
	
	public static void refrechClientes() {
		clientes.clear();
		clientes = ClienteModel.getInstance().consultaClientes();
	}

	public static int getLastnumFactura() {
		return lastnumFactura;
	}
}
