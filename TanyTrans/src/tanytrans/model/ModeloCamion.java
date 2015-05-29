package tanytrans.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeloCamion {

	private static final String ID = "idCamion";
	private static final String MARCA = "Marca";
	private static final String MODELO = "Modelo";
	private static final String MATRICULA = "Matricula";
	private static final String CHASIS = "NChasis";
	
	private static ModeloCamion instance = null;
	private static Connection conexion;
	
	private ModeloCamion() {
		conexion = ConexionDB.getConexion();
	}
	
	public ArrayList<Camion> consultaCamiones() throws SQLException {
		ArrayList<Camion> camiones = new ArrayList<Camion>();
		String consulta = "select * from Camiones;";
		Statement s = conexion.createStatement();
		ResultSet r = s.executeQuery(consulta);
		while (r.next()) {
			int id = r.getInt(ID);
			String marca = r.getString(MARCA);
			String modelo = r.getString(MODELO);
			String matricula = r.getString(MATRICULA);
			String chasis = r.getString(CHASIS);
			Camion c = new Camion(id, marca, modelo, matricula, chasis);
			camiones.add(c);
		}
		return camiones;
	}

	public static ModeloCamion getInstance() {
		if (instance == null) instance = new ModeloCamion();
		return instance;
	}
}
