package Model.Konwersja;

import Model.Sygnaly.Ciagle.SygnalCiagly;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

public abstract class KonwersjaCA extends SygnalCiagly {
	
	SygnalDyskretnyReal sygnal;
	
	protected KonwersjaCA(String name) {
		super(name);
	}
	
	public void konwertuj(SygnalDyskretnyReal sygnal){
		this.sygnal = sygnal;
	}
}
