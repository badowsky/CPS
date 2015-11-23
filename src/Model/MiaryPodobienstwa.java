package Model;

import java.util.ArrayList;

public class MiaryPodobienstwa {

	public static Double mse(ArrayList<Double> firstSig, ArrayList<Double> secondSig) {
		if (firstSig.size() != secondSig.size())
			return null;
		return squareError(firstSig, secondSig) / (firstSig.size());
	}

	public static Double snr(ArrayList<Double> oryginalSig, ArrayList<Double> secondSig) {
		if (oryginalSig.size() != secondSig.size())
			return null;
		double sqrSum = 0;

		for (int i = 0; i < oryginalSig.size(); i++) {
			sqrSum += Math.pow(oryginalSig.get(i), 2);
		}
		return 10 * Math.log10(sqrSum / squareError(oryginalSig, secondSig));
	}

	public static Double psnr(ArrayList<Double> oryginalSig, ArrayList<Double> secondSig) {
		if (oryginalSig.size() != secondSig.size())
			return null;
		double maxVal = oryginalSig.get(0);

		for (int i = 1; i < oryginalSig.size(); i++) {
			if (maxVal < oryginalSig.get(i))
				maxVal = oryginalSig.get(i);
		}
		return 10 * Math.log10(maxVal / mse(oryginalSig, secondSig));
	}
	
	public static Double enob(ArrayList<Double> oryginalSig, ArrayList<Double> secondSig) {
	
		return (snr(oryginalSig, secondSig) - 1.76) / 6.02;
	}

	public static Double md(ArrayList<Double> firstSig, ArrayList<Double> secondSig) {
		if (firstSig.size() != secondSig.size())
			return null;
		double maxDiff = 0;
		double currentDiff;

		for (int i = 0; i < firstSig.size(); i++) {
			currentDiff = Math.abs(firstSig.get(i) - secondSig.get(i));
			if (maxDiff < currentDiff)
				maxDiff = currentDiff;
		}
		return maxDiff;
	}

	static Double squareError(ArrayList<Double> firstSig, ArrayList<Double> secondSig) {
		if (firstSig.size() != secondSig.size())
			return null;
		double sum = 0;

		for (int i = 0; i < firstSig.size(); i++) {
			sum += Math.pow(firstSig.get(i) - secondSig.get(i), 2);
		}

		return sum;
	}

}
