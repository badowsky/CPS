package Model.Signals;

import java.util.ArrayList;

import Model.Signals.Discrete.DiscreteSignal;

public class ComparsionMeasures {

	public static Double mse(DiscreteSignal firstSig, DiscreteSignal secondSig) {
		if (firstSig.size() != secondSig.size())
			return null;
		return squareError(firstSig, secondSig) / (firstSig.size());
	}

	public static Double snr(DiscreteSignal oryginalSig, DiscreteSignal secondSig) {
		if (oryginalSig.size() != secondSig.size())
			return null;
		double sqrSum = 0;

		for (int i = 0; i < oryginalSig.size(); i++) {
			sqrSum += Math.pow(oryginalSig.getY(i), 2);
		}
		return 10 * Math.log10(sqrSum / squareError(oryginalSig, secondSig));
	}

	public static Double psnr(DiscreteSignal oryginalSig, DiscreteSignal secondSig) {
		if (oryginalSig.size() != secondSig.size())
			return null;
		double maxVal = oryginalSig.getY(0);

		for (int i = 1; i < oryginalSig.size(); i++) {
			if (maxVal < oryginalSig.getY(i))
				maxVal = oryginalSig.getY(i);
		}
		return 10 * Math.log10(maxVal / mse(oryginalSig, secondSig));
	}
	
	public static Double enob(DiscreteSignal oryginalSig, DiscreteSignal secondSig) {
	
		return (snr(oryginalSig, secondSig) - 1.76) / 6.02;
	}

	public static Double md(DiscreteSignal firstSig, DiscreteSignal secondSig) {
		if (firstSig.size() != secondSig.size())
			return null;
		double maxDiff = 0;
		double currentDiff;

		for (int i = 0; i < firstSig.size(); i++) {
			currentDiff = Math.abs(firstSig.getY(i) - secondSig.getY(i));
			if (maxDiff < currentDiff)
				maxDiff = currentDiff;
		}
		return maxDiff;
	}

	static Double squareError(DiscreteSignal firstSig, DiscreteSignal secondSig) {
		if (firstSig.size() != secondSig.size())
			return null;
		double sum = 0;

		for (int i = 0; i < firstSig.size(); i++) {
			sum += Math.pow(firstSig.getY(i) - secondSig.getY(i), 2);
		}

		return sum;
	}

}
