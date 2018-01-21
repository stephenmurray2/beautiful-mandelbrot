package com.stevemurraycreative.mandelbrot;

import java.awt.Color;

public class ColourArray {

	// number of times we cycle through the colour list
    private final static int numCycles = 2;
    
    public static Color[] colourArray = new Color[Constants.maxIter+1];
    
    
    // For now, the colour scheme is hard-coded here, though it would be nice 
    // to add a component to the GUI that allows the user to select the colours.
    public static Color[] nodeColours = {
    		
    		// New England Patriots
    		//new Color(12,35,64),
    		//new Color(200,16,46),
    		
    		// Jacksonville Jaguars
    		//new Color(255,255,255),
    		//new Color(0,96,115),
    		//new Color(0,0,0),
    		//new Color(161,121,37),
    		
    		// Philadelphia Eagles
    		new Color(6,76,83),
    		new Color(0,0,0),
    		new Color(112,128,143),
    		new Color(134,147,151)
    		
    		// Minnesota Vikings
    		//new Color(79,38,131),
    		//new Color(255,198,47)
    };
    
    
    
    public static int n = nodeColours.length;
    
    public ColourArray() {
        setArray();
    }
       
    public void setArray() {
        
    	int j,k;
    	double a;
    	
        for (int i = 0; i < Constants.maxIter; i++) {
        	
        	// Set the default value to white	
        	colourArray[i] = new Color(255,255,255);
        	
        	j = (int) Math.floor( (double)numCycles*(double)n*(double)i/((double)Constants.maxIter ) );
        	k = (j + 1) % n;
        	j = j % n;
        	a = (double)n*(double)numCycles*(double)i/(double)Constants.maxIter;
        	a = a-Math.floor(a);
        	//System.out.println("a is "+a);
        	System.out.println("j is "+j);
        		
        	int red =   Math.min(Math.max(nodeColours[j].getRed() + (int)(((double)nodeColours[k].getRed() - (double)nodeColours[j].getRed())*a) ,0),255);
        	int green =   Math.min(Math.max(nodeColours[j].getGreen() + (int)(((double)nodeColours[k].getGreen() - (double)nodeColours[j].getGreen())*a),0),255);
        	int blue =   Math.min(Math.max(nodeColours[j].getBlue() + (int)(((double)nodeColours[k].getBlue() - (double)nodeColours[j].getBlue())*a) ,0),255);

        	colourArray[i] = new Color(red,green,blue);
        }
        
        // the final entry in the area is the colour of the set itself
        colourArray[Constants.maxIter] = new Color(0,0,0);
        //colourArray[Constants.maxIter] = new Color(255,255,255);
        //colourArray[Constants.maxIter] = new Color(12,35,64);

    }

	 
}
