package com.stevemurraycreative.mandelbrot;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

public class MandelbrotPanel extends JPanel implements MouseListener, MouseWheelListener {
	
	public static BufferedImage img = new BufferedImage(Constants.lowResWidth,Constants.lowResWidth,BufferedImage.TYPE_INT_RGB);
	public static BufferedImage hiResImg = new BufferedImage(Constants.hiResWidth,Constants.hiResWidth,BufferedImage.TYPE_INT_RGB);
    
	public MandelbrotPanel() {
	
		addMouseListener(this);
		addMouseWheelListener(this);
		generateLowResImage();
		repaint();
		
	}
	
	public void generateLowResImage() 
	{
		System.out.println("generating the high res image");
		Mandelbrot.drawMandelbrot(img,Constants.lowResWidth);
		Graphics g = img.getGraphics();
		g.drawImage(img, 0, 0, Constants.lowResWidth, Constants.lowResWidth, null);  
		g.dispose(); 		   
	}
	
	public static BufferedImage generateHiResImage() 
	{
		Mandelbrot.drawMandelbrot(hiResImg,Constants.hiResWidth);
		Graphics g = img.getGraphics();
		g.drawImage(img, 0, 0, Constants.hiResWidth, Constants.hiResWidth, null);  
		g.dispose();
		return hiResImg;
	}
	
	 @Override
	   public void paintComponent(Graphics g)
	   {
		  super.paintComponent(g);
		  Graphics2D g2d = (Graphics2D) g;
		  //g.drawImage(img, 0, 0, Constants.targetWidth, (int)(Constants.targetWidth*img.getHeight()/img.getWidth()), null);
		  g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);

	   }

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
		    Mandelbrot.xc = Mandelbrot.width*((double)e.getX()/(double)this.getWidth() - 0.5) + Mandelbrot.xc; 
		    Mandelbrot.yc = Mandelbrot.width*((double)(e.getY())/(double)this.getHeight() - 0.5) + Mandelbrot.yc;
		    Mandelbrot.width = Mandelbrot.width/2;
		    Mandelbrot.drawMandelbrot(img,Constants.lowResWidth);
		    repaint();
		}
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		
		int notches = e.getWheelRotation();
	       if (notches > 0) {
	    	   // if scroll down, then zoom out
	    	   Mandelbrot.width = Mandelbrot.width*2;
	       } else {
	    	   // if scroll up, then zoom in
	    	   Mandelbrot.width = Mandelbrot.width/2;
	       }
	       Mandelbrot.xc = Mandelbrot.width*((double)e.getX()/(double)this.getWidth() - 0.5) + Mandelbrot.xc; 
		   Mandelbrot.yc = Mandelbrot.width*((double)(e.getY())/(double)this.getHeight() - 0.5) + Mandelbrot.yc;
		   Mandelbrot.drawMandelbrot(img,Constants.lowResWidth);
		   repaint();
	}

	 
}


