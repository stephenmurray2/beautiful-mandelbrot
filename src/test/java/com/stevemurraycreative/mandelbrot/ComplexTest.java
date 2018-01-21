package com.stevemurraycreative.mandelbrot;

import org.junit.*;
import static org.junit.Assert.*;

public class ComplexTest {
	
	private static double ADMISS_ERR_CONSTRUCTOR = 0;
	private static double ADMISS_ERR_DIST = 1e-20;
	
	@Test
	public void testComplexConstructor() {
		assertEquals(23, new Complex(23, 38).getReal(), ADMISS_ERR_CONSTRUCTOR);
		assertEquals(38, new Complex(23, 38).getImaginary(), ADMISS_ERR_CONSTRUCTOR);
	}
	
	@Test
	public void testMultiply()
	{
		double a1 = 2.0;
		double b1 = 3.0;
		
		double a2 = 5.0;
		double b2 = 7.0;
		
		Complex z1 = new Complex(a1, b1);
		Complex z2 = new Complex(a2, b2);
		
		assertEquals(a1*a2-b1*b2, z1.times(z2).getReal(), ADMISS_ERR_DIST);
		assertEquals(a1*b2+b1*a2, z1.times(z2).getImaginary(), ADMISS_ERR_DIST);
	}	
}
