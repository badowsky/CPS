package Model.Sygnaly.Ciagle.Zwykle;

import Model.Sygnaly.Ciagle.SygnalCiagly;

public class Trojkatny extends SygnalCiagly {

	double amplituda, przesuniecie, wspWypelnienia, okres;
	int k;

	public Trojkatny() {
		super("Tr�jk�tny");		
		parametersNames = new String[]{
				"Amplituda",
				"Pprzesuniecie",
				"Wsp�czynnik wype�nienia",
				"Okres"};
		k = 0;
	}

	@Override
	public double getValue(double x) {
		if (x >= okres * (k + 1) + przesuniecie){
			k++;
		}
		
		if( x >= (k * okres + przesuniecie) && x < (wspWypelnienia * okres + k * okres + przesuniecie)) {
			return amplituda * (x - k * okres - przesuniecie) / (wspWypelnienia * okres);
		}
		else if( x >= (wspWypelnienia * okres + przesuniecie + k * okres) && x < (okres + k * okres + przesuniecie)) {
			return (-amplituda * (x - k * okres - przesuniecie) / (okres * (1 - wspWypelnienia))) + (amplituda / (1 - wspWypelnienia));
		}
		else {
			return 0.0;
		}
	}
	
	@Override
	public Double srednia(){
		return amplituda/2;
	}
	
	@Override
	public Double sredniaBezwzgledna(){
		return amplituda/2;
	}
	
	@Override
	public Double mocSrednia(){
		return amplituda * (wspWypelnienia - (wspWypelnienia * wspWypelnienia + 1) / 2) / (wspWypelnienia - 1) + amplituda * wspWypelnienia * okres / 2;
	}

	@Override
	public void setParams(Double[] params) {
		this.amplituda = params[0];
		this.przesuniecie = params[1];
		this.wspWypelnienia = params[2];
		this.okres = params[3];
		k = 0;
	}

}
