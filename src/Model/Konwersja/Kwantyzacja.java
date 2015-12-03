package Model.Konwersja;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

abstract public class Kwantyzacja {
	
	public int ileStopni;
	
	public Kwantyzacja(int ileStopni) {
		this.ileStopni = ileStopni;
	}

	public abstract SygnalDyskretnyReal kwantyzuj(FunkcjaCiagla sygnal, double poczatek, int czestotliwosc, double koniec, int ileStopni);
	
	public abstract String toString();

}
