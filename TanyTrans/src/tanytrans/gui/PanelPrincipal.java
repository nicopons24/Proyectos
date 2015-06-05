package tanytrans.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tanytrans.controller.ControladorPrincipal;

public class PanelPrincipal extends JPanel {

	private Insets insets = new Insets(10, 10, 10, 10);
	private ImageIcon logo = new ImageIcon(getClass().getResource("/tanytrans/images/TanytransLogo.png"));
	private JPanel menu, eleccion, panelListas, panelNuevo;
	private JButton listas, nuevo, listaViajes, listaClientes, listaEmpleados,
			listaCamiones, nuevoViaje, nuevoCliente, nuevoEmpleado,
			nuevoCamion;

	public PanelPrincipal() {

		setName("MenuPrincipal");
		setLayout(new GridBagLayout());

		redimensionaLogo();
		colocaComponentes();

	}

	private void colocaComponentes() {

		JLabel icono = new JLabel(logo);
		GridBagConstraints gbc_icono = new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);
		add(icono, gbc_icono);

		menu = new JPanel();
		menu.setLayout(new GridBagLayout());
		JScrollPane scrollMenu = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_menu = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		add(scrollMenu, gbc_menu);

		listas = new JButton("Listas >");
		GridBagConstraints gbc_listas = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		menu.add(listas, gbc_listas);
		listas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().cambiaMenu(panelListas.getName());
				eleccion.setVisible(true);
			}
		});

		nuevo = new JButton("Nuevo >");
		GridBagConstraints gbc_nuevo = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		menu.add(nuevo, gbc_nuevo);
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorPrincipal.getInstance().cambiaMenu(panelNuevo.getName());
				eleccion.setVisible(true);
			}
		});

		eleccion = new JPanel();
		eleccion.setLayout(new CardLayout());
		eleccion.setVisible(false);
		GridBagConstraints gbc_eleccion = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		add(eleccion, gbc_eleccion);

		panelListas = new JPanel();
		panelListas.setName("PanelListas");
		JScrollPane scrollListas = new JScrollPane(panelListas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelListas.setLayout(new GridBagLayout());
		eleccion.add(panelListas.getName(), scrollListas);

		listaViajes = new JButton("Viajes >");
		GridBagConstraints gbc_listaViajes = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelListas.add(listaViajes, gbc_listaViajes);
		listaViajes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal frame = ControladorPrincipal.getInstance().getvPrincipal();
				ControladorPrincipal.getInstance().cambiaPanel(frame.getPanelListaViajes().getName());
			}
		});

		listaClientes = new JButton("Clientes >");
		GridBagConstraints gbc_listaClientes = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelListas.add(listaClientes, gbc_listaClientes);
		listaClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal frame = ControladorPrincipal.getInstance().getvPrincipal();
				ControladorPrincipal.getInstance().cambiaPanel(frame.getPanelListaClientes().getName());
			}
		});

		listaEmpleados = new JButton("Empleados >");
		GridBagConstraints gbc_listaEmpleados = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelListas.add(listaEmpleados, gbc_listaEmpleados);
		listaEmpleados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal frame = ControladorPrincipal.getInstance().getvPrincipal();
				ControladorPrincipal.getInstance().cambiaPanel(frame.getPanelListaEmpleados().getName());
			}
		});

		listaCamiones = new JButton("Camiones >");
		GridBagConstraints gbc_listaCamiones = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelListas.add(listaCamiones, gbc_listaCamiones);
		listaCamiones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal frame = ControladorPrincipal.getInstance().getvPrincipal();
				ControladorPrincipal.getInstance().cambiaPanel(frame.getPanelListaCamiones().getName());
			}
		});

		panelNuevo = new JPanel();
		panelNuevo.setName("PanelNuevo");
		JScrollPane scrollNuevo = new JScrollPane(panelNuevo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelNuevo.setLayout(new GridBagLayout());
		eleccion.add(panelNuevo.getName(), scrollNuevo);

		nuevoViaje = new JButton("Viaje");
		GridBagConstraints gbc_nuevoViaje = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelNuevo.add(nuevoViaje, gbc_nuevoViaje);
		nuevoViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal frame = ControladorPrincipal.getInstance().getvPrincipal();
				ControladorPrincipal.getInstance().cambiaPanel(frame.getPanelNuevoViaje().getName());
			}
		});

		nuevoCliente = new JButton("Cliente");
		GridBagConstraints gbc_nuevoCleinte = new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelNuevo.add(nuevoCliente, gbc_nuevoCleinte);
		nuevoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal frame = ControladorPrincipal.getInstance().getvPrincipal();
				ControladorPrincipal.getInstance().cambiaPanel(frame.getPanelNuevoCliente().getName());
			}
		});
		

		nuevoEmpleado = new JButton("Empleado");
		GridBagConstraints gbc_nuevoEmpleado = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelNuevo.add(nuevoEmpleado, gbc_nuevoEmpleado);
		nuevoEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal frame = ControladorPrincipal.getInstance().getvPrincipal();
				ControladorPrincipal.getInstance().cambiaPanel(frame.getPanelNuevoEmpleado().getName());
			}
		});

		nuevoCamion = new JButton("Camión");
		GridBagConstraints gbc_nuevoCamion = new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		panelNuevo.add(nuevoCamion, gbc_nuevoCamion);
		nuevoCamion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal frame = ControladorPrincipal.getInstance().getvPrincipal();
				ControladorPrincipal.getInstance().cambiaPanel(frame.getPanelNuevoCamion().getName());
			}
		});

	}

	private void redimensionaLogo() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		d.setSize(d.getWidth() / 2, d.getWidth() / 9);
		Image i = logo.getImage();
		i = i.getScaledInstance((int) d.getWidth() - 50, (int) d.getHeight(), Image.SCALE_SMOOTH);
		logo.setImage(i);
	}

	public JPanel getEleccion() {
		return eleccion;
	}

}