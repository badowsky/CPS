package Model.Signals.Continuous.Noise;

import java.util.Random;

import Model.Signals.Continuous.ContinuousSignal;

public class Gaussian extends ContinuousSignal {

	Random rnd = new Random();
	
	double wariancja, srednia;
	
	public Gaussian() {
		super("Szum Gaussowski");		
		parametersNames = new String[]{
				"Wariancja",
				"Œrednia"};
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
