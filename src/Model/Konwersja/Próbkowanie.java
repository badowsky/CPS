package Model.Konwersja;

import Model.Sygnaly.Ciagle.SygnalCiagly;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

public class Próbkowanie {
	
	public static SygnalDyskretnyReal próbkuj(SygnalCiagly sygnal, double poczatek, int czestotliwosc, double koniec){
		return próbkuj(sygnal, poczatek, czestotliwosc, koniec, false);
	}
	
	public static SygnalDyskretnyReal próbkuj(SygnalCiagly sygnal, double poczatek, int czestotliwosc, double koniec, boolean showAsContinuous){
		SygnalDyskretnyReal result = new SygnalDyskretnyReal(showAsContinuous);
		
		for (double i = poczatek; i < koniec; i += 1.0/czestotliwosc) {
			result.addX(i);
			result.addY(sygnal.getValue(i));
		}
		
		return result;
	}
}
