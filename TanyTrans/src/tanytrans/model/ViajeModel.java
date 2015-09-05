package tanytrans.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViajeModel {

	private static final String ID = "id";
	private static final String REF = "refViaje";
	private static final String FAC = "numFactura";
	private static final String MATRICULA = "viaje";
	private static final String PRECIO = "precio";
	private static final String IVA = "iva";
	
	private static ViajeModel instance = null;
	private Connection conexion;
	
	private ViajeModel() {
		conexion = ConexionBD.getInstance().getConnection();
	}
	
	public ArrayList<Viaje> consultaViajesFactura(int num) throws SQLException {
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();
		String consulta = "select * from viajes where "+FAC+" = "+num+";";
		ResultSet r = conexion.createStatement().executeQuery(consulta);
		while (r.next()) {
			int id = r.getInt(ID);
			int ref = r.getInt(REF);
			int fac = r.getInt(FAC);
			String matricula = r.getString(MATRICULA);
			double precio = r.getDouble(PRECIO);
			int iva = r.getInt(IVA);
			Viaje v = new Viaje(id, ref, iva, fac, precio, matricula);
			viajes.add(v);
		}
		return viajes;
	}
	
	public void insertaViajes(Viaje v) throws SQLException {
		String insert = "insert into viajes("+REF+","+FAC+","+MATRICULA+","+PRECIO+","+IVA+") values (?, ?, ?, ?, ?);";
		PreparedStatement p = conexion.prepareStatement(insert);
		p.setInt(1, v.getRefViaje());
		p.setInt(2, v.getNumFactura());
		p.setString(3, v.getMatricula());
		p.setDouble(4, v.getPrecio());
		p.setInt(5, v.getIva());
		p.executeUpdate();
	}
	
	public void updateViajes(Viaje v) throws SQLException {
		String update = "update viajes set "+REF+" = ?, "+FAC+" = ?, "+MATRICULA+" = ?, "+PRECIO+" = ?, "+IVA+" = ? where "+ID+" = "+v.getId()+";";
		PreparedStatement p = conexion.prepareStatement(update);
		p.setInt(1, v.getRefViaje());
		p.setInt(2, v.getNumFactura());
		p.setString(3, v.getMatricula());
		p.setDouble(4, v.getPrecio());
		p.setInt(5, v.getIva());
		p.executeUpdate();
	}
	
	public static ViajeModel getInstance() {
		if (instance == null) {instance = new ViajeModel();}
		return instance;
	}
}
