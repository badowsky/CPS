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
import Model.Signal;
import Model.SignalGenerator;
import Model.Operation.Addition;
import Model.Operation.Divide;
import Model.Operation.Multiplication;
import Model.Operation.SignalOperation;
import Model.Operation.Substraction;
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
		window.firstSignalPanel.getBtnGenerate().addActionListener(btnGenSig1Listener);
		window.secondSignalPanel.getBtnGenerate().addActionListener(btnGenSig2Listener);
		window.firstSignalPanel.getSignalChooser().addActionListener(firstSigListener);
		window.secondSignalPanel.getSignalChooser().addActionListener(secondSigListener);
		window.firstSignalPanel.getBtnLoad().addActionListener(loadFirstSigListener);
		window.secondSignalPanel.getBtnLoad().addActionListener(loadSecondSigListener);
		window.resultSignalPanel.getBtnDoOperation().addActionListener(doOperationListener);
		window.resultSignalPanel.getBtnSaveResult().addActionListener(saveResultSigListener);

		window.firstSignalPanel.subscribeOnChartChange(updateFirstSignalPreview);
		window.secondSignalPanel.subscribeOnChartChange(updateSecondSignalPreview);

		window.firstSignalPanel.getSignalChooser().addItem(new Sinus());
		window.firstSignalPanel.getSignalChooser().addItem(new SinusWyprostJednopol());
		window.firstSignalPanel.getSignalChooser().addItem(new SinusWyprostDwupol());
		window.firstSignalPanel.getSignalChooser().addItem(new Prostokatny());
		window.firstSignalPanel.getSignalChooser().addItem(new ProstokatnySymetryczny());
		window.firstSignalPanel.getSignalChooser().addItem(new Trojkatny());
		window.firstSignalPanel.getSignalChooser().addItem(new SkokJednostkowy());
		window.firstSignalPanel.getSignalChooser().addItem(new ImpulsJednostkowy());

		window.firstSignalPanel.getSignalChooser().addItem(new RozkladJednostajny());
		window.firstSignalPanel.getSignalChooser().addItem(new Gaussowski());
		window.firstSignalPanel.getSignalChooser().addItem(new Impulsowy());

		window.secondSignalPanel.getSignalChooser().addItem(new Sinus());
		window.secondSignalPanel.getSignalChooser().addItem(new SinusWyprostJednopol());
		window.secondSignalPanel.getSignalChooser().addItem(new SinusWyprostDwupol());
		window.secondSignalPanel.getSignalChooser().addItem(new Prostokatny());
		window.secondSignalPanel.getSignalChooser().addItem(new ProstokatnySymetryczny());
		window.secondSignalPanel.getSignalChooser().addItem(new Trojkatny());
		window.secondSignalPanel.getSignalChooser().addItem(new SkokJednostkowy());
		window.secondSignalPanel.getSignalChooser().addItem(new ImpulsJednostkowy());

		window.secondSignalPanel.getSignalChooser().addItem(new RozkladJednostajny());
		window.secondSignalPanel.getSignalChooser().addItem(new Gaussowski());
		window.secondSignalPanel.getSignalChooser().addItem(new Impulsowy());

		window.resultSignalPanel.getOperations().addItem(new Addition());
		window.resultSignalPanel.getOperations().addItem(new Substraction());
		window.resultSignalPanel.getOperations().addItem(new Multiplication());
		window.resultSignalPanel.getOperations().addItem(new Divide());
	}

	private void reloadSignal1Charts() {
		window.firstSignalPanel.setChart((firstSignal.getChart(null)));
		window.firstSignalPanel.setHistogram((firstSignal.getHistogram(null)));
	}

	private void reloadSignal2Charts() {
		window.secondSignalPanel.setChart((secondSignal.getChart(null)));
		window.secondSignalPanel.setHistogram((secondSignal.getHistogram(null)));
	}

	private void reloadSignal3Charts() {
		window.resultSignalPanel.setChart((resultSignal.getChart(null)));
		window.resultSignalPanel.setHistogram((resultSignal.getHistogram(null)));
	}

	private void updateSignal1Params() {
		window.firstSignalPanel.generatedParamsPanel.setWariancja(firstSignal.wariancja());
		window.firstSignalPanel.generatedParamsPanel.setWartoscSkuteczna(firstSignal.wartoscSkuteczna());
		window.firstSignalPanel.generatedParamsPanel.setSrednia(firstSignal.wartoscSrednia());
		window.firstSignalPanel.generatedParamsPanel.setSredniaBezwzgl(firstSignal.wartoscSredniaBezwzgledna());
		window.firstSignalPanel.generatedParamsPanel.setMoc(firstSignal.mocSrednia());
	}

	private void updateSignal2Params() {
		window.secondSignalPanel.generatedParamsPanel.setWariancja(secondSignal.wariancja());
		window.secondSignalPanel.generatedParamsPanel.setWartoscSkuteczna(secondSignal.wartoscSkuteczna());
		window.secondSignalPanel.generatedParamsPanel.setSrednia(secondSignal.wartoscSrednia());
		window.secondSignalPanel.generatedParamsPanel.setSredniaBezwzgl(secondSignal.wartoscSredniaBezwzgledna());
		window.secondSignalPanel.generatedParamsPanel.setMoc(secondSignal.mocSrednia());
	}

	private ActionListener btnGenSig1Listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SignalGenerator generator = (SignalGenerator) window.firstSignalPanel.getSignalChooser().getSelectedItem();
			ArrayList<Double> params = new ArrayList<Double>();
			try {
				for (JTextField field : window.firstSignalPanel.getParamsFields()) {
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
			updateSignal1Params();
		}
	};

	private ActionListener btnGenSig2Listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SignalGenerator generator = (SignalGenerator) window.secondSignalPanel.getSignalChooser().getSelectedItem();
			ArrayList<Double> params = new ArrayList<Double>();
			try {
				for (JTextField field : window.secondSignalPanel.getParamsFields()) {
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
			updateSignal2Params();
		}
	};

	private ActionListener firstSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SignalGenerator sig = (SignalGenerator) window.firstSignalPanel.getSignalChooser().getSelectedItem();
			window.firstSignalPanel.setParamsNames(sig.paramsNames);
		}

	};

	private ActionListener secondSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SignalGenerator sig = (SignalGenerator) window.secondSignalPanel.getSignalChooser().getSelectedItem();
			window.secondSignalPanel.setParamsNames(sig.paramsNames);
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
			SignalOperation operation = (SignalOperation) window.resultSignalPanel.getOperations().getSelectedItem();
			resultSignal = operation.DoOperation(firstSignal, secondSignal);
			reloadSignal3Charts();
		}

	};

	private MyCallable<JFreeChart> updateFirstSignalPreview = new MyCallable<JFreeChart>() {

		@Override
		public void call(JFreeChart chart) {
			window.resultSignalPanel.getPanelFirstSignalPrev().setChart(chart);
			window.resultSignalPanel.repaint();

		}
	};

	private MyCallable<JFreeChart> updateSecondSignalPreview = new MyCallable<JFreeChart>() {

		@Override
		public void call(JFreeChart chart) {
			window.resultSignalPanel.getPanelSecondSignalPrev().setChart(chart);
			window.resultSignalPanel.repaint();
		}
	};

}
