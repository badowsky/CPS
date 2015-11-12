package Model.Konwersja;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretny;

public class Pr�bkowanie {
	
	public static SygnalDyskretny pr�bkuj(FunkcjaCiagla sygnal, double poczatek, double czestotliwosc, double koniec){
		SygnalDyskretny result = new SygnalDyskretny();
		
		for (double i = poczatek; i < koniec; i += 1/czestotliwosc) {
			result.x.add(i);
			result.y.add(sygnal.getValue(i));
		}
		
		return result;
	}

}
