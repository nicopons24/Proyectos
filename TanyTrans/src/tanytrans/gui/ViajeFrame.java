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
import javax.swing.JSpinner;
import javax.swing.JTextField;

import tanytrans.custom.CustomPanel;

public class ViajeFrame extends JDialog {
	
	private static final Insets insets = new Insets(10,10,10,10);
	
	private Dimension size;
	private CustomPanel contentPane;
	private JPanel panel;
	private JLabel lblCod, lblMatricula, lblPrecio, lblIva;
	private JTextField codigo, articulo, precio;
	private JSpinner cantidad;
	private JButton done, cancel;
	
	public ViajeFrame(Dimension parentSize, JFrame frame) {
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
	
	public void setData(Object o) {
		
	}
	
	private void setPanel() {
		panel = new JPanel(new GridBagLayout());
		getContentPane().add("panel", panel);
		
		lblCod = new JLabel("Ref:");
		panel.add(lblCod, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, insets, 0, 0));
		
		codigo = new JTextField();
		panel.add(codigo, new GridBagConstraints(1, 0, 2, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblMatricula = new JLabel("Matricula:");
		panel.add(lblMatricula, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		
		articulo = new JTextField();
		panel.add(articulo, new GridBagConstraints(1, 1, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblPrecio = new JLabel("Precio:");
		panel.add(lblPrecio, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, insets, 0, 0));
		
		precio = new JTextField();
		panel.add(precio, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		lblIva = new JLabel("Iva:");
		panel.add(lblIva, new GridBagConstraints(2, 2, 1, 1, 0.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, insets, 0, 0));
		
		cantidad = new JSpinner();
		panel.add(cantidad, new GridBagConstraints(3, 2, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		cancel = new JButton("Cancelar");
		panel.add(cancel, new GridBagConstraints(3, 3, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		
		done = new JButton("Aceptar");
		panel.add(done, new GridBagConstraints(4, 3, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
	}

}
