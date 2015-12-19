package Model.Conversion;

import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;

public abstract class ConversionCA extends ContinuousSignal {
	
	DiscreteSignalReal sygnal;
	
	protected ConversionCA(String name) {
		super(name);
	}
	
	public void konwertuj(DiscreteSignalReal sygnal){
		this.sygnal = sygnal;
	}
}
