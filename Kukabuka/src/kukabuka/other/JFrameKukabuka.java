package kukabuka.other;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;

public class JFrameKukabuka extends JFrame{
	public JFrameKukabuka(){
		setUndecorated(true);
		getContentPane().setLayout(null);
		
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(), 70, 70));
            }
        });
	}
	
	public void setTitles(){
		
	}
}
