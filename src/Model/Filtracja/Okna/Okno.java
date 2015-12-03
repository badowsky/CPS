package Model.Filtracja.Okna;

import org.apache.commons.math3.complex.Complex;

public abstract class Okno {
	
	protected int M;
	
	protected Okno(int M){
		this.M = M;
	}

	public abstract Complex getValue(Complex n);
}
