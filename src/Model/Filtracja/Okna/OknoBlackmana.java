package Model.Filtracja.Okna;

import org.apache.commons.math3.complex.Complex;

public class OknoBlackmana extends Okno {

	public OknoBlackmana(int M) {
		super(M);
	}

	@Override
	public Complex getValue(Complex n) {
		return (new Complex(0.42)).subtract(n.multiply(Math.PI*2).divide(M).cos().multiply(0.5)).add(((new Complex(0.08)).multiply(n.multiply(Math.PI*2).divide(M).cos())));
	}

}
