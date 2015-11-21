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
import Model.MiaryPodobienstwa;
import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.FunkcjeCiagle.Sygnaly.ImpulsJednostkowy;
import Model.FunkcjeCiagle.Sygnaly.ProstokatnySymetryczny;
import Model.FunkcjeCiagle.Sygnaly.Prostok¹tny;
import Model.FunkcjeCiagle.Sygnaly.Sinus;
import Model.FunkcjeCiagle.Sygnaly.SinusWyprostDwupol;
import Model.FunkcjeCiagle.Sygnaly.SinusWyprostJednopol;
import Model.FunkcjeCiagle.Sygnaly.SkokJednostkowy;
import Model.FunkcjeCiagle.Sygnaly.Trojkatny;
import Model.FunkcjeCiagle.Szumy.Gaussowski;
import Model.FunkcjeCiagle.Szumy.Impulsowy;
import Model.FunkcjeCiagle.Szumy.RozkladJednostajny;
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
import Model.Sygnaly.Dyskretne.SygnalDyskretny;
import View.MainWindow;

public class MainController {

	private MainWindow window;
	private final JFileChooser fc = new JFileChooser();

	private SygnalDyskretny firstSignal;
	private SygnalDyskretny secondSignal;
	private SygnalDyskretny resultSignal;
	
	private static final int CZEST_PROB_F_CIAG = 1000;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainController();
			}
		});
	}

	public MainController() {
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
		window.panelKwantyzacji.getBtnKwantyzuj().addActionListener(btnKwantyzujListener);

		window.panelPierwszegoSygnalu.subscribeOnChartChange(updateFirstSignalPreview);
		window.panelDrugiegoSygnalu.subscribeOnChartChange(updateSecondSignalPreview);
		
		window.panelKonwersji.getWyborSygnaluPierwszego().addActionListener(zmianaSygnaluDoKonwersji);
		window.panelKonwersji.getWyborSygnaluDrugiego().addActionListener(zmianaSygnaluDoKonwersji);
		window.panelKonwersji.getKonwersja().addActionListener(zmianaMetodyOdtworzeniaListener);
		window.panelKonwersji.getBtnProbkuj().addActionListener(btnProbkujSygnalListener);
		window.panelKonwersji.getBtnOdtworz().addActionListener(btnOdtworzSygnalListener);
		
		window.panelKwantyzacji.getWyborSygnaluPierwszego().addActionListener(zmianaSygnaluDoKwantyzacji);
		window.panelKwantyzacji.getWyborSygnaluDrugiego().addActionListener(zmianaSygnaluDoKwantyzacji);

		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new Sinus());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new SinusWyprostJednopol());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new SinusWyprostDwupol());
		window.panelPierwszegoSygnalu.getSignalChooser().addItem(new Prostok¹tny());
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
		window.panelDrugiegoSygnalu.getSignalChooser().addItem(new Prostok¹tny());
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
		
		window.panelKonwersji.getKonwersja().addItem(new ZeroOrderHold());
		window.panelKonwersji.getKonwersja().addItem(new FirstOrderHold());
		
		window.panelKwantyzacji.getKwantyzacja().addItem(new KwantyzacjaZObcieciem(10));
		window.panelKwantyzacji.getKwantyzacja().addItem(new KwantyzacjaZZaokragleniem(10));
		
		
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
		window.panelPierwszegoSygnalu.generatedParamsPanel.setWariancja(firstSignal.wariancja());
		window.panelPierwszegoSygnalu.generatedParamsPanel.setWartoscSkuteczna(firstSignal.wartoscSkuteczna());
		window.panelPierwszegoSygnalu.generatedParamsPanel.setSrednia(firstSignal.wartoscSrednia());
		window.panelPierwszegoSygnalu.generatedParamsPanel.setSredniaBezwzgl(firstSignal.wartoscSredniaBezwzgledna());
		window.panelPierwszegoSygnalu.generatedParamsPanel.setMoc(firstSignal.mocSrednia());
	}

	private void updateSignal2Params() {
		window.panelDrugiegoSygnalu.generatedParamsPanel.setWariancja(secondSignal.wariancja());
		window.panelDrugiegoSygnalu.generatedParamsPanel.setWartoscSkuteczna(secondSignal.wartoscSkuteczna());
		window.panelDrugiegoSygnalu.generatedParamsPanel.setSrednia(secondSignal.wartoscSrednia());
		window.panelDrugiegoSygnalu.generatedParamsPanel.setSredniaBezwzgl(secondSignal.wartoscSredniaBezwzgledna());
		window.panelDrugiegoSygnalu.generatedParamsPanel.setMoc(secondSignal.mocSrednia());
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
				FunkcjaCiagla funkcja = (FunkcjaCiagla) window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem();
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
							"Parametry które zosta³y wprowadzone nie s¹ poprawne. WprowadŸ poprawne liczby.",
							"B³¹d podczas przetwarzania parametrów...", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Double[] parameters = params.toArray(new Double[params.size()]);
				FunkcjaCiagla funkcja = (FunkcjaCiagla) window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem();
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
			FunkcjaCiagla sig = (FunkcjaCiagla) window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem();
			window.panelPierwszegoSygnalu.setParamsNames(sig.getParametersNames());
		}

	};

	private ActionListener secondSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			FunkcjaCiagla sig = (FunkcjaCiagla) window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem();
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
			
			if(window.panelKonwersji.getWyborSygnaluPierwszego().isSelected()){
				window.panelKonwersji.getPodgladSygnalu().setChart(window.panelPierwszegoSygnalu.getChartPanel().getChart());
				window.panelKonwersji.setSygnalDoProbkowania((FunkcjaCiagla)window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem());
				window.panelKonwersji.repaint();
			}else if(window.panelKonwersji.getWyborSygnaluDrugiego().isSelected()){
				window.panelKonwersji.getPodgladSygnalu().setChart(window.panelDrugiegoSygnalu.getChartPanel().getChart());
				window.panelKonwersji.setSygnalDoProbkowania((FunkcjaCiagla)window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem());
				window.panelKonwersji.repaint();
			}
		}
	};
	
	private ActionListener zmianaSygnaluDoKwantyzacji = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(window.panelKwantyzacji.getWyborSygnaluPierwszego().isSelected()){
				window.panelKwantyzacji.setChart(window.panelPierwszegoSygnalu.getChartPanel().getChart());
				window.panelKwantyzacji.setSygnalDoKwantyzacji((FunkcjaCiagla)window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem());
				window.panelKwantyzacji.repaint();
			}else if(window.panelKwantyzacji.getWyborSygnaluDrugiego().isSelected()){
				window.panelKwantyzacji.setChart(window.panelDrugiegoSygnalu.getChartPanel().getChart());
				window.panelKwantyzacji.setSygnalDoKwantyzacji((FunkcjaCiagla)window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem());
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
			SygnalDyskretny sygnalSprobkowany = Próbkowanie.próbkuj(window.panelKonwersji.getSygnalDoProbkowania(), Double.parseDouble(window.panelKonwersji.getChartFrom().getText()), Integer.parseInt(window.panelKonwersji.getChartStep().getText()), Double.parseDouble(window.panelKonwersji.getChartTo().getText()), showAsContinuous);
			window.panelKonwersji.setSygnalSprobkowany(sygnalSprobkowany);
			window.panelKonwersji.setChart(sygnalSprobkowany.getChart(""));
		}
	};
	
	private ActionListener btnOdtworzSygnalListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			KonwersjaCA konwersja = (KonwersjaCA)window.panelKonwersji.getKonwersja().getSelectedItem();
			konwersja.konwertuj(window.panelKonwersji.getSygnalSprobkowany());
			boolean showAsContinuous = true;
			SygnalDyskretny odwzorowanieCiaglegoSygOdtworzonego = Próbkowanie.próbkuj(konwersja, Double.parseDouble(window.panelKonwersji.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(window.panelKonwersji.getChartTo().getText()), showAsContinuous);
			window.panelKonwersji.setHistogram(odwzorowanieCiaglegoSygOdtworzonego.getChart(""));
			oblicMiaryPodobienstwaKonwersji();
		}
	};

	private MyCallable<JFreeChart> updateFirstSignalPreview = new MyCallable<JFreeChart>() {

		@Override
		public void call(JFreeChart chart) {
			window.panelWynikuOperacji.getPanelFirstSignalPrev().setChart(chart);
			window.panelWynikuOperacji.repaint();
			
			if(window.panelKonwersji.getWyborSygnaluPierwszego().isSelected()){
				window.panelKonwersji.getPodgladSygnalu().setChart(chart);
				window.panelKonwersji.setSygnalDoProbkowania((FunkcjaCiagla)window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem());
			}
			
			if(window.panelKwantyzacji.getWyborSygnaluPierwszego().isSelected()){
				window.panelKwantyzacji.setChart(window.panelPierwszegoSygnalu.getChartPanel().getChart());
				window.panelKwantyzacji.setSygnalDoKwantyzacji((FunkcjaCiagla)window.panelPierwszegoSygnalu.getSignalChooser().getSelectedItem());
			}

		}
	};

	private MyCallable<JFreeChart> updateSecondSignalPreview = new MyCallable<JFreeChart>() {

		@Override
		public void call(JFreeChart chart) {
			window.panelWynikuOperacji.getPanelSecondSignalPrev().setChart(chart);
			window.panelWynikuOperacji.repaint();
			if(window.panelKonwersji.getWyborSygnaluDrugiego().isSelected()){
				window.panelKonwersji.getPodgladSygnalu().setChart(chart);
				window.panelKonwersji.setSygnalDoProbkowania((FunkcjaCiagla)window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem());
			}
			
			if(window.panelKwantyzacji.getWyborSygnaluDrugiego().isSelected()){
				window.panelKwantyzacji.setChart(window.panelDrugiegoSygnalu.getChartPanel().getChart());
				window.panelKwantyzacji.setSygnalDoKwantyzacji((FunkcjaCiagla)window.panelDrugiegoSygnalu.getSignalChooser().getSelectedItem());
			}
		}
	};
	
	private ActionListener btnKwantyzujListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Kwantyzacja kwantyzacja = (Kwantyzacja)window.panelKwantyzacji.getKwantyzacja().getSelectedItem();
			SygnalDyskretny skwantyzowany = kwantyzacja.kwantyzuj(window.panelKwantyzacji.getSygnalDoKwantyzacji(), Double.parseDouble(window.panelKonwersji.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(window.panelKonwersji.getChartTo().getText()), Integer.parseInt(window.panelKwantyzacji.getIloscStopniVal().getText()));
			window.panelKwantyzacji.setSygnalSkwantyzowany(skwantyzowany);
			window.panelKwantyzacji.setSecondChart(skwantyzowany.getChart(""));
			oblicMiaryPodobienstwaKwantyzacji();
		}
	};
	
	private void oblicMiaryPodobienstwaKonwersji(){
		double poczatek = Double.parseDouble(window.panelKonwersji.getChartFrom().getText());
		double koniec = Double.parseDouble(window.panelKonwersji.getChartTo().getText());
		int czestotliwosc = CZEST_PROB_F_CIAG;
		SygnalDyskretny sygPierwszy = Próbkowanie.próbkuj(window.panelKonwersji.getSygnalDoProbkowania(), poczatek, czestotliwosc, koniec);
		SygnalDyskretny sygDrugi = Próbkowanie.próbkuj((FunkcjaCiagla)window.panelKonwersji.getKonwersja().getSelectedItem(), poczatek, czestotliwosc, koniec);
	
		window.panelKonwersji.getPanelMiarPodobienstwa().setMSE(MiaryPodobienstwa.mse(sygPierwszy.y, sygDrugi.y));
		window.panelKonwersji.getPanelMiarPodobienstwa().setSNR(MiaryPodobienstwa.snr(sygPierwszy.y, sygDrugi.y));
		window.panelKonwersji.getPanelMiarPodobienstwa().setPSNR(MiaryPodobienstwa.psnr(sygPierwszy.y, sygDrugi.y));
		window.panelKonwersji.getPanelMiarPodobienstwa().setMD(MiaryPodobienstwa.md(sygPierwszy.y, sygDrugi.y));
		
		
	}
	
	private void oblicMiaryPodobienstwaKwantyzacji(){
		double poczatek = Double.parseDouble(window.panelKwantyzacji.getChartFrom().getText());
		double koniec = Double.parseDouble(window.panelKwantyzacji.getChartTo().getText());
		int czestotliwosc = CZEST_PROB_F_CIAG;
		SygnalDyskretny sygPierwszy = Próbkowanie.próbkuj(window.panelKwantyzacji.getSygnalDoKwantyzacji(), poczatek, czestotliwosc, koniec);
		SygnalDyskretny sygDrugi = window.panelKwantyzacji.getSygnalSkwantyzowany();
	
		window.panelKwantyzacji.getPanelMiarPodobienstwa().setMSE(MiaryPodobienstwa.mse(sygPierwszy.y, sygDrugi.y));
		window.panelKwantyzacji.getPanelMiarPodobienstwa().setSNR(MiaryPodobienstwa.snr(sygPierwszy.y, sygDrugi.y));
		window.panelKwantyzacji.getPanelMiarPodobienstwa().setPSNR(MiaryPodobienstwa.psnr(sygPierwszy.y, sygDrugi.y));
		window.panelKwantyzacji.getPanelMiarPodobienstwa().setMD(MiaryPodobienstwa.md(sygPierwszy.y, sygDrugi.y));
		
		
	}

}
