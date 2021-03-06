package Model.Filtration.Windows;

public class HanningsWindow extends Window {

	public HanningsWindow(int M) {
		super(M);
	}

	@Override
	public double getValue(double n) {
		// return (new
		// Complex(0.5)).subtract(n.multiply(Math.PI*2).divide(M).cos().multiply(0.5));
		if (n >= 0 && n <= M) {
			return 0.5 - 0.5 * Math.cos((2 * Math.PI * n) / M);
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Okno Hanninga";
	}

	public static final class Builder extends WindowBuilder {

		@Override
		public Window build() {
			return new HanningsWindow(M);
		}

		@Override
		public String toString() {
			return "Okno Hanninga";
		}
	}
}
