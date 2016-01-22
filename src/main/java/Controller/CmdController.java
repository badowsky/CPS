package Controller;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import Model.Conversion.Sampling;
import Model.Filtration.Fourier;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Continuous.Normal.S3;
import Model.Signals.Discrete.DiscreteSignalReal;
import Transformation.DWT;
import Transformation.DWT.Direction;
import Transformation.Wavelet;
import View.Graph;
import View.Graph.GraphType;

public class CmdController {
	
	public static void main(String[] args) {
		
		// ************** PARAMETRY ***************
		ContinuousSignal continuousSignal = new S3();
		int signalLength = 1024;
		Wavelet waveletType = Wavelet.Daubechies;
		int order = 8;
		//int L = (int) (Math.log(signalLength)/Math.log(2)); // which power of 2 is signalLength
		int L = 6;
		
		boolean doDFT = false;
		boolean doFFT = true;
		boolean doApacheFFT = false;
		boolean doWavelet = true;
		
		boolean transformationConnected = false;
		// ********** PARAMETRY - Koniec **********
		
		DiscreteSignalReal signal = Sampling.sample(continuousSignal, 0, 16, signalLength/16);
		Graph.printSignal("Sygna³ oryginalny", "Numer próbki", "Wartoœc", signal.toPrimitive(), true);
		Complex[] x = signal.toComplex();
//		System.out.println("Real:");
//		for(Complex c : x){
//			System.out.print(c.getReal() + ", ");
//		}
		
		System.out.println("Transformacja sygna³u o d³ugoœci " + signal.size() + " próbek:");
		
		long startTime, stopTime, elapsedTime;
		
		if(doDFT){
			startTime = System.currentTimeMillis();
	        Complex[] myDftTransformed = Fourier.DFT(x);
	        stopTime = System.currentTimeMillis();
	        elapsedTime = stopTime - startTime;
	        System.out.println("Czas wykonywania dla DFT: " + elapsedTime + " ms");
	        Graph.printComplex("DFT W1", myDftTransformed, GraphType.W1, transformationConnected);
	        Graph.printComplex("DFT W2", myDftTransformed, GraphType.W2, transformationConnected);
		}
		
        if(doFFT){
        	startTime = System.currentTimeMillis();
            Complex[] myFftTransformed = Fourier.FFT(x);
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("Czas wykonywania dla FFT: " + elapsedTime + " ms");
            Graph.printComplex("FFT W1", myFftTransformed, GraphType.W1, transformationConnected);
            Graph.printComplex("FFT W2", myFftTransformed, GraphType.W2, transformationConnected);
        }
        
        if(doApacheFFT){
    		FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
            Complex[] apacheFftTransformed = fft.transform(x, TransformType.FORWARD);
            Graph.printComplex("Apache FFT W1", apacheFftTransformed, GraphType.W1, transformationConnected);
          	Graph.printComplex("Apache FFT W2", apacheFftTransformed, GraphType.W2, transformationConnected);
        }
        
        if(doWavelet){
            try {
            	double[] signAfterTransformation = DWT.transform(signal.toPrimitive(), waveletType, order, L, Direction.forward);
    			Graph.printSignal("Po transformacji falki", "Numer próbki", "Wartoœc", signAfterTransformation, true);
    			double[] signTransformatedBack = DWT.transform(signAfterTransformation, waveletType, order, L, Direction.reverse);
    			Graph.printSignal("Transformacja wsteczna", "Numer próbki", "Wartoœc", signTransformatedBack, true);
    		} catch (Exception e) {
    			System.out.println("Coœ posz³o nie tak podczas generowania transformacji falki...");
    			e.printStackTrace();
    		}
        }
	}
}
