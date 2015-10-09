package Model.Szum;

import java.util.ArrayList;

import Model.SygnalBase;

public class RozkladJednostajny extends SygnalBase {

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
	public void generate() {
		double startingTime = params[0];
		double time = params[1];
		double frequency = params[2];
		double revFreq = 1/frequency;
		double amplitude = params[3];
		
		y = new ArrayList<Double>();
		x = new ArrayList<Double>();
		
		for (double i = startingTime; i < time + startingTime - 1 / (2 * frequency); i = i + revFreq) {
			x.add(i);
			y.add((Math.random() * 2 * amplitude - amplitude));
		}
	}

}
