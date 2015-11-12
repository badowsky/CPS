package Model.FunkcjeCiagle.Sygnaly;

import Model.FunkcjeCiagle.FunkcjaCiagla;

public class SinusWyprostJednopol extends FunkcjaCiagla {
	
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
		return 0.5 * amplituda * ((Math.sin(((2 * Math.PI) / okres) * (x - przesuniecie))) + Math.abs(Math.sin((2 * Math.PI / okres) * (x - przesuniecie))));
	}
	
	@Override
	public double srednia(){
		return amplituda/Math.PI;
	}
	
	@Override
	public double sredniaBezwzgledna(){
		return amplituda/Math.PI;
	}
	
	@Override
	public double mocSrednia(){
		return amplituda * amplituda / 4;
	}

	@Override
	public void setParams(Double[] params) {
		this.amplituda = params[0];
		this.przesuniecie = params[1];
		this.okres = params[2];	
	}

}
