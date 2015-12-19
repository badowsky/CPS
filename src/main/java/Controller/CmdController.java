package Controller;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import Helpers.Utils;
import Model.Conversion.Sampling;
import Model.Filtration.Filter;
import Model.Filtration.FiltrDolnoprzepustowy;
import Model.Filtration.FiltrGornoprzepustowy;
import Model.Filtration.FiltrSrodkowoprzepustowy;
import Model.Filtration.GeneratorFiltru;
import Model.Filtration.Windows.HammingsWindow;
import Model.Filtration.Windows.Window;
import Model.Operations.Correlation;
import Model.Operations.Splot;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Continuous.Normal.Sinus;
import Model.Signals.Discrete.DiscreteSignalComplex;
import Model.Signals.Discrete.DiscreteSignalReal;
import View.Graph;
import View.SimpleFrameChartCreator;

public class CmdController {
	
	private static int K = 8;
	private static int M = 63;
	private static int N = 128;

	public static void main(String[] args) {
		
		ContinuousSignal fun = new Sinus();
		ContinuousSignal fun2 = new Sinus();
		fun.setParams(new Double[]{10.0, 0.0, 10.0});
		fun2.setParams(new Double[]{10.0, 0.0, 10.0});
		DiscreteSignalReal sygnal = Sampling.sample(fun, 0, 100, 10, true);
		DiscreteSignalReal sygnal2 = Sampling.sample(fun2, 0, 100, 10, true);
		
		Filter filtr = new FiltrDolnoprzepustowy(K);
		Window okno = new HammingsWindow(M);
		GeneratorFiltru gen = new GeneratorFiltru(filtr, okno);
		DiscreteSignalComplex filtrSpróbkowany = gen.generuj(K, M, N);
		
		
        Splot splot = new Splot();        
        Correlation korelacja = new Correlation();
        DiscreteSignalReal sygKorelacji = korelacja.DoOperation(sygnal, sygnal2);
        DiscreteSignalReal sygSplotu = splot.DoOperation(sygnal, sygnal2);
        DiscreteSignalReal wyfiltrowany = splot.DoOperation(filtrSpróbkowany.toReal(), sygSplotu);
        JPanel aPanel = new JPanel();
        aPanel.setPreferredSize(new Dimension(600, 300));
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] transformed = fft.transform(filtrSpróbkowany.getY(), TransformType.FORWARD);

        SimpleFrameChartCreator.create(sygnal.getChart(""), "Sygnal");
        SimpleFrameChartCreator.create(sygnal2.getChart(""), "Sygnal 2");
        SimpleFrameChartCreator.create(filtrSpróbkowany.toReal().getChart(""), "Filtr");
        //SimpleFrameChartCreator.create(okno.generujPodglad().getChart(""), "Okno");
        SimpleFrameChartCreator.create(wyfiltrowany.getChart(""), "Wynik");
        SimpleFrameChartCreator.create(sygKorelacji.getChart(""), "Korelacja");
        SimpleFrameChartCreator.create(sygSplotu.getChart(""), "Splot");

        
        SimpleFrameChartCreator.create(Graph.drawGraph("", Utils.abs(transformed), true), "Modu³ transmitancji");
	}
}
