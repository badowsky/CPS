package Helpers;

import org.apache.commons.math3.complex.Complex;

public class Utils {

	public static double rounding(double x) {
		double temp = x * 10;
		int temp2 = (int)temp;
		
		return temp2 / 10.0;
	}
	
	public static Complex[] abs(Complex[] tab){
		for(int i = 0 ; i < tab.length ; i++){
			tab[i] = new Complex(tab[i].abs());
		}
		return tab;
	}

}
