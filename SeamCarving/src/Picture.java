import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture {
	BufferedImage img;
	
	public Picture(BufferedImage img) {
		this.img = img;
	}
	
	public Picture(String url) {
		img = null;
		try {
		    img = ImageIO.read(new File(url));
		   // System.out.println(img.getType());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getRow(){
		return img.getHeight();
	}
	public int getCol() {
		return img.getWidth();
	}

}
