package Model.Operations;

import Model.Signals.Discrete.DiscreteSignalReal;

public interface OperationOnSignals {
	
	public DiscreteSignalReal DoOperation(DiscreteSignalReal a, DiscreteSignalReal b);

}
