package Model.Operations;

import Model.Signals.Discrete.DiscreteSignalReal;

public class Splot implements OperationOnSignals{

	public DiscreteSignalReal DoOperation(DiscreteSignalReal h, DiscreteSignalReal x) {
		DiscreteSignalReal splot = new DiscreteSignalReal();
		
		int M = h.size();
		int N = x.size();
		double y;
		for(int n = 0 ; n < M+N ; n++){
			y = 0;
			for(int k = 0 ; k < M ; k++){
				if(n-k >= 0 && n-k < x.size()){
					y += h.getY(k)*x.getY(n-k);
				};
			}
			splot.addY(y);
			splot.addX((double)n);
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
		return "Splot";
	}
	
	

}
