package Model.Filtration.Windows;

public class RectangleWindow extends Window {

	public RectangleWindow(int M) {
		super(M);
	}

	@Override
	public double getValue(double n) {
		if(n > 0 && n < M){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Okno Prostokatne";
	}
	
	public static final class Builder extends WindowBuilder {

		@Override
		public Window build() {
			return new RectangleWindow(M);
		}
		
		@Override
		public String toString() {
			return "Okno Prostokatne";
		}
	}
}
