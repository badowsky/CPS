package Model.Konwersja;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Sygnaly.Dyskretne.SygnalDyskretny;

public abstract class KonwersjaCA extends FunkcjaCiagla {
	
	SygnalDyskretny sygnal;
	
	protected KonwersjaCA(String name) {
		super(name);
	}
	
	public void konwertuj(SygnalDyskretny sygnal){
		this.sygnal = sygnal;
	}
}
