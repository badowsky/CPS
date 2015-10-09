package Helpers;

public class Utils {

	public static double rounding(double x) {
		double temp = x * 10;
		int temp2 = (int)temp;
		
		return temp2 / 10.0;
	}
	
	public static int impuls(double x) {
		if (x == 0) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
