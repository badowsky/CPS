package Model.Filtracja.Okna;

public class OknoBlackmana extends Okno {

	public OknoBlackmana(int M) {
		super(M);
	}

	@Override
	public double getValue(double n) {
		//return (new Complex(0.42)).subtract(n.multiply(Math.PI*2).divide(M).cos().multiply(0.5)).add(((new Complex(0.08)).multiply(n.multiply(Math.PI*2).divide(M).cos())));
		return 0.42 - 0.5 * Math.cos((2 * Math.PI * n)/M) + 0.08 * Math.cos((4 * Math.PI * n)/M);
	}

}
