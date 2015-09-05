package tanytrans.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tanytrans.controller.MainController;
import tanytrans.custom.CustomPanel;
import tanytrans.model.MiEmpresa;

public class DatosEmpresaFrame extends JDialog {

private static final Insets insets = new Insets(10,10,10,10);
	
	private Dimension size;
	private CustomPanel contentPane;
	private JTabbedPane tabbled;
	private JLabel lblNombre, lblDir, lblCp, lblLocalidad, lblPais, lblTel1, lblTel2, lblFax, lblEmail, lblProp, lblIban, lblBanco, lblNie;
	private JTextField nombre, dir, cp, localidad, pais, tel1, tel2, fax, email, propietario, iban, banco, nie;
	
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
		
		tabbled = new JTabbedPane();
		getContentPane().add("Tabs", tabbled);
		
		setPanelDatos();
		setPanelContacto();
		setPanelBanco();
		
		setVisible(true);
	}
	
	private void setPanelBanco() {
		MiEmpresa e = MiEmpresa.getInstance();
		
		JPanel panelBanco = new JPanel(new GridBagLayout());
		tabbled.add("Datos bancarios", panelBanco);
		
		lblProp = new JLabel("Titular:");
		panelBanco.add(lblProp, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		propietario = new JTextField(e.getPropietario());
		panelBanco.add(propietario, new GridBagConstraints(1, 0, 3, 1, 0.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblBanco = new JLabel("Sucursal:");
		panelBanco.add(lblBanco, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		banco = new JTextField(e.getBanco());
		panelBanco.add(banco, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblIban = new JLabel("IBAN:");
		panelBanco.add(lblIban, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		iban = new JTextField(e.getIban());
		panelBanco.add(iban, new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		JButton save = new JButton("Guardar");
		panelBanco.add(save, new GridBagConstraints(0, 2, 4, 1, 0.0, 1.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, insets, 0, 0));
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				e.setNombre(nombre.getText());
				e.setDireccion(dir.getText());
				e.setCp(Integer.parseInt(cp.getText()));
				e.setLocalidad(localidad.getText());
				e.setPais(pais.getText());
				e.setTel1(tel1.getText());
				e.setTel2(tel2.getText());
				e.setFax(fax.getText());
				e.setEmail(email.getText());
				e.setPropietario(propietario.getText());
				e.setIban(iban.getText());
				e.setBanco(banco.getText());
				e.setIdEmpresa(nie.getText());
				MainController.getInstance().updateDatosEmpresa();
			}
		});
	}
	
	private void setPanelContacto() {
		MiEmpresa e = MiEmpresa.getInstance();
		
		JPanel panelContacto = new JPanel(new GridBagLayout());
		tabbled.add("Contacto", panelContacto);
		
		lblTel1 = new JLabel("Telf 1:");
		panelContacto.add(lblTel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		tel1 = new JTextField(e.getTel1());
		panelContacto.add(tel1, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblTel2 = new JLabel("Telf 2");
		panelContacto.add(lblTel2, new GridBagConstraints(2, 0, 1, 1, 0.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		tel2 = new JTextField(e.getTel2());
		panelContacto.add(tel2, new GridBagConstraints(3, 0, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblFax = new JLabel("Fax:");
		panelContacto.add(lblFax, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		fax = new JTextField(e.getFax());
		panelContacto.add(fax, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblEmail = new JLabel("E-mail:");
		panelContacto.add(lblEmail, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		email = new JTextField(e.getEmail());
		panelContacto.add(email, new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
	
		JButton save = new JButton("Guardar");
		panelContacto.add(save, new GridBagConstraints(0, 3, 4, 1, 0.0, 1.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, insets, 0, 0));
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				e.setNombre(nombre.getText());
				e.setDireccion(dir.getText());
				e.setCp(Integer.parseInt(cp.getText()));
				e.setLocalidad(localidad.getText());
				e.setPais(pais.getText());
				e.setTel1(tel1.getText());
				e.setTel2(tel2.getText());
				e.setFax(fax.getText());
				e.setEmail(email.getText());
				e.setPropietario(propietario.getText());
				e.setIban(iban.getText());
				e.setBanco(banco.getText());
				e.setIdEmpresa(nie.getText());
				MainController.getInstance().updateDatosEmpresa();
			}
		});
	}
	
	private void setPanelDatos() {
		MiEmpresa e = MiEmpresa.getInstance();
		
		JPanel panelDatos = new JPanel(new GridBagLayout());
		tabbled.add("Datos", panelDatos);
		
		lblNombre = new JLabel("Empresa:");
		panelDatos.add(lblNombre, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		nombre = new JTextField(MiEmpresa.getInstance().getNombre());
		panelDatos.add(nombre, new GridBagConstraints(1, 0, 3, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblNie = new JLabel("NIE:");
		panelDatos.add(lblNie, new GridBagConstraints(4, 0, 1, 1, 0.0, 1.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		nie = new JTextField(e.getIdEmpresa());
		panelDatos.add(nie, new GridBagConstraints(5, 0, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblDir = new JLabel("Direccion:");
		panelDatos.add(lblDir, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		dir = new JTextField(e.getDireccion());
		panelDatos.add(dir, new GridBagConstraints(1, 1, 5, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblCp = new JLabel("Codigo postal:");
		panelDatos.add(lblCp, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		cp = new JTextField(e.getCp()+"");
		panelDatos.add(cp, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblLocalidad = new JLabel("Localidad:");
		panelDatos.add(lblLocalidad, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		localidad = new JTextField(e.getLocalidad());
		panelDatos.add(localidad, new GridBagConstraints(3, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblPais = new JLabel("Pais:");
		panelDatos.add(lblPais, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		pais = new JTextField(e.getPais());
		panelDatos.add(pais, new GridBagConstraints(5, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		JButton save = new JButton("Guardar");
		panelDatos.add(save, new GridBagConstraints(0, 3, 6, 1, 0.0, 1.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, insets, 0, 0));
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				e.setNombre(nombre.getText());
				e.setDireccion(dir.getText());
				e.setCp(Integer.parseInt(cp.getText()));
				e.setLocalidad(localidad.getText());
				e.setPais(pais.getText());
				e.setTel1(tel1.getText());
				e.setTel2(tel2.getText());
				e.setFax(fax.getText());
				e.setEmail(email.getText());
				e.setPropietario(propietario.getText());
				e.setIban(iban.getText());
				e.setBanco(banco.getText());
				e.setIdEmpresa(nie.getText());
				MainController.getInstance().updateDatosEmpresa();
			}
		});
	}
}
