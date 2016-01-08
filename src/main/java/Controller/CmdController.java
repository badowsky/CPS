package Controller;

import org.apache.commons.math3.complex.Complex;

import Model.Conversion.Sampling;
import Model.Filtration.Fourier;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Continuous.Normal.S1;
import Model.Signals.Continuous.Normal.S2;
import Model.Signals.Discrete.DiscreteSignalReal;
import Transformation.DWT;
import Transformation.DWT.Direction;
import Transformation.Wavelet;
import View.Graph;
import View.Graph.GraphType;

public class CmdController {
	
	public static void main(String[] args) {
		
		// ************** PARAMETRY ***************
		ContinuousSignal continuousSignal = new S2();
		int signalLength = 1024;
		Wavelet waveletType = Wavelet.Daubechies;
		int order = 6;
		int L = (int) (Math.log(signalLength)/Math.log(2)); // which power of 2 is signalLength
		
		
		boolean transformationConnected = true;
		// ********** PARAMETRY - Koniec **********
		
		DiscreteSignalReal signal = Sampling.sample(continuousSignal, 0, 16, signalLength/16);
		Graph.printSignal("Sygnał oryginalny", "Numer próbki", "Wartość", signal.toPrimitive(), true);
		Complex[] x = signal.toComplex();
		
//		FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
//        Complex[] apacheFftTransformed = fft.transform(x, TransformType.FORWARD);
        
		System.out.println("Transformacja sygnału długości " + signal.size() + " próbek:");
        
		long startTime = System.currentTimeMillis();
        Complex[] myDftTransformed = Fourier.DFT(x);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Czas wykonywania dla DFT: " + elapsedTime + " ms");
        
        startTime = System.currentTimeMillis();
        Complex[] myFftTransformed = Fourier.FFT(x);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Czas wykonywania dla FFT: " + elapsedTime + " ms");
        
        Graph.printComplex("DFT W1", myDftTransformed, GraphType.W1, transformationConnected);
        Graph.printComplex("DFT W2", myDftTransformed, GraphType.W2, transformationConnected);
        
        Graph.printComplex("FFT W1", myFftTransformed, GraphType.W1, transformationConnected);
        Graph.printComplex("FFT W2", myFftTransformed, GraphType.W2, transformationConnected);

//        Graph.printComplex("Apache FFT W1", apacheFftTransformed, GraphType.W1);
//        Graph.printComplex("Apache FFT W2", apacheFftTransformed, GraphType.W2);
        
        
        try {
        	double[] signAfterTransformation = DWT.transform(signal.toPrimitive(), waveletType, order, L, Direction.forward);
			Graph.printSignal("Po transformacji falki", "Numer próbki", "Wartość", signAfterTransformation, transformationConnected);
			double[] signTransformatedBack = DWT.transform(signAfterTransformation, waveletType, order, L, Direction.reverse);
			Graph.printSignal("Transformacja wsteczna", "Numer próbki", "Wartość", signTransformatedBack, transformationConnected);
		} catch (Exception e) {
			System.out.println("Coś poszło nie tak...");
			e.printStackTrace();
		}
		
	}
}
