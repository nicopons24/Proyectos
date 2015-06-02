package kukabuka.model;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;

import javax.imageio.ImageIO;

public class Imagen {

	private int idImagen;
	private File imagen;
	private String nombre;
	private Blob blob;
	
	public Imagen(int idImagen, Blob img, String nombre) {
		this.idImagen = idImagen;
		this.blob = img;
		this.nombre = nombre;
	}
	
	public Imagen(File img, String nombre) {
		this.imagen = redimensinaImagen(img, nombre);
		this.nombre = nombre;
	}
	
	private File redimensinaImagen(File f, String nom) {
		BufferedImage img = null;
		File file = new File(System.getProperty("user.home")+"/Pictures/"+nom.replace("\\","/"));
		file.deleteOnExit();
		try {
			img = ImageIO.read(f);
			Image imagenRedimensionada = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
			BufferedImage bufferedImage = new BufferedImage(imagenRedimensionada.getWidth(null), imagenRedimensionada.getHeight(null), img.getType());
			Graphics2D graphics = bufferedImage.createGraphics();
			graphics.drawImage(imagenRedimensionada, 0, 0, null);
			graphics.dispose();
			ImageIO.write(bufferedImage, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public File getImagen() {
		return imagen;
	}

	public void setImagen(File img) {
		this.imagen = img;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Blob getBlob() {
		return blob;
	}

	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	
}
