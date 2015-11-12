package Model.FunkcjeCiagle.Sygnaly;

import Model.FunkcjeCiagle.FunkcjaCiagla;

public class Sinus extends FunkcjaCiagla {
	
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
		return amplituda * Math.sin(((2 * Math.PI)/okres) * (x - przesuniecie));
	}
	
	@Override
	public double srednia(){
		return 0;
	}
	
	@Override
	public double sredniaBezwzgledna(){
		return 2*amplituda/Math.PI;
	}
	
	@Override
	public double mocSrednia(){
		return amplituda * amplituda / 2;
	}

	@Override
	public void setParams(Double[] params) {
		this.amplituda = params[0];
		this.przesuniecie = params[1];
		this.okres = params[2];
	}

}
