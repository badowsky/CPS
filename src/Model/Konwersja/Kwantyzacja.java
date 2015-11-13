package Model.Konwersja;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretny;

abstract public class Kwantyzacja {
	
	public int ileStopni;
	
	public Kwantyzacja(int ileStopni) {
		this.ileStopni = ileStopni;
	}

	public abstract SygnalDyskretny kwantyzuj(FunkcjaCiagla sygnalCiagly);

}
