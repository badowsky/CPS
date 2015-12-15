package Model.Filtration.Windows;

import Model.Filtration.Windows.Window.WindowBuilder;

public class HammingsWindow extends Window {

	public HammingsWindow(int M) {
		super(M);
	}

	@Override
	public double getValue(double n) {
		//return (new Complex(0.53836)).subtract(n.multiply(Math.PI*2).divide(M).cos().multiply(0.46164));
		return 0.53836 - 0.46164 * Math.cos((2 * Math.PI * n)/M);
	}

	@Override
	public String toString() {
		return "Okno Hamminga";
	}
	
	public static final class Builder extends WindowBuilder {

		@Override
		public Window build() {
			return new HammingsWindow(M);
		}
		
		@Override
		public String toString() {
			return "Okno Hamminga";
		}
	}
}
