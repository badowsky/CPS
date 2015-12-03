package Model.Filtracja;

import org.apache.commons.math3.complex.Complex;

public class FiltrGornoprzepustowy extends Filtr {

	public FiltrGornoprzepustowy(int K) {
		super(K);
	}
	
	@Override
	public Complex getValue(Complex n) {
		return super.getValue(n).multiply((new Complex(-1)).pow(n));
	}

}
