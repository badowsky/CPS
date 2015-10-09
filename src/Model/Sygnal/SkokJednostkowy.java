package Model.Sygnal;

import java.util.ArrayList;
import Helpers.Utils;
import Model.SygnalBase;

public class SkokJednostkowy extends SygnalBase {

	public SkokJednostkowy(){
		name = "Skok jednostkowy";
		paramsNames = new String[]{
				"Starting Time",
				"Time",
				"Frequency",
				"Amplitude"};
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
		
		double ts = time / 2 + startingTime;

		for (double i = startingTime; i < time + startingTime - 1 / (2 * frequency); i = i + revFreq) {
			x.add(i);
			
			if (Utils.rounding(i) < ts) {
				y.add(0.0);
			}
			else if (Utils.rounding(i) == ts) {
				y.add(0.5 * amplitude);
			}
			else {
				y.add(amplitude);
			}
		}
	}

}
