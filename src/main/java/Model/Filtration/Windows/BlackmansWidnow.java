package Model.Filtration.Windows;

public class BlackmansWidnow extends Window {

	public BlackmansWidnow(int M) {
		super(M);
	}

	@Override
	public double getValue(double n) {
		//return (new Complex(0.42)).subtract(n.multiply(Math.PI*2).divide(M).cos().multiply(0.5)).add(((new Complex(0.08)).multiply(n.multiply(Math.PI*2).divide(M).cos())));
		if(n >= 0 && n <= M){
			return 0.42 - 0.5 * Math.cos((2 * Math.PI * n)/M) + 0.08 * Math.cos((4 * Math.PI * n)/M);
		}else{
			return 0;
		}
		
	}

	@Override
	public String toString() {
		return "Okno Blackmana";
	}

	public static final class Builder extends WindowBuilder {

		@Override
		public Window build() {
			return new BlackmansWidnow(M);
		}
		
		@Override
		public String toString() {
			return "Okno Blackmana";
		}
	}
}
