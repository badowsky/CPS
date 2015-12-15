package Controller;

import java.awt.EventQueue;

import javax.swing.UIManager;

import Controller.GuiView.ConversionController;
import Controller.GuiView.FiltrationController;
import Controller.GuiView.OperationsController;
import Controller.GuiView.QuantizationController;
import Controller.GuiView.SignalPanelController;
import Helpers.MyCallable;
import Helpers.MyCallable2;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;
import View.MainWindow;

public class GuiController {

	private MainWindow window;

	private SignalPanelController firstSignalController;
	private SignalPanelController secondSignalController;
	private OperationsController operationsController;
	private ConversionController conversionController;
	private QuantizationController quantizationController;
	private FiltrationController filtrationController;

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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong with setting look and feel...");
		}
		
		window = new MainWindow();
		firstSignalController = new SignalPanelController(window.firstSignalPanel);
		secondSignalController = new SignalPanelController(window.secondSignalPanel);
		operationsController = new OperationsController(window.operationsPanel);
		conversionController = new ConversionController(window.conversionPanel);
		quantizationController = new QuantizationController(window.quantizationPanel);
		filtrationController = new FiltrationController(window.filtrationPanel);
		
		this.initialize();
		window.frame.setVisible(true);
	}

	private void initialize() {
		firstSignalController.subscribeOnChartChange(firstSignalChangeListener);
		secondSignalController.subscribeOnChartChange(secondSignalChangeListener); 
		operationsController.subscribeOnResultChange(resultSignalChangeListener);
	}

	private MyCallable2<DiscreteSignalReal, ContinuousSignal> firstSignalChangeListener = new MyCallable2<DiscreteSignalReal, ContinuousSignal>() {

		@Override
		public void call(DiscreteSignalReal signal, ContinuousSignal signalContinuous) {
			operationsController.notifyFirstSignalChanged(signal, signalContinuous);
			conversionController.notifyFirstSignalChanged(signal, signalContinuous);
			quantizationController.notifyFirstSignalChanged(signal, signalContinuous);
		}
	};

	private MyCallable2<DiscreteSignalReal, ContinuousSignal> secondSignalChangeListener = new MyCallable2<DiscreteSignalReal, ContinuousSignal>() {

		@Override
		public void call(DiscreteSignalReal signal, ContinuousSignal signalContinuous) {
			operationsController.notifySecondSignalChanged(signal, signalContinuous);
			conversionController.notifySecondSignalChanged(signal, signalContinuous);
			quantizationController.notifySecondSignalChanged(signal, signalContinuous);
		}
	};
	
	private MyCallable<DiscreteSignalReal> resultSignalChangeListener = new MyCallable<DiscreteSignalReal>() {

		@Override
		public void call(DiscreteSignalReal signal) {
			filtrationController.notifySignalAfterOperationsChanged(signal);
		}
	};
	

}
