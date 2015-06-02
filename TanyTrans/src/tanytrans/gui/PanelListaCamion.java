package tanytrans.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tanytrans.controller.ControladorPrincipal;
import tanytrans.model.Camion;
import tanytrans.tablemodel.ModeloTablaCamion;

public class PanelListaCamion extends JPanel {
	
	private Insets insets;
	private JButton editar, eliminar, guardar, cancelar;
	private JTable tabla;
	private JPanel panelEditar;
	private JTextField marca, modelo, matricula, chasis;
	
	public PanelListaCamion(){
		
		insets = new Insets(10, 10, 10, 10);
		setName("ListaCamion");
		setLayout(new GridBagLayout());
		
		colocaComponentes();
		
	}

	private void colocaComponentes() {
		
		JLabel l = new JLabel("Lista camiones:");
		GridBagConstraints gbc_l = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		add(l, gbc_l);
		
		editar = new JButton("Editar");
		editar.setEnabled(false);
		GridBagConstraints gbc_editar = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		add(editar, gbc_editar);
		editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					ControladorPrincipal.getInstance().datosCamion(rowSelected);
					panelEditar.setVisible(true);
					guardar.setVisible(true);
					cancelar.setVisible(true);
				}
			}
		});
		
		eliminar = new JButton("Eliminar");
		eliminar.setEnabled(false);
		GridBagConstraints gbc_eliminar = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		add(eliminar, gbc_eliminar);
		eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					ControladorPrincipal.getInstance().eliminaCamion(rowSelected);
					tabla.clearSelection();
				}
			}
		});
		
		tabla = new JTable();
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		GridBagConstraints gbc_tabla = new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll, gbc_tabla);
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					editar.setEnabled(true);
					eliminar.setEnabled(true);
				}
				else {
					editar.setEnabled(false);
					eliminar.setEnabled(false);
				}
				panelEditar.setVisible(false);
				guardar.setVisible(false);
				cancelar.setVisible(false);
			}
		});
		
		panelEditar = new JPanel();
		panelEditar.setLayout(new GridBagLayout());
		panelEditar.setVisible(false);
		GridBagConstraints gbc_panelEditar = new GridBagConstraints(0, 2, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		add(panelEditar, gbc_panelEditar);
		
		JLabel mar = new JLabel("Marca:");
		GridBagConstraints gbc_mar = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(mar, gbc_mar);
		
		JLabel mod = new JLabel("Modelo:");
		GridBagConstraints gbc_mod = new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(mod, gbc_mod);
		
		JLabel mat = new JLabel("Matrícula:");
		GridBagConstraints gbc_mat = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(mat, gbc_mat);
		
		JLabel nchas = new JLabel("Nº Chasis:");
		GridBagConstraints gbc_nchas = new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(nchas, gbc_nchas);
		
		marca = new JTextField();
		GridBagConstraints gbc_marca = new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(marca, gbc_marca);
		
		modelo = new JTextField();
		GridBagConstraints gbc_modelo = new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(modelo, gbc_modelo);
		
		matricula = new JTextField();
		GridBagConstraints gbc_matricula = new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(matricula, gbc_matricula);
		
		chasis = new JTextField();
		GridBagConstraints gbc_chasis = new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0);
		panelEditar.add(chasis, gbc_chasis);
		
		cancelar = new JButton("Cancelar");
		cancelar.setVisible(false);
		GridBagConstraints gbc_cancelar = new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		add(cancelar, gbc_cancelar);
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					panelEditar.setVisible(false);
					cancelar.setVisible(false);
					guardar.setVisible(false);
					tabla.clearSelection();
				}
			}
		});
		
		guardar = new JButton("Guardar");
		guardar.setVisible(false);
		GridBagConstraints gbc_guardar = new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		add(guardar, gbc_guardar);
		guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tabla.getSelectedRow();
				if (rowSelected != -1) {
					ControladorPrincipal.getInstance().actualizaCamion(rowSelected);
					panelEditar.setVisible(false);
					cancelar.setVisible(false);
					guardar.setVisible(false);
					tabla.clearSelection();
				}
			}
		});
		
	}
	
	public void setDatos(Camion c) {
		marca.setText(c.getMarca());
		modelo.setText(c.getModelo());
		matricula.setText(c.getMatricula());
		chasis.setText(c.getChasis());
	}
	
	public Camion getDatos() {
		String mar = marca.getText();
		String mod =modelo.getText();
		String mat = matricula.getText();
		String nchas = chasis.getText();
		return new Camion(-1, mar, mod, mat, nchas);
	}
	
	public void setTableModel(ArrayList<Camion> camiones) {
		tabla.setModel(new ModeloTablaCamion(camiones));
	}

	public ModeloTablaCamion getTableModel() {
		return (ModeloTablaCamion) tabla.getModel();
	}
}
