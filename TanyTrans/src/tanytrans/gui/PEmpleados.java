package tanytrans.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.table.JTableHeader;

public class PEmpleados extends JTabbedPane{
	
	private JPanel lista, nuevo;
	private JTextField nombre, apellidos, docIde, telefono;
	private JButton editar, eliminar, guardar, borrar;
	private JTable tabla;

	public PEmpleados(){
		
		lista();
		nuevo();
		
		setVisible(true);
	}

	private void lista() {
		lista = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		lista.setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		margenesLista(gbc);
		
		JLabel l = new JLabel("Lista empleados:");
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.WEST;
		lista.add(l, gbc);
		
		editar = new JButton("Editar");
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		lista.add(editar, gbc);
		
		eliminar = new JButton("Eliminar");
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		lista.add(eliminar, gbc);
		
		tabla = new JTable(3, 4);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.BOTH;
		gbc.anchor = gbc.CENTER;
		JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lista.add(scroll, gbc);
		
		add("LISTA", lista);
		
	}
	
	private void nuevo() {
		nuevo = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		nuevo.setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		margenesNuevo(gbc);
		
		JLabel n = new JLabel("Nuevo empleado");
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(n, gbc);
		
		JLabel nom = new JLabel("    Nombre:");
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.WEST;
		nuevo.add(nom, gbc);
		
		JLabel ape = new JLabel("    Apellidos:");
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.WEST;
		nuevo.add(ape, gbc);
		
		JLabel dni = new JLabel("    DNI:");
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.WEST;
		nuevo.add(dni, gbc);
		
		JLabel tel = new JLabel("    Telefono:");
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.WEST;
		nuevo.add(tel, gbc);
		
		nombre = new JTextField(20);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(nombre, gbc);
		
		apellidos = new JTextField(20);
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(apellidos, gbc);
		
		docIde = new JTextField(20);
		gbc.gridx = 2;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(docIde, gbc);
		
		telefono = new JTextField(20);
		gbc.gridx = 2;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(telefono, gbc);
		
		borrar = new JButton("Borrar");
		gbc.gridx = 2;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(borrar, gbc);
		
		guardar = new JButton("Guardar");
		gbc.gridx = 3;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(guardar, gbc);
		
		add("NUEVO", nuevo);
	}
	
	private void margenesNuevo(GridBagConstraints gbc) {
		
		JLabel sep1 = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(sep1, gbc);
		
		JLabel sep2 = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(sep2, gbc);
		
		JLabel sep3 = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(sep3, gbc);
		
		JLabel sep4 = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(sep4, gbc);
		
		JLabel sep5 = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(sep5, gbc);
		
		JLabel btn = new JLabel(" ");
		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(btn, gbc);
		
		JLabel mSup = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(mSup, gbc);
		
		JLabel mInf = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(mInf, gbc);
		
		JLabel mIzq = new JLabel("        ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 13;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(mIzq,gbc);
		
		JLabel mDer = new JLabel("        ");
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 13;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		nuevo.add(mDer, gbc);
		
	}

	private void margenesLista(GridBagConstraints gbc) {
		
		JLabel sep = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		lista.add(sep, gbc);
		
		JLabel mSup = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		lista.add(mSup, gbc);
		
		JLabel mInf = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		lista.add(mInf, gbc);
		
		JLabel mIzq = new JLabel("        ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		lista.add(mIzq,gbc);
		
		JLabel mDer = new JLabel("        ");
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = gbc.NONE;
		gbc.anchor = gbc.CENTER;
		lista.add(mDer, gbc);
		
	}
}