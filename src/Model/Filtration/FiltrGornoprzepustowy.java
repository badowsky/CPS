package Model.Filtration;

import org.apache.commons.math3.complex.Complex;

public class FiltrGornoprzepustowy extends Filter {

	public FiltrGornoprzepustowy(int K) {
		super(K);
	}
	
	@Override
	public Complex getValue(Complex n) {
		return super.getValue(n).multiply((new Complex(-1)).pow(n));
	}

	@Override
	public String toString() {
		return "Filtr Gornoprzepustowy";
	}
	
	public static final class Builder extends FilterBuilder {

		@Override
		public Filter build() {
			return new FiltrGornoprzepustowy(K);
		}
		
		@Override
		public String toString() {
			return "Filtr Gornoprzepustowy";
		}
	}
}
