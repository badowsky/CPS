package Model.FunkcjeCiagle.Szumy;

import Model.FunkcjeCiagle.FunkcjaCiagla;

public class Impulsowy extends FunkcjaCiagla {
	
	double amplituda, p;
	
	public Impulsowy() {
		super("Szum impulsowy");		
		parametersNames = new String[]{
				"Amplituda",
				"P"};
	}

	@Override
	public double getValue(double x) {
		double z = Math.random();
		
		if (z <= p){
			return amplituda;
		}
		else {
			return 0.0;
		}
	}

	@Override
	public void setParams(Double[] params) {
		amplituda = params[0];
		p = params[1];		
	}

}
