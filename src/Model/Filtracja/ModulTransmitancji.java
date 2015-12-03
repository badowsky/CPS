package Model.Filtracja;

import org.apache.commons.math3.complex.Complex;

import Model.Sygnaly.Dyskretne.SygnalDyskretnyCmplx;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

public class ModulTransmitancji {

	public static SygnalDyskretnyReal oblicz(SygnalDyskretnyCmplx filtr){
		SygnalDyskretnyReal modulTransmitancji = new SygnalDyskretnyReal();
		
		Complex y;
		for(int i = 0; i < filtr.size() ; i++){
			//modulTransmitancji.addY(filtr.getComplexY(i).divide(filtr.getComplexX(i).pow(i)).abs());
			y = new Complex(0);
			for(int j = 0; j < filtr.size() ; j++){
				y = y.add(filtr.getComplexY(j).divide((double)Math.pow(i, j)));
			}
			modulTransmitancji.addY(y.abs());;
			modulTransmitancji.addX(i);
		}
		
		return modulTransmitancji;
	}
}
