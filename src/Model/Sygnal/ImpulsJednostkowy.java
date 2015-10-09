package Model.Sygnal;

import java.util.ArrayList;

import Helpers.Utils;
import Model.SygnalBase;

public class ImpulsJednostkowy extends SygnalBase {

	public ImpulsJednostkowy(){
		name = "Impuls jednostkowy";
		paramsNames = new String[]{
				"Starting Time",
				"Frequency",
				"Próbkowanie",
				"Amplituda"};
	}
	
	@Override
	public void generate() {
		double startingTime = params[0];
		double frequency = params[1];
		double sampling = params[2];
		double amplitude = params[3];
		
		y = new ArrayList<Double>();
		x = new ArrayList<Double>();
		
		int sampleNumber = 0;
		
		for (double i = startingTime; sampleNumber < sampling; i = i + 1 / frequency) {
			x.add(i);
			y.add(amplitude * Utils.impuls(sampleNumber - (Math.abs((int) Math.round(startingTime)))));
			sampleNumber++;
		}
	}
}
