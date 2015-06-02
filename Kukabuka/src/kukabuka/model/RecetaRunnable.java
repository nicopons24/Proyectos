package kukabuka.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import kukabuka.other.BotonReceta;

public class RecetaRunnable implements Runnable {

	private ConexionDB bd = ConexionDB.getInstance();
	private BotonReceta btnReceta;
	private int dim;

	public RecetaRunnable(BotonReceta btnReceta, int d) {
		this.btnReceta = btnReceta;
		this.dim = d;
	}
	
	private ArrayList<Imagen> consultaImagenesReceta(int idReceta) throws SQLException {
		ArrayList<Imagen> imagenes = new ArrayList<Imagen>();
		String consulta =  "SELECT * FROM imagenes where idReceta = "+idReceta+";";
		ResultSet r = bd.realizarConsulta(consulta);
		try {
			while (r.next()) {
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
	
	private ImageIcon convertirImagen(Blob blob) {
		byte[] data = null;
		BufferedImage bufferedImage = null;
		
		try {
			data = blob.getBytes(1, (int) blob.length());
			bufferedImage = ImageIO.read(new ByteArrayInputStream(data));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image i = bufferedImage;
		Image img = i.getScaledInstance(dim, dim, Image.SCALE_SMOOTH);
	    return new ImageIcon(img);
	}

	@Override
	public void run() {
		Receta r = btnReceta.getReceta();
		ArrayList<Imagen> imagenes = new ArrayList<Imagen>();
		try {
			imagenes = consultaImagenesReceta(r.getIdReceta());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		r.setImagenes(imagenes);
		ImageIcon i = convertirImagen(imagenes.get(0).getBlob());
		btnReceta.colocaImagen(i);
		
	}
	
}
