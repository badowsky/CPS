package Model.Operacje;

import Model.Sygnaly.Dyskretne.SygnalDyskretny;

public class Splot implements OperacjaNaSygnalach{

	public SygnalDyskretny DoOperation(SygnalDyskretny h, SygnalDyskretny x) {
		SygnalDyskretny splot = new SygnalDyskretny();
		
		int M = h.x.size();
		int N = x.x.size();
		double y;
		for(int n = 0 ; n < M+N ; n++){
			y = 0;
			for(int k = 0 ; k < M ; k++){
				if(n-k >= 0 && n-k < x.y.size()){
					y += h.y.get(k)*x.y.get(n-k);
				};
			}
			splot.x.add((double)n);
			splot.y.add(y);
		}
		return splot;
	}

	@Override
	public String toString() {
		return "Splot";
	}
	
	

}
