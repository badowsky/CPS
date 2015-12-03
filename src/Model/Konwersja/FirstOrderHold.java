package Model.Konwersja;

public class FirstOrderHold extends KonwersjaCA {

	public FirstOrderHold() {
		super("First Order Hold");
	}

	@Override
	public double getValue(double x) {
		double pierwszyX = sygnal.getX(0);
		double pierwszyY = sygnal.getY(0);
		if (x < pierwszyX)
			return pierwszyY;
		double ostatniX = sygnal.getX(sygnal.size() - 1);
		double ostatniY = sygnal.getY(sygnal.size() - 1);
		if (x > ostatniX)
			return ostatniY;

		double xPrzed, xZa, yPrzed, yZa;
		for (int i = 0; i < sygnal.size() - 1; i++) {
			xPrzed = sygnal.getX(i);
			xZa = sygnal.getX(i + 1);
			yPrzed = sygnal.getY(i);
			yZa = sygnal.getY(i + 1);
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
