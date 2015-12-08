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

import Helpers.IOUtils;
import Helpers.MyCallable;
import Model.Konwersja.FirstOrderHold;
import Model.Konwersja.KonwersjaCA;
import Model.Konwersja.Kwantyzacja;
import Model.Konwersja.KwantyzacjaZObcieciem;
import Model.Konwersja.KwantyzacjaZZaokragleniem;
import Model.Konwersja.Próbkowanie;
import Model.Konwersja.ZeroOrderHold;
import Model.Operacje.Dodawanie;
import Model.Operacje.Dzielenie;
import Model.Operacje.Mnozenie;
import Model.Operacje.Odejmowanie;
import Model.Operacje.OperacjaNaSygnalach;
import Model.Operacje.Splot;
import Model.Sygnaly.MiaryPodobienstwa;
import Model.Sygnaly.Ciagle.SygnalCiagly;
import Model.Sygnaly.Ciagle.Szumy.Gaussowski;
import Model.Sygnaly.Ciagle.Szumy.Impulsowy;
import Model.Sygnaly.Ciagle.Szumy.RozkladJednostajny;
import Model.Sygnaly.Ciagle.Zwykle.ImpulsJednostkowy;
import Model.Sygnaly.Ciagle.Zwykle.Prostokatny;
import Model.Sygnaly.Ciagle.Zwykle.ProstokatnySymetryczny;
import Model.Sygnaly.Ciagle.Zwykle.Sinus;
import Model.Sygnaly.Ciagle.Zwykle.SinusWyprostDwupol;
import Model.Sygnaly.Ciagle.Zwykle.SinusWyprostJednopol;
import Model.Sygnaly.Ciagle.Zwykle.SkokJednostkowy;
import Model.Sygnaly.Ciagle.Zwykle.Trojkatny;
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;
import View.MainWindow;

public class GuiController {

	private MainWindow window;
	private final JFileChooser fc = new JFileChooser();

	private SygnalDyskretnyReal firstSignal;
	private SygnalDyskretnyReal secondSignal;
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
			this.initialize();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		window.panelPierwszegoSygnalu.getBtnGenerate().addActionListener(btnGenSig1Listener);
		window.panelDrugiegoSygnalu.getBtnGenerate().addActionListener(btnGenSig2Listener);
		window.panelPierwszegoSygnalu.getSignalChooser().addActionListener(firstSigListener);
		window.panelDrugiegoSygnalu.getSignalChooser().addActionListener(secondSigListener);
		window.panelPierwszegoSygnalu.getBtnLoad().addActionListener(loadFirstSigListener);
		window.panelDrugiegoSygnalu.getBtnLoad().addActionListener(loadSecondSigListener);
		window.panelWynikuOperacji.getBtnDoOperation().addActionListener(doOperationListener);
		window.panelWynikuOperacji.getBtnSaveResult().addActionListener(saveResultSigListener);
		window.panelKwantyzacji.getBtnDoQuantization().addActionListener(btnKwantyzujListener);

		window.panelPierwszegoSygnalu.subscribeOnChartChange(updateFirstSignalPreview);
		window.panelDrugiegoSygnalu.subscribeOnChartChange(updateSecondSignalPreview);
		
		window.panelKonwersji.getFirstSignalChoose().addActionListener(zmianaSygnaluDoKonwersji);
		window.panelKonwersji.getSecondSignalChoose().addActionListener(zmianaSygnaluDoKonwersji);
		window.panelKonwersji.getConversion().addActionListener(zmianaMetodyOdtworzeniaListener);
		window.panelKonwersji.getBtnDoSampling().addActionListener(btnProbkujSygnalListener);
		window.panelKonwersji.getBtnReCreate().addActionListener(btnOdtworzSygnalListener);
		
		window.panelKwantyzacji.getFirstSignalChoose().addActionListener(zmianaSygnaluDoKwantyzacji);
		window.panelKwantyzacji.getSecondSignalChoose().addActionListener(zmianaSygnaluDoKwantyzacji);

		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new Sinus());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new SinusWyprostJednopol());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new SinusWyprostDwupol());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new Prostokatny());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new ProstokatnySymetryczny());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new Trojkatny());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new SkokJednostkowy());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new ImpulsJednostkowy());

		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new RozkladJednostajny());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new Gaussowski());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new Impulsowy());

		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new Sinus());
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new SinusWyprostJednopol());
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new SinusWyprostDwupol());
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new Prostokatny());
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new ProstokatnySymetryczny());
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new Trojkatny());
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new SkokJednostkowy());
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new ImpulsJednostkowy());

		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new RozkladJednostajny());
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new Gaussowski());
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new Impulsowy());

		window.panelWynikuOperacji.getOperations().addItem(new Dodawanie());
		window.panelWynikuOperacji.getOperations().addItem(new Odejmowanie());
		window.panelWynikuOperacji.getOperations().addItem(new Mnozenie());
		window.panelWynikuOperacji.getOperations().addItem(new Dzielenie());
		window.panelWynikuOperacji.getOperations().addItem(new Splot());
		
		window.panelKonwersji.getConversion().addItem(new ZeroOrderHold());
		window.panelKonwersji.getConversion().addItem(new FirstOrderHold());
		
		window.panelKwantyzacji.getQuantization().addItem(new KwantyzacjaZObcieciem(10));
		window.panelKwantyzacji.getQuantization().addItem(new KwantyzacjaZZaokragleniem(10));
		
		
	}

	private void reloadSignal1Charts() {
		window.panelPierwszegoSygnalu.setChart((firstSignal.getChart(null)));
		window.panelPierwszegoSygnalu.setHistogram((firstSignal.getHistogram(null)));
	}

	private void reloadSignal2Charts() {
		window.panelDrugiegoSygnalu.setChart((secondSignal.getChart(null)));
		window.panelDrugiegoSygnalu.setHistogram((secondSignal.getHistogram(null)));
	}

	private void reloadSignal3Charts() {
		window.panelWynikuOperacji.setChart((resultSignal.getChart(null)));
		window.panelWynikuOperacji.setHistogram((resultSignal.getHistogram(null)));
	}

	private void updateSignal1Params() {
		window.panelPierwszegoSygnalu.generatedParamsPanel.setVariance(firstSignal.wariancja());
		window.panelPierwszegoSygnalu.generatedParamsPanel.setEffectiveValue(firstSignal.wartoscSkuteczna());
		window.panelPierwszegoSygnalu.generatedParamsPanel.setAvg(firstSignal.wartoscSrednia());
		window.panelPierwszegoSygnalu.generatedParamsPanel.setAbsoluteAvg(firstSignal.wartoscSredniaBezwzgledna());
		window.panelPierwszegoSygnalu.generatedParamsPanel.setPower(firstSignal.mocSrednia());
	}

	private void updateSignal2Params() {
		window.panelDrugiegoSygnalu.generatedParamsPanel.setVariance(secondSignal.wariancja());
		window.panelDrugiegoSygnalu.generatedParamsPanel.setEffectiveValue(secondSignal.wartoscSkuteczna());
		window.panelDrugiegoSygnalu.generatedParamsPanel.setAvg(secondSignal.wartoscSrednia());
		window.panelDrugiegoSygnalu.generatedParamsPanel.setAbsoluteAvg(secondSignal.wartoscSredniaBezwzgledna());
		window.panelDrugiegoSygnalu.generatedParamsPanel.setPower(secondSignal.mocSrednia());
	}

	private ActionListener btnGenSig1Listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
				ArrayList<Double> params = new ArrayList<Double>();
				try {
					for (JTextField field : window.panelPierwszegoSygnalu.getParamsFields()) {
						if (field.isVisible())
							params.add(Double.parseDouble(field.getText()));
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(window.frame,
							"Parametry które zosta³y wprowadzone nie s¹ poprawne. WprowadŸ poprawne liczby.",
							"B³¹d podczas przetwarzania parametrów...", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Double[] parameters = params.toArray(new Double[params.size()]);
				SygnalCiagly funkcja = (SygnalCiagly) window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem();
				funkcja.setParams(parameters);
				boolean representAsContinuous = true;
				if(funkcja instanceof ImpulsJednostkowy) representAsContinuous = false;
				
				firstSignal = Próbkowanie.próbkuj(funkcja, Double.parseDouble(window.panelPierwszegoSygnalu.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(window.panelPierwszegoSygnalu.getChartTo().getText()), representAsContinuous);
				firstSignal.funkcjaCiagla = funkcja;
				reloadSignal1Charts();
				updateSignal1Params();

		}
	};

	private ActionListener btnGenSig2Listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
				ArrayList<Double> params = new ArrayList<Double>();
				try {
					for (JTextField field : window.panelDrugiegoSygnalu.getParamsFields()) {
						if (field.isVisible())
							params.add(Double.parseDouble(field.getText()));
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(window.frame,
							"Parametry które zostaly wprowadzone nie sa poprawne. Wprowadz poprawne liczby.",
							"Blad podczas przetwarzania parametrow...", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Double[] parameters = params.toArray(new Double[params.size()]);
				SygnalCiagly funkcja = (SygnalCiagly) window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem();
				funkcja.setParams(parameters);
				boolean representAsContinuous = true;
				secondSignal = Próbkowanie.próbkuj(funkcja, Double.parseDouble(window.panelDrugiegoSygnalu.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(window.panelDrugiegoSygnalu.getChartTo().getText()), representAsContinuous);
				secondSignal.funkcjaCiagla = funkcja;
				reloadSignal2Charts();
				updateSignal2Params();
		}
	};

	private ActionListener firstSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SygnalCiagly sig = (SygnalCiagly) window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem();
			window.panelPierwszegoSygnalu.setParamsNames(sig.getParametersNames());
		}

	};

	private ActionListener secondSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SygnalCiagly sig = (SygnalCiagly) window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem();
			window.panelDrugiegoSygnalu.setParamsNames(sig.getParametersNames());
		}

	};

	private ActionListener loadFirstSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			int returnVal = fc.showOpenDialog(window.frame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				firstSignal = IOUtils.LoadSignal(file);
				reloadSignal1Charts();
				updateSignal1Params();
			} else {

			}

		}

	};

	private ActionListener loadSecondSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			int returnVal = fc.showOpenDialog(window.frame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				secondSignal = IOUtils.LoadSignal(file);
				reloadSignal2Charts();
				updateSignal2Params();
			}

		}

	};

	private ActionListener saveResultSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			int returnVal = fc.showSaveDialog(window.frame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				IOUtils.SaveSignal(file, resultSignal);

			} else {

			}

		}

	};

	private ActionListener doOperationListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			OperacjaNaSygnalach operation = (OperacjaNaSygnalach) window.panelWynikuOperacji.getOperations()
					.getSelectedItem();
			resultSignal = operation.DoOperation(firstSignal, secondSignal);
			reloadSignal3Charts();
		}

	};
	
	private ActionListener zmianaSygnaluDoKonwersji = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(window.panelKonwersji.getFirstSignalChoose().isSelected()){
				window.panelKonwersji.getSignalPreview().setChart(window.panelPierwszegoSygnalu.getChartPanel().getChart());
				window.panelKonwersji.setSignalForSampling((SygnalCiagly)window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem());
				window.panelKonwersji.repaint();
			}else if(window.panelKonwersji.getSecondSignalChoose().isSelected()){
				window.panelKonwersji.getSignalPreview().setChart(window.panelDrugiegoSygnalu.getChartPanel().getChart());
				window.panelKonwersji.setSignalForSampling((SygnalCiagly)window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem());
				window.panelKonwersji.repaint();
			}
		}
	};
	
	private ActionListener zmianaSygnaluDoKwantyzacji = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(window.panelKwantyzacji.getFirstSignalChoose().isSelected()){
				window.panelKwantyzacji.setChart(window.panelPierwszegoSygnalu.getChartPanel().getChart());
				window.panelKwantyzacji.setSignalForQuantization((SygnalCiagly)window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem());
				window.panelKwantyzacji.repaint();
			}else if(window.panelKwantyzacji.getSecondSignalChoose().isSelected()){
				window.panelKwantyzacji.setChart(window.panelDrugiegoSygnalu.getChartPanel().getChart());
				window.panelKwantyzacji.setSignalForQuantization((SygnalCiagly)window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem());
				window.panelKwantyzacji.repaint();
			}
		}
	};
	
	private ActionListener zmianaMetodyOdtworzeniaListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private ActionListener btnProbkujSygnalListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean showAsContinuous = false;
			SygnalDyskretnyReal sygnalSprobkowany = Próbkowanie.próbkuj(window.panelKonwersji.getSignalForSampling(), Double.parseDouble(window.panelKonwersji.getChartFrom().getText()), Integer.parseInt(window.panelKonwersji.getChartStep().getText()), Double.parseDouble(window.panelKonwersji.getChartTo().getText()), showAsContinuous);
			window.panelKonwersji.setSignalSampled(sygnalSprobkowany);
			window.panelKonwersji.setChart(sygnalSprobkowany.getChart(""));
		}
	};
	
	private ActionListener btnOdtworzSygnalListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			KonwersjaCA konwersja = (KonwersjaCA)window.panelKonwersji.getConversion().getSelectedItem();
			konwersja.konwertuj(window.panelKonwersji.getSignalSampled());
			boolean showAsContinuous = true;
			SygnalDyskretnyReal odwzorowanieCiaglegoSygOdtworzonego = Próbkowanie.próbkuj(konwersja, Double.parseDouble(window.panelKonwersji.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(window.panelKonwersji.getChartTo().getText()), showAsContinuous);
			window.panelKonwersji.setHistogram(odwzorowanieCiaglegoSygOdtworzonego.getChart(""));
			oblicMiaryPodobienstwaKonwersji();
		}
	};

	private MyCallable<JFreeChart> updateFirstSignalPreview = new MyCallable<JFreeChart>() {

		@Override
		public void call(JFreeChart chart) {
			window.panelWynikuOperacji.getPanelFirstSignalPrev().setChart(chart);
			window.panelWynikuOperacji.repaint();
			
			if(window.panelKonwersji.getFirstSignalChoose().isSelected()){
				window.panelKonwersji.getSignalPreview().setChart(chart);
				window.panelKonwersji.setSignalForSampling((SygnalCiagly)window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem());
			}
			
			if(window.panelKwantyzacji.getFirstSignalChoose().isSelected()){
				window.panelKwantyzacji.setChart(window.panelPierwszegoSygnalu.getChartPanel().getChart());
				window.panelKwantyzacji.setSignalForQuantization((SygnalCiagly)window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem());
			}

		}
	};

	private MyCallable<JFreeChart> updateSecondSignalPreview = new MyCallable<JFreeChart>() {

		@Override
		public void call(JFreeChart chart) {
			window.panelWynikuOperacji.getPanelSecondSignalPrev().setChart(chart);
			window.panelWynikuOperacji.repaint();
			if(window.panelKonwersji.getSecondSignalChoose().isSelected()){
				window.panelKonwersji.getSignalPreview().setChart(chart);
				window.panelKonwersji.setSignalForSampling((SygnalCiagly)window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem());
			}
			
			if(window.panelKwantyzacji.getSecondSignalChoose().isSelected()){
				window.panelKwantyzacji.setChart(window.panelDrugiegoSygnalu.getChartPanel().getChart());
				window.panelKwantyzacji.setSignalForQuantization((SygnalCiagly)window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem());
			}
		}
	};
	
	private ActionListener btnKwantyzujListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Kwantyzacja kwantyzacja = (Kwantyzacja)window.panelKwantyzacji.getQuantization().getSelectedItem();
			SygnalDyskretnyReal skwantyzowany = kwantyzacja.kwantyzuj(window.panelKwantyzacji.getSignalForQuantization(), Double.parseDouble(window.panelKonwersji.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(window.panelKonwersji.getChartTo().getText()), Integer.parseInt(window.panelKwantyzacji.getNumberOfLevelsVal().getText()));
			window.panelKwantyzacji.setSignalAfterQuantization(skwantyzowany);
			window.panelKwantyzacji.setSecondChart(skwantyzowany.getChart(""));
			oblicMiaryPodobienstwaKwantyzacji();
		}
	};
	
	private void oblicMiaryPodobienstwaKonwersji(){
		double poczatek = Double.parseDouble(window.panelKonwersji.getChartFrom().getText());
		double koniec = Double.parseDouble(window.panelKonwersji.getChartTo().getText());
		int czestotliwosc = CZEST_PROB_F_CIAG;
		SygnalDyskretnyReal sygPierwszy = Próbkowanie.próbkuj(window.panelKonwersji.getSignalForSampling(), poczatek, czestotliwosc, koniec);
		SygnalDyskretnyReal sygDrugi = Próbkowanie.próbkuj((SygnalCiagly)window.panelKonwersji.getConversion().getSelectedItem(), poczatek, czestotliwosc, koniec);
	
		window.panelKonwersji.getSignalComparsionPanel().setMSE(MiaryPodobienstwa.mse(sygPierwszy, sygDrugi));
		window.panelKonwersji.getSignalComparsionPanel().setSNR(MiaryPodobienstwa.snr(sygPierwszy, sygDrugi));
		window.panelKonwersji.getSignalComparsionPanel().setPSNR(MiaryPodobienstwa.psnr(sygPierwszy, sygDrugi));
		window.panelKonwersji.getSignalComparsionPanel().setMD(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
		window.panelKonwersji.getSignalComparsionPanel().setENOB(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
		
	}
	
	private void oblicMiaryPodobienstwaKwantyzacji(){
		double poczatek = Double.parseDouble(window.panelKwantyzacji.getChartFrom().getText());
		double koniec = Double.parseDouble(window.panelKwantyzacji.getChartTo().getText());
		int czestotliwosc = CZEST_PROB_F_CIAG;
		SygnalDyskretnyReal sygPierwszy = Próbkowanie.próbkuj(window.panelKwantyzacji.getSignalForQuantization(), poczatek, czestotliwosc, koniec);
		SygnalDyskretnyReal sygDrugi = window.panelKwantyzacji.getSignalAfterQuantization();
	
		window.panelKwantyzacji.getSignalsComparsionPanel().setMSE(MiaryPodobienstwa.mse(sygPierwszy, sygDrugi));
		window.panelKwantyzacji.getSignalsComparsionPanel().setSNR(MiaryPodobienstwa.snr(sygPierwszy, sygDrugi));
		window.panelKwantyzacji.getSignalsComparsionPanel().setPSNR(MiaryPodobienstwa.psnr(sygPierwszy, sygDrugi));
		window.panelKwantyzacji.getSignalsComparsionPanel().setMD(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
		window.panelKwantyzacji.getSignalsComparsionPanel().setENOB(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
		
	}

}
