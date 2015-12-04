package Model.Filtracja.Okna;

public class OknoHanninga extends Okno {

	public OknoHanninga(int M) {
		super(M);
	}

	@Override
	public double getValue(double n) {
		//return (new Complex(0.5)).subtract(n.multiply(Math.PI*2).divide(M).cos().multiply(0.5));
		return 0.5 - 0.5 * Math.cos((2 * Math.PI * n)/M);
	}

}
