package Model.Signals.Continuous.Normal;

import Model.Signals.Continuous.ContinuousSignal;

public class S3 extends ContinuousSignal {

	public S3() {
		super("S3");
	}

	@Override
	public void setParams(Double[] params) {

	}

	@Override
	public double getValue(double t) {
		return 5 * Math.sin((2 * Math.PI / 2) * t) + Math.sin((2 * Math.PI / 0.25) * t);
	}

}
