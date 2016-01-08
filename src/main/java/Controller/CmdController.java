package Controller;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import Model.Conversion.Sampling;
import Model.Filtration.Fourier;
import Model.Signals.Continuous.Normal.S3;
import Model.Signals.Discrete.DiscreteSignalReal;
import Transformation.DWT;
import Transformation.Wavelet;
import Transformation.DWT.Direction;
import View.Graph;
import View.Graph.GraphType;

public class CmdController {
	
	public static void main(String[] args) {
		S3 s = new S3();
		DiscreteSignalReal signal = Sampling.sample(s, 0, 16, 64);
		Graph.printSignal(null, signal);
		Complex[] x = new Complex[signal.size()];
		for(int i = 0 ; i < signal.size() ; i++){
			x[i] = new Complex(signal.getY(i));
		}
		
		FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] apacheFftTransformed = fft.transform(x, TransformType.FORWARD);
        
		System.out.println("Transforming signal of " + signal.size() + " samples:");
        
		long startTime = System.currentTimeMillis();
        Complex[] myDftTransformed = Fourier.DFT(x);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("DFT execution time: " + elapsedTime + " ms");
        
        startTime = System.currentTimeMillis();
        Complex[] myFftTransformed = Fourier.FFT(x);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("FFT execution time: " + elapsedTime + " ms");
        
        Graph.printComplex("My DFT W1", myDftTransformed, GraphType.W1);
        Graph.printComplex("My DFT W2", myDftTransformed, GraphType.W2);
        
        Graph.printComplex("My FFT W1", myFftTransformed, GraphType.W1);
        Graph.printComplex("My FFT W2", myFftTransformed, GraphType.W2);

//        Graph.printComplex("Apache FFT W1", apacheFftTransformed, GraphType.W1);
//        Graph.printComplex("Apache FFT W2", apacheFftTransformed, GraphType.W2);
        
        
        try {
			Graph.printSignal("Falki", DWT.transform(signal.toPrimitive(), Wavelet.Daubechies, 8, 10, Direction.forward), true);
		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
	}
}
