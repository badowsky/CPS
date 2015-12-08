package Model.Konwersja;

import Model.Sygnaly.Ciagle.SygnalCiagly;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

abstract public class Kwantyzacja {
	
	public int ileStopni;
	
	public Kwantyzacja(int ileStopni) {
		this.ileStopni = ileStopni;
	}

	public abstract SygnalDyskretnyReal kwantyzuj(SygnalCiagly sygnal, double poczatek, int czestotliwosc, double koniec, int ileStopni);
	
	public abstract String toString();

}
