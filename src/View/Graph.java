package View;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {
	
	public static void drawGraph(String title, ArrayList<Double> x, ArrayList<Double> y) {
		
		XYSeries series = new XYSeries("Signal");
		
		for(int i = 0; i < x.size(); i++) {
			series.add(x.get(i), y.get(i));
		}
		
		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		// Generate the graph
		JFreeChart chart = ChartFactory.createXYLineChart(
			title, // Title
			"t[s]", // x-axis Label
			"A", // y-axis Label
			dataset, // Dataset
			PlotOrientation.VERTICAL, // Plot Orientation
			false, // Show Legend
			true, // Use tooltips
			false // Configure chart to generate URLs?
		);
		
		//return chart;
		
    	ChartFrame graph = new ChartFrame(title, chart);
    	graph.setSize(700,350);
    	graph.setVisible(true);
		
//		try {
//			ChartUtilities.saveChartAsJPEG(new File("/home/patryk/Pulpit/graph"), chart, 500, 300);
//		} catch (IOException e) {
//			System.err.println("Problem occurred creating chart.");
//		}
	}
	
	public static void drawHistogram(String title, double[] x, double[] y, int interval) {
		HistogramDataset dataset = new HistogramDataset();
	    
    	
    	dataset.addSeries(title, y, interval);

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
    	
    	ChartFrame histogram = new ChartFrame(title, chart);
    	histogram.setSize(700,350);
    	histogram.setVisible(true);
	}
}