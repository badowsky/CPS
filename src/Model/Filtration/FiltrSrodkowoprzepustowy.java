package Model.Filtration;

import org.apache.commons.math3.complex.Complex;

import Model.Filtration.Filter.FilterBuilder;

public class FiltrSrodkowoprzepustowy extends Filter {

	public FiltrSrodkowoprzepustowy(int K) {
		super(K);
	}

	@Override
	public Complex getValue(Complex n) {
		return super.getValue(n).multiply(n.multiply(Math.PI).divide(2).sin().multiply(2));
	}

	@Override
	public String toString() {
		return "Filtr Srodkowoprzepustowy";
	}
	
	public static final class Builder extends FilterBuilder {

		@Override
		public Filter build() {
			return new FiltrSrodkowoprzepustowy(K);
		}
		
		@Override
		public String toString() {
			return "Filtr Srodkowoprzepustowy";
		}
	}
}
