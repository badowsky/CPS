package Operations;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Operations.Splot;
import Model.Signals.Discrete.DiscreteSignalReal;

public class SplotTest {

	@Test
	public void testSplot(){
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
		
		assertEquals(result.getY(0), 5, 0);
		assertEquals(result.getY(1), 16, 0);
		assertEquals(result.getY(2), 34, 0);
		assertEquals(result.getY(3), 52, 0);
		assertEquals(result.getY(4), 45, 0);
		assertEquals(result.getY(5), 28, 0);
		
	}
}
