package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelNuevoCamion extends JPanel {

	private Insets insets;
	private JTextField marca, modelo, matricula, chasis;
	private JButton guardar;

	public PanelNuevoCamion() {
		
		insets = new Insets(10, 10, 10, 10);
		setName("NuevoCamion");
		setLayout(new GridBagLayout());
		
		colocaComponentes();
		
	}

	private void colocaComponentes() {
		
		JLabel newCam = new JLabel("Nuevo camión");
		GridBagConstraints gbc_newCam = new GridBagConstraints(0, 0, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(newCam, gbc_newCam);
		
		JLabel mar = new JLabel("Marca:");
		GridBagConstraints gbc_mar = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(mar, gbc_mar);
		
		JLabel mod = new JLabel("Modelo:");
		GridBagConstraints gbc_mod = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(mod, gbc_mod);
		
		JLabel mat = new JLabel("Matrícula:");
		GridBagConstraints gbc_mat = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(mat, gbc_mat);
		
		JLabel chas = new JLabel("Nº Chasis:");
		GridBagConstraints gbc_chas = new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(chas, gbc_chas);
		
		marca = new JTextField();
		GridBagConstraints gbc_marca = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(marca, gbc_marca);
		
		modelo = new JTextField();
		GridBagConstraints gbc_modelo = new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(modelo, gbc_modelo);
		
		matricula = new JTextField();
		GridBagConstraints gbc_matricula = new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(matricula, gbc_matricula);
		
		chasis = new JTextField();
		GridBagConstraints gbc_chasis = new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(chasis, gbc_chasis);
		
		guardar = new JButton("Guardar");
		GridBagConstraints gbc_guardar = new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		add(guardar, gbc_guardar);
	}
	
}
