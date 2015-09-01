package tanytrans.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tanytrans.custom.CustomPanel;

public class DatosEmpresaFrame extends JDialog {

private static final Insets insets = new Insets(10,10,10,10);
	
	private Dimension size;
	private CustomPanel contentPane;
	private JPanel panel;
	private JLabel lblNombre, lblDir, lblCp, lblLocalidad, lblPais, lblTel1, lblTel2, lblFax, lblEmail;
	private JTextField nombre, dir, cp, localidad, pais, tel1, tel2, fax, email;
	private JButton edit, cancel, save;
	
	public DatosEmpresaFrame(Dimension parentSize, JFrame frame) {
		super(frame, true);
		size = new Dimension((int) parentSize.getWidth()/3,(int) parentSize.getHeight()/3);
		contentPane = new CustomPanel();
		
		setTitle("TanyTrans");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/tanytrans/images/CamionLogo.png")));
		
		setSize(size);
		setMinimumSize(new Dimension((int) parentSize.getWidth()/2, (int) parentSize.getHeight()/2));
		setMaximumSize(size);
		setResizable(true);
		setLocationRelativeTo(frame);
		
		setContentPane(contentPane);
		getContentPane().setLayout(new CardLayout(getWidth()/35, getHeight()/35));
		setPanel();
		
		setVisible(true);
	}
	
	private void setPanel() {
		panel = new JPanel(new GridBagLayout());
		getContentPane().add("DatosEmpresa", panel);
		
		JPanel panelDatos = new JPanel(new GridBagLayout());
		panel.add(panelDatos, new GridBagConstraints(0, 0, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
		
		lblNombre = new JLabel("Empresa:");
		panelDatos.add(lblNombre, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		nombre = new JTextField();
		panelDatos.add(nombre, new GridBagConstraints(1, 0, 3, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblDir = new JLabel("Direccion:");
		panelDatos.add(lblDir, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		dir = new JTextField();
		panelDatos.add(dir, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblCp = new JLabel("Codigo postal:");
		panelDatos.add(lblCp, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		cp = new JTextField();
		panelDatos.add(cp, new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblLocalidad = new JLabel("Localidad:");
		panelDatos.add(lblLocalidad, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		localidad = new JTextField();
		panelDatos.add(localidad, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblPais = new JLabel("Pais:");
		panelDatos.add(lblPais, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		pais = new JTextField();
		panelDatos.add(pais, new GridBagConstraints(3, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblTel1 = new JLabel("Telf 1:");
		panelDatos.add(lblTel1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		tel1 = new JTextField();
		panelDatos.add(tel1, new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblTel2 = new JLabel("Telf 2");
		panelDatos.add(lblTel2, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		tel2 = new JTextField();
		panelDatos.add(tel2, new GridBagConstraints(3, 3, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblFax = new JLabel("Fax:");
		panelDatos.add(lblFax, new GridBagConstraints(0, 4, 1, 1, 0.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		fax = new JTextField();
		panelDatos.add(fax, new GridBagConstraints(1, 4, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblEmail = new JLabel("E-mail:");
		panelDatos.add(lblEmail, new GridBagConstraints(2, 4, 1, 1, 0.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		email = new JTextField();
		panelDatos.add(email, new GridBagConstraints(3, 4, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		edit = new JButton("Editar");
		panel.add(edit, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, insets, 0, 0));
		
		cancel = new JButton("Cancelar");
		panel.add(cancel, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, insets, 0, 0));
	
		save = new JButton("Guardar");
		panel.add(save, new GridBagConstraints(2, 1, 1, 1, 0.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, insets, 0, 0));
	}
}
