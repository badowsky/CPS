package View;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SignalParamsPanel extends JPanel {

	private JLabel avgLbl;
	private JLabel absoluteAvglLbl;
	private JLabel varianceLbl;
	private JLabel powerLbl;
	private JLabel effectiveValueLbl;

	private JLabel avgVal;
	private JLabel absoluteAvgVal;
	private JLabel varianceVal;
	private JLabel powerVal;
	private JLabel effectiveValueVal;
	
	public SignalParamsPanel(){
		this.initialize();
	}

	public void initialize() {
		avgLbl = new JLabel("Wartosc srednia");
		avgLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		avgLbl.setBounds(10, 40, 150, 14);
		
		absoluteAvglLbl = new JLabel("Wartosc srednia bezwzgl");
		absoluteAvglLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		absoluteAvglLbl.setBounds(10, 70, 150, 14);
		
		effectiveValueLbl = new JLabel("Wartosc skuteczna");
		effectiveValueLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		effectiveValueLbl.setBounds(10, 100, 150, 14);
		
		varianceLbl = new JLabel("Wariancja");
		varianceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		varianceLbl.setBounds(10, 130, 150, 14);
		
		powerLbl = new JLabel("Moc rednia");
		powerLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		powerLbl.setBounds(10, 160, 150, 14);
		

		avgVal = new JLabel("-");
		avgVal.setHorizontalAlignment(SwingConstants.LEFT);
		avgVal.setBounds(200, 40, 82, 14);
		
		absoluteAvgVal = new JLabel("-");
		absoluteAvgVal.setHorizontalAlignment(SwingConstants.LEFT);
		absoluteAvgVal.setBounds(200, 70, 82, 14);
		
		effectiveValueVal = new JLabel("-");
		effectiveValueVal.setHorizontalAlignment(SwingConstants.LEFT);
		effectiveValueVal.setBounds(200, 100, 82, 14);
		
		varianceVal = new JLabel("-");
		varianceVal.setHorizontalAlignment(SwingConstants.LEFT);
		varianceVal.setBounds(200, 130, 82, 14);
		
		powerVal = new JLabel("-");
		powerVal.setHorizontalAlignment(SwingConstants.LEFT);
		powerVal.setBounds(200, 160, 82, 14);
		
		
		
		this.add(avgLbl);
		this.add(absoluteAvglLbl);
		this.add(varianceLbl);
		this.add(powerLbl);
		this.add(effectiveValueLbl);
		
		this.add(avgVal);
		this.add(absoluteAvgVal);
		this.add(varianceVal);
		this.add(powerVal);
		this.add(effectiveValueVal);
		
		this.setBorder(
				new TitledBorder(new LineBorder(new Color(105, 105, 105)), "Parametry wygenerowanego sygna\u0142u",
						TitledBorder.LEFT, TitledBorder.TOP, null, new Color(105, 105, 105)));
		this.setLayout(null);
	}

	public void setAvg(Double val) {
		this.avgVal.setText(String.format("%1$,.2f", val));
	}

	public void setAbsoluteAvg(Double val) {
		this.absoluteAvgVal.setText(String.format("%1$,.2f", val));
	}

	public void setVariance(Double val) {
		this.varianceVal.setText(String.format("%1$,.2f", val));
	}

	public void setPower(Double val) {
		this.powerVal.setText(String.format("%1$,.2f", val));
	}

	public void setEffectiveValue(Double val) {
		this.effectiveValueVal.setText(String.format("%1$,.2f", val));
	}

}
