package Model.Sygnal;

import Model.Signal;
import Model.SignalGenerator;

public class SkokJednostkowy extends SignalGenerator {

	public SkokJednostkowy(){
		name = "Skok jednostkowy";
		paramsNames = new String[]{
				"Starting Time",
				"Time",
				"Frequency",
				"Amplitude"};
	}
	
	@Override
	public Signal generate() {
		double startingTime = params[0];
		double time = params[1];
		double frequency = params[2];
		double revFreq = 1/frequency;
		double amplitude = params[3];
		
		Signal result = new Signal();
		
		double ts = time / 2 + startingTime;
		double closest_ts = Double.MAX_VALUE;
		for (double i = startingTime; i < time + startingTime - 1 / (2 * frequency); i = i + revFreq) {
			result.x.add(i);
			if(Math.round(ts - i) < Math.round(closest_ts)) closest_ts = i;
			System.out.println(closest_ts);
		}
		
		for(double x_val : result.x){
			if (x_val < closest_ts) {
				result.y.add(0.0);
			}
			else if (x_val == closest_ts) {
				result.y.add(0.5 * amplitude);
			}
			else {
				result.y.add(amplitude);
			}
		}
		
		return result;
	}

}
