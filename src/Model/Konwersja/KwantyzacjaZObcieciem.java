package Model.Konwersja;

import java.util.TreeSet;

import Model.Konwersja.Próbkowanie;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.SygnalDyskretnyReal;

public class KwantyzacjaZObcieciem extends Kwantyzacja {

	public KwantyzacjaZObcieciem(int ileStopni) {
		super(ileStopni);
	}

	public SygnalDyskretnyReal kwantyzuj(ContinuousSignal sygnal, double poczatek, int czestotliwosc, double koniec, int ileStopni) {
		
		SygnalDyskretnyReal discreteSignal = Próbkowanie.próbkuj(sygnal, poczatek, czestotliwosc, koniec);
		
		double max = discreteSignal.getX(0);
		double min = discreteSignal.getY(0);
		
		for (int i=0; i<discreteSignal.size(); i++) {
			
			if (max < discreteSignal.getY(i)) {
				max = discreteSignal.getY(i);
			}
			
			if (min > discreteSignal.getY(i)) {
				min = discreteSignal.getY(i);
			}
		}
		
		double sub = max - min;

		TreeSet<Double> treeSet = new TreeSet<Double>();
		
		for (int i=0; i < ileStopni; i++) {
			treeSet.add(min + ((sub / (ileStopni)) * i));
		}
	
		SygnalDyskretnyReal discreteSignalReturn = new SygnalDyskretnyReal();
		
		for (int i=0; i<discreteSignal.size(); i++) {
			Double tempX, tempY;
			tempX = discreteSignal.getX(i);
			tempY = treeSet.floor(discreteSignal.getY(i));
			discreteSignalReturn.addX(tempX);
			discreteSignalReturn.addY(tempY);
		}
		
		return discreteSignalReturn;
	}
	
	@Override
	public String toString() {
		return "Kwantyzacja z obcieciem";
	}
	
}
