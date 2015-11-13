package Model.FunkcjeCiagle.Sygnaly;

import Model.FunkcjeCiagle.FunkcjaCiagla;

public class ProstokatnySymetryczny extends FunkcjaCiagla {

	double amplituda, przesuniecie, wspWypelnienia, okres;
	int k;

	public ProstokatnySymetryczny() {
		super("Prostokatny Symetryczny");
		parametersNames = new String[] { "Amplituda", "Pprzesuniecie", "Wsp�czynnik wype�nienia", "Okres" };

		k = 0;
	}

	@Override
	public double getValue(double x) {

		if (x >= okres * (k + 1) + przesuniecie) {
			k++;
		}
		if (x >= (k * okres + przesuniecie) && x < (wspWypelnienia * okres + k * okres + przesuniecie)) {
			return amplituda;
		} else if (x >= (wspWypelnienia * okres - k * okres + przesuniecie) && x < (okres + k * okres + przesuniecie)) {
			return -amplituda;
		} else {
			return 0.0;
		}
	}

	@Override
	public Double srednia() {
		return amplituda * (2 * wspWypelnienia - 1);
	}

	@Override
	public Double sredniaBezwzgledna() {
		return amplituda;
	}

	@Override
	public Double mocSrednia() {
		return amplituda * amplituda;
	}

	@Override
	public void setParams(Double[] params) {
		this.amplituda = params[0];
		this.przesuniecie = params[1];
		this.wspWypelnienia = params[2];
		this.okres = params[3];

	}

}
