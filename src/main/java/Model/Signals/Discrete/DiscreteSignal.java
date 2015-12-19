package Model.Signals.Discrete;

import java.util.Iterator;

public interface DiscreteSignal {

	double getX(int index);
	double getY(int index);
	int size();
	void addX(double x);
	void addY(double y);
	Iterator<Double> iteratorX();
	Iterator<Double> iteratorY();
}
