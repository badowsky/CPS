package Model.Szum;

import Model.Signal;
import Model.SignalGenerator;

public class Impulsowy extends SignalGenerator {

	public Impulsowy(){
		name = "Szum impulsowy";
		paramsNames = new String[]{
				"Starting Time",
				"Frequency",
				"Amplitude",
				"Próbkowanie",
				"P"};
	}

	@Override
	public Signal generate() {
		double startingTime = params[0];
		double frequency = params[1];
		double amplitude = params[2];
		double sampling = params[3];
		double p = params[4];
		
		Signal result = new Signal();
		
		int sampleNumber = 0;
		
		for (double i = startingTime; sampleNumber < sampling; i = i + 1 / frequency) {
			double z = Math.random();
			
			result.x.add(i);
			
			if (z <= p){
				result.y.add(amplitude);
			}
			else {
				result.y.add(0.0);
			}
			
			sampleNumber++;
		}
		
		return result;
	}

}
