package Model.Sygnal;

import Model.Signal;
import Model.SignalGenerator;

public class Prostokatny extends SignalGenerator {

	public Prostokatny(){
		name = "Sygna³ prostok¹tny";
		paramsNames = new String[]{
				"Starting Time",
				"Time",
				"Frequency",
				"Amplitude",
				"Interval",
				"Kwantyzacja"};
	}
	
	@Override
	public Signal generate() {
		double startingTime = params[0];
		double time = params[1];
		double frequency = params[2];
		double revFreq = 1/frequency;
		double amplitude = params[3];
		double interval = params[4];
		double quantization = params[5];
		double k = 0;
		
		Signal result = new Signal();
		
		for (double i = startingTime; i < time + startingTime - 1 / (2 * frequency); i = i + revFreq) {
			if(i >= interval * (k + 1) + startingTime) {
				k++;
			}
			
			result.x.add(i);
			
			if( i >= (k * interval + startingTime) && i < (quantization * interval + k * interval + startingTime)) {
				result.y.add(amplitude);
			} 
			else if( i >= (quantization * interval - k * interval + startingTime) && i < (interval + k * interval + startingTime)) {
				result.y.add(0.0);
			}
			else {
				result.y.add(0.0);
			}
		}	
		
		return result;
	}

}
