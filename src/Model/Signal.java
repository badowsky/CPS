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
	
	public double wartoscSrednia() {
		int n1 = 0;
		int n2 = x.size();
		double wartoscSrednia = 0.0;
		
		for(int i = n1; i < n2; i++) {
			wartoscSrednia = wartoscSrednia + y.get(i);
		}
		
		return wartoscSrednia * (1.0 / (n2 - n1 + 1));
	}
	
	public double wartoscSredniaBezwzgledna() {
		int n1 = 0;
		int n2 = x.size();
		double wartoscSredniaBezwzgledna = 0.0;
		
		for(int i = n1; i < n2; i++) {
			wartoscSredniaBezwzgledna = wartoscSredniaBezwzgledna + Math.abs(y.get(i));
		}
		
		return wartoscSredniaBezwzgledna * (1.0 / (n2 - n1 + 1));
	}
	
	public double wartoscSkuteczna() {
		return Math.sqrt(mocSrednia());	
	}
	
	public double wariancja(){
		int n1 = 0;
		int n2 = x.size();
		double wariancja = 0.0;
		
		for(int i = n1; i < n2; i++) {
			wariancja = wariancja + Math.pow((y.get(i) - wartoscSrednia()),2);
		}
		
		return wariancja * (1.0 / (n2 - n1 + 1));
	}
	
	public double mocSrednia(){
		int n1 = 0;
		int n2 = x.size();
		double mocSrednia = 0.0;
		
		for(int i = n1; i < n2; i++) {
			mocSrednia = mocSrednia + (Math.pow(y.get(i), 2));
		}
		
		return mocSrednia * (1.0 / (n2 - n1 + 1));
	}
}
