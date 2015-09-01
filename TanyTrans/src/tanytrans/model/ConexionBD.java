package tanytrans.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexionBD {

	private static ConexionBD instance = null;
	private static final String CLASS_NAME = "com.mysql.jdbc.Driver";
	private String bd = "tanytrans";
	private String usuario = "tanytrans";
	private String pass = "tanytrans";
	private String port = "8006";
	private String url = "jdbc:mysql://raspinico.ddns.net:"+port+"/"+bd;
	private Connection conexion = null;
	
	private ConexionBD() {
		try {
			Class.forName(CLASS_NAME);
			conexion = (Connection) DriverManager.getConnection(url, usuario, pass);
			conexion.setAutoCommit(false);
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e, "Tanytrans", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Connection getConnection() {
		return conexion;
	}
	
	public static ConexionBD getInstance() {
		if (instance == null) {instance = new ConexionBD();}
		return instance;
	}
}
