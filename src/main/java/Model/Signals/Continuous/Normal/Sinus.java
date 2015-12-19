package Model.Signals.Continuous.Normal;

import Model.Signals.Continuous.ContinuousSignal;

public class Sinus extends ContinuousSignal {
	
	double amplituda, przesuniecie, okres;
	
	public Sinus(){
		super("Sinus");
		parametersNames = new String[]{
				"Amplituda",
				"Pprzesuniecie",
				"Okres"};
	}

	@Override
	public double getValue(double x) {
		return amplituda * Math.sin(((2 * Math.PI)/okres) * (x + przesuniecie));
	}
	
	@Override
	public Double srednia(){
		return 0.0;
	}
	
	@Override
	public Double sredniaBezwzgledna(){
		return 2*amplituda/Math.PI;
	}
	
	@Override
	public Double mocSrednia(){
		return amplituda * amplituda / 2;
	}

	@Override
	public void setParams(Double[] params) {
		this.amplituda = params[0];
		this.przesuniecie = params[1];
		this.okres = params[2];
	}

}
