package Controller.GuiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import Helpers.IOUtils;
import Helpers.MyCallable;
import Model.Operations.Addition;
import Model.Operations.Correlation;
import Model.Operations.Division;
import Model.Operations.Multiplication;
import Model.Operations.OperationOnSignals;
import Model.Operations.Splot;
import Model.Operations.Subtraction;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;
import View.OperationsPanel;

public class OperationsController {

	private DiscreteSignalReal resultSignal;
	private DiscreteSignalReal firstSignal, secondSignal;
	private OperationsPanel panel;
	private final JFileChooser fc;
	private List<MyCallable<DiscreteSignalReal>> subscribersOnResultChange;
	
	public OperationsController(OperationsPanel panel){
		this.panel = panel;
		fc = new JFileChooser();
		subscribersOnResultChange = new ArrayList<MyCallable<DiscreteSignalReal>>();
		initialize();
	}
	
	private void initialize(){
		this.panel.getBtnSaveResult().addActionListener(saveSignalListener);
		this.panel.getBtnDoOperation().addActionListener(doOperationListener);
		
		this.panel.getOperations().addItem(new Addition());
		this.panel.getOperations().addItem(new Subtraction());
		this.panel.getOperations().addItem(new Multiplication());
		this.panel.getOperations().addItem(new Division());
		this.panel.getOperations().addItem(new Splot());
		this.panel.getOperations().addItem(new Correlation());
	}
	
	public void subscribeOnResultChange(MyCallable<DiscreteSignalReal> subscriber){
		subscribersOnResultChange.add(subscriber);
	}
	
	private void resultSignalChanged(){
		for(MyCallable<DiscreteSignalReal> subscriber : subscribersOnResultChange){
			subscriber.call(resultSignal);
		}
	}
	
	private void reloadResultSignalCharts() {
		panel.setChart((resultSignal.getChart(null)));
		panel.setHistogram((resultSignal.getHistogram(null)));
	}
	
	public void notifyFirstSignalChanged(DiscreteSignalReal firstSignal, ContinuousSignal firstSignalContinuous){
		this.firstSignal = firstSignal;
		this.panel.getFirstSignalPrevPanel().setChart(firstSignal.getChart(null));
		this.panel.getFirstSignalPrevPanel().repaint();
	}
	
	public void notifySecondSignalChanged(DiscreteSignalReal secondSignal, ContinuousSignal secondSignalContinuous){
		this.secondSignal = secondSignal;
		this.panel.getSecondSignalPrevPanel().setChart(secondSignal.getChart(null));
		this.panel.getSecondSignalPrevPanel().repaint();
	}
	private ActionListener saveSignalListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			int returnVal = fc.showSaveDialog(panel);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				IOUtils.SaveSignal(file, resultSignal);

			}
		}
	};
	
	private ActionListener doOperationListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			OperationOnSignals operation = (OperationOnSignals) panel.getOperations().getSelectedItem();
			resultSignal = operation.DoOperation(firstSignal, secondSignal);
			reloadResultSignalCharts();
			resultSignalChanged();
		}

	};
}
