package Model.Konwersja;

import java.util.ArrayList;
import java.util.TreeSet;

public class Kwantyzacja {
	
	public ArrayList <Double> sampledSignalX = new ArrayList<Double>();
	public ArrayList <Double> sampledSignalY = new ArrayList<Double>();
	
	public ArrayList <Double> quantizedSignalX = new ArrayList<Double>();
	public ArrayList <Double> quantizedSignalY = new ArrayList<Double>();
	
	double numberOfQuantizationLevels = 20;
	
	public void kwantyzacjaZObcieciem() {
		
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
		
		for (int i=0; i < numberOfQuantizationLevels; i++) {
			treeSet.add(min + ((sub / (numberOfQuantizationLevels)) * i));
		}
	
		for (int i=0; i<sampledSignalY.size(); i++) {
			Double tempX, tempY;
			tempX = sampledSignalX.get(i);
			tempY = treeSet.floor(sampledSignalY.get(i));
			quantizedSignalX.add(tempX);
			quantizedSignalY.add(tempY);
		}
	}

}
