package Model.Filtracja.Okna;

public class OknoHamminga extends Okno {

	public OknoHamminga(int M) {
		super(M);
	}

	@Override
	public double getValue(double n) {
		//return (new Complex(0.53836)).subtract(n.multiply(Math.PI*2).divide(M).cos().multiply(0.46164));
		return 0.53836 - 0.46164 * Math.cos((2 * Math.PI * n)/M);
	}
}
