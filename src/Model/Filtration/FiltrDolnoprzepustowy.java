package Model.Filtration;

public class FiltrDolnoprzepustowy extends Filter {

	public FiltrDolnoprzepustowy(int K) {
		super(K);
	}

	@Override
	public String toString() {
		return "Filtr Dolnoprzepustowy";
	}
	
	public static final class Builder extends FilterBuilder {

		@Override
		public Filter build() {
			return new FiltrDolnoprzepustowy(K);
		}
		
		@Override
		public String toString() {
			return "Filtr Dolnoprzepustowy";
		}
	}
}
