package Model.Filtration;

import org.apache.commons.math3.complex.Complex;

public class Filter {

	private int K;
	
	public Filter(int K) {
		this.K = K;
	}
	
	public Complex getValue(Complex n) {
		if(n.equals(Complex.ZERO)){
			return (new Complex(2).divide(K));
		}else{
			return n.multiply(2*Math.PI).divide(K).sin().divide(n.multiply(Math.PI));
		}
	}
	
	public static abstract class FilterBuilder {
		int K;
		public FilterBuilder setK(int K){
			this.K = K;
			return this;
		}
		public abstract Filter build();
	}
}
