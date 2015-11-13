package Model.Konwersja;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretny;

public class Pr�bkowanie {
	
	public static SygnalDyskretny pr�bkuj(FunkcjaCiagla sygnal, double poczatek, int czestotliwosc, double koniec){
		return pr�bkuj(sygnal, poczatek, czestotliwosc, koniec, false);
	}
	
	public static SygnalDyskretny pr�bkuj(FunkcjaCiagla sygnal, double poczatek, int czestotliwosc, double koniec, boolean showAsContinuous){
		SygnalDyskretny result = new SygnalDyskretny(showAsContinuous);
		
		for (double i = poczatek; i < koniec; i += 1.0/czestotliwosc) {
			result.x.add(i);
			result.y.add(sygnal.getValue(i));
		}
		
		return result;
	}

}
