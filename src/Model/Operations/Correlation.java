package Model.Operations;

import Model.Signals.Discrete.DiscreteSignalReal;

public class Correlation implements OperationOnSignals{

	public DiscreteSignalReal DoOperation(DiscreteSignalReal h, DiscreteSignalReal x) {
		DiscreteSignalReal splot = new DiscreteSignalReal();
		
		int M = h.size();
		int N = x.size();
		double y;
		int roznica = -(Math.min(M, N)-1);
		for(int n = roznica; n < N ; n++){
			y = 0;
			for(int k = 0 ; k < M ; k++){
				if (k+n > 0 && k+n < N){
					y += h.getY(k)*x.getY(k+n);
				};
			}
			splot.addX((double)n-roznica);
			splot.addY(y);
		}
		return splot;
	}
	
//	public SygnalDyskretnyCmplx DoOperation(SygnalDyskretnyCmplx h, SygnalDyskretnyCmplx x) {
//		SygnalDyskretnyCmplx splot = new SygnalDyskretnyCmplx();
//		
//		int M = h.size();
//		int N = x.size();
//		Complex y;
//		for(int n = 0 ; n < M+N ; n++){
//			y = new Complex(0);
//			for(int k = 0 ; k < M ; k++){
//				if(n-k >= 0 && n-k < x.size()){
//					y.add(h.getY(k).multiply(x.getY(n-k)));
//				};
//			}
//			splot.addX((double)n);
//			splot.addY(y);
//		}
//		return splot;
//	}
//	
//	public SygnalDyskretnyCmplx DoOperation(SygnalDyskretnyCmplx h, SygnalDyskretnyReal x) {
//		SygnalDyskretnyCmplx splot = new SygnalDyskretnyCmplx();
//		
//		int M = h.size();
//		int N = x.size();
//		Complex y;
//		for(int n = 0 ; n < M+N ; n++){
//			y = new Complex(0);
//			for(int k = 0 ; k < M ; k++){
//				if(n-k >= 0 && n-k < x.size()){
//					y.add(h.getY(k).multiply(x.getY(n-k)));
//				};
//			}
//			splot.addX((double)n);
//			splot.addY(y);
//		}
//		return splot;
//	}

	@Override
	public String toString() {
		return "Korelacja";
	}
	
	

}
