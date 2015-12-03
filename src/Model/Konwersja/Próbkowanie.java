package Model.Konwersja;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

public class Pr�bkowanie {
	
	public static SygnalDyskretnyReal pr�bkuj(FunkcjaCiagla sygnal, double poczatek, int czestotliwosc, double koniec){
		return pr�bkuj(sygnal, poczatek, czestotliwosc, koniec, false);
	}
	
	public static SygnalDyskretnyReal pr�bkuj(FunkcjaCiagla sygnal, double poczatek, int czestotliwosc, double koniec, boolean showAsContinuous){
		SygnalDyskretnyReal result = new SygnalDyskretnyReal(showAsContinuous);
		
		for (double i = poczatek; i < koniec; i += 1.0/czestotliwosc) {
			result.addX(i);
			result.addY(sygnal.getValue(i));
		}
		
		return result;
	}
}
