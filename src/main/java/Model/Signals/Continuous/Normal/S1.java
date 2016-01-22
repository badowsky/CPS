package Model.Signals.Continuous.Normal;

import Model.Signals.Continuous.ContinuousSignal;

public class S1 extends ContinuousSignal {

	public S1() {
		super("S1");
	}

	@Override
	public void setParams(Double[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getValue(double t) {
		return 2 * Math.sin((2 * Math.PI / 2) * t + Math.PI / 2)
				+ 5 * Math.sin((2 * Math.PI / 0.5) * t + Math.PI / 2);
	}
}
