package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tanytrans.controller.ControladorPrincipal;
import tanytrans.model.Camion;
import tanytrans.model.Cliente;
import tanytrans.model.Empleado;

import com.toedter.calendar.JDateChooser;

public class PanelNuevoViaje extends JPanel{

	private Insets insets;
	private JTextField origen, destino;
	private JDateChooser fecha;
	private JSpinner curso, kilometro;
	private JComboBox<Cliente> cliente;
	private JComboBox<Empleado> empleado;
	private JComboBox<Camion> camion;
	private JTextArea transporte;
	private JButton guardar;

	public PanelNuevoViaje() {
		
		insets = new Insets(10, 10, 10, 10);
		setName("NuevoViaje");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		colocaComponentes();
	}
	
	public void cargaClientes(ArrayList<Cliente> clientes) {
		cliente.removeAll();
		cliente.addItem(null);
		for (Cliente c : clientes) {
			cliente.addItem(c);
		}
	}
	
	public void cargaCamiones(ArrayList<Camion> camiones) {
		camion.removeAll();
		camion.addItem(null);
		for (Camion c : camiones) {
			camion.addItem(c);
		}
	}
	
	public void cargaEmpleados(ArrayList<Empleado> empleados) {
		empleado.removeAll();
		empleado.addItem(null);
		for (Empleado e : empleados) {
			empleado.addItem(e);
		}
	}
	
	private void colocaComponentes() {
		
		JLabel newViaje = new JLabel("Nuevo viaje");
		GridBagConstraints gbc_newViaje = new GridBagConstraints(0, 0, 4, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(newViaje, gbc_newViaje);
		
		JLabel ori = new JLabel("Origen:");
		GridBagConstraints gbc_ori = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(ori, gbc_ori);
		
		JLabel des = new JLabel("Destino:");
		GridBagConstraints gbc_des = new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(des, gbc_des);
		
		JLabel sal = new JLabel("Fecha salida:");
		GridBagConstraints gbc_sal = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(sal, gbc_sal);
		
		JLabel km = new JLabel("Km:");
		GridBagConstraints gbc_km = new GridBagConstraints(2, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(km, gbc_km);
		
		JLabel cli = new JLabel("Cliente:");
		GridBagConstraints gbc_cli = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(cli, gbc_cli);
		
		JLabel cur = new JLabel("Curso:");
		GridBagConstraints gbc_cur = new GridBagConstraints(2, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(cur, gbc_cur);
		
		JLabel con = new JLabel("Conductor:");
		GridBagConstraints gbc_con = new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(con, gbc_con);
		
		JLabel cam = new JLabel("Camión:");
		GridBagConstraints gbc_cam = new GridBagConstraints(2, 4, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
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
		
		kilometro = new JSpinner();
		GridBagConstraints gbc_kilometro = new GridBagConstraints(3, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(kilometro, gbc_kilometro);
		
		cliente = new JComboBox<Cliente>();
		GridBagConstraints gbc_cliente = new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(cliente, gbc_cliente);
		
		curso = new JSpinner();
		GridBagConstraints gbc_curso = new GridBagConstraints(3, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(curso, gbc_curso);
		
		empleado = new JComboBox<Empleado>();
		GridBagConstraints gbc_empleado = new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(empleado, gbc_empleado);
		
		camion = new JComboBox<Camion>();
		GridBagConstraints gbc_camion = new GridBagConstraints(3, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(camion, gbc_camion);
		
		transporte = new JTextArea();
		GridBagConstraints gbc_transporte = new GridBagConstraints(0, 6, 4, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		JScrollPane scroll = new JScrollPane(transporte, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll, gbc_transporte);
		
		guardar = new JButton("Guardar");
		GridBagConstraints gbc_guardar = new GridBagConstraints(3, 7, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		add(guardar, gbc_guardar);
		
	}
}
