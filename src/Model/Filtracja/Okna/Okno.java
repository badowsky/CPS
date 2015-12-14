package Model.Filtracja.Okna;

import Model.Signals.Discrete.SygnalDyskretnyReal;

public abstract class Okno {
	
	protected int M;
	
	protected Okno(int M){
		this.M = M;
	}

	public abstract double getValue(double n);
	
	public SygnalDyskretnyReal generujPodglad(){
		SygnalDyskretnyReal syg = new SygnalDyskretnyReal();
		for(int n = 0; n <= M ; n++){
			syg.addY(this.getValue(n));
			syg.addX(n);
		}
		return syg;
	}
}
