package kukabuka.controller;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kukabuka.config.Idioma;
import kukabuka.gui.VentanaInicio;
import kukabuka.model.Datos;
import kukabuka.model.Favorito;
import kukabuka.model.Imagen;
import kukabuka.model.Receta;
import kukabuka.model.RecetaModel;
import kukabuka.model.Usuario;
import kukabuka.model.UsuarioModel;

public class ControladorInicio {

	private Usuario user = ControladorPrincipal.getInstance().getUser();
	private Idioma idioma = Idioma.getInstance();
	private VentanaInicio vInicio;
	private RecetaModel modeloReceta;
	private UsuarioModel modeloUsuario;
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private int numRecetas;
	
	public ControladorInicio() {
		
		this.numRecetas = 0;
		this.vInicio = new VentanaInicio(this.d, this.user);
		modeloReceta = RecetaModel.getInstance();
		modeloUsuario = UsuarioModel.getInstance();
		
		mostrarInicio();
		cargaSugerencias();
	}
	
	public void mostrarInicio() {
		cargaRecetas();
		JPanel p = vInicio.getPanelPrincipal();
		CardLayout c = (CardLayout) p.getLayout();
		c.show(p, "INICIO");
		vInicio.getBtnAtras().setEnabled(false);
		vInicio.getBtnFavoritos().setEnabled(true);
		vInicio.getBtnBuscarMenu().setEnabled(true);
	}

	private void cargaRecetas(){
		ArrayList<Receta> recetas = new ArrayList<Receta>();
		try {
			recetas = modeloReceta.consultaRecetas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.numRecetas = recetas.size();
		this.vInicio.cargaRecetas(recetas);
	}
	
	private void cargaSugerencias() {
		Receta[] r = consultaSugerencias();
		vInicio.cargaSugerencias(r);
	}
	
	public Receta[] consultaSugerencias(){
		Receta[] recetas = new Receta[2];
		for (int i = 0; i < recetas.length; i++){
			int aleat = (int) (Math.random()*numRecetas + 1);
			try {
				Receta r = modeloReceta.consultaReceta(aleat);
				recetas[i] = r;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recetas;
	}
	
	public void mostrarBuscador() {
		JPanel p = vInicio.getPanelPrincipal();
		CardLayout c = (CardLayout) p.getLayout();
		c.show(p, "BUSCAR");
		vInicio.getBtnAtras().setEnabled(true);
		vInicio.getBtnFavoritos().setEnabled(true);
		vInicio.getBtnBuscarMenu().setEnabled(false);
	}
	
	public void recetasBuscador(){
		ArrayList<Receta> recetas =  buscarRecetas();
		this.vInicio.muestraBusqueda(recetas);
	}
	
	public ArrayList<Receta> buscarRecetas() {
		
		ArrayList<Receta> recetas =  new ArrayList<Receta>();
		String nombre = vInicio.getTxtBuscar().getText();
		int cat = vInicio.getCboxCategoria().getSelectedIndex();
		int tipo = vInicio.getCboxTipo().getSelectedIndex();
		int dif = vInicio.getCboxDificultad().getSelectedIndex();
		
		if(!nombre.equals("")){
			String consulta = "SELECT * FROM recetas WHERE (nombre like '%"+nombre+"%' or nombre like '%"+nombre+"' or nombre like '"+nombre+"%') ORDER BY nombre;";
			//CONSULTA CATEGORIA
			if(cat != 0 && tipo == 0 && dif == 0){
				consulta = "SELECT * FROM recetas WHERE (nombre like '%"+nombre+"%' or nombre like '%"+nombre+"' or nombre like '"+nombre+"%') and idCategoria = "+cat+" ORDER BY nombre;";
			}
			// CONSULTA TIPO
			if(cat == 0 && tipo != 0 && dif == 0){	
				String t = Datos.TIPO[tipo];
				consulta = "SELECT * FROM recetas WHERE (nombre like '%"+nombre+"%' or nombre like '%"+nombre+"' or nombre like '"+nombre+"%') and tipo like '"+t+"' ORDER BY nombre;";
			}
			// CONSULTA DIFICULTAD
			if(cat == 0 && tipo == 0 && dif != 0){
				String dificultad = Datos.DIFICULTAD[dif];
				consulta = "SELECT * FROM recetas WHERE (nombre like '%"+nombre+"%' or nombre like '%"+nombre+"' or nombre like '"+nombre+"%') and dificultad like '"+dificultad+"' ORDER BY nombre;";
			}
			// CONSULTA CATEGORIA Y TIPO
			if(cat != 0 && tipo != 0 && dif == 0){
				String t = Datos.TIPO[tipo];
				consulta = "SELECT * FROM recetas WHERE (nombre like '%"+nombre+"%' or nombre like '%"+nombre+"' or nombre like '"+nombre+"%') and tipo like '"+t+"' and idCategoria = "+cat+" ORDER BY nombre;";
			}
			// CONSULTA CATEGORIA Y DIFICULTAD
			if(cat != 0 && tipo == 0 && dif != 0){
				String dificultad = Datos.DIFICULTAD[dif];
				consulta = "SELECT * FROM recetas WHERE (nombre like '%"+nombre+"%' or nombre like '%"+nombre+"' or nombre like '"+nombre+"%') and idCategoria = "+cat+" and dificultad like '"+dificultad+"' ORDER BY nombre;";
			}
			// CONSULTA TIPO Y DIFICULTAD
			if(cat == 0 && tipo != 0 && dif != 0){
				String dificultad = Datos.DIFICULTAD[dif];
				String t = Datos.TIPO[tipo];
				consulta = "SELECT * FROM recetas WHERE (nombre like '%"+nombre+"%' or nombre like '%"+nombre+"' or nombre like '"+nombre+"%') and dificultad like '"+dificultad+"' and tipo like '"+t+"' ORDER BY nombre;";
			}
			// CONSULTA CATEGORIA TIPO Y DIFICULTAD
			if(cat != 0 && tipo != 0 && dif != 0){
				String dificultad = Datos.DIFICULTAD[dif];
				String t = Datos.TIPO[tipo];
				consulta = "SELECT * FROM recetas WHERE (nombre like '%"+nombre+"%' or nombre like '%"+nombre+"' or nombre like '"+nombre+"%') and idCategoria = "+cat+" and dificultad like '"+dificultad+"' and tipo like '"+t+"' ORDER BY nombre;";
			}
			try {
				recetas = modeloReceta.buscarRecetas(consulta);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(vInicio, idioma.getProperty("JPanelInicio1"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		}
		return recetas;
	}
	
	public void mostrarFavoritos() {
		cargaFavoritos();
		JPanel p = vInicio.getPanelPrincipal();
		CardLayout c = (CardLayout) p.getLayout();
		c.show(p, "FAVORITOS");
		vInicio.getBtnAtras().setEnabled(true);
		vInicio.getBtnBuscarMenu().setEnabled(true);
		vInicio.getBtnFavoritos().setEnabled(false);
	}
	
	private void cargaFavoritos() {
		ArrayList<Favorito> favoritos = new ArrayList<Favorito>();
		ArrayList<Receta> recetas = new ArrayList<Receta>();
		try {
			favoritos = modeloUsuario.consultaFavoritosUsuario(user.getIdUsuario());
			for (int i = 0; i < favoritos.size(); i++) {
				int id = favoritos.get(i).getIdReceta();
				Receta r = modeloReceta.consultaReceta(id);
				recetas.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		vInicio.cargaFavoritos(recetas);
	}
	
	public void cerrarSesion(){
		Usuario u = null;
		ControladorPrincipal.getInstance().setUser(u);
		ControladorPrincipal.getInstance().setAutenticated(false);
		vInicio.dispose();
		File f = new File(System.getProperty("user.home")+"/auth_file.txt".replace("\\","/"));
		f.delete();
		ControladorPrincipal.getInstance().ventanaLogin();
	}
}
