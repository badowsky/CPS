package Model.Signals.Discrete;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

public class SygnalDyskretnyCmplx {

	private ArrayList<Double> x;
	private ArrayList<Complex> y;
	
	public SygnalDyskretnyCmplx(){
		this.x = new ArrayList<Double>();
		this.y = new ArrayList<Complex>();
	}
	
	public SygnalDyskretnyReal toReal(){
		SygnalDyskretnyReal sig = new SygnalDyskretnyReal();
		
		for(int n = 0; n < x.size() ; n++){
			sig.addY(y.get(n).getReal());
			sig.addX(x.get(n));
		}
		return sig;	
	}
	
	public double getX(int index){
		return this.x.get(index);
	}
	
	public Complex getY(int index){
		return this.y.get(index);
	}
	
	public void addX(double x) {
		this.x.add(x);
	}
	public void addY(Complex y) {
		this.y.add(y);
	}
	
	public int size() {
		return this.x.size();
	}

}
