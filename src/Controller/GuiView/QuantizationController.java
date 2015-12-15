package Controller.GuiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Conversion.Quantization;
import Model.Conversion.KwantyzacjaZObcieciem;
import Model.Conversion.KwantyzacjaZZaokragleniem;
import Model.Conversion.Sampling;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;
import View.QuantizationPanel;

public class QuantizationController {
	
	private QuantizationPanel panel;
	private static final int CZEST_PROB_F_CIAG = 1000;
	
	public QuantizationController(QuantizationPanel panel){
		this.panel = panel;
		initialize();
	}
	
	private void initialize(){
		this.panel.getQuantization().addItem(new KwantyzacjaZObcieciem(10));
		this.panel.getQuantization().addItem(new KwantyzacjaZZaokragleniem(10));
		
		this.panel.getBtnDoQuantization().addActionListener(btnKwantyzujListener);
		
		this.panel.getFirstSignalChoose().addActionListener(null);
		this.panel.getSecondSignalChoose().addActionListener(null);
	}
	
	public void notifyFirstSignalChanged(DiscreteSignalReal firstSignal, ContinuousSignal firstSignalContinuous){
		if(this.panel.getFirstSignalChoose().isSelected()){
			this.panel.setChart(firstSignal.getChart(null));
			this.panel.setSignalForQuantization(firstSignalContinuous);
			this.panel.repaint();
		}
	}
	
	public void notifySecondSignalChanged(DiscreteSignalReal secondSignal, ContinuousSignal secondSignalContinuous){
		if(this.panel.getSecondSignalChoose().isSelected()){
			this.panel.setChart(secondSignal.getChart(null));
			this.panel.setSignalForQuantization(secondSignalContinuous);
			this.panel.repaint();
		}
	}
	
	private void calculateComparsionMeasures(){
		double poczatek = Double.parseDouble(this.panel.getChartFrom().getText());
		double koniec = Double.parseDouble(this.panel.getChartTo().getText());
		DiscreteSignalReal sygPierwszy = Sampling.sample(this.panel.getSignalForQuantization(), poczatek, CZEST_PROB_F_CIAG, koniec);
		DiscreteSignalReal sygDrugi = this.panel.getSignalAfterQuantization();
	
		Utils.calculateComparsionMeasures(sygPierwszy, sygDrugi, this.panel.getSignalsComparsionPanel());
	}
	
	private ActionListener btnKwantyzujListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Quantization kwantyzacja = (Quantization)panel.getQuantization().getSelectedItem();
			DiscreteSignalReal skwantyzowany = kwantyzacja.kwantyzuj(panel.getSignalForQuantization(), Double.parseDouble(panel.getChartFrom().getText()), CZEST_PROB_F_CIAG, Double.parseDouble(panel.getChartTo().getText()), Integer.parseInt(panel.getNumberOfLevelsVal().getText()));
			panel.setSignalAfterQuantization(skwantyzowany);
			panel.setSecondChart(skwantyzowany.getChart(""));
			calculateComparsionMeasures();
		}
	};
}
