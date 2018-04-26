package com.stevemurraycreative.mandelbrot;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class App {
	
    private MandelbrotPanel mandelbrotPanel;
    private JFrame mainFrame;
    private JPanel controlPanel;
    private JPanel buttonPanelOneWrap,buttonPanelTwoWrap,buttonPanelThreeWrap;

    public App() {
        
    	// Define the colours to be used when rendering the image
    	ColourArray colourArray = new ColourArray();
    	// Prepare the GUI
        prepareGUI();
    }
    
    public void prepareGUI() {

        // create the GUI for viewing the image if needed
        if (mainFrame == null) {
            
            mainFrame = new JFrame("Mandelbrot viewer");
            mainFrame.setSize(500,600);
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
	      
            JPanel mandelbrotContainer = new JPanel(new BorderLayout());
            mandelbrotContainer.setBorder(compound);
            //mandelbrotContainer.setLayout();
            
            mandelbrotPanel = new MandelbrotPanel();
            mandelbrotContainer.add(mandelbrotPanel);
            centrePanel.add(mandelbrotContainer);
            
            //TODO: add some sort of JLayer object, which can somehow 
            // communicate to the user the progress of the image rendering
            controlPanel = new JPanel();
            controlPanel.setLayout(new GridLayout(3,1));
            
            buttonPanelOneWrap = new JPanel(new FlowLayout());
            buttonPanelTwoWrap = new JPanel(new FlowLayout());
            buttonPanelThreeWrap = new JPanel(new FlowLayout());
            
            controlPanel.add(buttonPanelOneWrap);
            controlPanel.add(buttonPanelTwoWrap);
            controlPanel.add(buttonPanelThreeWrap);

            mainFrame.add(centrePanel,BorderLayout.CENTER);
            mainFrame.add(controlPanel, BorderLayout.PAGE_END);
            
        }

        mainFrame.setVisible(true);
    }
    
    public void show() {
    	
    	JButton saveHiResImage = new JButton("Save high resolution image");
    	JButton reset = new JButton("Reset");
    	JButton zoomIn = new JButton("Zoom +");
    	JButton zoomOut = new JButton("Zoom -");
    	
    	saveHiResImage.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 
				 try {
				 	File dir = new File("gallery/");
				 	File outputfile = File.createTempFile("Mandelbrot", ".png", dir);
				    ImageIO.write( MandelbrotPanel.generateHiResImage(), "png", outputfile);
				 } catch (IOException ex) {
				    ex.printStackTrace();
				 }
	         }          
	      });
    	
    	reset.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 mandelbrotPanel.reset();
	         }          
	      });
    	
    	zoomIn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 mandelbrotPanel.zoomIn();
	         }          
	      });
    	
    	zoomOut.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 mandelbrotPanel.zoomOut();
	         }          
	      });
    	
    	
    	buttonPanelOneWrap.add(zoomIn);
    	buttonPanelOneWrap.add(zoomOut);
    	buttonPanelTwoWrap.add(reset);
    	buttonPanelThreeWrap.add(saveHiResImage);
    	
    	
    	
    	mainFrame.setVisible(true);
    }


    public static void main(String[] args) {
        App app = new App();
        app.show();
    }

}
