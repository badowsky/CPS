package Model.Signals.Continuous.Normal;

import Model.Signals.Continuous.ContinuousSignal;

public class Prostokatny extends ContinuousSignal {
	
	double amplituda, przesuniecie, wspWypelnienia, okres;
	int k;
	
	public Prostokatny() {
		super("Prostokatny");
		parametersNames = new String[]{
				"Amplituda",
				"Pprzesuniecie",
				"Wspó³czynnik wype³nienia",
				"Okres"};
		
		k = 0;
	}

	@Override
	public double getValue(double x) {
		x+=przesuniecie;
		if(x >= okres * (k + 1)) {
			k++;
		}
		if( x >= (k * okres) && x < (wspWypelnienia * okres + k * okres)) {
			return amplituda;
		} 
		else if( x >= (wspWypelnienia * okres - k * okres) && x < (okres + k * okres)) {
			return 0.0;
		}else{
			return 0.0;
		}
	}
	
	@Override
	public Double srednia(){
		return amplituda * wspWypelnienia;
	}
	
	@Override
	public Double sredniaBezwzgledna(){
		return amplituda * wspWypelnienia;
	}
	
	@Override
	public Double mocSrednia(){
		return amplituda * amplituda * wspWypelnienia;
	}

	@Override
	public void setParams(Double[] params) {
		this.amplituda = params[0];
		this.przesuniecie = params[1];
		this.wspWypelnienia = params[2];
		this.okres = params[3];
		k=0;
	}

}
