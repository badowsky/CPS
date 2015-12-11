package Controller.GuiView;

import Model.Konwersja.FirstOrderHold;
import Model.Konwersja.ZeroOrderHold;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.SygnalDyskretnyReal;
import View.ConversionPanel;

public class ConvertionController {
	
	private ConversionPanel panel;

	public ConvertionController(ConversionPanel panel){
		this.panel = panel;
		initialize();
	}
	
	private void initialize(){
		this.panel.getConversion().addItem(new ZeroOrderHold());
		this.panel.getConversion().addItem(new FirstOrderHold());
	}
	
	public void notifyFirstSignalChanged(SygnalDyskretnyReal firstSignal, ContinuousSignal firstSignalContinuous){
		if(this.panel.getFirstSignalChoose().isSelected()){
			this.panel.getSignalPreview().setChart(firstSignal.getChart(null));
			this.panel.setSignalForSampling(firstSignalContinuous);
			this.panel.repaint();
		}
	}
	
	public void notifySecondSignalChanged(SygnalDyskretnyReal secondSignal, ContinuousSignal secondSignalContinuous){
		if(this.panel.getSecondSignalChoose().isSelected()){
			this.panel.getSignalPreview().setChart(secondSignal.getChart(null));
			this.panel.setSignalForSampling(secondSignalContinuous);
			this.panel.repaint();
		}
	}
}
