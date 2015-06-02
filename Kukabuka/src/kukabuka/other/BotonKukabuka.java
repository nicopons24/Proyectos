package kukabuka.other;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import kukabuka.font.FuenteKukabuka;

public class BotonKukabuka extends JButton{
	
	private static final Font FONT = new FuenteKukabuka().setFontType(Font.PLAIN, 12.0f);
	
	private static final Color COLORFONDO = new Color (236, 239, 241);
	private static final Color COLORPRESS = new Color (176, 190, 197);; 
	private static final int F = 20; 
	
	public BotonKukabuka(String text){
	        super(text); 
	        setFont(FONT);
	        setForeground(Color.BLACK);
	        setContentAreaFilled(false);
	        setFocusPainted(false);
			setBackground(Color.WHITE);
	}

	@Override 
    protected void paintComponent(Graphics g) { 
        if (getModel().isArmed()) { 
            g.setColor(COLORPRESS); 
        } else { 
            g.setColor(COLORFONDO); 
        } 
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, F, F); 
        super.paintComponent(g);
    }
	
	@Override 
    protected void paintBorder(Graphics g) { 
		if(getModel().isArmed()) { 
            g.setColor(Color.BLACK);
            setForeground(Color.WHITE);
        } else { 
            g.setColor(Color.BLACK);
            setForeground(Color.BLACK);
        }
		if(getModel().isRollover()){
        	g.setColor(Color.WHITE);
        }
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, F, F); 
	}
	
	Shape figura; 

    @Override 
    public boolean contains(int x, int y) { 
        figura = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), F, F); 
        return (figura.contains(x, y)); 
    }
}
