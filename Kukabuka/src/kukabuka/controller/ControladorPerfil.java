package kukabuka.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kukabuka.config.Idioma;
import kukabuka.gui.VentanaPerfil;
import kukabuka.model.Usuario;
import kukabuka.model.UsuarioModel;

public class ControladorPerfil {
	
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private Idioma idioma = Idioma.getInstance();
	private Usuario user = ControladorPrincipal.getInstance().getUser();
	private VentanaPerfil vPerfil;
	private UsuarioModel modeloUsuario;
	
	public ControladorPerfil(){
		
		this.vPerfil = new VentanaPerfil(this.d, this.user);
		modeloUsuario = UsuarioModel.getInstance();
	}
	
	public void guardarDatos(){
		
		if(modificar() == "Pass"){
			cambioPass();
			JOptionPane.showMessageDialog(vPerfil, idioma.getProperty("JPanelPerfil1"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
			vPerfil.dispose();
		}
				
		if(modificar() == "Correo"){
			cambioCorreo();
			JOptionPane.showMessageDialog(vPerfil, idioma.getProperty("JPanelPerfil2"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
			vPerfil.dispose();
		}
		
		if(modificar() == "Error"){
			JOptionPane.showMessageDialog(vPerfil, idioma.getProperty("JPanelPerfil4"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		}
		
		if(modificar() == "PError"){
			JOptionPane.showMessageDialog(vPerfil, idioma.getProperty("JPanelPerfil5"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		}
		
		if(modificar() == "Completo"){
			cambioCorreo();
			cambioPass();
			JOptionPane.showMessageDialog(vPerfil, idioma.getProperty("JPanelPerfil3"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
			vPerfil.dispose();
		}
		
	}
	
	private boolean compruebaPass(String p1, String p2) {
		
		if(p1.equals(p2)){return false;}
		else{return true;}
	}
	
	public void cambioPass(){
			
		String passC = new String(vPerfil.getPassContrasea().getPassword());
		Usuario u = this.user;
		u.setPass(passC);
		try {
			modeloUsuario.actualizaUsuario(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cambioCorreo(){
		
		String correoC = vPerfil.getTxtCorreo().getText();
		Usuario u = this.user;
		u.setCorreo(correoC);
		try {
			modeloUsuario.actualizaUsuario(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String modificar(){
		
		String pass = new String(vPerfil.getPassContrasea().getPassword());
		String passRep = new String(vPerfil.getPassRepitaContrasea().getPassword());
		String correo = vPerfil.getTxtCorreo().getText();
		String mod = "Completo";
		
		if(pass.equals("") && passRep.equals("") && correo.equals("")){
			
			mod = "Error";
			return mod;	 
		}
		else{
			if(correo.equals("")){
				if(compruebaPass(pass, passRep) != true){
					
					mod = "Pass";
					return mod;	
				}
				else{
					mod = "PError";
					return mod;
				}
			}
			else{
				
				if(pass.equals("") && passRep.equals("")){
					
					mod = "Correo";
					return mod;
				}	
			return mod;	
			}			
			
		}
	}
}			
			
				
		
	
		