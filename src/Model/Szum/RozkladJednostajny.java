package Model.Szum;

import Model.Signal;
import Model.SignalGenerator;

public class RozkladJednostajny extends SignalGenerator {

	public RozkladJednostajny(){
		name = "Szum o rozk³adzie jednostajnym";
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
		
		Signal result = new Signal();
		
		for (double i = startingTime; i < time + startingTime - 1 / (2 * frequency); i = i + revFreq) {
			result.x.add(i);
			result.y.add((Math.random() * 2 * amplitude - amplitude));
		}
		
		return result;
	}

}
