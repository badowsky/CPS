package Model.Operations;

import Model.Signals.Discrete.DiscreteSignalReal;

public class Multiplication implements OperationOnSignals {

	@Override
	public DiscreteSignalReal DoOperation(DiscreteSignalReal a, DiscreteSignalReal b) {
		DiscreteSignalReal result = new DiscreteSignalReal();

		for (int i = 0; i < a.size(); i++) {
			result.addX(a.getX(i));
			result.addY(a.getY(i) * b.getY(i));
		}

		return result;
	}
	
	@Override
	public String toString() {
		return "Mno¿enie";
	}

}
