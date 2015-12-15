package Controller.GuiView;

import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;

public abstract class SignalsChangedListener {
	
	
	public abstract void notifyFirstSignalChanged(DiscreteSignalReal firstSignal, ContinuousSignal firstSignalContinuous);
	
	public abstract void notifySecondSignalChanged(DiscreteSignalReal secondSignal, ContinuousSignal secondSignalContinuous);
}
