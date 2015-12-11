package Model.Konwersja;

import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.SygnalDyskretnyReal;

public abstract class KonwersjaCA extends ContinuousSignal {
	
	SygnalDyskretnyReal sygnal;
	
	protected KonwersjaCA(String name) {
		super(name);
	}
	
	public void konwertuj(SygnalDyskretnyReal sygnal){
		this.sygnal = sygnal;
	}
}
