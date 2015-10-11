package Model;

import java.util.ArrayList;

public abstract class SignalGenerator {

	public String name;
	
	public double[] params;
	
	public String[] paramsNames;
	
	
	public void setParams(ArrayList<Double> params){
		this.params = new double[paramsNames.length];
		for(int i = 0 ; i < this.paramsNames.length ; i++){
			this.params[i] = params.get(i);
		}
	}
	
	public void show(){
		this.show();
	}
	
	public abstract Signal generate();

	@Override
	public String toString() {
		return name;
	}
	
	
}
