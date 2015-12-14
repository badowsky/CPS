package Controller;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Helpers.Utils;
import Model.Filtracja.Filtr;
import Model.Filtracja.FiltrDolnoprzepustowy;
import Model.Filtracja.Fourier;
import Model.Filtracja.GeneratorFiltru;
import Model.Filtracja.Okna.Okno;
import Model.Filtracja.Okna.OknoHamminga;
import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.FunkcjeCiagle.Sygnaly.Sinus;
import Model.Konwersja.Próbkowanie;
import Model.Operacje.Korelacja;
import Model.Operacje.Splot;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyCmplx;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;
import View.Graph;
import View.SimpleFrameChartCreator;

public class CmdController {
	
	private static int K = 8;
	private static int M = 63;
	private static int N = 128;

	public static void main(String[] args) {
		
		FunkcjaCiagla fun = new Sinus();
		FunkcjaCiagla fun2 = new Sinus();
		fun.setParams(new Double[]{10.0, 4.0, 9.0});
		fun2.setParams(new Double[]{10.0, 0.0, 50.0});
		SygnalDyskretnyReal sygnal = Próbkowanie.próbkuj(fun, 0, 10, N, true);
		SygnalDyskretnyReal sygnal2 = Próbkowanie.próbkuj(fun2, 0, 10, N, true);
		
		Filtr filtr = new FiltrDolnoprzepustowy(K);
		Okno okno = new OknoHamminga(M);
		GeneratorFiltru gen = new GeneratorFiltru(filtr, okno);
		SygnalDyskretnyCmplx filtrSpróbkowany = gen.generuj(K, M, N);
		
		
        Splot splot = new Splot();
        SygnalDyskretnyReal wyfiltrowany = splot.DoOperation(filtrSpróbkowany.toReal(), sygnal);
        Korelacja korelacja = new Korelacja();
        SygnalDyskretnyReal sygKorelacji = korelacja.DoOperation(sygnal, sygnal2);
        SygnalDyskretnyReal sygSplotu = splot.DoOperation(sygnal, sygnal2);
        JPanel aPanel = new JPanel();
        aPanel.setPreferredSize(new Dimension(600, 300));
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] transformed = fft.transform(filtrSpróbkowany.getY(), TransformType.FORWARD);

        SimpleFrameChartCreator.create(sygnal.getChart(""), "Sygnal");
        SimpleFrameChartCreator.create(sygnal2.getChart(""), "Sygnal 2");
        SimpleFrameChartCreator.create(filtrSpróbkowany.toReal().getChart(""), "Filtr");
        SimpleFrameChartCreator.create(okno.generujPodglad().getChart(""), "Okno");
        SimpleFrameChartCreator.create(wyfiltrowany.getChart(""), "Wynik");
        SimpleFrameChartCreator.create(sygKorelacji.getChart(""), "Korelacja");
        SimpleFrameChartCreator.create(sygSplotu.getChart(""), "Splot");

        
        SimpleFrameChartCreator.create(Graph.drawGraph("", Utils.abs(transformed), true), "Modu³ transmitancji");
	}
}
