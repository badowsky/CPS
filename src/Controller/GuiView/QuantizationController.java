package Controller.GuiView;

import Model.Konwersja.KwantyzacjaZObcieciem;
import Model.Konwersja.KwantyzacjaZZaokragleniem;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.SygnalDyskretnyReal;
import View.QuantizationPanel;

public class QuantizationController {
	
	private QuantizationPanel panel;
	
	public QuantizationController(QuantizationPanel panel){
		this.panel = panel;
		initialize();
	}
	
	private void initialize(){
		this.panel.getQuantization().addItem(new KwantyzacjaZObcieciem(10));
		this.panel.getQuantization().addItem(new KwantyzacjaZZaokragleniem(10));
	}
	
	public void notifyFirstSignalChanged(SygnalDyskretnyReal firstSignal, ContinuousSignal firstSignalContinuous){
		if(this.panel.getFirstSignalChoose().isSelected()){
			this.panel.setChart(firstSignal.getChart(null));
			this.panel.setSignalForQuantization(firstSignalContinuous);
			this.panel.repaint();
		}
	}
	
	public void notifySecondSignalChanged(SygnalDyskretnyReal secondSignal, ContinuousSignal secondSignalContinuous){
		if(this.panel.getSecondSignalChoose().isSelected()){
			this.panel.setChart(secondSignal.getChart(null));
			this.panel.setSignalForQuantization(secondSignalContinuous);
			this.panel.repaint();
		}
	}
}
