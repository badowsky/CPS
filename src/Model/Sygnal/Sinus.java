package Model.Sygnal;

import Model.Signal;
import Model.SignalGenerator;

public class Sinus extends SignalGenerator {
	
	public Sinus(){
		name = "Sygna³ sinusoidalny";
		paramsNames = new String[]{
				"Starting Time",
				"Time",
				"Frequency",
				"Amplitude",
				"Interval"};
	}

	@Override
	public Signal generate() {
		double startingTime = params[0];
		double time = params[1];
		double frequency = params[2];
		double revFreq = 1/frequency;
		double amplitude = params[3];
		double interval = params[4];
		
		Signal result = new Signal();
		
		for(double i = startingTime ; i < time + startingTime -1  / (2 * frequency); i = i + revFreq) {
			System.out.println("i = " + i);
			result.x.add(i);
			result.y.add(amplitude * Math.sin(((2 * Math.PI) / interval) * (i - startingTime)));
		}
		
		return result;
	}

}
