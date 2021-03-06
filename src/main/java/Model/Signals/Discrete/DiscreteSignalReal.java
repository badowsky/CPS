package Model.Signals.Discrete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.JFreeChart;

import Model.Signals.Continuous.ContinuousSignal;
import View.Graph;

public class DiscreteSignalReal implements DiscreteSignal{
	
	private List<Double> x;
	private List<Double> y;
	
	public ContinuousSignal funkcjaCiagla;
	
	public static int HISTOGRAM_INTERVAL = 10;
	private boolean REPRESENT_AS_CONTINUOUS;
	
	public DiscreteSignalReal(boolean continuous){
		this(continuous, new ArrayList<Double>(), new ArrayList<Double>());
	}
	
	public DiscreteSignalReal(){
		this(true, new ArrayList<Double>(), new ArrayList<Double>());
	}
	
	public DiscreteSignalReal(ArrayList<Double> x, ArrayList<Double> y){
		this(true, x, y);
	}
	
	public DiscreteSignalReal(boolean continuous, ArrayList<Double> x, ArrayList<Double> y){
		this.REPRESENT_AS_CONTINUOUS = continuous;
		this.y = y;
		this.x = x;
	}
	
	public DiscreteSignalReal sublist(int from, int to){
		DiscreteSignalReal copy = new DiscreteSignalReal();
		copy.x = this.x.subList(from, to);
		copy.y = this.y.subList(from, to);
		copy.funkcjaCiagla = this.funkcjaCiagla;
		
		return copy;		
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
	
	public double[] toPrimitive(){
		double[] result = new double[this.y.size()];
		
		for(int i = 0 ; i < this.y.size() ; i++){
			result[i] = this.y.get(i);
		}
		
		return result;
	}
	
	public Complex[] toComplex(){
		Complex[] x = new Complex[this.size()];
		for(int i = 0 ; i < this.size() ; i++){
			x[i] = new Complex(this.y.get(i), 0);
		}
		return x;
	}

	@Override
	public double getX(int index) {
		return this.x.get(index);
	}
	
	public List<Double> getRawArgs(){
		return this.x;
	}

	@Override
	public double getY(int index) {
		return this.y.get(index);
	}
	
	public List<Double> getRawValues(){
		return this.y;
	}

	@Override
	public int size() {
		return this.y.size();
	}

	@Override
	public void addX(double x) {
		this.x.add(x);
	}

	@Override
	public void addY(double y) {
		this.y.add(y);		
	}

	@Override
	public Iterator<Double> iteratorX() {
		return this.x.iterator();
	}

	@Override
	public Iterator<Double> iteratorY() {
		return this.y.iterator();
	}
	
	
}
