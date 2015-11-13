package Model.Konwersja;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretny;
	
public class KwantyzacjaZZaokragleniem extends Kwantyzacja {

	public KwantyzacjaZZaokragleniem(int ileStopni) {
		super(ileStopni);
	}

	public SygnalDyskretny kwantyzuj(FunkcjaCiagla sygnal, double poczatek, int czestotliwosc, double koniec, int ileStopni) {

		return null;
	}
	
	@Override
	public String toString() {
		return "Kwantyzacja z zaok¹gleniem";
	}

}
