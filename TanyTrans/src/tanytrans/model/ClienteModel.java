package tanytrans.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteModel {

	private static final String ID = "idCliente";
	private static final String NOM = "nombre";
	private static final String DIR = "direccion";
	private static final String CP = "cp";
	private static final String LOC = "localidad";
	private static final String PAIS = "pais";
	private static final String NUM = "num";
	private static final String TEL1 = "tel1";
	private static final String TEL2 = "tel2";
	
	private static ClienteModel instance = null;
	private Connection conexion;
	
	private ClienteModel() {
		conexion = ConexionBD.getInstance().getConnection();
	}
	
	public ArrayList<Cliente> consultaClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String consulta = "select * from clientes;";
		try {
			ResultSet r = conexion.createStatement().executeQuery(consulta);
			while (r.next()) {
				int id = r.getInt(ID);
				String nom = r.getString(NOM);
				String dir = r.getString(DIR);
				int cp = r.getInt(CP);
				String loc = r.getString(LOC);
				String pais = r.getString(PAIS);
				String num = r.getString(NUM);
				String tel1 = r.getString(TEL1);
				String tel2 = r.getString(TEL2);
				Cliente c = new Cliente(id, cp, nom, dir, loc, pais, num, tel1, tel2);
				clientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	public void insertaCliente(Cliente c) throws SQLException {
		String insert = "insert into clientes("+NOM+", "+DIR+", "+CP+", "+LOC+", "+PAIS+", "+NUM+", "+TEL1+", "+TEL2+") values (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement p = conexion.prepareStatement(insert);
		p.setString(1, c.getNombre());
		p.setString(2, c.getDireccion());
		p.setInt(3, c.getCp());
		p.setString(4, c.getLocalidad());
		p.setString(5, c.getPais());
		p.setString(6, c.getNum());
		p.setString(7, c.getTel1());
		p.setString(8, c.getTel2());
		p.executeUpdate();
	}
	
	public void updateCliente(Cliente c) throws SQLException {
		String update = "update clientes set "+NOM+"= ?, "+DIR+"= ?, "+CP+"= ?, "+LOC+"= ?, "+PAIS+"= ?, "+NUM+" = ?, "+TEL1+"= ?,"+TEL2+"= ? where "+ID+" = "+c.getIdCliente()+";";
		PreparedStatement p = conexion.prepareStatement(update);
		p.setString(1, c.getNombre());
		p.setString(2, c.getDireccion());
		p.setInt(3, c.getCp());
		p.setString(4, c.getLocalidad());
		p.setString(5, c.getPais());
		p.setString(6, c.getNum());
		p.setString(7, c.getTel1());
		p.setString(8, c.getTel2());
		p.executeUpdate();
	}
	
	public static ClienteModel getInstance() {
		if (instance == null) { instance = new ClienteModel();}
		return instance;
	}
}
