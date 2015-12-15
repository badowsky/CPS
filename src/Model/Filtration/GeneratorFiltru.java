package Model.Filtration;

import org.apache.commons.math3.complex.Complex;

import Model.Filtration.Windows.Window;
import Model.Signals.Discrete.DiscreteSignalComplex;

public class GeneratorFiltru {
	
	private Filter filtr;
	private Window okno;
	
	public GeneratorFiltru(Filter filtr, Window okno){
		this.filtr = filtr;
		this.okno = okno;
	}
	
	public DiscreteSignalComplex generuj(int K, int M, int N){
		Complex[] y = new Complex[N];
		double[] x = new double[N];
		Complex n_shifted = new Complex(-Math.floor(M/2.0));
		for(int n = 0 ; n < N ; n++){
			y[n] = filtr.getValue(n_shifted).multiply(okno.getValue(n));
			x[n] = n;
			n_shifted = n_shifted.add(1);
		}
		return new DiscreteSignalComplex(y, x);
	}
	
	

}
