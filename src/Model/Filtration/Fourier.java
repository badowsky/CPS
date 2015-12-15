package Model.Filtration;

import org.apache.commons.math3.complex.Complex;

import Model.Signals.Discrete.DiscreteSignalComplex;
import Model.Signals.Discrete.DiscreteSignalReal;

public class Fourier {

	public static DiscreteSignalReal DFT(DiscreteSignalComplex s){
		DiscreteSignalReal wynik = new DiscreteSignalReal();
		int n = s.size();
		for (int k = 0; k < n; k++) {  // For each output element
			double sumreal = 0;
			double sumimag = 0;
			for (int t = 0; t < n; t++) {  // For each input element
				double angle = 2 * Math.PI * t * k / n;
				sumreal +=  s.getY(t).getReal() * Math.cos(angle) + s.getY(t).getImaginary() * Math.sin(angle);
				sumimag += -s.getY(t).getReal() * Math.sin(angle) +  s.getY(t).getImaginary() * Math.cos(angle);
			}
			wynik.addY(new Complex(sumreal, sumimag).abs());
			wynik.addX(k);
		}
		
		
		return wynik;
	}
}
