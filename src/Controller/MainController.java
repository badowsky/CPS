package Controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.SygnalBase;
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
	
	private SygnalBase firstSignal;
	private SygnalBase secondSignal;
	
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
	
	public MainController(){
		try {
			window = new MainWindow();
			this.initialize();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initialize(){
		window.getBtnGenSig1().addActionListener(btnGenSig1Listener);
		window.getBtnGenSig2().addActionListener(btnGenSig2Listener);
		window.getSig1ComboBox().addActionListener(firstSigListener);
		window.getSig2ComboBox().addActionListener(secondSigListener);
		
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
	}
	
	private ActionListener btnGenSig1Listener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			firstSignal = (SygnalBase)window.getSig1ComboBox().getSelectedItem();
			ArrayList<Double> params = new ArrayList<Double>();
			try{
				for(JTextField field : window.getSig1ParamsTextField()){
					if(field.isVisible()) params.add(Double.parseDouble(field.getText()));
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(window.frame,
					    "Parametry które zosta³y wprowadzone nie s¹ poprawne. WprowadŸ poprawne liczby.",
					    "B³¹d podczas przetwarzania parametrów...",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			firstSignal.setParams(params);
			firstSignal.show("First Signal");
		}
	};
	
	private ActionListener btnGenSig2Listener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			secondSignal = (SygnalBase)window.getSig2ComboBox().getSelectedItem();
			ArrayList<Double> params = new ArrayList<Double>();
			try{
				for(JTextField field : window.getSig2ParamsTextField()){
					if(field.isVisible()) params.add(Double.parseDouble(field.getText()));
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(window.frame,
					    "Parametry które zosta³y wprowadzone nie s¹ poprawne. WprowadŸ poprawne liczby.",
					    "B³¹d podczas przetwarzania parametrów...",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			secondSignal.setParams(params);
			secondSignal.show("Second signal");
		}
	};
	
	private ActionListener firstSigListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SygnalBase sig = (SygnalBase)window.getSig1ComboBox().getSelectedItem();
			window.setSig1ParamsNames(sig.paramsNames);
		}
		
	};
	
	private ActionListener secondSigListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SygnalBase sig = (SygnalBase)window.getSig2ComboBox().getSelectedItem();
			window.setSig2ParamsNames(sig.paramsNames);
		}
		
	};

}
