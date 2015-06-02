package kukabuka.model;

import java.util.ArrayList;


public class Usuario {

	private int idUsuario;
	private String nomUsuario;
	private String pass;
	private String correo;
	
	
	public Usuario(int idUsuario, String nomUsuario, String pass, String correo) {
		
		this.idUsuario = idUsuario;
		this.nomUsuario = nomUsuario;
		this.pass = pass;
		this.correo = correo;
	}
	
	public Usuario(String nomUsuario, String pass, String correo) {
		
		this.nomUsuario = nomUsuario;
		this.pass = pass;
		this.correo = correo;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
}
