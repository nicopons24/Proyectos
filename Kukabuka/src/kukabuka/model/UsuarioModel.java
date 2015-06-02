package kukabuka.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.ISUB;

public class UsuarioModel {

	private static UsuarioModel instance = new UsuarioModel();
	private ConexionDB bd;
	private String consultaUsuarios = "SELECT * FROM usuarios;";
	
	private UsuarioModel() {
		bd = ConexionDB.getInstance();
	}
	
	public ArrayList<Usuario> consultaUsuario() throws SQLException {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ResultSet r = bd.realizarConsulta(consultaUsuarios);
		int id = 0;
		try {
			while (r.next()) {
				id = r.getInt("idUsuario");
				String nombre = r.getString("nomUsuario");
				String pass = r.getString("pass");
				String correo = r.getString("correo");
				Usuario u =  new Usuario(id, nombre, pass, correo);
				usuarios.add(u);
			}
		}
		finally {
			r.close();
		}
		return usuarios;
	}
	
	public Usuario consultaUsuario(int idUsuario) throws SQLException {
		Usuario usuario = null;
		String consulta = "SELECT * FROM usuarios where idUsuario = "+idUsuario+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				String nombre = r.getString("nomUsuario");
				String pass = r.getString("pass");
				String correo = r.getString("correo");
				usuario = new Usuario(idUsuario, nombre, pass, correo);
			}
		}
		finally {
			r.close();
		}
		return usuario;
	}
	
	public Usuario consultaUsuario(String nomUsuario) throws SQLException {
		Usuario usuario = null;
		String consulta = "SELECT * FROM usuarios where nomUsuario like'"+nomUsuario+"';";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				int id = r.getInt("idUsuario");
				String pass = r.getString("pass");
				String correo = r.getString("correo");
				usuario = new Usuario(id, nomUsuario, pass, correo);
			}
		}
		finally {
			r.close();
		}
		return usuario;
	}
	
	public ArrayList<Favorito> consultaFavoritosUsuario(int idUsuario) throws SQLException {
		ArrayList<Favorito> favoritos = new ArrayList<Favorito>();
		String consulta = "select f.* FROM favoritos f inner join favorito_usuario fu on f.idFavorito = fu.idFavorito inner join usuarios u on u.idUsuario = fu.idUsuario WHERE u.idUsuario = "+idUsuario+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				int idFav = r.getInt("idFavorito");
				int idReceta = r.getInt("idReceta");
				String nomRceta = r.getString("nomReceta");
				Favorito f = new Favorito(idFav, idReceta, nomRceta);
				favoritos.add(f);
			}
		}
		finally {
			r.close();
		}
		return favoritos;
	}
	
	public Favorito consultaFavoritoUsuario(int idUsuario, int idReceta) throws SQLException {
		Favorito favorito = null;
		String consulta = "select f.* FROM favoritos f inner join favorito_usuario fu on f.idFavorito = fu.idFavorito inner join usuarios u on u.idUsuario = fu.idUsuario WHERE u.idUsuario = "+idUsuario+" and f.idReceta = "+idReceta+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				int idFav = r.getInt("idFavorito");
				String nomReceta = r.getString("nomReceta");
				favorito = new Favorito(idFav, idReceta, nomReceta);				
			}
		}
		finally {
			r.close();
		}
		return favorito;
	}
	
	public void insertarFavoritoUsuario(Usuario u, Favorito f) throws SQLException{
		bd.insertarDatos("INSERT INTO favorito_usuario "+"VALUES ("+f.getIdFavorito()+", "+u.getIdUsuario()+");");
	}
	
	public void insertaUsuario(Usuario u) throws SQLException {
		bd.insertarDatos("INSERT INTO usuarios (NomUsuario,Pass,correo) "+"VALUES ('"+u.getNomUsuario()+"','"+u.getPass()+"','"+u.getCorreo()+"');");
	}

	public void actualizaUsuario(Usuario u) throws SQLException {
		bd.insertarDatos("update usuarios set pass = '"+u.getPass()+"', correo = '"+u.getCorreo()+"' where idUsuario = "+u.getIdUsuario()+";");
	}
	
	public static UsuarioModel getInstance() {
		return instance;
	}
}
