package Model.Sygnal;

import java.util.ArrayList;

import Model.SygnalBase;

public class Sinus extends SygnalBase {
	
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
	public void generate() {
		double startingTime = params[0];
		double time = params[1];
		double frequency = params[2];
		double revFreq = 1/frequency;
		double amplitude = params[3];
		double interval = params[4];
		
		y = new ArrayList<Double>();
		x = new ArrayList<Double>();
		
		for(double i = startingTime ; i < time + startingTime -1  / (2 * frequency); i = i + revFreq) {
			System.out.println("i = " + i);
			x.add(i);
			y.add(amplitude * Math.sin(((2 * Math.PI) / interval) * (i - startingTime)));
		}
	}

}
