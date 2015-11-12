package Model.FunkcjeCiagle.Sygnaly;

import Model.FunkcjeCiagle.FunkcjaCiagla;

public class SkokJednostkowy extends FunkcjaCiagla {

	private double amplituda, przesuniecie;
	
	public SkokJednostkowy() {
		super("Skok jednostkowy");
		parametersNames = new String[]{
				"Amplituda",
				"Pprzesuniecie"};
	}
	
	@Override
	public double getValue(double x) {
		if (x < przesuniecie) {
			return 0.0;
		}
		else if (x == przesuniecie) {
			return 0.5 * amplituda;
		}
		else {
			return amplituda;
		}
	}

	@Override
	public void setParams(Double[] params) {
		this.amplituda = params[0];
		this.przesuniecie = params[1];
	}
}
