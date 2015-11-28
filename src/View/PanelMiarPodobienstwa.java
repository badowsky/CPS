package View;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PanelMiarPodobienstwa extends JPanel {

	private JLabel mseLbl;
	private JLabel snrLbl;
	private JLabel psnrLbl;
	private JLabel mdLbl;
	private JLabel enobLbl;

	private JLabel mseVal;
	private JLabel snrVal;
	private JLabel psnrVal;
	private JLabel mdVal;
	private JLabel enobVal;
	
	public PanelMiarPodobienstwa(){
		this.initialize();
	}

	public void initialize() {
		mseLbl = new JLabel("MSE");
		mseLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		mseLbl.setBounds(10, 40, 150, 14);
		
		snrLbl = new JLabel("SNR");
		snrLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		snrLbl.setBounds(10, 70, 150, 14);
		
		enobLbl = new JLabel("ENOB");
		enobLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		enobLbl.setBounds(10, 100, 150, 14);
		
		psnrLbl = new JLabel("PSNR");
		psnrLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		psnrLbl.setBounds(10, 130, 150, 14);
		
		mdLbl = new JLabel("MD");
		mdLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		mdLbl.setBounds(10, 160, 150, 14);
		

		mseVal = new JLabel("-");
		mseVal.setHorizontalAlignment(SwingConstants.LEFT);
		mseVal.setBounds(200, 40, 82, 14);
		
		snrVal = new JLabel("-");
		snrVal.setHorizontalAlignment(SwingConstants.LEFT);
		snrVal.setBounds(200, 70, 82, 14);
		
		enobVal = new JLabel("-");
		enobVal.setHorizontalAlignment(SwingConstants.LEFT);
		enobVal.setBounds(200, 100, 82, 14);
		
		psnrVal = new JLabel("-");
		psnrVal.setHorizontalAlignment(SwingConstants.LEFT);
		psnrVal.setBounds(200, 130, 82, 14);
		
		mdVal = new JLabel("-");
		mdVal.setHorizontalAlignment(SwingConstants.LEFT);
		mdVal.setBounds(200, 160, 82, 14);
		
		
		
		this.add(mseLbl);
		this.add(snrLbl);
		this.add(psnrLbl);
		this.add(mdLbl);
		this.add(enobLbl);
		
		this.add(mseVal);
		this.add(snrVal);
		this.add(psnrVal);
		this.add(mdVal);
		this.add(enobVal);
		
		
		this.setBorder(
				new TitledBorder(new LineBorder(new Color(105, 105, 105)), "Miary podobieñstwa sygna\u0142ów",
						TitledBorder.LEFT, TitledBorder.TOP, null, new Color(105, 105, 105)));
		this.setLayout(null);
	}

	public void setMSE(Double val) {
		this.mseVal.setText(String.format("%1$,.2f", val));
	}

	public void setSNR(Double val) {
		this.snrVal.setText(String.format("%1$,.2f", val));
	}
	
	public void setENOB(Double val) {
		this.enobVal.setText(String.format("%1$,.2f", val));
	}

	public void setPSNR(Double val) {
		this.psnrVal.setText(String.format("%1$,.2f", val));
	}

	public void setMD(Double val) {
		this.mdVal.setText(String.format("%1$,.2f", val));
	}

}
