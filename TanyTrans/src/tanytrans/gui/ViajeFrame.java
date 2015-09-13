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
import javax.swing.JTextField;

import tanytrans.config.Calculos;
import tanytrans.controller.MainController;
import tanytrans.custom.CustomPanel;
import tanytrans.model.Viaje;

public class ViajeFrame extends JDialog {
	
	private static final Insets insets = new Insets(10,10,10,10);
	
	private boolean editable;
	private int pos, numFactura, id;
	private Dimension size;
	private CustomPanel contentPane;
	private JPanel panel;
	private JLabel lblCod, lblMatricula, lblPrecio, lblIva;
	private JTextField codigo, articulo, precio, iva;
	private JButton done, cancel;
	
	public ViajeFrame(Dimension parentSize, JFrame frame) {
		super(frame, true);
		size = new Dimension((int) parentSize.getWidth()/3,(int) parentSize.getHeight()/3);
		contentPane = new CustomPanel();
		editable = false;
		
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
	
	public ViajeFrame(Dimension parentSize, JFrame frame, Viaje v, int pos) {
		super(frame, true);
		size = new Dimension((int) parentSize.getWidth()/3,(int) parentSize.getHeight()/3);
		contentPane = new CustomPanel();
		editable = true;
		numFactura = v.getNumFactura();
		id = v.getId();
		this.pos = pos;
		
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
		setData(v);
		
		setVisible(true);
	}
	
	private void setData(Viaje v) {
		codigo.setText(v.getRefViaje()+"");
		articulo.setText(v.getMatricula());
		precio.setText(v.getPrecio()+"");
		iva.setText(v.getIva()+"");
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
		
		iva = new JTextField();
		panel.add(iva, new GridBagConstraints(3, 2, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
		
		cancel = new JButton("Cancelar");
		panel.add(cancel, new GridBagConstraints(3, 3, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0));
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		done = new JButton("Aceptar");
		panel.add(done, new GridBagConstraints(4, 3, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
		done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Viaje v = new Viaje();
				v.setNumFactura(numFactura);
				if (id != -1) {
					v.setId(id);
				}
				v.setRefViaje(codigo.getText());
				v.setMatricula(articulo.getText());
				if (Calculos.isNumeric(iva.getText())) {v.setIva(Double.parseDouble(iva.getText())); }
				if (Calculos.isNumeric(precio.getText())) { v.setPrecio(Double.parseDouble(precio.getText()));}
				if (editable) {MainController.getInstance().editViajeFactura(pos, v);}
				else{MainController.getInstance().addViajeFactura(v);}
				dispose();
			}
		});
	}

}
