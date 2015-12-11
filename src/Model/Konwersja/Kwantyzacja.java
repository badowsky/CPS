package Model.Konwersja;

import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.SygnalDyskretnyReal;

abstract public class Kwantyzacja {
	
	public int ileStopni;
	
	public Kwantyzacja(int ileStopni) {
		this.ileStopni = ileStopni;
	}

	public abstract SygnalDyskretnyReal kwantyzuj(ContinuousSignal sygnal, double poczatek, int czestotliwosc, double koniec, int ileStopni);
	
	public abstract String toString();

}
