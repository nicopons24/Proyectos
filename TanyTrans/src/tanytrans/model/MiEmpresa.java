package tanytrans.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MiEmpresa {
	
	private static final int ID = 1;
	private static final String NOM = "nombre";
	private static final String DIR = "direccion";
	private static final String LOC = "localidad";
	private static final String PAIS = "pais";
	private static final String EMAIL = "email";
	private static final String CP = "cp";
	private static final String TEL1 = "tel1";
	private static final String TEL2 = "tel2";
	private static final String FAX = "fax";
	private static final String BANCO = "banco";
	private static final String IBAN = "iban";
	private static final String PROP = "firma";
	private static final String NUM = "num";

	private static MiEmpresa instance = null;
	private Connection conexion;
	private String nombre, direccion, localidad, pais, email, banco, idEmpresa, propietario, tel1, tel2, fax, iban;
	private int cp;
	
	private MiEmpresa() {
		conexion = ConexionBD.getInstance().getConnection();
		setData();
	}
	
	public void updateEmpresa() throws SQLException {
		String update = "update datosEmpresa set "+NOM+" = ?, "+DIR+" = ?, "+LOC+" = ?, "+PAIS+" = ?, "+EMAIL+" = ?,"+CP+" = ?,"+TEL1+" = ?,"+TEL2+" = ?,"+FAX+" = ?,"+BANCO+" = ?,"+IBAN+" = ?,"+PROP+" = ?,"+NUM+" = ? where id = "+ID+";";
		PreparedStatement p = conexion.prepareStatement(update);
		p.setString(1, nombre);
		p.setString(2, direccion);
		p.setString(3, localidad);
		p.setString(4, pais);
		p.setString(5, email);
		p.setInt(6, cp);
		p.setString(7, tel1);
		p.setString(8, tel2);
		p.setString(9, fax);
		p.setString(10, banco);
		p.setString(11, iban);
		p.setString(12, propietario);
		p.setString(13, idEmpresa);
		p.executeUpdate();
	}
	
	private void setData() {
		String consulta = "select * from datosEmpresa;";
		try {
			ResultSet r = conexion.createStatement().executeQuery(consulta);
			if (r.next()) {
				idEmpresa = r.getString(NUM);
				nombre = r.getString(NOM);
				direccion = r.getString(DIR);
				localidad = r.getString(LOC);
				pais = r.getString(PAIS);
				email = r.getString(EMAIL);
				banco = r.getString(BANCO);
				propietario = r.getString(PROP);
				cp = r.getInt(CP);
				tel1 = r.getString(TEL1);
				tel2 = r.getString(TEL2);
				fax = r.getString(FAX);
				iban = r.getString(IBAN);
				idEmpresa = r.getString(NUM);
				propietario = r.getString(PROP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static MiEmpresa getInstance() {
		if (instance == null) {instance = new MiEmpresa();}
		return instance;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
}
