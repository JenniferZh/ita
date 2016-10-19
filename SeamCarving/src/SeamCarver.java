import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SeamCarver {
	Picture pic;
	int row;
	int col;
	double[][] EnergyMap;
	double[][] dpMap;
	
	public SeamCarver(Picture picture) {
		pic = picture;
		row = pic.getRow();
		col = pic.getCol();
		EnergyMap = new double[row][col];
	}
	
	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		//System.out.println(x+" "+y);

		Color up = new Color(pic.img.getRGB((x-1+col)%col, y));
		Color down = new Color(pic.img.getRGB((x+1)%col, y));
		Color left = new Color(pic.img.getRGB(x, (y-1+row)%row));
		Color right = new Color(pic.img.getRGB(x, (y+1)%row));
		
		double xdiff = (right.getRed()-left.getRed())*(right.getRed()-left.getRed()) + (right.getGreen()-left.getGreen())*(right.getGreen()-left.getGreen()) + (right.getBlue()-left.getBlue())*(right.getBlue()-left.getBlue());
		double ydiff = (up.getRed()-down.getRed())*(up.getRed()-down.getRed())+(up.getGreen()-down.getGreen())*(up.getGreen()-down.getGreen())+(up.getBlue()-down.getBlue())*(up.getBlue()-down.getBlue());
		
		return xdiff+ydiff;
	}
	
	public void showgray() {
		BufferedImage im = new BufferedImage(col,row,BufferedImage.TYPE_BYTE_GRAY);
		 WritableRaster raster = im.getRaster();
		 double max = -1;
		 for(int i=0;i<row;i++)
		     for(int j=0;j<col;j++) {
		    	 if(EnergyMap[i][j] > max) max = EnergyMap[i][j];
		     }
		 for(int i=0;i<row;i++) {
		     for(int j=0;j<col;j++) {
		    	 int gray = (int)((((double)EnergyMap[i][j])/max)*255);
		    	 gray = 255- gray;
		    	 //System.out.print(gray+" ");
		    	 int graylevel = (gray << 16) | (gray << 8) | gray;
		    	 im.setRGB(j, i, graylevel);
		     }
		    // System.out.println();
		 }
		 File f=new File("D:/test.png");
	        try {
	            ImageIO.write(im, "png",f);
	            im.flush();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		 //ImageIO.write(im, BufferedImage.TYPE_BYTE_GRAY, new File("result.jpg"));
		 
	}
	
	public void computeEnergy() {
		for(int i = 0; i < row; i++)
			for(int j = 0; j < col; j++) {
				EnergyMap[i][j] = energy(j, i);
			}
	}
	
	public void showEnergy() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(EnergyMap[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public double getMinValue(double[] numbers){  
		double minValue = numbers[0];
		for(int i=0;i<numbers.length;i++){  
			if(numbers[i] < minValue){  
				minValue = numbers[i];
			}  
		}
		return minValue;  
	}
	
	public int getMinIndex(double[] numbers){  
		double minValue = numbers[0];
		int minIndex = 0;
		for(int i=0; i<numbers.length; i++){  
			if(numbers[i] < minValue){  
				minValue = numbers[i];
				minIndex = i;
			}  
		}
		return minIndex;  
	} 
	
	public double[][] culmulateEnergy() {
		double[][] dpEnergyMap = new double[row][col];
		for(int i = 0; i < col; i++)
			dpEnergyMap[0][i] = EnergyMap[0][i];
	
		for(int i = 1; i < row; i++) {
			for(int j = 0; j < col ; j++) {
				
				if(j == 0) {
					dpEnergyMap[i][0] = Math.max(dpEnergyMap[i-1][0],dpEnergyMap[i-1][1])+EnergyMap[i][0];
					continue;
				} else if( j != col-1) {
				
					double[] temparr = new double[3];
					temparr[0] = dpEnergyMap[i-1][j-1];
					temparr[1] = dpEnergyMap[i-1][j];
					temparr[2] = dpEnergyMap[i-1][j+1];
					dpEnergyMap[i][j] = getMinValue(temparr) + EnergyMap[i][j];		
				} else {
					dpEnergyMap[i][col-1] = Math.max(dpEnergyMap[i-1][col-1],dpEnergyMap[i-1][col-2])+EnergyMap[i][col-1];
					continue;
				}
			}
		}
		dpMap = dpEnergyMap;
		return dpEnergyMap;
	}
	
	public void culmulateEnergyHori() {
		double[][] dpEnergyMap = new double[row][col];
		for(int i = 0; i < row; i++)
			dpEnergyMap[i][0] = EnergyMap[i][0];
		for(int i = 1; i < col; i++) {
			for(int j = 0; j < row; j++) {
				if(j == 0) dpEnergyMap[0][i] = Math.max(dpEnergyMap[0][i-1], dpEnergyMap[1][i-1])+EnergyMap[0][i];
				else if(j != row-1) {
					double[] temparr = new double[3];
					temparr[0] = dpEnergyMap[j-1][i-1];
					temparr[1] = dpEnergyMap[j][i-1];
					temparr[2] = dpEnergyMap[j+1][i-1];
					dpEnergyMap[j][i] = getMinValue(temparr) + EnergyMap[j][i];		
				} else {
					dpEnergyMap[row-1][i] = Math.max(dpEnergyMap[row-1][i-1], dpEnergyMap[row-2][i-1])+EnergyMap[row-1][i];
				}
			}
		}
		dpMap = dpEnergyMap;
			
	}
	
	public int[] findVerticalSeam() {
		//double[][] dpEnergyMap = culmulateEnergy();
		int index = getMinIndex(dpMap[row-1]);
		int[] path = new int[row];
		path[row - 1] = index;
		int ind_temp = 0;
		double[] temparr = new double[3];
		for (int i = row-1; i > 0; --i){
			if(path[i] - 1 < 0) temparr[0] = Double.MAX_VALUE;
			else temparr[0] = dpMap[i-1][path[i]-1];
			
			temparr[1] = dpMap[i-1][path[i]];
			
			if(path[i] + 1 > col-1) temparr[2] = Double.MAX_VALUE;
			else temparr[2] = dpMap[i-1][path[i]+1];
			
			ind_temp = getMinIndex(temparr);
			path[i-1] = path[i] + ind_temp - 1;
			if (path[i-1] <= 0){
				path[i-1] = 1;
			}
			else if (path[i-1] >= col-1){
				path[i-1] = col-2;
			}
		}
		return path;
	}
	
	public int[] findHorizontalSeam() {
		int[] path = new int[col];
		double[] lastcol = new double[row];
		for(int i = 0; i < row; i++) lastcol[i] = dpMap[i][col-1];
		path[col-1] = getMinIndex(lastcol);
		
		int ind_temp = 0;
		double[] temparr = new double[3];
		for (int i = col-1; i > 0; --i){
			if(path[i] - 1 < 0) temparr[0] = Double.MAX_VALUE;
			else temparr[0] = dpMap[path[i]-1][i-1];
			
			temparr[1] = dpMap[path[i]][i-1];
			
			if(path[i] + 1 > col-1) temparr[2] = Double.MAX_VALUE;
			else temparr[2] = dpMap[path[i]+1][i-1];
			
			ind_temp = getMinIndex(temparr);
			path[i-1] = path[i] + ind_temp - 1;
			if (path[i-1] <= 0){
				path[i-1] = 1;
			}
			else if (path[i-1] >= row-1){
				path[i-1] = row-2;
			}
		}
		return path;
	}
	
	public void removePath(int[] path){
		BufferedImage im = pic.img;
		BufferedImage im2 = new BufferedImage(col-1, row, im.getType());
		WritableRaster ra = im.getRaster();
		WritableRaster ra2 = im2.getRaster();
		for (int y = 0; y < row; ++y){
			for (int x = 0; x <= path[y]-1; ++x){
				dpMap[y][x] = dpMap[y][x];
				ra2.setSample(x, y, 0, ra.getSample(x, y, 0));
			}
			for (int x = path[y]; x < col-1; ++x){
				dpMap[y][x] = dpMap[y][x+1];
				ra2.setSample(x, y, 0, ra.getSample(x+1, y, 0));
			}
		}
		pic.img = im2;
		col--;
	}
	
	public void  removePathHori(int[] path) {
		BufferedImage im = pic.img;
		BufferedImage im2 = new BufferedImage(col, row-1, im.getType());
		WritableRaster ra = im.getRaster();
		WritableRaster ra2 = im2.getRaster();
		for (int y = 0; y < col; ++y){
			for (int x = 0; x <= path[y]-1; ++x){
				dpMap[x][y] = dpMap[x][y];
				ra2.setSample(y, x, 0, ra.getSample(y, x, 0));
			}
			for (int x = path[y]; x < row-1; ++x){
				dpMap[x][y] = dpMap[x+1][y];
				ra2.setSample(y, x, 0, ra.getSample(y, x+1, 0));
			}
		}
		pic.img = im2;
		row--;
	}
	
	public void showImage(String url) {
		BufferedImage im = pic.img;
		 WritableRaster raster = im.getRaster();
		 File f=new File(url);
	        try {
	            ImageIO.write(im, "png",f);
	            im.flush();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		 //ImageIO.write(im, BufferedImage.TYPE_BYTE_GRAY, new File("result.jpg"));
		 
	}
	
	public BufferedImage zipImage(int width) {
		computeEnergy();
		culmulateEnergy();
		for(int i = 0; i < width; i++) {
			int[] line = findVerticalSeam();
			removePath(line);
		}
		showImage("D:/test5.png");
		System.out.println("down");
		return pic.img;
	}
	
	public BufferedImage zipImageHori(int height) {
		computeEnergy();
		culmulateEnergyHori();
		for(int i = 0; i < height; i++) {
			int[] line = findHorizontalSeam();
			removePathHori(line);
		}
		showImage("D:/test5.png");
		System.out.println("down");
		return pic.img;
	}
	   
	   public static void main(String[] args) {
		   Picture a = new Picture("lina.jpg");
		   SeamCarver t = new SeamCarver(a);
		  
		   t.zipImageHori(400);

		   //System.out.println(a.getRow()+" "+a.getCol());
	   }


}
