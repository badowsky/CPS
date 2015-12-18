package Controller;

import Model.Operations.Correlation;
import Model.Operations.Splot;
import Model.Signals.Discrete.DiscreteSignalReal;

public class SimpleTests {

	public static void main(String[] args) {
		System.out.println("Correlation test: " + testCorrelation());
		System.out.println("Splot test: " + testSplot());

	}
	
	private static boolean testCorrelation(){
		DiscreteSignalReal h = new DiscreteSignalReal();
		h.addY(1);
		h.addY(2);
		h.addY(3);
		h.addY(4);
		
		DiscreteSignalReal x = new DiscreteSignalReal();
		x.addY(5);
		x.addY(6);
		x.addY(7);
		
		Correlation correlation = new Correlation();
		DiscreteSignalReal result = correlation.DoOperation(h, x);
		
		boolean passed = true;
		
		if(result.getY(0) != 7) passed = false;
		if(result.getY(1) != 20) passed = false;
		if(result.getY(2) != 38) passed = false;
		if(result.getY(3) != 56) passed = false;
		if(result.getY(4) != 39) passed = false;
		if(result.getY(5) != 20) passed = false;
		
		return passed;
	}
	
	private static boolean testSplot(){
		DiscreteSignalReal h = new DiscreteSignalReal();
		h.addY(1);
		h.addY(2);
		h.addY(3);
		h.addY(4);
		
		DiscreteSignalReal x = new DiscreteSignalReal();
		x.addY(5);
		x.addY(6);
		x.addY(7);
		
		Splot splot = new Splot();
		DiscreteSignalReal result = splot.DoOperation(h, x);
		
		boolean passed = true;
		
		if(result.getY(0) != 5) passed = false;
		if(result.getY(1) != 16) passed = false;
		if(result.getY(2) != 34) passed = false;
		if(result.getY(3) != 52) passed = false;
		if(result.getY(4) != 45) passed = false;
		if(result.getY(5) != 28) passed = false;
		
		return passed;
	}

}
