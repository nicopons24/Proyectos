package kukabuka.model;

import java.util.ArrayList;

public class Receta {

	private int idReceta;
	private String nombre;
	private String descripcion;
	private String tipoIngPrincipal;
	private String tipo;
	private String preparacion;
	private String videoUrl;
	private int idUsuario;
	private String consejo;
	private String dificultad;
	private double puntuacion;
	private String pais;
	private int idCategoria;
	private int tPrepaacion;
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Imagen> imagenes;

	public Receta(int idReceta, String nombre, String descripcion,
			String tipoIngPrincipal, String tipo, String preparacion,
			String videoUrl, int idUsuario, String consejo, String dificultad,
			double puntuacion, String pais, int idCategoria, int tPrepaacion,
			ArrayList<Ingrediente> ingredientes, ArrayList<Imagen> imagenes) {
		this.idReceta = idReceta;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoIngPrincipal = tipoIngPrincipal;
		this.tipo = tipo;
		this.preparacion = preparacion;
		this.videoUrl = videoUrl;
		this.idUsuario = idUsuario;
		this.consejo = consejo;
		this.dificultad = dificultad;
		this.puntuacion = puntuacion;
		this.pais = pais;
		this.idCategoria = idCategoria;
		this.tPrepaacion = tPrepaacion;
		this.ingredientes = ingredientes;
		this.imagenes = imagenes;
	}

	public Receta(String nombre, String descripcion, String tipoIngPrincipal,
			String tipo, String preparacion, String videoUrl, int idUsuario,
			String consejo, String dificultad, double puntuacion, String pais,
			int idCategoria, int tPrepaacion,
			ArrayList<Ingrediente> ingredientes, ArrayList<Imagen> imagenes) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoIngPrincipal = tipoIngPrincipal;
		this.tipo = tipo;
		this.preparacion = preparacion;
		this.videoUrl = videoUrl;
		this.idUsuario = idUsuario;
		this.consejo = consejo;
		this.dificultad = dificultad;
		this.puntuacion = puntuacion;
		this.pais = pais;
		this.idCategoria = idCategoria;
		this.tPrepaacion = tPrepaacion;
		this.ingredientes = ingredientes;
		this.imagenes = imagenes;
	}

	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public ArrayList<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(ArrayList<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public int getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoIngPrincipal() {
		return tipoIngPrincipal;
	}

	public void setTipoIngPrincipal(String tipoIngPrincipal) {
		this.tipoIngPrincipal = tipoIngPrincipal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getConsejo() {
		return consejo;
	}

	public void setConsejo(String consejo) {
		this.consejo = consejo;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int gettPrepaacion() {
		return tPrepaacion;
	}

	public void settPrepaacion(int tPrepaacion) {
		this.tPrepaacion = tPrepaacion;
	}
	
}
