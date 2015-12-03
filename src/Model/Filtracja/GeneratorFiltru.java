package Model.Filtracja;

import org.apache.commons.math3.complex.Complex;

import Model.Filtracja.Okna.Okno;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyCmplx;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

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
		for(Complex n = new Complex(-Math.floor(M/2.0)) ; n.getReal() < N ; n = n.add(1)){
			y_tmp = filtr.getValue(n);
			if(okno != null) y_tmp = y_tmp.multiply(okno.getValue(n));
			syg.addComplexY(y_tmp);
			syg.addComplexX(n.add(Math.floor(M/2.0)));
		}
		
//		for(Complex n = new Complex(Math.ceil(M/2.0)) ; n.getReal() < N ; n = n.add(1)){
//			syg.addComplexY(new Complex(0.0));
//			syg.addComplexX(n.add(Math.floor(M/2.0)));
//		}
		
		return syg;
	}
	
	

}
