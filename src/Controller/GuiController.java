package Controller;

import java.awt.EventQueue;

import javax.swing.UIManager;

import Controller.GuiView.ConversionController;
import Controller.GuiView.OperationsController;
import Controller.GuiView.QuantizationController;
import Controller.GuiView.SignalPanelController;
import Helpers.MyCallable2;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.SygnalDyskretnyReal;
import View.MainWindow;

public class GuiController {

	private MainWindow window;

	private SignalPanelController firstSignalController;
	private SignalPanelController secondSignalController;
	private OperationsController operationsController;
	private ConversionController conversionController;
	private QuantizationController quantizationController;

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
		window = new MainWindow();
		firstSignalController = new SignalPanelController(window.firstSignalPanel);
		secondSignalController = new SignalPanelController(window.secondSignalPanel);
		operationsController = new OperationsController(window.operationsPanel);
		conversionController = new ConversionController(window.conversionPanel);
		quantizationController = new QuantizationController(window.quantizationPanel);
		this.initialize();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong with setting look and feel...");
		}
		
		window.frame.setVisible(true);
	}

	private void initialize() {
		firstSignalController.subscribeOnChartChange(updateFirstSignalPreview);
		secondSignalController.subscribeOnChartChange(updateSecondSignalPreview); 
	}

	private MyCallable2<SygnalDyskretnyReal, ContinuousSignal> updateFirstSignalPreview = new MyCallable2<SygnalDyskretnyReal, ContinuousSignal>() {

		@Override
		public void call(SygnalDyskretnyReal signal, ContinuousSignal signalContinuous) {
			operationsController.notifyFirstSignalChanged(signal, signalContinuous);
			conversionController.notifyFirstSignalChanged(signal, signalContinuous);
			quantizationController.notifyFirstSignalChanged(signal, signalContinuous);
		}
	};

	private MyCallable2<SygnalDyskretnyReal, ContinuousSignal> updateSecondSignalPreview = new MyCallable2<SygnalDyskretnyReal, ContinuousSignal>() {

		@Override
		public void call(SygnalDyskretnyReal signal, ContinuousSignal signalContinuous) {
			operationsController.notifySecondSignalChanged(signal, signalContinuous);
			conversionController.notifySecondSignalChanged(signal, signalContinuous);
			quantizationController.notifySecondSignalChanged(signal, signalContinuous);
		}
	};
	

}
