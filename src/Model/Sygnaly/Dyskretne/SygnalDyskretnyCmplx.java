package Model.Sygnaly.Dyskretne;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.math3.complex.Complex;

public class SygnalDyskretnyCmplx implements SygnalDyskretny {

	private ArrayList<Complex> x;
	private ArrayList<Complex> y;
	
	public SygnalDyskretnyCmplx(){
		this.x = new ArrayList<Complex>();
		this.y = new ArrayList<Complex>();
	}
	
	public SygnalDyskretnyReal toReal(){
		SygnalDyskretnyReal sig = new SygnalDyskretnyReal();
		
		for(int n = 0; n < x.size() ; n++){
			sig.addY(y.get(n).getReal());
			sig.addX(x.get(n).getReal());
		}
		return sig;	
	}
	
	@Override
	public double getX(int index) {
		return this.getComplexX(index).getReal();
	}
	
	public Complex getComplexX(int index){
		return this.x.get(index);
	}

	@Override
	public double getY(int index) {
		return this.getComplexY(index).getReal();
	}
	
	public Complex getComplexY(int index){
		return this.y.get(index);
	}

	@Override
	public int size() {
		return this.x.size();
	}

	@Override
	public void addX(double x) {
		this.x.add(new Complex(x));
	}
	
	public void addComplexX(Complex x) {
		this.x.add(x);
	}

	@Override
	public void addY(double y) {
		this.y.add(new Complex(y));
	}
	
	public void addComplexY(Complex y) {
		this.y.add(y);
	}

	@Override
	public Iterator<Double> iteratorX() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Double> iteratorY() {
		// TODO Auto-generated method stub
		return null;
	}

}
