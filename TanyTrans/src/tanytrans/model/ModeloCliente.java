package tanytrans.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeloCliente {

	private static final String ID = "idCliente";
	private static final String NOMBRE = "Nombre";
	private static final String DIRECCION = "Direccion";
	private static final String TEL1 = "Tel1";
	private static final String TEL2 = "Tel2";
	
	private static ModeloCliente instance = null;
	private static Connection conexion;
	
	private ModeloCliente() {
		conexion = ConexionDB.getConexion();
	}
	
	public ArrayList<Cliente> consultaClientes() throws SQLException {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String consulta = "select * from Clientes";
		Statement s = conexion.createStatement();
		ResultSet r = s.executeQuery(consulta);
		while (r.next()) {
			int id = r.getInt(ID);
			String nombre = r.getString(NOMBRE);
			String direccion = r.getString(DIRECCION);
			int tel1 = r.getInt(TEL1);
			int tel2 = r.getInt(TEL2);
			Cliente c = new Cliente(id, nombre, tel1, tel2, direccion);
			clientes.add(c);
		}
		return clientes;
	}

	public static ModeloCliente getInstance() {
		if (instance == null) instance = new ModeloCliente();
		return instance;
	}
}
