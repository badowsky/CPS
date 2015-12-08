package Model.Sygnaly.Ciagle.Zwykle;

import Model.Sygnaly.Ciagle.SygnalCiagly;

public class SinusWyprostDwupol extends SygnalCiagly {

	double amplituda, przesuniecie, okres;
	
	public SinusWyprostDwupol(){
		super("Sinus Wyprostowany Dwupo³ówkowo");		
		parametersNames = new String[]{
				"Amplituda",
				"Pprzesuniecie",
				"Okres"};
	}
	
	@Override
	public double getValue(double x) {
		return amplituda * Math.abs(Math.sin((2 * Math.PI / okres) * (x - przesuniecie)));
	}

	@Override
	public Double srednia(){
		return 2*amplituda/Math.PI;
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
