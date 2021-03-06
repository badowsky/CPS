package Model.Signals.Continuous.Noise;

import Model.Signals.Continuous.ContinuousSignal;

public class RozkladJednostajny extends ContinuousSignal {

	double amplituda;
	
	public RozkladJednostajny() {
		super("Szum o Rozkładzie Jednostajnym");		
		parametersNames = new String[]{
				"Amplituda"};
	}
	
	@Override
	public double getValue(double x) {
		return (Math.random() * 2 * amplituda - amplituda);
	}

	@Override
	public void setParams(Double[] params) {
		amplituda = params[0];		
	}

}
