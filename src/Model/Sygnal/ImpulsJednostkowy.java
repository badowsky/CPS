package Model.Sygnal;

import Helpers.Utils;
import Model.Signal;
import Model.SignalGenerator;

public class ImpulsJednostkowy extends SignalGenerator {

	public ImpulsJednostkowy(){
		name = "Impuls jednostkowy";
		paramsNames = new String[]{
				"Starting Time",
				"Frequency",
				"Próbkowanie",
				"Amplituda"};
	}
	
	@Override
	public Signal generate() {
		double startingTime = params[0];
		double frequency = params[1];
		double sampling = params[2];
		double amplitude = params[3];
		
		Signal result = new Signal();
		
		int sampleNumber = 0;
		
		for (double i = startingTime; sampleNumber < sampling; i = i + 1 / frequency) {
			result.x.add(i);
			result.y.add(amplitude * Utils.impuls(sampleNumber - (Math.abs((int) Math.round(startingTime)))));
			sampleNumber++;
		}
		
		return result;
	}
}
