package Helpers;

public class Utils {

	public static double rounding(double x) {
		double temp = x * 10;
		int temp2 = (int)temp;
		
		return temp2 / 10.0;
	}

}
