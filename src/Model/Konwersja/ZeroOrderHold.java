package Model.Konwersja;

public class ZeroOrderHold extends KonwersjaCA {

	public ZeroOrderHold() {
		super("Zero order hold");
	}

	@Override
	public void setParams(Double[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getValue(double x) {
		double firstX = sygnal.x.get(0);
		double firstY = sygnal.y.get(0);
		if (x < firstX)
			return firstY;
		double lastX = sygnal.x.get(sygnal.x.size() - 1);
		double lastY = sygnal.y.get(sygnal.y.size() - 1);
		if (x >= lastX)
			return lastY;
		for (int i = 0; i < sygnal.x.size(); i++) {
			if (sygnal.x.get(i) <= x && sygnal.x.get(i + 1) > x) {
				return sygnal.y.get(i);
			}
		}
		return 0;
	}

}
