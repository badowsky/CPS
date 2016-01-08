package Model.Signals.Continuous.Normal;

import Model.Signals.Continuous.ContinuousSignal;

public class S2 extends ContinuousSignal {

	public S2() {
		super("S2");
	}

	@Override
	public void setParams(Double[] params) {
	}

	@Override
	public double getValue(double t) {
		return 2 * Math.sin((2 * Math.PI / 2) * t) + Math.sin(2 * Math.PI * t) 
		+ 5 * Math.sin((2 * Math.PI / 0.5) * t);
	}

}
