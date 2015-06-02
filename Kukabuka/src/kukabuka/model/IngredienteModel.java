package kukabuka.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredienteModel {
	
	private static IngredienteModel instance = new IngredienteModel();
	private ConexionDB bd;
	private String consultaIngrediente = "SELECT * FROM ingredientes ORDER BY nombre;";
	
	private IngredienteModel() {
		bd = ConexionDB.getInstance();
	}
	
	public ArrayList<Ingrediente> consultaIngredientes() throws SQLException {
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		ResultSet r = bd.realizarConsulta(consultaIngrediente);
		try {
			while(r.next()) {
				int idIngrediente = r.getInt("idIngrediente");
				String nombre = r.getString("nombre");
				String tipo = r.getString("tipo");
				Ingrediente i = new Ingrediente(idIngrediente, nombre, tipo);
				ingredientes.add(i);
			}
		}
		finally {
			r.close();
		}
		return ingredientes;
	}
	
	public Ingrediente consultaIngrediente(int idIngrediente) throws SQLException{
		Ingrediente ingrediente = null;
		String consulta = "SELECT * FROM ingredientes where idIngrediente = "+idIngrediente+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				String nombre = r.getString("nombre");
				String tipo = r.getString("tipo");
				ingrediente = new Ingrediente(idIngrediente, nombre, tipo);
			}
		}
		finally {
			r.close();
		}
		return ingrediente;
	}
	
	public Ingrediente consultaIngrediente(String nombre) throws SQLException{
		Ingrediente ingrediente = null;
		String consulta = "SELECT * FROM ingredientes where nombre = '"+nombre+"';";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				int idIngrediente = r.getInt("idIngrediente");
				String tipo = r.getString("tipo");
				ingrediente = new Ingrediente(idIngrediente, nombre, tipo);
			}
		}
		finally {
			r.close();
		}
		return ingrediente;
	}
	
	public void insertaIngredientes(Ingrediente i) throws SQLException {
		bd.insertarDatos("INSERT INTO ingredientes (nombre, tipo) values ('"+i.getNombre()+"','"+i.getTipo()+"');");
	}

	public static IngredienteModel getInstance() {
		return instance;
	}

}