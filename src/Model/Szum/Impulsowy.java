package Model.Szum;

import java.util.ArrayList;

import Model.SygnalBase;

public class Impulsowy extends SygnalBase {

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
	public void generate() {
		double startingTime = params[0];
		double frequency = params[1];
		double amplitude = params[2];
		double sampling = params[3];
		double p = params[4];
		
		y = new ArrayList<Double>();
		x = new ArrayList<Double>();
		
		int sampleNumber = 0;
		
		for (double i = startingTime; sampleNumber < sampling; i = i + 1 / frequency) {
			double z = Math.random();
			
			x.add(i);
			
			if (z <= p){
				y.add(amplitude);
			}
			else {
				y.add(0.0);
			}
			
			sampleNumber++;
		}
	}

}
