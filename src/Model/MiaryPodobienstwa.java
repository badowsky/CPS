package Model;

import java.util.ArrayList;

import Model.Sygnaly.Dyskretne.SygnalDyskretny;

public class MiaryPodobienstwa {

	public static Double mse(SygnalDyskretny firstSig, SygnalDyskretny secondSig) {
		if (firstSig.size() != secondSig.size())
			return null;
		return squareError(firstSig, secondSig) / (firstSig.size());
	}

	public static Double snr(SygnalDyskretny oryginalSig, SygnalDyskretny secondSig) {
		if (oryginalSig.size() != secondSig.size())
			return null;
		double sqrSum = 0;

		for (int i = 0; i < oryginalSig.size(); i++) {
			sqrSum += Math.pow(oryginalSig.getY(i), 2);
		}
		return 10 * Math.log10(sqrSum / squareError(oryginalSig, secondSig));
	}

	public static Double psnr(SygnalDyskretny oryginalSig, SygnalDyskretny secondSig) {
		if (oryginalSig.size() != secondSig.size())
			return null;
		double maxVal = oryginalSig.getY(0);

		for (int i = 1; i < oryginalSig.size(); i++) {
			if (maxVal < oryginalSig.getY(i))
				maxVal = oryginalSig.getY(i);
		}
		return 10 * Math.log10(maxVal / mse(oryginalSig, secondSig));
	}
	
	public static Double enob(SygnalDyskretny oryginalSig, SygnalDyskretny secondSig) {
	
		return (snr(oryginalSig, secondSig) - 1.76) / 6.02;
	}

	public static Double md(SygnalDyskretny firstSig, SygnalDyskretny secondSig) {
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

	static Double squareError(SygnalDyskretny firstSig, SygnalDyskretny secondSig) {
		if (firstSig.size() != secondSig.size())
			return null;
		double sum = 0;

		for (int i = 0; i < firstSig.size(); i++) {
			sum += Math.pow(firstSig.getY(i) - secondSig.getY(i), 2);
		}

		return sum;
	}

}
