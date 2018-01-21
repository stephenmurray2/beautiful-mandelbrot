package com.stevemurraycreative.mandelbrot;

public class Complex {
	private final double re;
	private final double im;

	public Complex(double x, double y) {
		re = x;
		im = y;
	}
	
	public double getReal() {
		return this.re;
	}
	
	public double getImaginary() {
		return this.im;
	}
	
	public double abs() {
		 return Math.hypot(re, im);
	}
	
	public Complex times(Complex z) {
		Complex a = new Complex(this.re*z.re - this.im*z.im,this.re*z.im + this.im*z.re);
		return a;
	}
	
	public Complex plus(Complex z) {
		return new Complex(this.re + z.re,this.im + z.im);
	}
}
