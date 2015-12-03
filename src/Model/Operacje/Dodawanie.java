package Model.Operacje;

import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

public class Dodawanie implements OperacjaNaSygnalach {

	@Override
	public SygnalDyskretnyReal DoOperation(SygnalDyskretnyReal a, SygnalDyskretnyReal b) {
		SygnalDyskretnyReal result = new SygnalDyskretnyReal();
		
		for(int i = 0; i < a.size(); i++){
			result.addX(a.getX(i));
			result.addY(a.getY(i) + b.getY(i));
		}

		return result;
	}

	@Override
	public String toString() {
		return "Dodawanie";
	}
}
