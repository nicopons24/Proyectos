package kukabuka.controller;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import kukabuka.config.Idioma;
import kukabuka.gui.VentanaNuevaReceta;
import kukabuka.model.Datos;
import kukabuka.model.Imagen;
import kukabuka.model.ImagenModel;
import kukabuka.model.Ingrediente;
import kukabuka.model.IngredienteModel;
import kukabuka.model.Receta;
import kukabuka.model.RecetaModel;
import kukabuka.model.Usuario;

public class ControladorNuevaReceta {
	
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private Idioma idioma = Idioma.getInstance();
	private VentanaNuevaReceta vNuevaReceta;
	private Usuario user = ControladorPrincipal.getInstance().getUser();
	private Receta recetaInsertar;
	private IngredienteModel modeloIngredientes;
	private ImagenModel modeloImagenes;
	private RecetaModel modeloReceta;
	private boolean video;
	private ArrayList<Imagen> imagenes = new ArrayList<Imagen>();
	private ArrayList<Ingrediente> nuevosIngredientes = new ArrayList<Ingrediente>();
	private ArrayList<Ingrediente> recetaIngredientes = new ArrayList<Ingrediente>();
	
	public ControladorNuevaReceta() {
		
		this.video = false;
		this.vNuevaReceta = new VentanaNuevaReceta(this.d, this.user);
		this.modeloImagenes = ImagenModel.getInstance();
		this.modeloIngredientes = IngredienteModel.getInstance();
		this.modeloReceta = RecetaModel.getInstance();
		
		cargaDatosNuevaRececeta();
	}
	
	private void cargaDatosNuevaRececeta() {
		ArrayList<Ingrediente> ingredientes = null;
		try {
			ingredientes = this.modeloIngredientes.consultaIngredientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.vNuevaReceta.cargaIngredientes(ingredientes);
	}
	
	public void guardarReceta() {
		if (!isCamposVacios()) {
			try {
				// INSERT RECETA
				modeloReceta.insertarReceta(recetaInsertar);
				int idReceta = modeloReceta.consultaReceta(recetaInsertar.getNombre()).getIdReceta();
				recetaInsertar.setIdReceta(idReceta);
				// INSERT INGREDIENTES
				if (!nuevosIngredientes.isEmpty()) {
					for (int i = 0; i < nuevosIngredientes.size(); i++) {
						Ingrediente ing = nuevosIngredientes.get(i);
						modeloIngredientes.insertaIngredientes(ing);
						int idIngrediente = modeloIngredientes.consultaIngrediente(ing.getNombre()).getIdIngrediente();
						ing.setIdIngrediente(idIngrediente);
						modeloReceta.insertarIngredientesReceta(recetaInsertar, ing);
					}
				}
				if (!recetaIngredientes.isEmpty()) {
					for (int i = 0; i < recetaIngredientes.size(); i++) {
						Ingrediente ing = recetaIngredientes.get(i);
						modeloReceta.insertarIngredientesReceta(recetaInsertar, ing);
					}
				}
				// INSERT IMAGENES
				if (!imagenes.isEmpty()) {
					for (int i = 0; i < imagenes.size(); i++) {
						Imagen img = imagenes.get(i);
						modeloImagenes.insertarImagen(img, recetaInsertar);
					}
				}
				JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta1"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
				vNuevaReceta.dispose();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void agregarIngredienteExist() {
		Ingrediente ingrediente = (Ingrediente) vNuevaReceta.getCboxIngrediente().getSelectedItem();
		if (ingrediente == null) {
			JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta2"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		}
		else {
			recetaIngredientes.add(ingrediente);
			vNuevaReceta.getModeloIngredientes().addElement(ingrediente);
			vNuevaReceta.getCboxIngrediente().setSelectedIndex(0);
		}
	}
	
	
	public void agregaNuevoIngrediente() {
		String nombre = vNuevaReceta.getTxtNombreIng().getText();
		String tipo = vNuevaReceta.getTxtTipoIng().getText();
		if (!nombre.equals("")) {
			Ingrediente in = new Ingrediente(nombre,tipo);
			nuevosIngredientes.add(in);
			vNuevaReceta.getModeloIngredientes().addElement(in);
			vNuevaReceta.getTxtNombreIng().setText("");
			vNuevaReceta.getTxtTipoIng().setText("");
		}
		else {
			JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta3"), "NUEVO INGREDIENTE", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	public void quitarIngrediente() {
		int ie = vNuevaReceta.getListIngredientes().getSelectedIndex();
		if (ie >= 0) {
			vNuevaReceta.getModeloIngredientes().remove(ie);
		} else {
			JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta4"), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void agregarVideo(){
		if (!this.video) {
			String video = JOptionPane.showInputDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta5"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
			vNuevaReceta.getModeloMultimedia().add(0, video);
			this.video = true;
		}
		else {
			String video = JOptionPane.showInputDialog(vNuevaReceta,  idioma.getProperty("JPanelNuevaReceta5"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
			vNuevaReceta.getModeloMultimedia().add(0, video);
		}
	}
	
	public void agregarImagen() {
		String nota = "Archivo de imagen";
		String[] extensiones = {"jpg","png"};
		File fichero = abrirEleccionFicheros(nota, extensiones);
		if (fichero != null) {
			File img = new File(fichero.getPath());
			Imagen imagen = new Imagen(img, fichero.getName());
			imagenes.add(imagen);
			vNuevaReceta.getModeloMultimedia().addElement(imagen.getNombre());
		}
	}
	
	public void quitarMultimedia() {
		int s= vNuevaReceta.getListMultimedia().getSelectedIndex();
		if (s == -1) {
			JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta4"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		}
		else {
			vNuevaReceta.getModeloMultimedia().remove(s);
		}
	}
	
	public void borrarDatos() {
		vNuevaReceta.getTxtTitulo().setText(null);
		vNuevaReceta.getTxtDescripcion().setText(null);
		vNuevaReceta.getCboxTipo().setSelectedIndex(0);
		vNuevaReceta.getCboxCategoria().setSelectedIndex(0);
		vNuevaReceta.getCboxDificultad().setSelectedIndex(0);
		vNuevaReceta.getTxtPais().setText(null);
		vNuevaReceta.getSpnDuracion().setValue(0);
		vNuevaReceta.getTxtConsejos().setText(null);
	}
	
	public void borrarIngredientes() {
		vNuevaReceta.getTxtIngPrincipal().setText(null);
		vNuevaReceta.getCboxIngrediente().setSelectedIndex(0);
		vNuevaReceta.getModeloIngredientes().removeAllElements();
		vNuevaReceta.getTxtNombreIng().setText(null);
		vNuevaReceta.getTxtTipoIng().setText(null);
		for (int i = 0; i < nuevosIngredientes.size(); i++) {
			nuevosIngredientes.remove(i);
		}
		for (int i = 0; i < recetaIngredientes.size(); i++) {
			recetaIngredientes.remove(i);
		}
	}
	
	public void borrarPreparacion() {
		vNuevaReceta.getTxtPreparacion().setText(null);
	}
	
	public void borrarMultimedia() {
		vNuevaReceta.getModeloMultimedia().removeAllElements();
		this.video = false;
		for (int i = 0; i < imagenes.size(); i++) {
			imagenes.remove(i);
		}
	}
	
	private File abrirEleccionFicheros(String nota, String[] extensiones) {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter(nota,extensiones);
		JFileChooser eleccion = new JFileChooser();
		eleccion.setFileFilter(filtro);
		int opcion = eleccion.showOpenDialog(vNuevaReceta);
		if (opcion == JFileChooser.APPROVE_OPTION) {
			File f = eleccion.getSelectedFile();
			return f;
		}
		return null;
	}
	
	private boolean isCamposVacios() {
		String tit = vNuevaReceta.getTxtTitulo().getText();
		String desc = vNuevaReceta.getTxtDescripcion().getText();
		String pais = vNuevaReceta.getTxtPais().getText();
		String autor = vNuevaReceta.getTxtAutor().getText();
		String ingPrinc = vNuevaReceta.getTxtIngPrincipal().getText();
		String prep = vNuevaReceta.getTxtPreparacion().getText();
		int nTipo = vNuevaReceta.getCboxTipo().getSelectedIndex();
		int nCat = vNuevaReceta.getCboxCategoria().getSelectedIndex();
		int nDif = vNuevaReceta.getCboxDificultad().getSelectedIndex();
		int tiempo = (int) vNuevaReceta.getSpnDuracion().getValue();
		String cons = vNuevaReceta.getTxtConsejos().getText();
		if (tit.equals("") || desc.equals("") || pais.equals("") || autor.equals("") || ingPrinc.equals("") || prep.equals("") || nTipo == 0 || nCat == 0 ||
				nDif == 0 || tiempo == 0 || vNuevaReceta.getModeloIngredientes().isEmpty()) {
			JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta6"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
			return true;
		}if(desc.length() > 140){
			JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta8"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
			return true;
		}if(cons.length() > 140){
			JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta9"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
			return true;
		}if(ingPrinc.length() > 20){
			JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta10"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else {
			int idUsuario = user.getIdUsuario();
			String vidUrl = "";
			if (video) {
				vidUrl = vNuevaReceta.getModeloMultimedia().getElementAt(0);
			}
			if (idUsuario <= 0) {
				JOptionPane.showMessageDialog(vNuevaReceta, idioma.getProperty("JPanelNuevaReceta7"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
				return true;
			}
			else {
				String tipo = Datos.TIPO[nTipo];
				String dif = Datos.DIFICULTAD[nDif];
				recetaInsertar = new Receta(tit, desc, ingPrinc, tipo, prep, vidUrl, idUsuario, cons, dif, 0, pais, nCat, tiempo, null, imagenes);
				return false;
			}
		}
	}
}
