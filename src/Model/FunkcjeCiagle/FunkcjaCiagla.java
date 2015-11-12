package Model.FunkcjeCiagle;

public abstract class FunkcjaCiagla {

	protected String name;
	public String[] parametersNames;
	
	protected FunkcjaCiagla(String name){
		this.name = name;
	}
	
	public abstract void setParams(Double[] params);
	
	public abstract double getValue(double x);

	@Override
	public String toString() {
		return name;
	}
	
	public double srednia(){
		return -1.777;
	}
	
	public double sredniaBezwzgledna(){
		return -1.777;
	}
	
	public double mocSrednia(){
		return -1.777;
	}
	
	public double wariancja(){
		return -1.777;
	}
	
	public double wartoscSkuteczna(){
		return Math.sqrt(mocSrednia());
	}

	public String[] getParametersNames() {
		return parametersNames;
	}
}
