package Controller.GuiView;

import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.SygnalDyskretnyReal;

public abstract class SignalsChangedListener {
	
	
	public abstract void notifyFirstSignalChanged(SygnalDyskretnyReal firstSignal, ContinuousSignal firstSignalContinuous);
	
	public abstract void notifySecondSignalChanged(SygnalDyskretnyReal secondSignal, ContinuousSignal secondSignalContinuous);
}
