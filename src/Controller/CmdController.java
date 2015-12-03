package Controller;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Model.Filtracja.Filtr;
import Model.Filtracja.FiltrDolnoprzepustowy;
import Model.Filtracja.FiltrGornoprzepustowy;
import Model.Filtracja.FiltrSrodkowoprzepustowy;
import Model.Filtracja.GeneratorFiltru;
import Model.Filtracja.ModulTransmitancji;
import Model.Filtracja.Okna.OknoHamminga;
import Model.Filtracja.Okna.OknoProstokatne;
import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.FunkcjeCiagle.Sygnaly.Sinus;
import Model.FunkcjeCiagle.Sygnaly.SinusWyprostDwupol;
import Model.Konwersja.Próbkowanie;
import Model.Operacje.Dzielenie;
import Model.Operacje.Splot;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyCmplx;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

public class CmdController {
	
	private static int K = 4;
	private static int M = 63;
	private static int N = 256;

	public static void main(String[] args) {
		
		FunkcjaCiagla fun = new Sinus();
		fun.setParams(new Double[]{10.0, 0.0, 10.0});
		
		Filtr filtr = new FiltrSrodkowoprzepustowy(K);
		GeneratorFiltru gen = new GeneratorFiltru(filtr, new OknoProstokatne(M));
		SygnalDyskretnyCmplx filtrSpróbkowany = gen.generuj(K, M, N);
		SygnalDyskretnyReal sygnal = Próbkowanie.próbkuj(fun, 0, 1, N, true);
		
        Splot splot = new Splot();
        SygnalDyskretnyReal wyfiltrowany = splot.DoOperation(filtrSpróbkowany.toReal(), sygnal);
        JPanel aPanel = new JPanel();
        aPanel.setPreferredSize(new Dimension(600, 300));
        
        SygnalDyskretnyReal modulTransmitancji = ModulTransmitancji.oblicz(filtrSpróbkowany);

        JFreeChart sygnalChart = sygnal.getChart("");
        JFreeChart filtrChart = filtrSpróbkowany.toReal().getChart("");
        JFreeChart wynikChart = wyfiltrowany.getChart("");
        
        ChartPanel sygnalChartPanel = new ChartPanel(sygnalChart);
        sygnalChartPanel.setPreferredSize(new Dimension(600, 300));
        
        ChartPanel filtrChartPanel = new ChartPanel(filtrChart);
        filtrChartPanel.setPreferredSize(new Dimension(600, 300));
        
        ChartPanel wynikChartPanel = new ChartPanel(wynikChart);
        wynikChartPanel.setPreferredSize(new Dimension(600, 300));

        JFrame sygnalFrame = new JFrame("Sygnal");
        sygnalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sygnalFrame.getContentPane().add(sygnalChartPanel);
        sygnalFrame.pack();
        sygnalFrame.setVisible(true);
        
        JFrame filtrFrame = new JFrame("Filtr");
        filtrFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filtrFrame.getContentPane().add(filtrChartPanel);
        filtrFrame.pack();
        filtrFrame.setVisible(true);
        
        JFrame wynikFrame = new JFrame("Wynik");
        wynikFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wynikFrame.getContentPane().add(wynikChartPanel);
        wynikFrame.pack();
        wynikFrame.setVisible(true);
	}
}
