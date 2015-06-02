package kukabuka.controller;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kukabuka.config.Idioma;
import kukabuka.gui.VentanaReceta;
import kukabuka.model.Datos;
import kukabuka.model.Favorito;
import kukabuka.model.Ingrediente;
import kukabuka.model.Puntuacion;
import kukabuka.model.Receta;
import kukabuka.model.RecetaModel;
import kukabuka.model.Usuario;
import kukabuka.model.UsuarioModel;

public class ControladorReceta {

	private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	private Idioma idioma = Idioma.getInstance();
	private VentanaReceta vReceta;
	private Receta receta;
	private Usuario user = ControladorPrincipal.getInstance().getUser();
	private UsuarioModel modeloUsuario;
	private RecetaModel modeloReceta;
	private int nImagen;

	public ControladorReceta(Receta r) {

		this.receta = r;
		this.modeloReceta = RecetaModel.getInstance();
		this.modeloUsuario = UsuarioModel.getInstance();
		this.vReceta = new VentanaReceta(this.size);
		this.nImagen = 0;

		cargaDatos();
		mostrarImagenes();
	}

	public void mostrarVideo(){
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		String link = receta.getVideoUrl();
		if (link.equals("")) {
			vReceta.getBtnVideo().setEnabled(false);
		}
		else {
			try {
				desktop.browse(new URI(link));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}

	}   

	private void cargaDatos(){
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		Usuario u = null;
		try {
			ingredientes = modeloReceta.consultaIngredientesReceta(receta.getIdReceta());
			u = modeloUsuario.consultaUsuario(receta.getIdUsuario());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.receta.setIngredientes(ingredientes);
		String categoria = Datos.CATEGORIA[receta.getIdCategoria()];
		String autor = u.getNomUsuario();
		this.vReceta.cargaDatos(receta, categoria, autor);
		consultaPuntuacionUsuario();
		consultaFavorito();
	}

	public void puntuar(int i){
		try {
			modeloReceta.insertarPuntuacion(receta, user, i);
			JOptionPane.showMessageDialog(vReceta, idioma.getProperty("JPanelReceta1")+i+idioma.getProperty("JPanelReceta2")+receta.getNombre()+idioma.getProperty("gracias"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
			double nuevoPnt = modeloReceta.consultaReceta(receta.getIdReceta()).getPuntuacion();
			vReceta.asignarPuntuacion(nuevoPnt);
			desactivarPuntuacion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarImagenes(){
		if(receta.getImagenes().size() == 1){
			vReceta.getBtnSig().setEnabled(false);
			vReceta.getBtnAnt().setEnabled(false);
		}
		Blob b = receta.getImagenes().get(nImagen).getBlob();
		ImageIcon i = convertirImagen(b);
		vReceta.asignarImagen(i);
	}

	public void anterior(){
		vReceta.getBtnSig().setEnabled(true);
		if(nImagen == 0){
			vReceta.getBtnAnt().setEnabled(false);
		}
		if(nImagen > 0){
			nImagen--;
			Blob b = receta.getImagenes().get(nImagen).getBlob();
			ImageIcon i = convertirImagen(b);
			vReceta.asignarImagen(i);
			if(nImagen == 0){
				vReceta.getBtnAnt().setEnabled(false);
			}
		}
	}

	public void siguiente(){
		vReceta.getBtnAnt().setEnabled(true);
		if(nImagen == receta.getImagenes().size()-1){
			vReceta.getBtnSig().setEnabled(false);
		}
		if(nImagen < receta.getImagenes().size()){
			nImagen++;
			Blob b = receta.getImagenes().get(nImagen).getBlob();
			ImageIcon i = convertirImagen(b);
			vReceta.asignarImagen(i);
			if(nImagen == receta.getImagenes().size()-1){
				vReceta.getBtnSig().setEnabled(false);
			}
		}
	}

	public void abrirVentanaCompartir() {
		ControladorPrincipal.getInstance().ventanaTweet(receta);
		ControladorPrincipal.getInstance().getcTwitter().lanzaVentana();
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
		return new ImageIcon(i);
	}

	private void desactivarPuntuacion(){

		vReceta.getP1().setEnabled(false);
		vReceta.getP2().setEnabled(false);
		vReceta.getP3().setEnabled(false);
		vReceta.getP4().setEnabled(false);
		vReceta.getP5().setEnabled(false);   
	}

	private void consultaPuntuacionUsuario(){
		Puntuacion p = null;
		try {
			p = modeloReceta.consultaPuntuacionReceta(receta, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (p != null) {
			desactivarPuntuacion();
		}
	}

	private void consultaFavorito(){
		Favorito favorito = null;
		try {
			favorito = modeloUsuario.consultaFavoritoUsuario(user.getIdUsuario(), receta.getIdReceta());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (favorito == null) {
			vReceta.getBtnFavorito().setEnabled(true);
		}
		else {
			vReceta.getBtnFavorito().setEnabled(false);
		}
	}
	
	public void marcarFavorito(){
		try {
			Favorito favorito = modeloReceta.consultaFavorito(receta.getIdReceta());
			modeloUsuario.insertarFavoritoUsuario(user, favorito);
			JOptionPane.showMessageDialog(vReceta, receta.getNombre()+idioma.getProperty("JPanelReceta3"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
			vReceta.getBtnFavorito().setEnabled(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}