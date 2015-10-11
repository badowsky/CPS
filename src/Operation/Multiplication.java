package Operation;

import Model.Signal;

public class Multiplication implements SignalOperation {

	@Override
	public Signal DoOperation(Signal a, Signal b) {
		Signal result = new Signal();

		for (int i = 0; i < a.x.size(); i++) {
			result.x.add(a.x.get(i));
			result.y.add(a.y.get(i) * b.y.get(i));
		}

		return result;
	}
	
	@Override
	public String toString() {
		return "Mno¿enie";
	}

}
