package Model.Filtracja.Okna;

import org.apache.commons.math3.complex.Complex;

public class OknoHanninga extends Okno {

	public OknoHanninga(int M) {
		super(M);
	}

	@Override
	public Complex getValue(Complex n) {
		return (new Complex(0.5)).subtract(n.multiply(Math.PI*2).divide(M).cos().multiply(0.5));
	}

}
