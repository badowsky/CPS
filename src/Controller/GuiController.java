package Controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jfree.chart.JFreeChart;

import Controller.GuiView.SignalPanelController;
import Helpers.IOUtils;
import Helpers.MyCallable;
import Model.Konwersja.FirstOrderHold;
import Model.Konwersja.KonwersjaCA;
import Model.Konwersja.Kwantyzacja;
import Model.Konwersja.KwantyzacjaZObcieciem;
import Model.Konwersja.KwantyzacjaZZaokragleniem;
import Model.Konwersja.Próbkowanie;
import Model.Konwersja.ZeroOrderHold;
import Model.Operations.Addition;
import Model.Operations.Division;
import Model.Operations.Multiplication;
import Model.Operations.OperationOnSignals;
import Model.Operations.Splot;
import Model.Operations.Subtraction;
import Model.Signals.MiaryPodobienstwa;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Continuous.Noise.Gaussian;
import Model.Signals.Continuous.Noise.Impulsowy;
import Model.Signals.Continuous.Noise.RozkladJednostajny;
import Model.Signals.Continuous.Normal.ImpulsJednostkowy;
import Model.Signals.Continuous.Normal.Prostokatny;
import Model.Signals.Continuous.Normal.ProstokatnySymetryczny;
import Model.Signals.Continuous.Normal.Sinus;
import Model.Signals.Continuous.Normal.SinusWyprostDwupol;
import Model.Signals.Continuous.Normal.SinusWyprostJednopol;
import Model.Signals.Continuous.Normal.SkokJednostkowy;
import Model.Signals.Continuous.Normal.Trojkatny;
import Model.Signals.Discrete.SygnalDyskretnyReal;
import View.MainWindow;

public class GuiController {

	private MainWindow window;

	private SignalPanelController firstSignalController;
	private SignalPanelController secondSignalController;
	private SygnalDyskretnyReal resultSignal;
	
	private static final int CZEST_PROB_F_CIAG = 1000;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GuiController();
			}
		});
	}

	public GuiController() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			window = new MainWindow();
			firstSignalController = new SignalPanelController(window.firstSignalPanel);
			secondSignalController = new SignalPanelController(window.secondSignalPanel);
			this.initialize();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {

		window.quantizationPanel.getBtnDoQuantization().addActionListener(btnKwantyzujListener);

		firstSignalController.subscribeOnChartChange(updateFirstSignalPreview);
		secondSignalController.subscribeOnChartChange(updateSecondSignalPreview);

		window.conversionPanel.getFirstSignalChoose().addActionListener(zmianaSygnaluDoKonwersji);
		window.conversionPanel.getSecondSignalChoose().addActionListener(zmianaSygnaluDoKonwersji);
		window.conversionPanel.getBtnDoSampling().addActionListener(btnProbkujSygnalListener);
		window.conversionPanel.getBtnReCreate().addActionListener(btnOdtworzSygnalListener);

		window.quantizationPanel.getFirstSignalChoose().addActionListener(zmianaSygnaluDoKwantyzacji);
		window.quantizationPanel.getSecondSignalChoose().addActionListener(zmianaSygnaluDoKwantyzacji);
 


	}

	
	private ActionListener zmianaSygnaluDoKonwersji = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(window.conversionPanel.getFirstSignalChoose().isSelected()){
				window.conversionPanel.getSignalPreview().setChart(window.firstSignalPanel.getChartPanel().getChart());
				window.conversionPanel.setSignalForSampling((ContinuousSignal)window.firstSignalPanel.getSignalChooser().getSelectedItem());
				window.conversionPanel.repaint();
			}else if(window.conversionPanel.getSecondSignalChoose().isSelected()){
				window.conversionPanel.getSignalPreview().setChart(window.secondSignalPanel.getChartPanel().getChart());
				window.conversionPanel.setSignalForSampling((ContinuousSignal)window.secondSignalPanel.getSignalChooser().getSelectedItem());
				window.conversionPanel.repaint();
			}
		}
	};
	
	private ActionListener zmianaSygnaluDoKwantyzacji = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(window.quantizationPanel.getFirstSignalChoose().isSelected()){
				window.quantizationPanel.setChart(window.firstSignalPanel.getChartPanel().getChart());
				window.quantizationPanel.setSignalForQuantization((ContinuousSignal)window.firstSignalPanel.getSignalChooser().getSelectedItem());
				window.quantizationPanel.repaint();
			}else if(window.quantizationPanel.getSecondSignalChoose().isSelected()){
				window.quantizationPanel.setChart(window.secondSignalPanel.getChartPanel().getChart());
				window.quantizationPanel.setSignalForQuantization((ContinuousSignal)window.secondSignalPanel.getSignalChooser().getSelectedItem());
				window.quantizationPanel.repaint();
			}
		}
	};
	
	private ActionListener btnProbkujSygnalListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean showAsContinuous = false;
			SygnalDyskretnyReal sygnalSprobkowany = Próbkowanie.próbkuj(window.conversionPanel.getSignalForSampling(), Double.parseDouble(window.conversionPanel.getChartFrom().getText()), Integer.parseInt(window.conversionPanel.getChartStep().getText()), Double.parseDouble(window.conversionPanel.getChartTo().getText()), showAsContinuous);
			window.conversionPanel.setSignalSampled(sygnalSprobkowany);
			window.conversionPanel.setChart(sygnalSprobkowany.getChart(""));
		}
	};
	
	private ActionListener btnOdtworzSygnalListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			KonwersjaCA konwersja = (KonwersjaCA)window.conversionPanel.getConversion().getSelectedItem();
			konwersja.konwertuj(window.conversionPanel.getSignalSampled());
			boolean showAsContinuous = true;
			SygnalDyskretnyReal odwzorowanieCiaglegoSygOdtworzonego = Próbkowanie.próbkuj(konwersja, Double.parseDouble(window.conversionPanel.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(window.conversionPanel.getChartTo().getText()), showAsContinuous);
			window.conversionPanel.setHistogram(odwzorowanieCiaglegoSygOdtworzonego.getChart(""));
			oblicMiaryPodobienstwaKonwersji();
		}
	};

	private MyCallable<JFreeChart> updateFirstSignalPreview = new MyCallable<JFreeChart>() {

		@Override
		public void call(JFreeChart chart) {
			window.operationsPanel.getPanelFirstSignalPrev().setChart(chart);
			window.operationsPanel.repaint();
			
			if(window.conversionPanel.getFirstSignalChoose().isSelected()){
				window.conversionPanel.getSignalPreview().setChart(chart);
				window.conversionPanel.setSignalForSampling((ContinuousSignal)window.firstSignalPanel.getSignalChooser().getSelectedItem());
			}
			
			if(window.quantizationPanel.getFirstSignalChoose().isSelected()){
				window.quantizationPanel.setChart(window.firstSignalPanel.getChartPanel().getChart());
				window.quantizationPanel.setSignalForQuantization((ContinuousSignal)window.firstSignalPanel.getSignalChooser().getSelectedItem());
			}

		}
	};

	private MyCallable<JFreeChart> updateSecondSignalPreview = new MyCallable<JFreeChart>() {

		@Override
		public void call(JFreeChart chart) {
			window.operationsPanel.getPanelSecondSignalPrev().setChart(chart);
			window.operationsPanel.repaint();
			if(window.conversionPanel.getSecondSignalChoose().isSelected()){
				window.conversionPanel.getSignalPreview().setChart(chart);
				window.conversionPanel.setSignalForSampling((ContinuousSignal)window.secondSignalPanel.getSignalChooser().getSelectedItem());
			}
			
			if(window.quantizationPanel.getSecondSignalChoose().isSelected()){
				window.quantizationPanel.setChart(window.secondSignalPanel.getChartPanel().getChart());
				window.quantizationPanel.setSignalForQuantization((ContinuousSignal)window.secondSignalPanel.getSignalChooser().getSelectedItem());
			}
		}
	};
	
	private ActionListener btnKwantyzujListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Kwantyzacja kwantyzacja = (Kwantyzacja)window.quantizationPanel.getQuantization().getSelectedItem();
			SygnalDyskretnyReal skwantyzowany = kwantyzacja.kwantyzuj(window.quantizationPanel.getSignalForQuantization(), Double.parseDouble(window.conversionPanel.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(window.conversionPanel.getChartTo().getText()), Integer.parseInt(window.quantizationPanel.getNumberOfLevelsVal().getText()));
			window.quantizationPanel.setSignalAfterQuantization(skwantyzowany);
			window.quantizationPanel.setSecondChart(skwantyzowany.getChart(""));
			oblicMiaryPodobienstwaKwantyzacji();
		}
	};
	
	private void oblicMiaryPodobienstwaKonwersji(){
		double poczatek = Double.parseDouble(window.conversionPanel.getChartFrom().getText());
		double koniec = Double.parseDouble(window.conversionPanel.getChartTo().getText());
		int czestotliwosc = CZEST_PROB_F_CIAG;
		SygnalDyskretnyReal sygPierwszy = Próbkowanie.próbkuj(window.conversionPanel.getSignalForSampling(), poczatek, czestotliwosc, koniec);
		SygnalDyskretnyReal sygDrugi = Próbkowanie.próbkuj((ContinuousSignal)window.conversionPanel.getConversion().getSelectedItem(), poczatek, czestotliwosc, koniec);
	
		window.conversionPanel.getSignalComparsionPanel().setMSE(MiaryPodobienstwa.mse(sygPierwszy, sygDrugi));
		window.conversionPanel.getSignalComparsionPanel().setSNR(MiaryPodobienstwa.snr(sygPierwszy, sygDrugi));
		window.conversionPanel.getSignalComparsionPanel().setPSNR(MiaryPodobienstwa.psnr(sygPierwszy, sygDrugi));
		window.conversionPanel.getSignalComparsionPanel().setMD(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
		window.conversionPanel.getSignalComparsionPanel().setENOB(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
		
	}
	
	private void oblicMiaryPodobienstwaKwantyzacji(){
		double poczatek = Double.parseDouble(window.quantizationPanel.getChartFrom().getText());
		double koniec = Double.parseDouble(window.quantizationPanel.getChartTo().getText());
		int czestotliwosc = CZEST_PROB_F_CIAG;
		SygnalDyskretnyReal sygPierwszy = Próbkowanie.próbkuj(window.quantizationPanel.getSignalForQuantization(), poczatek, czestotliwosc, koniec);
		SygnalDyskretnyReal sygDrugi = window.quantizationPanel.getSignalAfterQuantization();
	
		window.quantizationPanel.getSignalsComparsionPanel().setMSE(MiaryPodobienstwa.mse(sygPierwszy, sygDrugi));
		window.quantizationPanel.getSignalsComparsionPanel().setSNR(MiaryPodobienstwa.snr(sygPierwszy, sygDrugi));
		window.quantizationPanel.getSignalsComparsionPanel().setPSNR(MiaryPodobienstwa.psnr(sygPierwszy, sygDrugi));
		window.quantizationPanel.getSignalsComparsionPanel().setMD(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
		window.quantizationPanel.getSignalsComparsionPanel().setENOB(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
		
	}

}
