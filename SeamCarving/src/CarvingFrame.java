import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class BoundedChangeListener implements ChangeListener {
	JLabel p;
	public BoundedChangeListener(JLabel p) {
		this.p = p;
	}
		@Override
	  public void stateChanged(ChangeEvent changeEvent) {
	    Object source = changeEvent.getSource();
	    if (source instanceof BoundedRangeModel) {
	      BoundedRangeModel aModel = (BoundedRangeModel) source;
	      if (!aModel.getValueIsAdjusting()) {
	        System.out.println("Changed: " + aModel.getValue());
	      }
	    } else if (source instanceof JSlider) {
	      JSlider theJSlider = (JSlider) source;
	      if (!theJSlider.getValueIsAdjusting()) {
	    	  p.setText(Integer.toString(theJSlider.getValue())+"%");
	        System.out.println("Slider changed: " + theJSlider.getValue());
	      }
	    } else {
	      System.out.println("Something changed: " + source);
	    }
	  }


	}


public class CarvingFrame extends JFrame{
	JPanel function;
	JPanel display;
	
	JMenuBar jbar;
	JMenu jmenu;
	JMenuItem jmopen;
	JMenuItem jmsave;
	
	JPanel rbutton;
	JRadioButton vertical;
	JRadioButton horizontal;

	JSlider percent;
	JLabel per;
	
	JLabel image;
	Image dimg;
	
	int stripe;
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), 5);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

	
	public CarvingFrame() {
		stripe = 0;
		
		setTitle("SeamCarving");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLayout(new BorderLayout());
		
		function = new JPanel();
		Font font = new Font("Serif", Font.BOLD, 15);
		
		function.setLayout(new GridLayout(6,1));
		function.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		JLabel con = new JLabel("SeamCarving Configuration");
		con.setFont(font);
		function.add(con);
		JLabel dir = new JLabel("Direction");
		dir.setFont(font);
		function.add(dir);
		
		
		rbutton = new JPanel();
		vertical = new JRadioButton("Vertical");
		horizontal = new JRadioButton("Horizontal");
		ButtonGroup gp = new ButtonGroup();
		gp.add(horizontal);
		gp.add(vertical);	
		rbutton.add(vertical);
		rbutton.add(horizontal);
		function.add(rbutton);
		
		JPanel tmp = new JPanel();
		tmp.setLayout(new GridLayout(1,2));
		
		JLabel zip = new JLabel("Zip Percetage");
		zip.setFont(font);
		tmp.add(zip);
		per = new JLabel("0%");
		tmp.add(per);
		function.add(tmp);
		
		percent = new JSlider(JSlider.HORIZONTAL,0,100,1);
		percent.setMajorTickSpacing(100);
		percent.setMinorTickSpacing(10);
		percent.setPaintTicks(true);
		percent.setPaintLabels(true);
		percent.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));
       // Font font = new Font("Serif", Font.ITALIC, 15);
        percent.setFont(font);
        percent.addChangeListener(new BoundedChangeListener(per));
		function.add(percent);
		
		JPanel tmp2 = new JPanel();
		JButton submit = new JButton("Start!");
		tmp2.add(submit);
		function.add(tmp2);
		

		this.add(function,BorderLayout.WEST);
		
		image = new JLabel(" ");
		JPanel display = new JPanel();
		display.add(image);
		this.add(display,BorderLayout.CENTER);
		
		
		jbar = new JMenuBar();
		jmenu = new JMenu("File");
		jmopen = new JMenuItem("Open");
        jmopen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                    	BufferedImage img = ImageIO.read(file);
                    	
                    	int imgheight = img.getHeight();
                    	int imgwidth = img.getWidth();
                    	if(imgheight > imgwidth) 
                    		dimg =  img.getScaledInstance(-1, 500, Image.SCALE_SMOOTH);  
                    	else 
                    		dimg = img.getScaledInstance(500, -1, Image.SCALE_SMOOTH);
                    	image.setIcon(new ImageIcon(dimg));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        jmsave = new JMenuItem("Save");
        jmsave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	JFileChooser fc = new JFileChooser();
            	int retVal = fc.showSaveDialog(null);
            	if(retVal == JFileChooser.APPROVE_OPTION) {
            		BufferedImage im = toBufferedImage(dimg);
       		 		File f=fc.getSelectedFile();
       		 		try {
       		 			ImageIO.write(im, "png",f);
       		 		} catch (IOException e) {
       	            // TODO Auto-generated catch block
       		 			e.printStackTrace();
       		 		}
            	}
            }
        });
		jmenu.add(jmopen);
		jmenu.add(jmsave);
		jbar.add(jmenu);
		this.setJMenuBar(jbar);
		
		submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	String percent = per.getText();
            	percent = percent.replace("%", "");
            	int tmp = Integer.parseInt(percent);
            	
            	if(dimg == null) {
            		JOptionPane  
                    .showMessageDialog(  
                        null,  "Please upload a picture", "Warning",  
                        JOptionPane.ERROR_MESSAGE);  
            	}
            	
            	else {
    
            		int height = dimg.getHeight(null);
            		int width = dimg.getWidth(null);
            	
            		Picture a = new Picture(toBufferedImage(dimg));
            		SeamCarver car = new SeamCarver(a);
            	
            		if(vertical.isSelected()) {
            			int strip = width*tmp/100;
            			BufferedImage t = car.zipImage(strip);
            			dimg = t;
            			image.setIcon(new ImageIcon(dimg));
            		} else if(horizontal.isSelected()) {
            			int strip = height*tmp/100;
            			BufferedImage t = car.zipImageHori(100);
            			dimg = t;
            			image.setIcon(new ImageIcon(dimg));
            		
            		} else {
            			JOptionPane  
                        .showMessageDialog(  
                            null,  "Please select vertical/horizontal", "Warning",  
                            JOptionPane.ERROR_MESSAGE);  
            		}
            	}
            	
        
            }

        });
	
		
		setVisible(true);
		

	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CarvingFrame();
            }
        });
	}


}///:~