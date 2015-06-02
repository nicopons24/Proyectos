package kukabuka.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {

	private static ConexionDB instance = new ConexionDB();
	private String bd = "kukabuka";
	private String usuario = "kukabuka";
	private String pass = "kukabuka";
	private String url = "jdbc:mysql://mycoach.cua8xanrei4u.us-west-2.rds.amazonaws.com:3306/"+bd;
	private Connection conexion = null;
	private boolean conected = false;
	
	private ConexionDB() {
		try {
			Class.forName("com.mysql.jdbc.Connection");
			conexion = (Connection) DriverManager.getConnection(url, usuario, pass);
			conexion.setAutoCommit(false);
			if (conexion != null) conected = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet realizarConsulta(String consulta) throws SQLException {
		
		Statement s = null;//envia consulta
		ResultSet resultado = null;//resultado de la consulta
		try {
			s = (Statement) conexion.createStatement();
			resultado = s.executeQuery(consulta);
			conexion.commit();
		}
		finally {
			
		}
		return resultado;
	}
	
	public void insertarDatos(String insert) throws SQLException {

		try {
			Statement s = (Statement) conexion.createStatement();
			s.executeUpdate(insert);
			s.close();
			conexion.commit();
		}
		finally {
			
		}
	}
	
	public static ConexionDB getInstance() {
		return instance;
	}
	
	public boolean isConected() {
		return this.conected;
	}

	public Connection getConexion() {
		return conexion;
	}
	
}
