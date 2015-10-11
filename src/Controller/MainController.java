package Controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Helpers.IOUtils;
import Model.Signal;
import Model.SignalGenerator;
import Model.Sygnal.ImpulsJednostkowy;
import Model.Sygnal.Prostokatny;
import Model.Sygnal.ProstokatnySymetryczny;
import Model.Sygnal.Sinus;
import Model.Sygnal.SinusWyprostDwupol;
import Model.Sygnal.SinusWyprostJednopol;
import Model.Sygnal.SkokJednostkowy;
import Model.Sygnal.Trojkatny;
import Model.Szum.Gaussowski;
import Model.Szum.Impulsowy;
import Model.Szum.RozkladJednostajny;
import Operation.Addition;
import Operation.Divide;
import Operation.Multiplication;
import Operation.SignalOperation;
import Operation.Substraction;
import View.MainWindow;

public class MainController {

	private MainWindow window;
	private final JFileChooser fc = new JFileChooser();

	private Signal firstSignal;
	private Signal secondSignal;
	private Signal resultSignal;

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
		window.getBtnGenSig1().addActionListener(btnGenSig1Listener);
		window.getBtnGenSig2().addActionListener(btnGenSig2Listener);
		window.getSig1ComboBox().addActionListener(firstSigListener);
		window.getSig2ComboBox().addActionListener(secondSigListener);
		window.getBtnWczytajSig1().addActionListener(loadFirstSigListener);
		window.getBtnWczytajSig2().addActionListener(loadSecondSigListener);
		window.getBtnWykonajOperacje().addActionListener(doOperationListener);
		window.getBtnSaveResult().addActionListener(saveResultSigListener);

		window.getSig1ComboBox().addItem(new Sinus());
		window.getSig1ComboBox().addItem(new SinusWyprostJednopol());
		window.getSig1ComboBox().addItem(new SinusWyprostDwupol());
		window.getSig1ComboBox().addItem(new Prostokatny());
		window.getSig1ComboBox().addItem(new ProstokatnySymetryczny());
		window.getSig1ComboBox().addItem(new Trojkatny());
		window.getSig1ComboBox().addItem(new SkokJednostkowy());
		window.getSig1ComboBox().addItem(new ImpulsJednostkowy());

		window.getSig1ComboBox().addItem(new RozkladJednostajny());
		window.getSig1ComboBox().addItem(new Gaussowski());
		window.getSig1ComboBox().addItem(new Impulsowy());

		window.getSig2ComboBox().addItem(new Sinus());
		window.getSig2ComboBox().addItem(new SinusWyprostJednopol());
		window.getSig2ComboBox().addItem(new SinusWyprostDwupol());
		window.getSig2ComboBox().addItem(new Prostokatny());
		window.getSig2ComboBox().addItem(new ProstokatnySymetryczny());
		window.getSig2ComboBox().addItem(new Trojkatny());
		window.getSig2ComboBox().addItem(new SkokJednostkowy());
		window.getSig2ComboBox().addItem(new ImpulsJednostkowy());

		window.getSig2ComboBox().addItem(new RozkladJednostajny());
		window.getSig2ComboBox().addItem(new Gaussowski());
		window.getSig2ComboBox().addItem(new Impulsowy());

		window.getRodzajOperComboBox().addItem(new Addition());
		window.getRodzajOperComboBox().addItem(new Substraction());
		window.getRodzajOperComboBox().addItem(new Multiplication());
		window.getRodzajOperComboBox().addItem(new Divide());
	}
	
	private void reloadSignal1Charts(){
		window.setChart1((firstSignal.getChart(null)));
		window.setHistogram1((firstSignal.getHistogram(null)));
	}
	
	private void reloadSignal2Charts(){
		window.setChart2((secondSignal.getChart(null)));
		window.setHistogram2((secondSignal.getHistogram(null)));
	}
	
	private void reloadSignal3Charts(){
		window.setChart3((resultSignal.getChart(null)));
		window.setHistogram3((resultSignal.getHistogram(null)));
	}

	private ActionListener btnGenSig1Listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SignalGenerator generator = (SignalGenerator) window.getSig1ComboBox().getSelectedItem();
			ArrayList<Double> params = new ArrayList<Double>();
			try {
				for (JTextField field : window.getSig1ParamsTextField()) {
					if (field.isVisible())
						params.add(Double.parseDouble(field.getText()));
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(window.frame,
						"Parametry które zosta³y wprowadzone nie s¹ poprawne. WprowadŸ poprawne liczby.",
						"B³¹d podczas przetwarzania parametrów...", JOptionPane.ERROR_MESSAGE);
				return;
			}

			generator.setParams(params);
			firstSignal = generator.generate();
			reloadSignal1Charts();
		}
	};

	private ActionListener btnGenSig2Listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SignalGenerator generator = (SignalGenerator) window.getSig2ComboBox().getSelectedItem();
			ArrayList<Double> params = new ArrayList<Double>();
			try {
				for (JTextField field : window.getSig2ParamsTextField()) {
					if (field.isVisible())
						params.add(Double.parseDouble(field.getText()));
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(window.frame,
						"Parametry które zosta³y wprowadzone nie s¹ poprawne. WprowadŸ poprawne liczby.",
						"B³¹d podczas przetwarzania parametrów...", JOptionPane.ERROR_MESSAGE);
				return;
			}
			generator.setParams(params);
			secondSignal = generator.generate();
			reloadSignal2Charts();
		}
	};

	private ActionListener firstSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SignalGenerator sig = (SignalGenerator) window.getSig1ComboBox().getSelectedItem();
			window.setSig1ParamsNames(sig.paramsNames);
		}

	};

	private ActionListener secondSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SignalGenerator sig = (SignalGenerator) window.getSig2ComboBox().getSelectedItem();
			window.setSig2ParamsNames(sig.paramsNames);
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
			SignalOperation operation = (SignalOperation) window.getRodzajOperComboBox().getSelectedItem();
			resultSignal = operation.DoOperation(firstSignal, secondSignal);
			reloadSignal3Charts();
		}

	};
	
	}
	
	
