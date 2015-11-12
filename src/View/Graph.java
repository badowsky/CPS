package View;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {
	
	public static JFreeChart drawGraph(String title, ArrayList<Double> x, ArrayList<Double> y, Boolean connectedPoint) {
		
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
				"t[s]", // x-axis Label
				"A", // y-axis Label
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
					"t[s]", // x-axis Label
					"A", // y-axis Label
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
		
//		try {
//			ChartUtilities.saveChartAsJPEG(new File("/home/patryk/Pulpit/graph"), chart, 500, 300);
//		} catch (IOException e) {
//			System.err.println("Problem occurred creating chart.");
//		}
	}
	
	public static JFreeChart drawHistogram(String title, ArrayList<Double> x, ArrayList<Double> y, int interval) {
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