package tanytrans.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import tanytrans.config.Calculos;

public class FacturaModel {
	
	private static final String NUM = "numFctura";
	private static final String FECHA = "fecha";
	private static final String IMPORTE = "importe";
	private static final String PAGO = "pago";
	private static final String VIAJE = "refViaje";
	private static final String CLIENTE = "idCliente";

	private static FacturaModel instance = null;
	private Connection conexion;
	
	private FacturaModel() {
		conexion = ConexionBD.getInstance().getConnection();
	}
	
	public ArrayList<Factura> consultaFacturasMes(int mes, int year) {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		String consulta = "select * from facturas where fecha between "+year+"-"+mes+"-1 and "+year+"-"+mes+"-31;";
		try {
			ResultSet r = conexion.createStatement().executeQuery(consulta);
			while (r.next()){
				int numFactura = r.getInt(NUM);
				String fecha = r.getDate(FECHA).toString();
				double importe = r.getDouble(IMPORTE);
				String pago = r.getString(PAGO);
				int refViaje = r.getInt(VIAJE);
				int idCliente = r.getInt(CLIENTE);
				Factura f = new Factura(numFactura, refViaje, idCliente, importe, pago, fecha);
				facturas.add(f);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return facturas;
	}
	
	public ArrayList<Factura> consultaFacturasDia(Date inicio, Date fin) {
		String fechaInicio = Calculos.getInstance().toSqlDate(inicio);
		String fechaFin = Calculos.getInstance().toSqlDate(fin);
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		String consulta = "select * from facturas where fecha between "+fechaInicio+" and "+fechaFin+";";
		try {
			ResultSet r = conexion.createStatement().executeQuery(consulta);
			while (r.next()){
				int numFactura = r.getInt(NUM);
				String fecha = r.getDate(FECHA).toString();
				double importe = r.getDouble(IMPORTE);
				String pago = r.getString(PAGO);
				int refViaje = r.getInt(VIAJE);
				int idCliente = r.getInt(CLIENTE);
				Factura f = new Factura(numFactura, refViaje, idCliente, importe, pago, fecha);
				facturas.add(f);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return facturas;
	}
	
	public static FacturaModel getInstance() {
		if (instance == null) {instance = new FacturaModel();}
		return instance;
	}
}
