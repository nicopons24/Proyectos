package tanytrans.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeloEmpleado {

	private static final String ID = "idEmpleado";
	private static final String NOMBRE = "Nombre";
	private static final String APELLIDO = "Apellidos";
	private static final String DNI = "Dni";
	private static final String TEL = "Tel";
	
	private static ModeloEmpleado instance = null;
	private static Connection conexion;
	
	private ModeloEmpleado() {
		conexion = ConexionDB.getConexion();
	}
	
	public ArrayList<Empleado> consultaEmpleados() throws SQLException {
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		String consulta = "select * from Empleados;";
		Statement s = conexion.createStatement();
		ResultSet r = s.executeQuery(consulta);
		while (r.next()) {
			int id = r.getInt(ID);
			String nombre = r.getString(NOMBRE);
			String apellido = r.getString(APELLIDO);
			String dni = r.getString(DNI);
			int telefono = r.getInt(TEL);
			Empleado e = new Empleado(id, nombre, apellido, dni, telefono);
			empleados.add(e);
		}
		return empleados;
	}

	public static ModeloEmpleado getInstance() {
		if (instance == null) instance = new ModeloEmpleado();
		return instance;
	}
	
}
