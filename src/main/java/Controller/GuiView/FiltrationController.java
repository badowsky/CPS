package Controller.GuiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import Helpers.Utils;
import Model.Filtration.Filter;
import Model.Filtration.Filter.FilterBuilder;
import Model.Filtration.FiltrDolnoprzepustowy;
import Model.Filtration.FiltrGornoprzepustowy;
import Model.Filtration.FiltrSrodkowoprzepustowy;
import Model.Filtration.Fourier;
import Model.Filtration.GeneratorFiltru;
import Model.Filtration.Windows.BlackmansWidnow;
import Model.Filtration.Windows.HammingsWindow;
import Model.Filtration.Windows.HanningsWindow;
import Model.Filtration.Windows.RectangleWindow;
import Model.Filtration.Windows.Window;
import Model.Filtration.Windows.Window.WindowBuilder;
import Model.Operations.Splot;
import Model.Signals.Discrete.DiscreteSignalComplex;
import Model.Signals.Discrete.DiscreteSignalReal;
import View.FiltrationPanel;
import View.Graph;

public class FiltrationController {
	
	private FiltrationPanel panel;
	private int K, M, N;
	private Filter filter;
	private FilterBuilder filterBuilder;
	private Window window;
	private WindowBuilder windowBuilder;
	private FastFourierTransformer fft;
	private DiscreteSignalReal signalToFiltration;
	
	public FiltrationController(FiltrationPanel panel){
		this.panel = panel;
		this.filter = null;
		this.window = null;
		this.signalToFiltration = null;
		fft = new FastFourierTransformer(DftNormalization.STANDARD);
		initialize();
	}
	
	public void initialize(){
		this.panel.getFilterTypeChoose().addItem(new FiltrDolnoprzepustowy.Builder());
		this.panel.getFilterTypeChoose().addItem(new FiltrGornoprzepustowy.Builder());
		this.panel.getFilterTypeChoose().addItem(new FiltrSrodkowoprzepustowy.Builder());
		
		this.panel.getWindowTypeChoose().addItem(new RectangleWindow.Builder());
		this.panel.getWindowTypeChoose().addItem(new HammingsWindow.Builder());
		this.panel.getWindowTypeChoose().addItem(new HanningsWindow.Builder());
		this.panel.getWindowTypeChoose().addItem(new BlackmansWidnow.Builder());
		
		parseNumberFelds();
		this.panel.getWindowTypeChoose().addActionListener(windowTypeChangedListener);
		windowTypeChangedListener.actionPerformed(new ActionEvent(this.panel.getWindowTypeChoose(), ActionEvent.ACTION_PERFORMED, "Refresh"));
		this.panel.getFilterTypeChoose().addActionListener(filterTypeChangedListener);
		filterTypeChangedListener.actionPerformed(new ActionEvent(this.panel.getFilterTypeChoose(), ActionEvent.ACTION_PERFORMED, "Refresh"));
		
		
		this.panel.getFieldK().addChangeListener(parametersChangedListener);
		this.panel.getFieldM().addChangeListener(parametersChangedListener);
		this.panel.getFieldN().addChangeListener(parametersChangedListener);
	}
	
	public void notifySignalAfterOperationsChanged(DiscreteSignalReal signal){
		signalToFiltration = signal;
		refreshResultChart();
	}
	
	private void refreshCharts(){
		parseNumberFelds();
		refreshFilterCharts();
		refreshWindowChart();
		refreshResultChart();
	}

	private void refreshFilterCharts() {
		if(filter != null && window != null){
			filter = filterBuilder.setK(K).build();
			GeneratorFiltru gen = new GeneratorFiltru(filter, window);
			DiscreteSignalComplex filterSampled = gen.generuj(K, M, N);
			panel.setFilterChart(filterSampled.toReal().getChart("Odpowiedü impulsowa"));
	        //Complex[] transformed = fft.transform(filterSampled.getY(), TransformType.FORWARD);
	        Complex[] transformed = Fourier.DFT(filterSampled.getY());
	        panel.setFilterTransmitationChart(Graph.drawComplexGraphRealPart("Transmitancja", Arrays.copyOf(Utils.abs(transformed), N/2), true));
		}
	}

	private void refreshWindowChart() {
		if(windowBuilder != null){
			window = windowBuilder.setM(M).build();
			panel.setWindowChart(window.generujPodglad().getChart("Okno"));
		}
	}
	
	private void refreshResultChart(){
		if(signalToFiltration != null){
			Splot splot = new Splot();
			GeneratorFiltru gen = new GeneratorFiltru(filter, window);
			DiscreteSignalComplex sampledFilter = gen.generuj(K, M, N);
			DiscreteSignalReal filtered = splot.DoOperation(sampledFilter.toReal(), signalToFiltration);
			panel.setResultChart(filtered.getChart("Wynik filtracji"));
		}
	}
	
	private void parseNumberFelds(){
		this.K = (Integer)this.panel.getFieldK().getValue();
		this.M = (Integer)this.panel.getFieldM().getValue();
		this.N = (Integer)this.panel.getFieldN().getValue();
	}
	
	private ActionListener filterTypeChangedListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			filterBuilder = ((FilterBuilder)((JComboBox<FilterBuilder>)e.getSource()).getSelectedItem());
			filter = filterBuilder.setK(K).build();
			System.out.println("Everything ok - created: " + filter.toString());
			refreshCharts();
			
		}
	};
	
	private ActionListener windowTypeChangedListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			windowBuilder = ((WindowBuilder)((JComboBox<WindowBuilder>)e.getSource()).getSelectedItem());
			window = windowBuilder.setM(M).build();
			System.out.println("Everything ok - created: " + window.toString());
			refreshCharts();
		}
	};
	
	private ChangeListener parametersChangedListener = new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent arg0) {
			refreshCharts();
		}


	};

}
