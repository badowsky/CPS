package Controller;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import Helpers.Utils;
import Model.Filtracja.Filtr;
import Model.Filtracja.FiltrDolnoprzepustowy;
import Model.Filtracja.FiltrGornoprzepustowy;
import Model.Filtracja.FiltrSrodkowoprzepustowy;
import Model.Filtracja.GeneratorFiltru;
import Model.Filtracja.Okna.Okno;
import Model.Filtracja.Okna.OknoHamminga;
import Model.Konwersja.Próbkowanie;
import Model.Operations.Correlation;
import Model.Operations.Splot;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Continuous.Normal.Sinus;
import Model.Signals.Discrete.SygnalDyskretnyCmplx;
import Model.Signals.Discrete.SygnalDyskretnyReal;
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
		SygnalDyskretnyReal sygnal = Próbkowanie.próbkuj(fun, 0, 100, 10, true);
		SygnalDyskretnyReal sygnal2 = Próbkowanie.próbkuj(fun2, 0, 100, 10, true);
		
		Filtr filtr = new FiltrDolnoprzepustowy(K);
		Okno okno = new OknoHamminga(M);
		GeneratorFiltru gen = new GeneratorFiltru(filtr, okno);
		SygnalDyskretnyCmplx filtrSpróbkowany = gen.generuj(K, M, N);
		
		
        Splot splot = new Splot();        
        Correlation korelacja = new Correlation();
        SygnalDyskretnyReal sygKorelacji = korelacja.DoOperation(sygnal, sygnal2);
        SygnalDyskretnyReal sygSplotu = splot.DoOperation(sygnal, sygnal2);
        SygnalDyskretnyReal wyfiltrowany = splot.DoOperation(filtrSpróbkowany.toReal(), sygSplotu);
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
