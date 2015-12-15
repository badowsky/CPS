package Model.Conversion;

import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;

public class Sampling {
	
	public static DiscreteSignalReal sample(ContinuousSignal sygnal, double poczatek, int czestotliwosc, double koniec){
		return sample(sygnal, poczatek, czestotliwosc, koniec, false);
	}
	
	public static DiscreteSignalReal sample(ContinuousSignal sygnal, double poczatek, int czestotliwosc, double koniec, boolean showAsContinuous){
		DiscreteSignalReal result = new DiscreteSignalReal(showAsContinuous);
		
		for (double i = poczatek; i < koniec; i += 1.0/czestotliwosc) {
			result.addX(i);
			result.addY(sygnal.getValue(i));
		}
		
		return result;
	}
}
