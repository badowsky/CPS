package Model.Conversion;

import java.util.TreeSet;

import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;
	
public class KwantyzacjaZZaokragleniem extends Quantization {

	public KwantyzacjaZZaokragleniem(int ileStopni) {
		super(ileStopni);
	}

	public DiscreteSignalReal kwantyzuj(ContinuousSignal sygnal, double poczatek, int czestotliwosc, double koniec, int ileStopni) {
		
		DiscreteSignalReal discreteSignal = Sampling.sample(sygnal, poczatek, czestotliwosc, koniec);
		
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
			treeSet.add(min + (sub / ileStopni) * i);
		}
	
		DiscreteSignalReal discreteSignalReturn = new DiscreteSignalReal();
		
		for (int i=0; i<discreteSignal.size(); i++) {
			Double tempX, tempY;
			tempX = discreteSignal.getX(i);
			tempY = treeSet.floor(discreteSignal.getY(i));
			discreteSignalReturn.addX(tempX/2);
			discreteSignalReturn.addY(tempY/2);
		}
		
		return discreteSignalReturn;
	}
	
	@Override
	public String toString() {
		return "Kwantyzacja z zaokragleniem";
	}

}
