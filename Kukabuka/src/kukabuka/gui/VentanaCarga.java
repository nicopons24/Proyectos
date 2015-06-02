package kukabuka.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kukabuka.controller.ControladorPrincipal;
import kukabuka.font.FuenteKukabuka;

public class VentanaCarga extends JWindow {
	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	private int duration;
	private VentanaLogin vl;
	private Dimension di;
	private int minValue = 0;
	private int maxValue = 100;
	private int counter = 0;
	private JProgressBar progressBar;
	
	public VentanaCarga(int d) {
		
		duration = d;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		UIManager.put("ProgressBar.selectionBackground", Color.BLACK);
	    UIManager.put("ProgressBar.selectionForeground", Color.WHITE);
	    UIManager.put("ProgressBar.foreground", Color.RED);

	    progressBar = new JProgressBar();
	    progressBar.setMinimum(minValue);
	    progressBar.setMaximum(maxValue);
	    progressBar.setStringPainted(true);
	    progressBar.setBorderPainted(true);
	    progressBar.setFont(FONT);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 100, 100));
			}
		});
		
	   addWindowListener(new WindowListener() {
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			        Thread runner = new Thread() {
			          public void run() {
			            counter = minValue;
			            while (counter <= maxValue) {
			              Runnable runme = new Runnable() {
			                public void run() {
			                  progressBar.setValue(counter);
			                }
			              };
			              SwingUtilities.invokeLater(runme);
			              counter+=2;
			              if(counter >= 0 && counter < 25){
			            	  progressBar.setString("Limpiando las sartenes...");
			              }
			              if(counter >= 25 && counter < 50){
			            	  progressBar.setString("Encendiendo los fogones...");
			              }
			              if(counter >= 50 && counter < 75){
			            	  progressBar.setString("Poniendo la mesa...");
			              }
			              if(counter >= 75 && counter < 100){
			            	  progressBar.setString("Llamando a Chicote...");
			              }
			              try {
			                Thread.sleep(100);
			              } catch (Exception ex) {
			              }
			            }
			          }
			        };
			        runner.start();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	    });
	}

	public void showSplash() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(new Color(255, 255, 255));

		content.add(progressBar, BorderLayout.SOUTH);
		JLabel label = new JLabel(new ImageIcon((this.getClass().getResource("/kukabuka/images/logoKukabuka.png"))));
		content.add(label, BorderLayout.CENTER);
		
		int width = 500;
		int height = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);
		
		setVisible(true);

		try {
			Thread.sleep(duration);
			ControladorPrincipal.getInstance().ventanaLogin();
		} catch (Exception e) {
		}

		setVisible(false);

	}

	public void showSplashAndExit() {
		showSplash();
		dispose();
	}
}