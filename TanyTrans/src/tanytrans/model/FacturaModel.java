package tanytrans.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import tanytrans.config.Calculos;

public class FacturaModel {
	
	private static final String ID = "id";
	private static final String NUM = "numFactura";
	private static final String FECHA = "fecha";
	private static final String IMPORTE = "importe";
	private static final String PAGO = "pago";
	private static final String CLIENTE = "idCliente";
	private static final String LOC = "localidad";

	private static FacturaModel instance = null;
	private Connection conexion;
	
	private FacturaModel() {
		conexion = ConexionBD.getInstance().getConnection();
	}
	
	public ArrayList<Factura> consultaFacturasMes(int mes, int year) throws SQLException {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		String consulta = "select * from facturas where fecha between '"+year+"-"+mes+"-1' and '"+year+"-"+mes+"-31';";
		ResultSet r = conexion.createStatement().executeQuery(consulta);
		while (r.next()){
			int id = r.getInt(ID);
			int numFactura = r.getInt(NUM);
			String fecha = r.getDate(FECHA).toString();
			double importe = r.getDouble(IMPORTE);
			String pago = r.getString(PAGO);
			int idCliente = r.getInt(CLIENTE);
			String loc = r.getString(LOC);
			Factura f = new Factura(id, numFactura, idCliente, importe, pago, fecha, loc);
			ArrayList<Viaje> v = ViajeModel.getInstance().consultaViajesFactura(numFactura);
			f.setViajes(v);
			facturas.add(f);
		}
		return facturas;
	}
	
	public ArrayList<Factura> consultaFacturasDia(Date inicio, Date fin) throws SQLException {
		String fechaInicio = Calculos.getInstance().toSqlDate(inicio);
		String fechaFin = Calculos.getInstance().toSqlDate(fin);
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		String consulta = "select * from facturas where fecha between '"+fechaInicio+"' and '"+fechaFin+"';";
		ResultSet r = conexion.createStatement().executeQuery(consulta);
		while (r.next()){
			int id = r.getInt(ID);
			int numFactura = r.getInt(NUM);
			String fecha = r.getDate(FECHA).toString();
			double importe = r.getDouble(IMPORTE);
			String pago = r.getString(PAGO);
			int idCliente = r.getInt(CLIENTE);
			String loc = r.getString(LOC);
			Factura f = new Factura(id, numFactura, idCliente, importe, pago, fecha, loc);
			ArrayList<Viaje> v = ViajeModel.getInstance().consultaViajesFactura(numFactura);
			f.setViajes(v);
			facturas.add(f);
		}
		return facturas;
	}
	
	public int lastNumFactura() {
		String consulta = "select "+NUM+" from facturas order by "+NUM+" desc;";
		int last = 1;
		try {
		ResultSet r = conexion.createStatement().executeQuery(consulta);
		if (r.next()) {
			last = r.getInt(NUM) +1;
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return last;
	}
	
	public void insertFactura(Factura f) throws SQLException {
		String insert = "insert into facturas("+NUM+","+FECHA+","+IMPORTE+","+PAGO+","+CLIENTE+","+LOC+") values (?, ?, ?, ?, ?, ?);";
		PreparedStatement p = conexion.prepareStatement(insert);
		p.setInt(1, f.getNumFactura());
		p.setString(2, f.getFecha());
		p.setDouble(3, f.getImporte());
		p.setString(4, f.getPago());
		p.setInt(5, f.getIdCliente());
		p.setString(6, f.getLocalidad());
		p.executeUpdate();
	}
	
	public void updateFactura(Factura f) throws SQLException {
		String update = "update facturas set "+NUM+"= ?, "+FECHA+"= ?, "+IMPORTE+"= ?, "+PAGO+"= ?, "+CLIENTE+"= ?, "+LOC+" = ? where "+ID+" = "+f.getId()+";";
		PreparedStatement p = conexion.prepareStatement(update);
		p.setInt(1, f.getNumFactura());
		p.setString(2, f.getFecha());
		p.setDouble(3, f.getImporte());
		p.setString(4, f.getPago());
		p.setInt(5, f.getIdCliente());
		p.setString(6, f.getLocalidad());
		p.executeUpdate();
	}
	
	public static FacturaModel getInstance() {
		if (instance == null) {instance = new FacturaModel();}
		return instance;
	}
}
