package View;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
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

	public static void printSignal(String title, String x_name , String y_name, double[] signal, boolean connected) {
		JFrame frame = new JFrame(title);
		frame.setBounds(0, 0, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		ChartPanel panel = new ChartPanel(drawGraph(title, x_name, y_name, signal, connected));
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	public static void printSignal(String title, DiscreteSignalReal signal, boolean connected) {
		JFrame frame = new JFrame(title);
		frame.setBounds(0, 0, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		ChartPanel panel = new ChartPanel(drawGraph(null, signal.getRawArgs(), signal.getRawValues(), connected));
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	public static void printComplexInOne(String title, Complex[] y, GraphType type, boolean connected) {
		JFrame frame = new JFrame(title);
		frame.setBounds(0, 0, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		ChartPanel panel = null;
		switch (type) {
		case W1:
			panel = new ChartPanel(drawComplexGraphW1(null, y, connected));
			break;
		case W2:
			panel = new ChartPanel(drawComplexGraphW2(null, y, connected));
			break;
		}
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	public static void printComplex(String title, Complex[] y, GraphType type, boolean connected) {
		JFrame frame = new JFrame(title);
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		ChartPanel firstPanel = null;
		ChartPanel secondPanel = null;
		switch (type) {
		case W1:
			firstPanel = new ChartPanel(drawComplexGraphW1RealPart(y, connected));
			secondPanel = new ChartPanel(drawComplexGraphW1ImagPart(y, connected));
			break;
		case W2:
			firstPanel = new ChartPanel(drawComplexGraphW2ModulePart(y, connected));
			secondPanel = new ChartPanel(drawComplexGraphW2ArgumentPart(y, connected));
			break;
		}
		frame.getContentPane().add(firstPanel);
		frame.getContentPane().add(secondPanel);
		frame.setVisible(true);
	}

	public static JFreeChart drawComplexGraphW1RealPart(Complex[] y, boolean connected) {
		XYSeries seriesReal = new XYSeries("");

		for (int i = 0; i < y.length; i++) {
			seriesReal.add(i, y[i].getReal());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesReal);
		JFreeChart chart = null;
		if (connected) {
			chart = ChartFactory.createXYLineChart("CzÄ™Å›Ä‡ rzeczywista", // Title
					"Numer próbki", // x-axis Label
					"Wartoœc rzeczywista", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		} else {
			chart = ChartFactory.createScatterPlot("CzÄ™Å›Ä‡ rzeczywista", // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ rzeczywista", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		}

		return chart;
	}

	public static JFreeChart drawComplexGraphW1ImagPart(Complex[] y, boolean connected) {
		XYSeries seriesImag = new XYSeries("");

		for (int i = 0; i < y.length; i++) {
			seriesImag.add(i, y[i].getImaginary());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesImag);

		JFreeChart chart = null;
		if (connected) {
			chart = ChartFactory.createXYLineChart("CzÄ™Å›Ä‡ urojona", // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ urojona", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		} else {
			chart = ChartFactory.createScatterPlot("CzÄ™Å›Ä‡ urojona", // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ urojona", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		}

		return chart;
	}

	public static JFreeChart drawComplexGraphW1(String title, Complex[] y, boolean connected) {
		XYSeries seriesReal = new XYSeries("Rzeczywista");
		XYSeries seriesImag = new XYSeries("Urojona");

		for (int i = 0; i < y.length; i++) {
			seriesReal.add(i, y[i].getReal());
			seriesImag.add(i, y[i].getImaginary());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesReal);
		dataset.addSeries(seriesImag);

		JFreeChart chart = null;
		if (connected) {
			chart = ChartFactory.createXYLineChart(title, // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		} else {
			chart = ChartFactory.createScatterPlot(title, // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		}

		return chart;
	}

	public static JFreeChart drawComplexGraphW2ModulePart(Complex[] y, boolean connected) {
		XYSeries seriesImagModule = new XYSeries("");

		for (int i = 0; i < y.length; i++) {
			seriesImagModule.add(i, y[i].abs());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesImagModule);

		JFreeChart chart = null;
		if (connected) {
			chart = ChartFactory.createXYLineChart("ModuÅ‚", // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ modu³u", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		} else {
			chart = ChartFactory.createScatterPlot("ModuÅ‚", // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ modu³u", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		}

		return chart;
	}

	public static JFreeChart drawComplexGraphW2ArgumentPart(Complex[] y, boolean connected) {
		XYSeries seriesArg = new XYSeries("");

		for (int i = 0; i < y.length; i++) {
			seriesArg.add(i, y[i].getArgument());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesArg);

		JFreeChart chart = null;
		if (connected) {
			chart = ChartFactory.createXYLineChart("Argument", // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ argumentu", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		} else {
			chart = ChartFactory.createScatterPlot("Argument", // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ argumentu", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		}

		return chart;
	}

	public static JFreeChart drawComplexGraphW2(String title, Complex[] y, boolean connected) {
		XYSeries seriesImagModule = new XYSeries("Module");
		XYSeries seriesArg = new XYSeries("Argument");

		for (int i = 0; i < y.length; i++) {
			seriesImagModule.add(i, y[i].abs());
			seriesArg.add(i, y[i].getArgument());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesImagModule);
		dataset.addSeries(seriesArg);

		JFreeChart chart = null;
		if (connected) {
			chart = ChartFactory.createXYLineChart(title, // Title
					"Numer próbki", // x-axis Label
					"", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		} else {
			chart = ChartFactory.createScatterPlot(title, // Title
					"Numer próbki", // x-axis Label
					"", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		}

		return chart;
	}
	
	public static JFreeChart drawComplexGraphRealPart(String title, Complex[] y, boolean connected) {
		XYSeries seriesReal = new XYSeries("");

		for (int i = 0; i < y.length; i++) {
			seriesReal.add(i, y[i].getReal());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesReal);
		JFreeChart chart = null;
		if (connected) {
			chart = ChartFactory.createXYLineChart("CzÄ™Å›Ä‡ rzeczywista", // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ rzeczywista", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		} else {
			chart = ChartFactory.createScatterPlot("CzÄ™Å›Ä‡ rzeczywista", // Title
					"Numer próbki", // x-axis Label
					"Wartoœæ rzeczywista", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					dataset.getSeriesCount() > 1, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		}

		return chart;
	}

	public static JFreeChart drawGraph(String title, List<Double> x, List<Double> y, Boolean connectedPoint) {

		XYSeries series = new XYSeries("Signal");

		for (int i = 0; i < x.size(); i++) {
			series.add(x.get(i), y.get(i));
		}

		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		JFreeChart chart = null;

		if (connectedPoint) {
			chart = ChartFactory.createXYLineChart(title, // Title
					"", // x-axis Label
					"", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		} else {
			chart = ChartFactory.createScatterPlot(title, // Title
					"", // x-axis Label
					"", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		}

		// ChartFrame graph = new ChartFrame(title, chart);
		// graph.setSize(700,350);
		// graph.setVisible(true);

		return chart;
	}

	public static JFreeChart drawGraph(String title, String x_name, String y_name, double[] signal, Boolean connectedPoint) {

		XYSeries series = new XYSeries("Signal");

		for (int i = 0; i < signal.length; i++) {
			series.add(i, signal[i]);
		}

		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		JFreeChart chart = null;

		if (connectedPoint) {
			chart = ChartFactory.createXYLineChart(title, // Title
					x_name, // x-axis Label
					y_name, // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		} else {
			chart = ChartFactory.createScatterPlot(title, // Title
					x_name, // x-axis Label
					y_name, // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					false, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
			);
		}

		// ChartFrame graph = new ChartFrame(title, chart);
		// graph.setSize(700,350);
		// graph.setVisible(true);

		return chart;

		// try {
		// ChartUtilities.saveChartAsJPEG(new File("/home/patryk/Pulpit/graph"),
		// chart, 500, 300);
		// } catch (IOException e) {
		// System.err.println("Problem occurred creating chart.");
		// }
	}

	public static JFreeChart drawHistogram(String title, List<Double> x, List<Double> y, int interval) {
		HistogramDataset dataset = new HistogramDataset();
		double[] wartosci = new double[x.size()];

		for (int i = 0; i < y.size(); i++) {
			wartosci[i] = y.get(i);
		}

		dataset.addSeries("", wartosci, interval);

		JFreeChart chart = ChartFactory.createHistogram(title, "wartosci", "ilosc", dataset, PlotOrientation.VERTICAL,
				false, true, false);

		return chart;
	}
}