package com.stevemurraycreative.mandelbrot;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class App {
	
    private MandelbrotPanel mandelbrotPanel;
    private JFrame mainFrame;
    private JPanel centrePanel,controlPanel,renderingPanel;                      

    public App() {
        
    	ColourArray colourArray = new ColourArray();
        prepareGUI();
        
    }
    
   /**
     * Displays the picture in a window on the screen.
     */
    public void prepareGUI() {

        // create the GUI for viewing the image if needed
        if (mainFrame == null) {
            
            mainFrame = new JFrame("Mandelbrot viewer");
            mainFrame.setSize(500,500);
            mainFrame.getContentPane().setLayout(new BorderLayout());
            mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent){
                   System.exit(0);
                }        
            });
            
            JPanel centrePanel = new JPanel(); 
            centrePanel.setLayout(new GridLayout(1,1));
            
            Border padding = BorderFactory.createEmptyBorder(30, 30, 30, 30);
            Border borderLine = BorderFactory.createLineBorder(Color.black);
            Border compound = BorderFactory.createCompoundBorder(padding, borderLine);
	      
            JPanel mandelbrotContainer = new JPanel();
            mandelbrotContainer.setBorder(compound);
            mandelbrotContainer.setLayout(new BorderLayout());
            
            mandelbrotPanel = new MandelbrotPanel();
            mandelbrotContainer.add(mandelbrotPanel);
            
            //TODO: add a JLayer object, which can somehow communicate to the user that rendering is occurring
            
            controlPanel = new JPanel();
            controlPanel.setLayout(new GridLayout(2,1));
            
            centrePanel.add(mandelbrotContainer);
            
            mainFrame.add(centrePanel,BorderLayout.CENTER);
            mainFrame.add(controlPanel, BorderLayout.PAGE_END);
            
            //mainFrame.add(mandelbrotContainer);
            
        }

        mainFrame.setVisible(true);
        // frame.repaint();
    }
    
    public void show() {
    	
    	JButton download = new JButton("Save high resolution image");
    	download.addActionListener(new ActionListener() {
	         
			 public void actionPerformed(ActionEvent e) {
				 
				 try {
					    // retrieve image
					 	File dir = new File("gallery/");
					 	File outputfile = File.createTempFile("Mandelbrot", ".png", dir);
					    //File outputfile = new File("gallery/Mandelbrot3.png");
					    ImageIO.write( MandelbrotPanel.generateHiResImage(), "png", outputfile);
					 } catch (IOException ex) {
					    
					    
					}
					
	         }          
	      });
    	
    	controlPanel.add(download);
    	mainFrame.setVisible(true);
    }


    public static void main(String[] args) {
        App app = new App();
        //System.out.printf("%d-by-%d\n", picture.width(), picture.height());
        app.show();
    }

}
