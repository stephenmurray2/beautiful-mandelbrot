package com.stevemurraycreative.mandelbrot;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Mandelbrot {
	
	public static double xc;
	public static double yc;
	public static double width;
	
	public static int[][] rawNumIter = new int[Constants.hiResWidth][Constants.hiResWidth];
	public static int kernelWidth = 3;
	
	public static Random r = new Random();
	
	public Mandelbrot() {
		reset();
	}

	public static void reset() {
		xc = -0.5;
		yc = 0;
		width = 3;
	}
	
	public static int mand(Complex z0) {
	        Complex z = z0;
	        for (int t = 0; t < Constants.maxIter; t++) {
	            if (z.abs() > 2.0) return t;
	            z = z.times(z).plus(z0);
	        }
	        return Constants.maxIter;
	}
     
	public static void drawMandelbrot(BufferedImage image,int w)  {
	        
	    double rangeMin = -width/(2*w);
	    double rangeMax = +width/(2*w);
	    double temp,count;
		int buffer = (int) Math.floor(kernelWidth/2.0);
	    
	    for (int i = 0; i < w; i++) {
	    	//TODO; the progress does not vary linearly with time, since some pixels take longer
	    	//to render than others. As a solution, an estimate of rendering time for a pixel can
	    	//be obtained when the low resolution image renders.
	    	System.out.println("rendered "+(int)(100*(double)i/(double)w) + "% of the image");
	        for (int j = 0; j < w; j++) {
	        	
	            double x0 = xc - width/2 + width*i/w + rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	            double y0 = yc - width/2 + width*j/w + rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	            Complex z0 = new Complex(x0, y0);
	            rawNumIter[i][j] = mand(z0); 
	        }
	    }
	    
	    // The colour array is smoothed, to avoid abrupt changes of colour
	    // between neighbouring pixels
	    for (int i = 0; i < w; i++) {
			for (int j = 0; j < w; j++) {
				temp = 0;
				count = 0;
				for (int m = i-buffer; m <= i+buffer; m++) {
					for (int n = j-buffer; n <= j+buffer; n++) {
						if (m >= 0 && m < w && n >= 0 && n < w) {
							temp = temp+rawNumIter[m][n];
							count++;
						}
			    	}
				}
				
				if (rawNumIter[i][j] != Constants.maxIter) {
					image.setRGB(i, j, ColourArray.colourArray[((int) (temp/count)) % Constants.maxIter].getRGB());
				} else {
					image.setRGB(i, j, ColourArray.colourArray[Constants.maxIter].getRGB());
				}
			}
	    }
	}
}

