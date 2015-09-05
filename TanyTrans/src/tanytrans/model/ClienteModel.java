package tanytrans.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteModel {

	private static final String ID = "idCliente";
	private static final String NOM = "nombre";
	private static final String DIR = "direccion";
	private static final String CP = "cp";
	private static final String LOC = "localidad";
	private static final String PAIS = "pais";
	private static final String NUM = "num";
	
	private static ClienteModel instance = null;
	private Connection conexion;
	
	private ClienteModel() {
		conexion = ConexionBD.getInstance().getConnection();
	}
	
	public ArrayList<Cliente> consultaClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String consulta = "select * from clientes;";
		try {
			ResultSet r = conexion.createStatement().executeQuery(consulta);
			while (r.next()) {
				int id = r.getInt(ID);
				String nom = r.getString(NOM);
				String dir = r.getString(DIR);
				int cp = r.getInt(CP);
				String loc = r.getString(LOC);
				String pais = r.getString(PAIS);
				String num = r.getString(NUM);
				Cliente c = new Cliente(id, cp, nom, dir, loc, pais, num);
				clientes.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	public static ClienteModel getInstance() {
		if (instance == null) { instance = new ClienteModel();}
		return instance;
	}
}
