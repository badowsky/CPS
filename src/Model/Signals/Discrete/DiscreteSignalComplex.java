package Model.Signals.Discrete;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

public class DiscreteSignalComplex {

	private Complex[] y;
	private double[] x;
	
	public DiscreteSignalComplex(Complex[] y, double[] x){
		this.x = x;
		this.y = y;
	}
	
	public DiscreteSignalReal toReal(){
		DiscreteSignalReal sig = new DiscreteSignalReal();
		
		for(int n = 0; n < y.length ; n++){
			sig.addY(y[n].getReal());
			sig.addX(x[n]);
		}
		return sig;
	}
	
	public double getX(int index){
		return this.x[index];
	}
	
	public Complex getY(int index){
		return this.y[index];
	}
	
	public int size() {
		return this.y.length;
	}

	public Complex[] getY() {
		return y;
	}

}
