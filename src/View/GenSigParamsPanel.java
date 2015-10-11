package View;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GenSigParamsPanel extends JPanel {

	private JLabel sredniaLbl;
	private JLabel sredniaBezwzglLbl;
	private JLabel wariancjaLbl;
	private JLabel mocLbl;
	private JLabel wartoscSkutecznaLbl;

	private JLabel sredniaVal;
	private JLabel sredniaBezwzglVal;
	private JLabel wariancjaVal;
	private JLabel mocVal;
	private JLabel wartoscSkutecznaVal;
	
	public GenSigParamsPanel(){
		this.initialize();
	}

	public void initialize() {
		sredniaLbl = new JLabel("Wartoœæ œrednia");
		sredniaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		sredniaLbl.setBounds(10, 40, 150, 14);
		
		sredniaBezwzglLbl = new JLabel("Wartoœæ œrednia bezwzgl");
		sredniaBezwzglLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		sredniaBezwzglLbl.setBounds(10, 70, 150, 14);
		
		wartoscSkutecznaLbl = new JLabel("Wartoœæ skuteczna");
		wartoscSkutecznaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		wartoscSkutecznaLbl.setBounds(10, 100, 150, 14);
		
		wariancjaLbl = new JLabel("Wariancja");
		wariancjaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		wariancjaLbl.setBounds(10, 130, 150, 14);
		
		mocLbl = new JLabel("Moc œrednia");
		mocLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		mocLbl.setBounds(10, 160, 150, 14);
		

		sredniaVal = new JLabel("-");
		sredniaVal.setHorizontalAlignment(SwingConstants.LEFT);
		sredniaVal.setBounds(200, 40, 82, 14);
		
		sredniaBezwzglVal = new JLabel("-");
		sredniaBezwzglVal.setHorizontalAlignment(SwingConstants.LEFT);
		sredniaBezwzglVal.setBounds(200, 70, 82, 14);
		
		wartoscSkutecznaVal = new JLabel("-");
		wartoscSkutecznaVal.setHorizontalAlignment(SwingConstants.LEFT);
		wartoscSkutecznaVal.setBounds(200, 100, 82, 14);
		
		wariancjaVal = new JLabel("-");
		wariancjaVal.setHorizontalAlignment(SwingConstants.LEFT);
		wariancjaVal.setBounds(200, 130, 82, 14);
		
		mocVal = new JLabel("-");
		mocVal.setHorizontalAlignment(SwingConstants.LEFT);
		mocVal.setBounds(200, 160, 82, 14);
		
		
		
		this.add(sredniaLbl);
		this.add(sredniaBezwzglLbl);
		this.add(wariancjaLbl);
		this.add(mocLbl);
		this.add(wartoscSkutecznaLbl);
		
		this.add(sredniaVal);
		this.add(sredniaBezwzglVal);
		this.add(wariancjaVal);
		this.add(mocVal);
		this.add(wartoscSkutecznaVal);
		
		this.setBorder(
				new TitledBorder(new LineBorder(new Color(105, 105, 105)), "Parametry wygenerowanego sygna\u0142u",
						TitledBorder.LEFT, TitledBorder.TOP, null, new Color(105, 105, 105)));
		this.setLayout(null);
	}

	public void setSrednia(Double val) {
		this.sredniaVal.setText(String.format("%1$,.2f", val));
	}

	public void setSredniaBezwzgl(Double val) {
		this.sredniaBezwzglVal.setText(String.format("%1$,.2f", val));
	}

	public void setWariancja(Double val) {
		this.wariancjaVal.setText(String.format("%1$,.2f", val));
	}

	public void setMoc(Double val) {
		this.mocVal.setText(String.format("%1$,.2f", val));
	}

	public void setWartoscSkuteczna(Double val) {
		this.wartoscSkutecznaVal.setText(String.format("%1$,.2f", val));
	}

}
