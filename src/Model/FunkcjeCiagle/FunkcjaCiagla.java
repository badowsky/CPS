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
	
	public Double srednia(){
		return null;
	}
	
	public Double sredniaBezwzgledna(){
		return null;
	}
	
	public Double mocSrednia(){
		return null;
	}
	
	public Double wariancja(){
		return null;
	}
	
	public Double wartoscSkuteczna(){
		if(mocSrednia() == null) return null;
		return Math.sqrt(mocSrednia());
	}

	public String[] getParametersNames() {
		return parametersNames;
	}
}
