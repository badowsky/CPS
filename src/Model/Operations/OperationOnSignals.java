package Model.Operations;

import Model.Signals.Discrete.SygnalDyskretnyReal;

public interface OperationOnSignals {
	
	public SygnalDyskretnyReal DoOperation(SygnalDyskretnyReal a, SygnalDyskretnyReal b);

}
