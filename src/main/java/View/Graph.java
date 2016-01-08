package View;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Model.Signals.Discrete.DiscreteSignalReal;

public class Graph {
	
	public enum GraphType {
		W1, W2;
	}
	
	public static void printSignal(String title, double[] signal, boolean connected){
		JFrame frame = new JFrame(title);
		frame.setBounds(0, 0, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		ChartPanel panel = new ChartPanel(drawGraph(null, signal, connected));
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	public static void printSignal(String title, DiscreteSignalReal signal){
		JFrame frame = new JFrame(title);
		frame.setBounds(0, 0, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		ChartPanel panel = new ChartPanel(drawGraph(null, signal.getRawArgs(), signal.getRawValues(), true));
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	public static void printComplex(String title, Complex[] y, GraphType type){
		JFrame frame = new JFrame(title);
		frame.setBounds(0, 0, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		ChartPanel panel = null;
		switch(type){
			case W1:
				panel = new ChartPanel(drawComplexGraphW1(null, y));
				break;
			case W2:		
				panel = new ChartPanel(drawComplexGraphW2(null, y));
				break;
		}
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	public static JFreeChart drawComplexGraphW1(String title, Complex[] y){
		XYSeries seriesReal = new XYSeries("Real");
		XYSeries seriesImag = new XYSeries("Imag");
		
		for(int i = 0; i < y.length; i++) {
			seriesReal.add(i, y[i].getReal());
			seriesImag.add(i, y[i].getImaginary());
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesReal);
		dataset.addSeries(seriesImag);
		
		JFreeChart chart = ChartFactory.createScatterPlot(
			title, // Title
			"", // x-axis Label
			"", // y-axis Label
			dataset, // Dataset
			PlotOrientation.VERTICAL, // Plot Orientation
			true, // Show Legend
			true, // Use tooltips
			false // Configure chart to generate URLs?
		);
		
		return chart;
	}
	
	public static JFreeChart drawComplexGraphW2(String title, Complex[] y){
		XYSeries seriesImagModule = new XYSeries("Module");
		XYSeries seriesArg = new XYSeries("Argument");
		
		for(int i = 0; i < y.length; i++) {
			seriesImagModule.add(i, y[i].abs());
			seriesArg.add(i, y[i].getArgument());
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesImagModule);
		dataset.addSeries(seriesArg);
		
		JFreeChart chart = ChartFactory.createScatterPlot(
			title, // Title
			"", // x-axis Label
			"", // y-axis Label
			dataset, // Dataset
			PlotOrientation.VERTICAL, // Plot Orientation
			true, // Show Legend
			true, // Use tooltips
			false // Configure chart to generate URLs?
		);
		
		return chart;
	}
	
	public static JFreeChart drawGraph(String title, List<Double> x, List<Double> y, Boolean connectedPoint) {
		
		XYSeries series = new XYSeries("Signal");
		
		for(int i = 0; i < x.size(); i++) {
			series.add(x.get(i), y.get(i));
		}
		
		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		JFreeChart chart = null;
		
		if (connectedPoint) {
			chart = ChartFactory.createXYLineChart(
				title, // Title
				"", // x-axis Label
				"", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				false, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
			);
		}
		else {
			chart = ChartFactory.createScatterPlot(
					title, // Title
					"", // x-axis Label
					"", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
				);
		}
		
		
//    	ChartFrame graph = new ChartFrame(title, chart);
//    	graph.setSize(700,350);
//    	graph.setVisible(true);
    	
    	return chart;
	}
    	public static JFreeChart drawGraph(String title, double[] signal, Boolean connectedPoint) {
    		
    		XYSeries series = new XYSeries("Signal");
    		
    		for(int i = 0; i < signal.length; i++) {
    			series.add(i, signal[i]);
    		}
    		
    		// Add the series to your data set
    		XYSeriesCollection dataset = new XYSeriesCollection();
    		dataset.addSeries(series);
    		
    		JFreeChart chart = null;
    		
    		if (connectedPoint) {
    			chart = ChartFactory.createXYLineChart(
    				title, // Title
    				"", // x-axis Label
    				"", // y-axis Label
    				dataset, // Dataset
    				PlotOrientation.VERTICAL, // Plot Orientation
    				false, // Show Legend
    				true, // Use tooltips
    				false // Configure chart to generate URLs?
    			);
    		}
    		else {
    			chart = ChartFactory.createScatterPlot(
    					title, // Title
    					"", // x-axis Label
    					"", // y-axis Label
    					dataset, // Dataset
    					PlotOrientation.VERTICAL, // Plot Orientation
    					false, // Show Legend
    					true, // Use tooltips
    					false // Configure chart to generate URLs?
    				);
    		}
    		
    		
//        	ChartFrame graph = new ChartFrame(title, chart);
//        	graph.setSize(700,350);
//        	graph.setVisible(true);
        	
        	return chart;
		
//		try {
//			ChartUtilities.saveChartAsJPEG(new File("/home/patryk/Pulpit/graph"), chart, 500, 300);
//		} catch (IOException e) {
//			System.err.println("Problem occurred creating chart.");
//		}
	}
	
	public static JFreeChart drawHistogram(String title, List<Double> x, List<Double> y, int interval) {
		HistogramDataset dataset = new HistogramDataset();
	    double[] wartosci = new double[x.size()];
	    
    	for(int i = 0; i < y.size(); i++) {
    		wartosci[i] = y.get(i);
    	}
    	
    	dataset.addSeries("",wartosci, interval);

    	JFreeChart chart = ChartFactory.createHistogram(
    		title,
    		"wartosci",
    		"ilosc",
    		dataset,
    		PlotOrientation.VERTICAL,
    		false,
    		true,
    		false
    	); 
    	
    	return chart;
	}
}