package kukabuka.controller;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kukabuka.config.Idioma;
import kukabuka.gui.VentanaInicio;
import kukabuka.gui.VentanaLogin;
import kukabuka.model.ConexionDB;
import kukabuka.model.Usuario;
import kukabuka.model.UsuarioModel;

public class ControladorLogin{

	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private Idioma idioma = Idioma.getInstance();
	private UsuarioModel modeloUsuarios;
	private VentanaLogin vLogin;
	private URI url;

	public ControladorLogin() {
		
		this.vLogin = new VentanaLogin(d);
		modeloUsuarios = UsuarioModel.getInstance();
		try {
			url = new URI("http://kukabuka.esy.es/contrase%C3%B1aKukabuka.html");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void signin(){
		boolean b = true;

		if(vLogin.getTxtUserRegistro().getText().equals("") || vLogin.getTxtUserRegistro().getText() == null) {
			JOptionPane.showMessageDialog(vLogin, idioma.getProperty("JPaneLogin1"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
			b = false;
		}
		else {
			if(vLogin.getTxtUserRegistro().getText().length() > 10){
				JOptionPane.showMessageDialog(vLogin, idioma.getProperty("JPaneLogin2"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
				b = false;
			}
			else {
				String pass = new String(vLogin.getPassRegistro().getPassword());
				if(pass.equals("") || pass == null) {
					JOptionPane.showMessageDialog(vLogin, idioma.getProperty("JPaneLogin3"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
					b = false;
				}
				else {
					if(vLogin.getTxtCorreoRegistro().getText().equals("") || vLogin.getTxtCorreoRegistro().getText() == null){
						JOptionPane.showMessageDialog(vLogin, idioma.getProperty("JPaneLogin4"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
						b = false;
					}
					else{
						try {
							Usuario u = modeloUsuarios.consultaUsuario(vLogin.getTxtUserRegistro().getText());
							if (u == null) {
								if(b==true) {
									String nombre = vLogin.getTxtUserRegistro().getText();
									pass = new String(vLogin.getPassRegistro().getPassword());
									String correo = vLogin.getTxtCorreoRegistro().getText();
									u = new Usuario(nombre, pass, correo);
									modeloUsuarios.insertaUsuario(u);
									JOptionPane.showMessageDialog(vLogin, idioma.getProperty("JPaneLogin6"), "KUKABUKA", JOptionPane.INFORMATION_MESSAGE);
								}
							}
							else {
								if (u.getIdUsuario() != 0) {
									JOptionPane.showMessageDialog(vLogin, idioma.getProperty("JPaneLogin5"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		
								}
							}
						} 
						catch (SQLException e) {
							e.printStackTrace();
						}
					}		
				}
			}
		}
	}

	public void login(){
		String nomUsuario = vLogin.getTxtUsuarioLogin().getText();
		Usuario u = null;
		try {
			u = modeloUsuarios.consultaUsuario(nomUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String password = new String(vLogin.getPassLogin().getPassword());
		
		if (u == null){
			JOptionPane.showMessageDialog(vLogin, idioma.getProperty("JPaneLogin7"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		}
		else{
		if(u.getPass().equals(password) && password != null && password != ("")){
			ControladorPrincipal.getInstance().setAutenticated(true);
			ControladorPrincipal.getInstance().setUser(u);
			ControladorPrincipal.getInstance().ventanaInicio();
			vLogin.dispose();
		}
		else{
			JOptionPane.showMessageDialog(vLogin, idioma.getProperty("JPaneLogin7"), "KUKABUKA", JOptionPane.ERROR_MESSAGE);
		}}
	}

	public void cambioContrasena(){
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if ( desktop != null && desktop.isSupported(Desktop.Action.BROWSE) ) {
			try {
				desktop.browse(url);
			} catch ( Exception ex ) {
				System.err.println( ex.getMessage() );
			}
		} 
	}
}
