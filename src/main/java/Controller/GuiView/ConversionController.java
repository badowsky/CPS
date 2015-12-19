package Controller.GuiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Conversion.FirstOrderHold;
import Model.Conversion.ConversionCA;
import Model.Conversion.Sampling;
import Model.Conversion.ZeroOrderHold;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;
import View.ConversionPanel;

public class ConversionController {
	
	private ConversionPanel panel;
	private DiscreteSignalReal firstSignal, secondSignal;
	private ContinuousSignal firstContinuousSignal, secondContinuousSignal;
	private static final int CZEST_PROB_F_CIAG = 1000;

	public ConversionController(ConversionPanel panel){
		this.panel = panel;
		initialize();
	}
	
	private void initialize(){
		this.panel.getConversionType().addItem(new ZeroOrderHold());
		this.panel.getConversionType().addItem(new FirstOrderHold());
		
		this.panel.getBtnDoSampling().addActionListener(btnSampleSignalListener);
		this.panel.getBtnReCreate().addActionListener(btnReCreateSignalListener);
		this.panel.getFirstSignalChoose().addActionListener(firstSignalChoosedListener);
		this.panel.getSecondSignalChoose().addActionListener(secondSignalChoosedListener);
	}
	
	public void notifyFirstSignalChanged(DiscreteSignalReal firstSignal, ContinuousSignal firstSignalContinuous){
		this.firstSignal = firstSignal;
		this.firstContinuousSignal = firstSignalContinuous;
		if(this.panel.getFirstSignalChoose().isSelected()){
			displaySignal(firstSignal, firstSignalContinuous);
		}
	}
	
	public void notifySecondSignalChanged(DiscreteSignalReal secondSignal, ContinuousSignal secondSignalContinuous){
		this.secondSignal = secondSignal;
		this.secondContinuousSignal = secondSignalContinuous;
		if(this.panel.getSecondSignalChoose().isSelected()){
			displaySignal(secondSignal, secondSignalContinuous);
		}
	}
	
	private void displaySignal(DiscreteSignalReal firstSignal, ContinuousSignal firstSignalContinuous) {
		this.panel.getSignalPreview().setChart(firstSignal.getChart(null));
		this.panel.setSignalForSampling(firstSignalContinuous);
		this.panel.repaint();
	}
	
	private void calculateComparsionMeasures(){
		double poczatek = Double.parseDouble(panel.getChartFrom().getText());
		double koniec = Double.parseDouble(panel.getChartTo().getText());
		DiscreteSignalReal sygPierwszy = Sampling.sample(panel.getSignalForSampling(), poczatek, CZEST_PROB_F_CIAG, koniec);
		DiscreteSignalReal sygDrugi = Sampling.sample((ContinuousSignal)panel.getConversionType().getSelectedItem(), poczatek, CZEST_PROB_F_CIAG, koniec);
	
		Utils.calculateComparsionMeasures(sygPierwszy, sygDrugi, panel.getSignalComparsionPanel());
	}
	
	private ActionListener btnSampleSignalListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean showAsContinuous = false;
			DiscreteSignalReal sygnalSprobkowany = Sampling.sample(panel.getSignalForSampling(), Double.parseDouble(panel.getChartFrom().getText()), Integer.parseInt(panel.getChartStep().getText()), Double.parseDouble(panel.getChartTo().getText()), showAsContinuous);
			panel.setSignalSampled(sygnalSprobkowany);
			panel.setChart(sygnalSprobkowany.getChart(""));
		}
	};
	
	private ActionListener btnReCreateSignalListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ConversionCA konwersja = (ConversionCA)panel.getConversionType().getSelectedItem();
			konwersja.konwertuj(panel.getSignalSampled());
			boolean showAsContinuous = true;
			DiscreteSignalReal odwzorowanieCiaglegoSygOdtworzonego = Sampling.sample(konwersja, Double.parseDouble(panel.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(panel.getChartTo().getText()), showAsContinuous);
			panel.setHistogram(odwzorowanieCiaglegoSygOdtworzonego.getChart(""));
			calculateComparsionMeasures();
		}
	};
	
	private ActionListener firstSignalChoosedListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			displaySignal(firstSignal, firstContinuousSignal);
		}
	};
	
	private ActionListener secondSignalChoosedListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			displaySignal(secondSignal, secondContinuousSignal);
		}
	};
}
