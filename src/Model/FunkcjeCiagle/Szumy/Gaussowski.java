package Model.FunkcjeCiagle.Szumy;

import java.util.Random;

import Model.FunkcjeCiagle.FunkcjaCiagla;

public class Gaussowski extends FunkcjaCiagla {

	Random rnd = new Random();
	
	double wariancja, srednia;
	
	public Gaussowski() {
		super("Szum Gaussowski");		
		parametersNames = new String[]{
				"Wariancja",
				"�rednia"};
	}

	@Override
	public double getValue(double x) {
		return rnd.nextGaussian() * Math.sqrt(wariancja) + srednia;
	}

	@Override
	public void setParams(Double[] params) {
		this.wariancja = params[0];
		this.srednia = params[1];
	}

}
