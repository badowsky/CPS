package Model.Filtracja;

import org.apache.commons.math3.complex.Complex;

public class Filtr {

	private int K;
	
	public Filtr(int K) {
		this.K = K;
	}
	
	public Complex getValue(Complex n) {
		if(n.equals(Complex.ZERO)){
			return (new Complex(2).divide(K));
		}else{
			return n.multiply(2*Math.PI).divide(K).sin().divide(n.multiply(Math.PI));
		}
	}
}
