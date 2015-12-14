package Model.Filtracja;

import org.apache.commons.math3.complex.Complex;

import Model.Filtracja.Okna.Okno;
import Model.Signals.Discrete.SygnalDyskretnyCmplx;

public class GeneratorFiltru {
	
	private Filtr filtr;
	private Okno okno;
	
	public GeneratorFiltru(Filtr filtr, Okno okno){
		this.filtr = filtr;
		this.okno = okno;
	}
	
	public SygnalDyskretnyCmplx generuj(int K, int M, int N){
		Complex[] y = new Complex[N];
		double[] x = new double[N];
		Complex n_shifted = new Complex(-Math.floor(M/2.0));
		for(int n = 0 ; n < N ; n++){
			y[n] = filtr.getValue(n_shifted).multiply(okno.getValue(n));
			x[n] = n;
			n_shifted = n_shifted.add(1);
		}
		return new SygnalDyskretnyCmplx(y, x);
	}
	
	

}
