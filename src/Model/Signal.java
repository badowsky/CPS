package Model;

import java.util.ArrayList;

import org.jfree.chart.JFreeChart;

import View.Graph;

public class Signal {
	
	public ArrayList<Double> x;
	
	public ArrayList<Double> y;
	
	public Signal(){
		y = new ArrayList<Double>();
		x = new ArrayList<Double>();
	}
	
	public JFreeChart getChart(String title){
		return Graph.drawGraph(title, x, y);
	}
	
	public JFreeChart getHistogram(String title){
		return Graph.drawHistogram(title, x, y, 10);
	}
}
