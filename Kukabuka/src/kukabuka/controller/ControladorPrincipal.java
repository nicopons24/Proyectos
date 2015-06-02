package kukabuka.controller;

import javax.swing.JOptionPane;

import kukabuka.config.Token;
import kukabuka.gui.VentanaCarga;
import kukabuka.model.ConexionDB;
import kukabuka.model.Receta;
import kukabuka.model.Usuario;
import kukabuka.twitter.Autorizacion;
import kukabuka.twitter.TwitterController;

public class ControladorPrincipal {

	private static ControladorPrincipal instance = new ControladorPrincipal();
	private ConexionDB bd;
	private ControladorLogin cLogin;
	private ControladorInicio cInicio;
	private ControladorNuevaReceta cNuevaReceta;
	private ControladorReceta cReceta;
	private ControladorPerfil cPerfil;
	private TwitterController cTwitter;
	private Autorizacion autTwitter;
	private Usuario user;
	private boolean autenticated;
	
	private ControladorPrincipal() {
		this.autenticated = false;
		bd = ConexionDB.getInstance();
	}
	
	public void ventanaLogin(){
		if (bd.isConected()) {
			this.cLogin = new ControladorLogin();
		}
		else {
			JOptionPane.showMessageDialog(null, "Error de conexion", "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void ventanaInicio() {
		if (this.autenticated) {
			this.cInicio = new ControladorInicio();
		}
	}
	
	public void ventanaPerfil() {
		if (this.autenticated) {
				this.cPerfil = new ControladorPerfil();
		}
	}
	
	public void ventanaNuevaReceta(){
		if (this.autenticated) {
			this.cNuevaReceta = new ControladorNuevaReceta();
		}
	}
	
	public void ventanaReceta(Receta r){
		if (this.autenticated) {
			this.cReceta = new ControladorReceta(r);
		}
	}
	
	public void autorizarTwitter() {
		if (this.autenticated) {
			this.autTwitter = new Autorizacion();
		}
	}
	
	public void ventanaTweet(Receta r) {
		if (this.autenticated) {
			this.cTwitter = new TwitterController(r);
		}
	}

	public static ControladorPrincipal getInstance() {
		return instance;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public boolean isAutenticated() {
		return autenticated;
	}

	public void setAutenticated(boolean autenticated) {
		this.autenticated = autenticated;
	}

	public ControladorLogin getcLogin() {
		return cLogin;
	}

	public ControladorInicio getcInicio() {
		return cInicio;
	}

	public ControladorNuevaReceta getcNuevaReceta() {
		return cNuevaReceta;
	}

	public ControladorReceta getcReceta() {
		return cReceta;
	}

	public ControladorPerfil getcPerfil() {
		return cPerfil;
	}

	public TwitterController getcTwitter() {
		return cTwitter;
	}

	public Autorizacion getAutTwitter() {
		return autTwitter;
	}
}
