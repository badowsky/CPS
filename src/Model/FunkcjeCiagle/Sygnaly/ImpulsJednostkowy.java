package Model.FunkcjeCiagle.Sygnaly;

import Model.FunkcjeCiagle.FunkcjaCiagla;

public class ImpulsJednostkowy extends FunkcjaCiagla {
	
	private double amplituda, przesuniecie;

	public ImpulsJednostkowy() {
		super("Impuls Jednostkowy");
		parametersNames = new String[]{
				"Amplituda",
				"Pprzesuniecie"};
	}
	
	@Override
	public double getValue(double x) {		
		if (Math.abs(x - przesuniecie) < 0.0005) {
			return amplituda;
		}
		else {
			return 0;
		}
	}

	@Override
	public void setParams(Double[] params) {
		this.amplituda = params[0];
		this.przesuniecie = params[1];
		
	}

}
