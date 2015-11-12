package Model.Operacje;

public class MiaryPodobienstwa {

	public static Double mse(double[] firstSig, double[] secondSig) {
		if (firstSig.length != secondSig.length)
			return null;
		return squareError(firstSig, secondSig) / (firstSig.length + 1);
	}

	public static Double snr(double[] oryginalSig, double[] secondSig) {
		if (oryginalSig.length != secondSig.length)
			return null;
		double sqrSum = 0;

		for (int i = 0; i < oryginalSig.length; i++) {
			sqrSum += Math.pow(oryginalSig[i], 2);
		}
		return 10 * Math.log10(sqrSum / squareError(oryginalSig, secondSig));
	}

	public static Double psnr(double[] oryginalSig, double[] secondSig) {
		if (oryginalSig.length != secondSig.length)
			return null;
		double maxVal = oryginalSig[0];

		for (int i = 1; i < oryginalSig.length; i++) {
			if (maxVal < oryginalSig[i])
				maxVal = oryginalSig[i];
		}
		return 10 * Math.log10(maxVal / mse(oryginalSig, secondSig));
	}

	public static Double md(double[] firstSig, double[] secondSig) {
		if (firstSig.length != secondSig.length)
			return null;
		double maxDiff = 0;
		double currentDiff;

		for (int i = 0; i < firstSig.length; i++) {
			currentDiff = Math.abs(firstSig[i] - secondSig[i]);
			if (maxDiff < currentDiff)
				maxDiff = currentDiff;
		}
		return maxDiff;
	}

	static Double squareError(double[] firstSig, double[] secondSig) {
		if (firstSig.length != secondSig.length)
			return null;
		double sum = 0;

		for (int i = 0; i < firstSig.length; i++) {
			sum += Math.pow(firstSig[i] - secondSig[i], 2);
		}

		return sum;
	}

}
