package Model;

import java.util.ArrayList;

import View.Graph;

public abstract class SygnalBase {

	public String name;
	
	public double[] params;
	
	public String[] paramsNames;
	
	public ArrayList<Double> x;
	
	public ArrayList<Double> y;
	
	public void setParams(ArrayList<Double> params){
		this.params = new double[paramsNames.length];
		for(int i = 0 ; i < this.paramsNames.length ; i++){
			this.params[i] = params.get(i);
		}
		this.generate();
	}
	
	public void show(){
		this.show();
	}
	
	public void show(String title){
		Graph.drawGraph(title, x, y);
	}
	
	public abstract void generate();

	@Override
	public String toString() {
		return name;
	}
	
	
}
