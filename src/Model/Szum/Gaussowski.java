package Model.Szum;

import java.util.ArrayList;
import java.util.Random;

import Model.SygnalBase;

public class Gaussowski extends SygnalBase {

	public Gaussowski(){
		name = "Szum Gaussowski";
		paramsNames = new String[]{
				"Starting Time",
				"Time",
				"Frequency",
				"Amplitude"};
	}

	@Override
	public void generate() {
		double startingTime = params[0];
		double time = params[1];
		double frequency = params[2];
		double revFreq = 1/frequency;
		double amplitude = params[3];
		
		y = new ArrayList<Double>();
		x = new ArrayList<Double>();
		
		Random gauss = new Random();

		for (double i = startingTime; i < time + startingTime - 1 / (2 * frequency); i = i + revFreq) {
			double nextGaussianDouble = 0.0;
			
			do {
				nextGaussianDouble = gauss.nextGaussian();
			} while (nextGaussianDouble < -1 || nextGaussianDouble > 1);
			
			x.add(i);
			y.add(nextGaussianDouble * amplitude);
		}
	}

}
