package Operations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Model.Operations.Correlation;
import Model.Signals.Discrete.DiscreteSignalReal;

public class CorrelationTest {

	@Test
	public void testCorrelation(){
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

		assertEquals(result.getY(0), 7, 0);
		assertEquals(result.getY(1), 20, 0);
		assertEquals(result.getY(2), 38, 0);
		assertEquals(result.getY(3), 56, 0);
		assertEquals(result.getY(4), 39, 0);
		assertEquals(result.getY(5), 20, 0);
	}
}
