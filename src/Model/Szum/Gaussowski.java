package Model.Szum;

import java.util.Random;

import Model.Signal;
import Model.SignalGenerator;

public class Gaussowski extends SignalGenerator {

	public Gaussowski(){
		name = "Szum Gaussowski";
		paramsNames = new String[]{
				"Starting Time",
				"Time",
				"Frequency",
				"Odchylenie std",
				"Œrednia"};
	}

	@Override
	public Signal generate() {
		double startingTime = params[0];
		double time = params[1];
		double frequency = params[2];
		double revFreq = 1/frequency;
		double variance = params[3];
		double mean = params[4];
		
		Signal result = new Signal();

		Random rnd = new Random();
		
		for (double i = startingTime; i < time + startingTime - 1 / (2 * frequency); i = i + revFreq) {			
			result.x.add(i);
			result.y.add(rnd.nextGaussian() * Math.sqrt(variance) + mean);
		}
		
		return result;
	}

}
