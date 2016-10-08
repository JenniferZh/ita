import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AlgoFrame extends JFrame{
	Container p;
	private JPanel buttonpanel;
	private JPanelDraw draw;
	private JButton jbnButtons[];
	
	
	public AlgoFrame() {
		p = getContentPane();      
		setTitle("Closest Pair of Points");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.white);
		

		
		
		jbnButtons = new JButton[3];
		jbnButtons[0] = new JButton("Clear");
		jbnButtons[1] = new JButton("Compute");
		jbnButtons[2] = new JButton("Random");
		
		buttonpanel = new JPanel();
		for(JButton jbn: jbnButtons)
			buttonpanel.add(jbn);
		
		draw = new JPanelDraw();
		
		ActionListener clearListener = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	draw.algo.pointSet = new ArrayList<Point>();
            	draw.g.clearRect(0, 0, 800, 800);
            }
        };
        
        ActionListener computeListener = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	Collections.sort(draw.algo.pointSet);
                Pair tmp = draw.algo.ClosetPair(0, draw.algo.pointSet.size());
                draw.g.drawLine((int)(tmp.a.x+2.5), (int)(tmp.a.y+2.5), (int)(tmp.b.x+2.5), (int)(tmp.b.y+2.5));
            }
        };
        
        ActionListener randomListener = new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String inputValue = JOptionPane.showInputDialog("随机生成点集个数(2-100)");
        		int num = Integer.parseInt(inputValue);
        		draw.algo.generate(num);
        		if(draw.g == null) draw.setUpDrawingGraphics();
        		for(Point tmp: draw.algo.pointSet) {
        			draw.g.drawOval((int)tmp.x, (int)tmp.y, 5, 5);
        		}
        		
        	}
        };
        jbnButtons[0].addActionListener(clearListener);
        jbnButtons[1].addActionListener(computeListener);
        jbnButtons[2].addActionListener(randomListener);
		
		
		p.add(buttonpanel, BorderLayout.NORTH);
		p.add(draw, BorderLayout.CENTER);
	}
	

	
	public static void main(String[] args) {
		AlgoFrame f = new AlgoFrame();
		f.setVisible(true);
	}

}
