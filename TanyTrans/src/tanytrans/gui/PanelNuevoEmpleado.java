package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelNuevoEmpleado extends JPanel {
	
	private Insets insets;
	private JTextField nombre, apellidos, docIde, telefono;
	private JButton guardar;

	public PanelNuevoEmpleado() {
		
		insets = new Insets(10, 10, 10, 10);
		setName("NuevoEmpleado");
		setLayout(new GridBagLayout());
		
		colocaComponentes();
		
	}

	private void colocaComponentes() {
		
		JLabel newEmpl = new JLabel("Nuevo empleado");
		GridBagConstraints gbc_newEmpl = new GridBagConstraints(0, 0, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(newEmpl, gbc_newEmpl);
		
		JLabel nom = new JLabel("Nombre:");
		GridBagConstraints gbc_nom = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(nom, gbc_nom);
		
		JLabel ape = new JLabel("Apellidos:");
		GridBagConstraints gbc_ape = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(ape, gbc_ape);
		
		JLabel dni = new JLabel("DNI:");
		GridBagConstraints gbc_dni = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(dni, gbc_dni);
		
		JLabel tel = new JLabel("Telefono:");
		GridBagConstraints gbc_tel = new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(tel, gbc_tel);
		
		nombre = new JTextField();
		GridBagConstraints gbc_nombre = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(nombre, gbc_nombre);
		
		apellidos = new JTextField();
		GridBagConstraints gbc_apellidos = new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(apellidos, gbc_apellidos);
		
		docIde = new JTextField();
		GridBagConstraints gbc_deoIde = new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(docIde, gbc_deoIde);
		
		telefono = new JTextField();
		GridBagConstraints gbc_telefono = new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(telefono, gbc_telefono);
		
		guardar = new JButton("Guardar");
		GridBagConstraints gbc_guardar = new GridBagConstraints(2, 5, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		add(guardar, gbc_guardar);
	}
}
