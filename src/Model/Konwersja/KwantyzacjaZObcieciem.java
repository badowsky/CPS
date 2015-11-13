package Model.Konwersja;

import java.util.TreeSet;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretny;
import Model.Konwersja.Próbkowanie;

public class KwantyzacjaZObcieciem extends Kwantyzacja {

	public KwantyzacjaZObcieciem(int ileStopni) {
		super(ileStopni);
	}

	public SygnalDyskretny kwantyzuj(FunkcjaCiagla sygnal, double poczatek, int czestotliwosc, double koniec, int ileStopni) {
		
		SygnalDyskretny discreteSignal = Próbkowanie.próbkuj(sygnal, poczatek, czestotliwosc, koniec);
		
		double max = discreteSignal.x.get(0);
		double min = discreteSignal.y.get(0);
		
		for (int i=0; i<discreteSignal.y.size(); i++) {
			
			if (max < discreteSignal.y.get(i)) {
				max = discreteSignal.y.get(i);
			}
			
			if (min > discreteSignal.y.get(i)) {
				min = discreteSignal.y.get(i);
			}
		}
		
		double sub = max - min;

		TreeSet<Double> treeSet = new TreeSet<Double>();
		
		for (int i=0; i < ileStopni; i++) {
			treeSet.add(min + ((sub / (ileStopni)) * i));
		}
	
		SygnalDyskretny discreteSignalReturn = new SygnalDyskretny();
		
		for (int i=0; i<discreteSignal.y.size(); i++) {
			Double tempX, tempY;
			tempX = discreteSignal.x.get(i);
			tempY = treeSet.floor(discreteSignal.y.get(i));
			discreteSignalReturn.x.add(tempX);
			discreteSignalReturn.y.add(tempY);
		}
		
		return discreteSignalReturn;
	}
	
	@Override
	public String toString() {
		return "Kwantyzacja z obciêciem";
	}
	
}
