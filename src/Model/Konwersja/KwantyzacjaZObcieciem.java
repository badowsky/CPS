package Model.Konwersja;

import java.util.ArrayList;
import java.util.TreeSet;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretny;

public class KwantyzacjaZObcieciem extends Kwantyzacja {

	public KwantyzacjaZObcieciem(int ileStopni) {
		super(ileStopni);
	}

	public SygnalDyskretny kwantyzuj(FunkcjaCiagla sygnalCiagly) {
		
		ArrayList <Double> quantizedSignalX = new ArrayList<Double>();
		ArrayList <Double> quantizedSignalY = new ArrayList<Double>();
		
		ArrayList <Double> sampledSignalX = new ArrayList<Double>();
		ArrayList <Double> sampledSignalY = new ArrayList<Double>();
			
		double max = sampledSignalY.get(0);
		double min = sampledSignalY.get(0);
		
		for (int i=0; i<sampledSignalY.size(); i++) {
			
			if (max < sampledSignalY.get(i)) {
				max = sampledSignalY.get(i);
			}
			
			if (min > sampledSignalY.get(i)) {
				min = sampledSignalY.get(i);
			}
		}
		
		double sub = max - min;

		TreeSet<Double> treeSet = new TreeSet<Double>();
		
		for (int i=0; i < ileStopni; i++) {
			treeSet.add(min + ((sub / (ileStopni)) * i));
		}
	
		for (int i=0; i<sampledSignalY.size(); i++) {
			Double tempX, tempY;
			tempX = sampledSignalX.get(i);
			tempY = treeSet.floor(sampledSignalY.get(i));
			quantizedSignalX.add(tempX);
			quantizedSignalY.add(tempY);
		}
		
		return null;
	}
	
}
