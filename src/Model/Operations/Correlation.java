package Model.Operations;

import java.util.List;

import Controller.GuiView.SignalPanelController;
import Model.Signals.Discrete.DiscreteSignalReal;

public class Correlation implements OperationOnSignals{

	public DiscreteSignalReal DoOperation(DiscreteSignalReal h, DiscreteSignalReal x) {
		DiscreteSignalReal correlation = new DiscreteSignalReal();
		
		//System.out.println("Korelacja - start");
		
		int M = h.size();
		int N = x.size();
		System.out.println("M = " + M + "\nN = " + N);
		double y;
		
		int hIndex, xIndex;
		int i = 0;
		for(int n = -(Math.min(M, N)-1); i < M+N-1; n++){
			//System.out.print("\nRhx(" + n + ") = ");
			y = 0;
			for(int k = 0 ; k < M ; k++){
				hIndex = k;
				xIndex = k-n;
				if (xIndex >= 0 && xIndex < N && hIndex >= 0 && hIndex < M){
					y += h.getY(hIndex)*x.getY(xIndex);
					//System.out.print("h(" + hIndex + ") * x(" + xIndex +") + ");
				};
			}
			correlation.addY(y);
			correlation.addX(i++);
		}
		testCzujnik(correlation);
		return correlation;
	}
	
	public void testCzujnik(DiscreteSignalReal correlation){
		int startRightHalf = (int) Math.floor((correlation.size()/2.0));
		
		List<Double> rightHalf = correlation.getRawValues().subList(startRightHalf, correlation.size());
		
		int samplesDistance = 0;
		Double maxVal = rightHalf.get(0);
		
		Double currentVal;
		for(int i = 0; i < rightHalf.size(); i++){
			currentVal = rightHalf.get(i);
			if(maxVal < currentVal) {
				maxVal = currentVal;
				samplesDistance = i;
			}
		}
		System.out.println("Ca³kowity rozmiar = " + correlation.size());
		System.out.println("Pierwszy index prawej po³owy = " + startRightHalf);
		System.out.println("Odleg³oœc próbek = " + samplesDistance);
		System.out.println("Maksymalna wartosc = " + maxVal);
		double shift = (double)samplesDistance/SignalPanelController.CZEST_PROB_F_CIAG;
		System.out.println("Przesuniecie = " + shift);
		double distanceInKm= shift*300000;
		System.out.println("Odleg³oœc jak¹ pokona³ sygna³ [km] = " + distanceInKm);
		System.out.println("Odleg³oœc od przeszkody [km] = " + distanceInKm/2);
		
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
