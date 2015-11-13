package Model.Konwersja;

public class FirstOrderHold extends KonwersjaCA {

	public FirstOrderHold() {
		super("First Order Hold");
	}

	@Override
	public double getValue(double x) {
		double pierwszyX = sygnal.x.get(0);
		double pierwszyY = sygnal.y.get(0);
		if (x < pierwszyX)
			return pierwszyY;
		double ostatniX = sygnal.x.get(sygnal.x.size() - 1);
		double ostatniY = sygnal.y.get(sygnal.x.size() - 1);
		if (x > ostatniX)
			return ostatniY;

		double xPrzed, xZa, yPrzed, yZa;
		for (int i = 0; i < sygnal.x.size() - 1; i++) {
			xPrzed = sygnal.x.get(i);
			xZa = sygnal.x.get(i + 1);
			yPrzed = sygnal.y.get(i);
			yZa = sygnal.y.get(i + 1);
			if (x > xPrzed && x < xZa) {
				return yPrzed - (((yPrzed - yZa)/(xZa - xPrzed))*(x-xPrzed));
			}else if(x == xPrzed){
				return yPrzed;
			}
		}
		return 0;
	}

	@Override
	public void setParams(Double[] params) {
		// TODO Auto-generated method stub

	}

}
