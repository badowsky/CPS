package Controller.GuiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;

import Helpers.IOUtils;
import Helpers.MyCallable;
import Model.Konwersja.Próbkowanie;
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
import View.SignalPanel;

public class SignalPanelController {
	
	private SignalPanel panel;
	private SygnalDyskretnyReal signal;
	int CZEST_PROB_F_CIAG = 1000;
	private ArrayList<MyCallable<JFreeChart>> subscribersOnChange;
	private final JFileChooser fc;
	
	public SignalPanelController(SignalPanel panel){
		this.panel = panel;
		subscribersOnChange = new ArrayList<MyCallable<JFreeChart>>();
		fc = new JFileChooser();
		initialize();
	}
	
	private void initialize(){
		this.panel.getSignalChooser().addActionListener(chooseSigListener);
		this.panel.getBtnLoad().addActionListener(loadSigListener);
		this.panel.getSignalChooser().addItem(new Sinus());
		this.panel.getSignalChooser().addItem(new SinusWyprostJednopol());
		this.panel.getSignalChooser().addItem(new SinusWyprostDwupol());
		this.panel.getSignalChooser().addItem(new Prostokatny());
		this.panel.getSignalChooser().addItem(new ProstokatnySymetryczny());
		this.panel.getSignalChooser().addItem(new Trojkatny());
		this.panel.getSignalChooser().addItem(new SkokJednostkowy());
		this.panel.getSignalChooser().addItem(new ImpulsJednostkowy());

		this.panel.getSignalChooser().addItem(new RozkladJednostajny());
		this.panel.getSignalChooser().addItem(new Gaussian());
		this.panel.getSignalChooser().addItem(new Impulsowy());
		
		this.panel.getBtnGenerate().addActionListener(btnGenSigListener);
	}
	
	public void subscribeOnChartChange(MyCallable<JFreeChart> callable){
		subscribersOnChange.add(callable);
	}
	
	private void signalChanged(){
		reloadSignalCharts();
		updateSignalParameters();
		for(MyCallable<JFreeChart> callable : subscribersOnChange){
			callable.call(signal.getChart(null));
		}
	}
	
	private void reloadSignalCharts() {
		panel.setChart((signal.getChart(null)));
		panel.setHistogram((signal.getHistogram(null)));
	}

	private void updateSignalParameters(){
		this.panel.getGeneratedParamsPanel().setVariance(signal.wariancja());
		this.panel.getGeneratedParamsPanel().setEffectiveValue(signal.wartoscSkuteczna());
		this.panel.getGeneratedParamsPanel().setAvg(signal.wartoscSrednia());
		this.panel.getGeneratedParamsPanel().setAbsoluteAvg(signal.wartoscSredniaBezwzgledna());
		this.panel.getGeneratedParamsPanel().setPower(signal.mocSrednia());
	}
	
	private ActionListener btnGenSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
				ArrayList<Double> params = new ArrayList<Double>();
				try {
					for (JTextField field : panel.getParamsFields()) {
						if (field.isVisible())
							params.add(Double.parseDouble(field.getText()));
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(panel,
							"Parametry które zostały wprowadzone nie są poprawne. Wprowadź poprawne liczby.",
							"Błąd podczas przetwarzania parametrów...", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Double[] parameters = params.toArray(new Double[params.size()]);
				ContinuousSignal funkcja = (ContinuousSignal) panel.getSignalChooser().getSelectedItem();
				funkcja.setParams(parameters);
				boolean representAsContinuous = true;
				if(funkcja instanceof ImpulsJednostkowy) representAsContinuous = false;
				
				signal = Próbkowanie.próbkuj(funkcja, Double.parseDouble(panel.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(panel.getChartTo().getText()), representAsContinuous);
				signal.funkcjaCiagla = funkcja;
				signalChanged();

		}
	};

	private ActionListener chooseSigListener = new ActionListener() {
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ContinuousSignal sig = (ContinuousSignal) panel.getSignalChooser().getSelectedItem();
			panel.setParamsNames(sig.getParametersNames());
		}
	
	};
	
	private ActionListener loadSigListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			int returnVal = fc.showOpenDialog(panel);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				signal = IOUtils.LoadSignal(file);
				signalChanged();
			} else {

			}

		}

	};
}