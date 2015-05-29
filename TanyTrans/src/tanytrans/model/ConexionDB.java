package tanytrans.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	private static final String CONTROLADOR_MYSQL = "com.mysql.jdbc.Driver";
	private static final String HOST = "localhost";
	private static final String BBDD = "tanytrans";
	private static final String USER = "root";
	private static final String PASS = "";
	private static Connection conexion;
	private static ConexionDB instance = null;
	private String host, bbdd, user, pass, url;

	private ConexionDB(String host, String bbdd, String user, String pass) {
		this.host = host;
		this.bbdd = bbdd;
		this.user = user;
		this.pass = pass;
		this.url = "jdbc:mysql://" + this.host + "/" + this.bbdd;
	}

	public boolean connectDB() {	
		try {
			Class.forName(CONTROLADOR_MYSQL);
			conexion = DriverManager.getConnection(this.url, this.user,
					this.pass);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Connection getConexion() {
		return conexion;
	}
	
	public static ConexionDB getInstance(String host, String bbdd, String user,
			String pass) {
		if (instance == null) instance = new ConexionDB(host, bbdd, user, pass);
		return instance;
	}

	public static ConexionDB getInstance() {
		if (instance == null) instance = new ConexionDB(HOST, BBDD, USER, PASS);
		return instance;
	}

}
