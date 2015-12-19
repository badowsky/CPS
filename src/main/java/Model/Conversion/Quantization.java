package Model.Conversion;

import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;

abstract public class Quantization {
	
	public int ileStopni;
	
	public Quantization(int ileStopni) {
		this.ileStopni = ileStopni;
	}

	public abstract DiscreteSignalReal kwantyzuj(ContinuousSignal sygnal, double poczatek, int czestotliwosc, double koniec, int ileStopni);
	
	public abstract String toString();

}
