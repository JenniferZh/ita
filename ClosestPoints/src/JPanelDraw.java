import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class JPanelDraw extends JPanel
{
  // PROPERTIES
  private final int DEFAULT_WIDTH  = 800;
  private final int DEFAULT_HEIGHT = 800;
  private final Color BACK_COLOR   = Color.WHITE;
  ClosestPointsAlgorithm algo;

  private int x1, y1;

  private MyMouseHandler handler;
  Graphics g;

  // CONSTRUCTOR
  public JPanelDraw()
  {
	algo = new ClosestPointsAlgorithm();
    setBackground( BACK_COLOR );
    setPreferredSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HEIGHT ) );

    handler  = new MyMouseHandler();

    this.addMouseListener( handler );
    this.addMouseMotionListener( handler );
  }



  public void setUpDrawingGraphics()
  {
    g = getGraphics();
  }

  // INNER CLASS
  private class MyMouseHandler extends MouseAdapter
  {
    public void mousePressed( MouseEvent e )
    {
      x1 = e.getX();
      y1 = e.getY();
      algo.pointSet.add(new Point(x1, y1));
      System.out.println(x1+" "+y1);


      setUpDrawingGraphics();
      
      g.drawOval(x1, y1, 5, 5);

    }
  }
}