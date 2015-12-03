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
		double firstX = sygnal.getX(0);
		double firstY = sygnal.getY(0);
		if (x < firstX)
			return firstY;
		double lastX = sygnal.getX(sygnal.size() - 1);
		double lastY = sygnal.getY(sygnal.size() - 1);
		if (x >= lastX)
			return lastY;
		for (int i = 0; i < sygnal.size(); i++) {
			if (sygnal.getX(i) <= x && sygnal.getX(i + 1) > x) {
				return sygnal.getY(i);
			}
		}
		return 0;
	}

}
