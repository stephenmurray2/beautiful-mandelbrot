package com.stevemurraycreative.mandelbrot;

import java.awt.Color;

public class ColourArray {

	// number of times we cycle through the colour list
    private final static int numCycles = 2;
    
    public static Color[] colourArray = new Color[Constants.maxIter+1];
    
    // For now, the colour scheme is hard-coded here, though it would be nice 
    // to add a component to the GUI that allows the user to select the colours.
    public static Color[] mainColours = {
    		
    		new Color(6,76,83),
    		new Color(0,0,0),
    		new Color(112,128,143),
    		new Color(134,147,151)
    };
    
    public static int n = mainColours.length;
    
    public ColourArray() {
        setArray();
    }
       
    public void setArray() {
        
    	int j,k;
    	double a;
    	
    	// This loop defines an array that smoothly transitions between the colours given in mainColours array
        for (int i = 0; i < Constants.maxIter; i++) {
        	
        	j = (int) Math.floor( (double)numCycles*(double)n*(double)i/((double)Constants.maxIter ) );
        	k = (j + 1) % n;
        	j = j % n;
        	a = (double)n*(double)numCycles*(double)i/(double)Constants.maxIter;
        	a = a-Math.floor(a);
        		
        	int red =   Math.min(Math.max(mainColours[j].getRed() + (int)(((double)mainColours[k].getRed() - (double)mainColours[j].getRed())*a) ,0),255);
        	int green =   Math.min(Math.max(mainColours[j].getGreen() + (int)(((double)mainColours[k].getGreen() - (double)mainColours[j].getGreen())*a),0),255);
        	int blue =   Math.min(Math.max(mainColours[j].getBlue() + (int)(((double)mainColours[k].getBlue() - (double)mainColours[j].getBlue())*a) ,0),255);

        	colourArray[i] = new Color(red,green,blue);
        }
        
        colourArray[Constants.maxIter] = Constants.mandelbrotSetColour;

    }

	 
}
