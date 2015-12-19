package Model.Filtration;

import org.apache.commons.math3.complex.Complex;

public class Fourier {

	public static Complex[] DFT(Complex[] signal){
		int n = signal.length;
		Complex[] result = new Complex[n];
		double sumReal;
		double sumImag;
		double angle;
		for (int k = 0; k < n; k++) {
			sumReal = 0;
			sumImag = 0;
			for (int t = 0; t < n; t++) {
				angle = 2 * Math.PI * t * k / n;
				sumReal +=  signal[t].getReal() * Math.cos(angle) + signal[t].getImaginary() * Math.sin(angle);
				sumImag += -signal[t].getReal() * Math.sin(angle) +  signal[t].getImaginary() * Math.cos(angle);
			}
			result[k] = new Complex(sumReal, sumImag);
		}		
		return result;
	}
	
	public static Complex[] FFT(Complex[] x) {
	    int N = x.length;
	    if (N == 1) return new Complex[] { x[0] };
	    if (N % 2 != 0) { throw new RuntimeException("Ilosc probek nie jest potega 2"); }
	    
	    Complex[] parzyste = new Complex[N/2];
	    for (int k = 0; k < N/2; k++) {
	        parzyste[k] = x[2*k];
	    }
	    Complex[] q = FFT(parzyste);
	    
	    Complex[] nieparzyste  = parzyste;  
	    for (int k = 0; k < N/2; k++) {
	        nieparzyste[k] = x[2*k + 1];
	    }
	    Complex[] r = FFT(nieparzyste);

	    Complex[] y = new Complex[N];
	    for (int k = 0; k < N/2; k++) {
	        double WkN = -2 * k * Math.PI / N;
	        Complex wk = new Complex(Math.cos(WkN), Math.sin(WkN));
	        y[k]       = q[k].add(wk.multiply(r[k]));
	        y[k + N/2] = q[k].subtract(wk.multiply(r[k]));
	    }
	    return y;
	}
}



// Transformacje fouriera
//1 z definicji jako przeksztalcenie macierzowe
//2 szybka
// odwrotna

// Transformacja falkowa
// 2 z 3 warianty
