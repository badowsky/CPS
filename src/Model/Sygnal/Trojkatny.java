package Model.Sygnal;

import java.util.ArrayList;

import Model.SygnalBase;

public class Trojkatny extends SygnalBase {

	public Trojkatny(){
		name = "Sygna³ trójk¹tny";
		paramsNames = new String[]{
				"Starting Time",
				"Time",
				"Frequency",
				"Amplitude",
				"Interval",
				"Kwantyzacja"};
	}
	
	@Override
	public void generate() {
		double startingTime = params[0];
		double time = params[1];
		double frequency = params[2];
		double revFreq = 1/frequency;
		double amplitude = params[3];
		double interval = params[4];
		double quantization = params[5];
		double k = 0;
		
		y = new ArrayList<Double>();
		x = new ArrayList<Double>();
		
		for (double i = startingTime; i < time + startingTime - 1 / (2 * frequency); i = i + revFreq) {
			if (i >= interval * (k + 1) + startingTime){
				k++;
			}

			x.add(i);
			
			if( i >= (k * interval + startingTime) && i < (quantization * interval + k * interval + startingTime)) {
				y.add(amplitude * (i - k * interval - startingTime) / (quantization * interval));
			}
			else if( i >= (quantization * interval + startingTime + k * interval) && i < (interval + k * interval + startingTime)) {
				y.add((-amplitude * (i - k * interval - startingTime) / (interval * (1 - quantization))) + (amplitude / (1 - quantization)));
			}
			else {
				y.add(0.0);
			}
		}

	}

}
