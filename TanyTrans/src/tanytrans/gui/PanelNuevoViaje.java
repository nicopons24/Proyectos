package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class PanelNuevoViaje extends JPanel{

	private Insets insets;
	private JTextField origen;
	private JTextField destino;
	private JDateChooser fecha;

	public PanelNuevoViaje() {
		
		insets = new Insets(10, 10, 10, 10);
		setName("NuevoViaje");
		setLayout(new GridBagLayout());
		
		colocaComponentes();
		
	}
	
	private void colocaComponentes() {
		
		JLabel newViaje = new JLabel("Nuevo viaje");
		GridBagConstraints gbc_newViaje = new GridBagConstraints(0, 0, 4, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(newViaje, gbc_newViaje);
		
		JLabel ori = new JLabel("Origen:");
		GridBagConstraints gbc_ori = new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(ori, gbc_ori);
		
		JLabel des = new JLabel("Destino:");
		GridBagConstraints gbc_des = new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(des, gbc_des);
		
		JLabel sal = new JLabel("Fecha salida:");
		GridBagConstraints gbc_sal = new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(sal, gbc_sal);
		
		JLabel km = new JLabel("Km:");
		GridBagConstraints gbc_km = new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(km, gbc_km);
		
		JLabel cli = new JLabel("Cliente:");
		GridBagConstraints gbc_cli = new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(cli, gbc_cli);
		
		JLabel cur = new JLabel("Curso:");
		GridBagConstraints gbc_cur = new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(cur, gbc_cur);
		
		JLabel con = new JLabel("Conductor:");
		GridBagConstraints gbc_con = new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(con, gbc_con);
		
		JLabel cam = new JLabel("Camion:");
		GridBagConstraints gbc_cam = new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(cam, gbc_cam);
		
		JLabel tran = new JLabel("Transporte:");
		GridBagConstraints gbc_tran = new GridBagConstraints(0, 5, 4, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(tran, gbc_tran);
		
		origen = new JTextField();
		GridBagConstraints gbc_origen = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(origen, gbc_origen);
		
		destino = new JTextField();
		GridBagConstraints gbc_destino = new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(destino, gbc_destino);
		
		fecha = new JDateChooser();
		GridBagConstraints gbc_fecha = new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(fecha, gbc_fecha);
		
	}
}
