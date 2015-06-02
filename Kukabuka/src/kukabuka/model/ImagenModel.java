package kukabuka.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImagenModel {

	private static ImagenModel instance = new ImagenModel();
	private ConexionDB bd;
	private String consultaImagenes = "SELECT * FROM imagenes;";
	
	private ImagenModel() {
		bd = ConexionDB.getInstance();
	}
	
	public ArrayList<Imagen> consultaImagenes() throws SQLException {
		ArrayList<Imagen> imagenes = new ArrayList<Imagen>();
		ResultSet r = bd.realizarConsulta(consultaImagenes);
		try {
			while(r.next()) {
				int idImagen = r.getInt("idImagenes");
				String nombre = r.getString("nombre");
				Blob imagen = r.getBlob("imagenes");
				Imagen i = new Imagen(idImagen, imagen, nombre);
				imagenes.add(i);
			}
		}
		finally {
			r.close();
		}
		return imagenes;
	}
	
	public Imagen consultaImagen(int idImagen) throws SQLException{
		Imagen imagen = null;
		String consulta = "SELECT * FROM imagenes where idImagenes = "+idImagen+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				String nombre = r.getString("nombre");
				Blob img = r.getBlob("imagenes");
				imagen = new Imagen(idImagen, img, nombre);
			}
		}
		finally {
			r.close();
		}
		return imagen;
	}
	
	public Imagen consultaImagen(String nombre) throws SQLException{
		Imagen imagen = null;
		String consulta = "SELECT * FROM imagenes where nombre = '"+nombre+"';";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
				int idImagen = r.getInt("idImagenes");
				Blob img = r.getBlob("imagenes");
				imagen = new Imagen(idImagen, img, nombre);
			}
		}
		finally {
			r.close();
		}
		return imagen;
	}
	
	public void insertarImagen(Imagen i, Receta r) throws SQLException, FileNotFoundException {
		PreparedStatement pstmt = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(i.getImagen());
			pstmt = bd.getConexion().prepareStatement("INSERT INTO imagenes (nombre, imagenes, idReceta) VALUES ( ?, ?, ?);");
			pstmt.setString (1, i.getNombre());
			pstmt.setBinaryStream(2, fis, (int) i.getImagen().length());
			pstmt.setInt(3, r.getIdReceta());
			pstmt.executeUpdate();
			bd.getConexion().commit();
		}
		finally {
			pstmt.close();
		}
	}

	public static ImagenModel getInstance() {
		return instance;
	}
	
}
