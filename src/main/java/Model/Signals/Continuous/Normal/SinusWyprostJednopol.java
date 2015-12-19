package Model.Signals.Continuous.Normal;

import Model.Signals.Continuous.ContinuousSignal;

public class SinusWyprostJednopol extends ContinuousSignal {
	
	double amplituda, przesuniecie, okres;
	
	public SinusWyprostJednopol(){
		super("Sinus Wyprostowany Jednopo³ówkowo");
		parametersNames = new String[]{
				"Amplituda",
				"Pprzesuniecie",
				"Okres"};
	}

	@Override
	public double getValue(double x) {
		return 0.5 * amplituda * ((Math.sin(((2 * Math.PI) / okres) * (x + przesuniecie))) + Math.abs(Math.sin((2 * Math.PI / okres) * (x - przesuniecie))));
	}
	
	@Override
	public Double srednia(){
		return amplituda/Math.PI;
	}
	
	@Override
	public Double sredniaBezwzgledna(){
		return amplituda/Math.PI;
	}
	
	@Override
	public Double mocSrednia(){
		return amplituda * amplituda / 4;
	}

	@Override
	public void setParams(Double[] params) {
		this.amplituda = params[0];
		this.przesuniecie = params[1];
		this.okres = params[2];	
	}

}
