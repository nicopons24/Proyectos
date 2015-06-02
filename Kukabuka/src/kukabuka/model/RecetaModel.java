package kukabuka.model;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecetaModel {

	private static RecetaModel instance = new RecetaModel();
	private ConexionDB bd;
	private String consultaRecetas = "SELECT * FROM recetas ORDER BY nombre;";
	
	private RecetaModel() {
		bd = ConexionDB.getInstance();
	}
	
	public ArrayList<Receta> consultaRecetas() throws SQLException {
		ArrayList<Receta> recetas = new ArrayList<Receta>();
		ResultSet r = bd.realizarConsulta(consultaRecetas);
		try {
			while (r.next()) {
				int idReceta = r.getInt("idReceta");
				String nom = r.getString("nombre");
				String des = r.getString("descripcion");
				String tipoIng = r.getString("tipoIngPrincipal");
				String tipo = r.getString("tipo");
				String prep = r.getString("preparacion");
				String video = r.getString("video");
				int idUsuario = r.getInt("idUsuario");
				String cons = r.getString("consejo");
				String dif = r.getString("dificultad");
				double punt = r.getDouble("puntuacion");
				String pais = r.getString("pais");
				int idCat = r.getInt("idCategoria");
				int tPrep = r.getInt("tPrep");
				Receta re = new Receta(idReceta, nom, des, tipoIng, tipo, prep, video, idUsuario, cons, dif, punt, pais, idCat, tPrep, null, null);
				recetas.add(re);
			}
		}
		finally {
			r.close();
		}
		return recetas;
	}
	
	public Receta consultaReceta(int idReceta) throws SQLException {
		Receta receta = null;
		String consulta = "SELECT * FROM recetas where idReceta = "+idReceta+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				String nom = r.getString("nombre");
				String des = r.getString("descripcion");
				String tipoIng = r.getString("tipoIngPrincipal");
				String tipo = r.getString("tipo");
				String prep = r.getString("preparacion");
				String video = r.getString("video");
				int idUsuario = r.getInt("idUsuario");
				String cons = r.getString("consejo");
				String dif = r.getString("dificultad");
				double punt = r.getDouble("puntuacion");
				String pais = r.getString("pais");
				int idCat = r.getInt("idCategoria");
				int tPrep = r.getInt("tPrep");
				receta = new Receta(idReceta, nom, des, tipoIng, tipo, prep, video, idUsuario, cons, dif, punt, pais, idCat, tPrep, null, null);
			}
		}
		finally {
			r.close();
		}
		return receta;
	}
	
	public ArrayList<Receta> buscarRecetas(String consulta) throws SQLException{
		ArrayList<Receta> recetas = new ArrayList<Receta>();
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()){
				String nombre = r.getString("nombre");
				int id = r.getInt("idReceta");
				String tipo = r.getString("tipo");
				String dif = r.getString("dificultad");
				int idCat = r.getInt("idCategoria");
				String des = r.getString("descripcion");
				String tipoIng = r.getString("tipoIngPrincipal");
				String prep = r.getString("preparacion");
				String video = r.getString("video");
				int idUsuario = r.getInt("idUsuario");
				String cons = r.getString("consejo");
				double punt = r.getDouble("puntuacion");
				String pais = r.getString("pais");
				int tPrep = r.getInt("tPrep");
				Receta receta = new Receta(id, nombre, des, tipoIng, tipo, prep, video, idUsuario, cons, dif, punt, pais, idCat, tPrep, null, null);
				recetas.add(receta);
			}
		}
		finally {
			r.close();
		}
		return recetas;
	}
	
	public Receta consultaReceta(String nombre) throws SQLException {
		Receta receta = null;
		String consulta = "SELECT * FROM recetas where nombre = '"+nombre+"';";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				int id = r.getInt("idReceta");
				String des = r.getString("descripcion");
				String tipoIng = r.getString("tipoIngPrincipal");
				String tipo = r.getString("tipo");
				String prep = r.getString("preparacion");
				String video = r.getString("video");
				int idUsuario = r.getInt("idUsuario");
				String cons = r.getString("consejo");
				String dif = r.getString("dificultad");
				double punt = r.getDouble("puntuacion");
				String pais = r.getString("pais");
				int idCat = r.getInt("idCategoria");
				int tPrep = r.getInt("tPrep");
				receta = new Receta(id, nombre, des, tipoIng, tipo, prep, video, idUsuario, cons, dif, punt, pais, idCat, tPrep, null, null);
			}
		}
		finally {
			r.close();
		}
		return receta;
	}
	
	public ArrayList<Ingrediente> consultaIngredientesReceta(int idReceta) throws SQLException {
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		String consulta =  "SELECT i.* FROM recetas r inner join ingrediente_receta ir on r.idReceta = ir.idReceta inner join ingredientes i on ir.idIngrediente = i.idIngrediente where r.idReceta = "+idReceta+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				int idIngrediente = r.getInt("idIngrediente");
				String nombre = r.getString("nombre");
				String tipo = r.getString("tipo");
				Ingrediente i = new Ingrediente(idIngrediente, nombre, tipo);
				ingredientes.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredientes;
	}
	
	public Puntuacion consultaPuntuacionReceta(Receta c, Usuario u) throws SQLException{
		Puntuacion punt = null;
		String consulta = "SELECT * FROM puntuaciones WHERE idUsuario = "+u.getIdUsuario()+" AND idReceta = "+c.getIdReceta()+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				int idReceta = r.getInt("idReceta");
				int idUsuario= r.getInt("idUsuario");
				int puntuacion = r.getInt("puntuacion");
				punt = new Puntuacion(idUsuario, idReceta, puntuacion);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return punt;
	}
	
	public Favorito consultaFavorito(int idReceta) throws SQLException {
		Favorito favorito = null;
		String consulta = "SELECT * FROM favoritos where idReceta = "+idReceta+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				int idFav = r.getInt("idFavorito");
				String nombre = r.getString("nomReceta");
				favorito = new Favorito(idFav, idReceta, nombre);
			}
		}
		finally {
			r.close();
		}
		return favorito;
	}
	
	public void insertarReceta(Receta r) throws SQLException {
		String campos = "(nombre, descripcion, tipoIngPrincipal, tipo, preparacion, video, idUsuario, consejo, dificultad, puntuacion, pais, idCategoria, tPrep)";
		String valores = "('"+r.getNombre()+"','"+r.getDescripcion()+"','"+r.getTipoIngPrincipal()+"','"+r.getTipo()+"','"+r.getPreparacion()+"','"+r.getVideoUrl()+"',"+r.getIdUsuario()+
				",'"+r.getConsejo()+"','"+r.getDificultad()+"',"+r.getPuntuacion()+",'"+r.getPais()+"',"+r.getIdCategoria()+","+r.gettPrepaacion()+")";
		bd.insertarDatos("INSERT INTO recetas "+campos+" values "+valores+";");
	}
	
	public void insertarIngredientesReceta(Receta r, Ingrediente i) throws SQLException {
		bd.insertarDatos("INSERT INTO ingrediente_receta VALUES ("+r.getIdReceta()+","+i.getIdIngrediente()+");");
	}
	
	public void insertarPuntuacion(Receta r, Usuario i, int p) throws SQLException{
		bd.insertarDatos("INSERT INTO puntuaciones VALUES ("+r.getIdReceta()+","+i.getIdUsuario()+","+p+");");
	}

	public static RecetaModel getInstance() {
		return instance;
	}	
}
