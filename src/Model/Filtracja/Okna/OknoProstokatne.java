package Model.Filtracja.Okna;

import org.apache.commons.math3.complex.Complex;

public class OknoProstokatne extends Okno {

	public OknoProstokatne(int M) {
		super(M);
	}

	@Override
	public Complex getValue(Complex n) {
		if(n.getReal() > (M-1)/2 || n.getReal() < -((M-1)/2)){
			return new Complex(0);
		}else{
			return new Complex(1);
		}
	}

}
