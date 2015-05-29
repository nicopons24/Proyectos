package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelNuevoCliente extends JPanel {

	private Insets insets;
	private JTextField nombre, direccion, telefono1, telefono2;
	private JButton guardar;

	public PanelNuevoCliente() {
		
		insets = new Insets(10, 10, 10, 10);
		setName("NuevoCliente");
		setLayout(new GridBagLayout());
		
		colocaComponentes();
		
	}

	private void colocaComponentes() {
		
		JLabel newCli = new JLabel("Nuevo cliente");
		GridBagConstraints gbc_newCli = new GridBagConstraints(0, 0, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(newCli, gbc_newCli);
		
		JLabel nom = new JLabel("Nombre:");
		GridBagConstraints gbc_nom = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(nom, gbc_nom);
		
		JLabel dir = new JLabel("Dirección:");
		GridBagConstraints gbc_dir = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(dir, gbc_dir);
		
		JLabel tel1 = new JLabel("Teléfono 1:");
		GridBagConstraints gbc_tel1 = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(tel1, gbc_tel1);
		
		JLabel tel2 = new JLabel("Teléfono 2:");
		GridBagConstraints gbc_tel2 = new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(tel2, gbc_tel2);
		
		nombre = new JTextField();
		GridBagConstraints gbc_nombre = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(nombre, gbc_nombre);
		
		direccion = new JTextField();
		GridBagConstraints gbc_direccion = new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(direccion, gbc_direccion);
		
		telefono1 = new JTextField();
		GridBagConstraints gbc_telefono1 = new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(telefono1, gbc_telefono1);
		
		telefono2 = new JTextField();
		GridBagConstraints gbc_telefono2 = new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(telefono2, gbc_telefono2);
		
		guardar = new JButton("Guardar");
		GridBagConstraints gbc_guardar = new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		add(guardar, gbc_guardar);
	}
	
}
