package Model.Sygnaly.Dyskretne;

import java.util.ArrayList;

import org.jfree.chart.JFreeChart;

import Model.FunkcjeCiagle.FunkcjaCiagla;
import View.Graph;

public class SygnalDyskretny {
	
	public ArrayList<Double> x;
	public ArrayList<Double> y;
	
	public FunkcjaCiagla funkcjaCiagla;
	
	public static int HISTOGRAM_INTERVAL = 10;
	private boolean REPRESENT_AS_CONTINUOUS;
	
	public SygnalDyskretny(){
		this(true);
	}
	
	public SygnalDyskretny(boolean continuous){
		this.REPRESENT_AS_CONTINUOUS = continuous;
		y = new ArrayList<Double>();
		x = new ArrayList<Double>();
	}
	
	public JFreeChart getChart(String title){
		return Graph.drawGraph(title, x, y, REPRESENT_AS_CONTINUOUS);
	}
	
	public JFreeChart getHistogram(String title){
		return Graph.drawHistogram(title, x, y, HISTOGRAM_INTERVAL);
	}
	
	public double wartoscSrednia() {
		if(funkcjaCiagla != null && funkcjaCiagla.srednia() != null) return funkcjaCiagla.srednia(); 
		int n1 = 0;
		int n2 = x.size();
		double wartoscSrednia = 0.0;
		
		for(int i = n1; i < n2; i++) {
			wartoscSrednia = wartoscSrednia + y.get(i);
		}
		
		return wartoscSrednia * (1.0 / (n2 - n1 + 1));
	}
	
	public double wartoscSredniaBezwzgledna() {
		if(funkcjaCiagla != null && funkcjaCiagla.sredniaBezwzgledna() != null) return funkcjaCiagla.sredniaBezwzgledna(); 
		int n1 = 0;
		int n2 = x.size();
		double wartoscSredniaBezwzgledna = 0.0;
		
		for(int i = n1; i < n2; i++) {
			wartoscSredniaBezwzgledna = wartoscSredniaBezwzgledna + Math.abs(y.get(i));
		}
		
		return wartoscSredniaBezwzgledna * (1.0 / (n2 - n1 + 1));
	}
	
	public double wartoscSkuteczna() {
		if(funkcjaCiagla != null && funkcjaCiagla.wartoscSkuteczna() != null) return funkcjaCiagla.wartoscSkuteczna(); 
		return Math.sqrt(mocSrednia());	
	}
	
	public double wariancja(){
		if(funkcjaCiagla != null && funkcjaCiagla.wariancja() != null) return funkcjaCiagla.wariancja();
		int n1 = 0;
		int n2 = x.size();
		double wariancja = 0.0;
		
		for(int i = n1; i < n2; i++) {
			wariancja = wariancja + Math.pow((y.get(i) - wartoscSrednia()),2);
		}
		
		return wariancja * (1.0 / (n2 - n1 + 1));
	}
	
	public double mocSrednia(){
		if(funkcjaCiagla != null && funkcjaCiagla.mocSrednia() != null) return funkcjaCiagla.mocSrednia();
		int n1 = 0;
		int n2 = x.size();
		double mocSrednia = 0.0;
		
		for(int i = n1; i < n2; i++) {
			mocSrednia = mocSrednia + (Math.pow(y.get(i), 2));
		}
		
		return mocSrednia * (1.0 / (n2 - n1 + 1));
	}
}
