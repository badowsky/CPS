package Model.Operacje;

import Model.Sygnaly.Dyskretne.SygnalDyskretny;

public class Dzielenie implements OperacjaNaSygnalach {

	@Override
	public SygnalDyskretny DoOperation(SygnalDyskretny a, SygnalDyskretny b) {
		SygnalDyskretny result = new SygnalDyskretny();
		
		for(int i=0; i < a.x.size(); i++){
			result.x.add(a.x.get(i));
			result.y.add(a.y.get(i) / b.y.get(i));
		}

		return result;
	}

	@Override
	public String toString() {
		return "Dzielenie";
	}
	
	

}
