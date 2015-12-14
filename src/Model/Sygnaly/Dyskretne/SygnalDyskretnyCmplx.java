package Model.Sygnaly.Dyskretne;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

public class SygnalDyskretnyCmplx {

	private Complex[] y;
	private double[] x;
	
	public SygnalDyskretnyCmplx(Complex[] y, double[] x){
		this.x = x;
		this.y = y;
	}
	
	public SygnalDyskretnyReal toReal(){
		SygnalDyskretnyReal sig = new SygnalDyskretnyReal();
		
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
