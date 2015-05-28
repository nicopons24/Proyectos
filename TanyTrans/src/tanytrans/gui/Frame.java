package tanytrans.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tanytrans.custom.TPanel;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
	
	private PEmpleados empleados;
	
	public Frame() {
		
		setContentPane(new TPanel());
		
		setSize(800, 600);
		setTitle("TANYTRANS");
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new CardLayout(20, 20));
		
		colocaComponentes();
		
		setVisible(true);
	}
	
	private void colocaComponentes() {
		empleados = new PEmpleados();
		getContentPane().add(empleados,"EMPLEADOS");
	}

}
