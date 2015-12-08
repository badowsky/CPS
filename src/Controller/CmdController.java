package Controller;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Model.Filtracja.Filtr;
import Model.Filtracja.FiltrDolnoprzepustowy;
import Model.Filtracja.Fourier;
import Model.Filtracja.GeneratorFiltru;
import Model.Filtracja.Okna.Okno;
import Model.Filtracja.Okna.OknoHamminga;
import Model.Filtracja.Okna.OknoHanninga;
import Model.Filtracja.Okna.OknoProstokatne;
import Model.Konwersja.Próbkowanie;
import Model.Operacje.Splot;
import Model.Sygnaly.Ciagle.SygnalCiagly;
import Model.Sygnaly.Ciagle.Zwykle.Sinus;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyCmplx;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

public class CmdController {
	
	private static int K = 8;
	private static int M = 30;
	private static int N = 256;

	public static void main(String[] args) {
		
		SygnalCiagly fun = new Sinus();
		fun.setParams(new Double[]{10.0, 0.0, 10.0});
		
		Filtr filtr = new FiltrDolnoprzepustowy(K);
		Okno okno = new OknoHanninga(M);
		GeneratorFiltru gen = new GeneratorFiltru(filtr, okno);
		SygnalDyskretnyCmplx filtrSpróbkowany = gen.generuj(K, M, N);
		SygnalDyskretnyReal sygnal = Próbkowanie.próbkuj(fun, 0, 10, N, true);
		
        Splot splot = new Splot();
        SygnalDyskretnyReal wyfiltrowany = splot.DoOperation(filtrSpróbkowany.toReal(), sygnal);
        JPanel aPanel = new JPanel();
        aPanel.setPreferredSize(new Dimension(600, 300));
        
        SygnalDyskretnyReal modulTransmitancji = Fourier.DFT(filtrSpróbkowany);

        JFreeChart sygnalChart = sygnal.getChart("");
        JFreeChart filtrChart = filtrSpróbkowany.toReal().getChart("");
        JFreeChart transmitChart = modulTransmitancji.getChart("");
        JFreeChart oknoChart = okno.generuj().getChart("");
        JFreeChart wynikChart = wyfiltrowany.getChart("");
        
        ChartPanel sygnalChartPanel = new ChartPanel(sygnalChart);
        sygnalChartPanel.setPreferredSize(new Dimension(600, 300));
        
        ChartPanel filtrChartPanel = new ChartPanel(filtrChart);
        filtrChartPanel.setPreferredSize(new Dimension(600, 300));
        
        ChartPanel transmitChartPanel = new ChartPanel(transmitChart);
        transmitChartPanel.setPreferredSize(new Dimension(600, 300));
        
        ChartPanel oknoChartPanel = new ChartPanel(oknoChart);
        oknoChartPanel.setPreferredSize(new Dimension(600, 300));
        
        ChartPanel wynikChartPanel = new ChartPanel(wynikChart);
        wynikChartPanel.setPreferredSize(new Dimension(600, 300));

        JFrame sygnalFrame = new JFrame("Sygnal");
        sygnalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sygnalFrame.getContentPane().add(sygnalChartPanel);
        sygnalFrame.pack();
        sygnalFrame.setVisible(true);
        sygnalFrame.setLocation(0, 335);
        
        JFrame filtrFrame = new JFrame("Filtr");
        filtrFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filtrFrame.getContentPane().add(filtrChartPanel);
        filtrFrame.pack();
        filtrFrame.setVisible(true);
        filtrFrame.setLocation(0, 0);
        
        JFrame transmitancjaFrame = new JFrame("Transmitancja Filtru");
        transmitancjaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        transmitancjaFrame.getContentPane().add(transmitChartPanel);
        transmitancjaFrame.pack();
        transmitancjaFrame.setVisible(true);
        transmitancjaFrame.setLocation(615, 0);
        
        JFrame oknoFrame = new JFrame("Okno Filtru");
        oknoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        oknoFrame.getContentPane().add(oknoChartPanel);
        oknoFrame.pack();
        oknoFrame.setVisible(true);
        oknoFrame.setLocation(1230, 0);
        
        JFrame wynikFrame = new JFrame("Wynik");
        wynikFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wynikFrame.getContentPane().add(wynikChartPanel);
        wynikFrame.pack();
        wynikFrame.setVisible(true);
        wynikFrame.setLocation(615, 335);
	}
}
