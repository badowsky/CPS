package View;

import javax.swing.SpinnerNumberModel;

public class PowerOfTwoSpinner extends SpinnerNumberModel {

	public PowerOfTwoSpinner(int value, int minimum, int maximum) {
		super(value, minimum, maximum, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getNextValue() {
		return (Integer)super.getNextValue()*2;
	}

	@Override
	public Object getPreviousValue() {
		return (Integer)super.getPreviousValue()/2;
	}

}
