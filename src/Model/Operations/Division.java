package Model.Operations;

import Model.Signals.Discrete.SygnalDyskretnyReal;

public class Division implements OperationOnSignals {

	@Override
	public SygnalDyskretnyReal DoOperation(SygnalDyskretnyReal a, SygnalDyskretnyReal b) {
		SygnalDyskretnyReal result = new SygnalDyskretnyReal();
		
		for(int i=0; i < a.size(); i++){
			
			if(i<a.size() && i < b.size()){
				result.addX(a.getX(i));
				result.addY(a.getY(i) / b.getY(i));
			}
		}

		return result;
	}

	@Override
	public String toString() {
		return "Dzielenie";
	}
	
	

}
