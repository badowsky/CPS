package Model.Filtration.Windows;

import Model.Filtration.Filter;
import Model.Filtration.Filter.FilterBuilder;
import Model.Signals.Discrete.DiscreteSignalReal;

public abstract class Window {
	
	protected int M;
	
	protected Window(int M){
		this.M = M;
	}

	public abstract double getValue(double n);
	
	public DiscreteSignalReal generujPodglad(){
		DiscreteSignalReal syg = new DiscreteSignalReal();
		for(int n = 0; n <= M ; n++){
			syg.addY(this.getValue(n));
			syg.addX(n);
		}
		return syg;
	}
	
	public static abstract class WindowBuilder {
		int M;
		public WindowBuilder setM(int M){
			this.M = M;
			return this;
		}
		public abstract Window build();
	}
}
