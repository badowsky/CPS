package Model.Sygnaly.Ciagle.Szumy;

import Model.Sygnaly.Ciagle.SygnalCiagly;

public class RozkladJednostajny extends SygnalCiagly {

	double amplituda;
	
	public RozkladJednostajny() {
		super("Szum o Rozk³adzie Jednostajnym");		
		parametersNames = new String[]{
				"Amplituda"};
	}
	
	@Override
	public double getValue(double x) {
		return (Math.random() * 2 * amplituda - amplituda);
	}

	@Override
	public void setParams(Double[] params) {
		amplituda = params[0];		
	}

}
