package Model.Konwersja;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

public abstract class KonwersjaCA extends FunkcjaCiagla {
	
	SygnalDyskretnyReal sygnal;
	
	protected KonwersjaCA(String name) {
		super(name);
	}
	
	public void konwertuj(SygnalDyskretnyReal sygnal){
		this.sygnal = sygnal;
	}
}
