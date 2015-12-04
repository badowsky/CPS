package Model.Filtracja;

import org.apache.commons.math3.complex.Complex;

import Model.Filtracja.Okna.Okno;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyCmplx;

public class GeneratorFiltru {
	
	private Filtr filtr;
	private Okno okno;
	
	public GeneratorFiltru(Filtr filtr, Okno okno){
		this.filtr = filtr;
		this.okno = okno;
	}
	
	public SygnalDyskretnyCmplx generuj(int K, int M, int N){
		SygnalDyskretnyCmplx syg = new SygnalDyskretnyCmplx();
		
		Complex y_tmp;
		int n = 0;
		for(Complex n_shifted = new Complex(-Math.floor(M/2.0)) ; n_shifted.getReal() < N ; n_shifted = n_shifted.add(1)){
			y_tmp = filtr.getValue(n_shifted).multiply(okno.getValue(n));
			syg.addY(y_tmp);
			syg.addX(n++);
		}
		return syg;
	}
	
	

}
