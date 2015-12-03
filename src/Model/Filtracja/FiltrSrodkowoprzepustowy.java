package Model.Filtracja;

import org.apache.commons.math3.complex.Complex;

public class FiltrSrodkowoprzepustowy extends Filtr {

	public FiltrSrodkowoprzepustowy(int K) {
		super(K);
	}

	@Override
	public Complex getValue(Complex n) {
		return super.getValue(n).multiply(n.multiply(Math.PI).divide(2).sin().multiply(2));
	}
}
