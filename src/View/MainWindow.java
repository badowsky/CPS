package View;

import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;

import Model.SygnalBase;

public class MainWindow {

	public JFrame frame;
	private JPanel panel;
	
	private JTextField sig1Param1TextField;
	private JTextField sig1Param2TextField;
	private JTextField sig1Param3TextField;
	private JTextField sig1Param4TextField;
	private JTextField sig1Param5TextField;
	private JTextField sig1Param6TextField;
	private JComboBox<SygnalBase> sig1ComboBox;
	private JButton btnGenSig1;
	
	private ArrayList<JTextField> sig1Params;
	private ArrayList<JLabel> sig1ParamLabels;
	private JPanel sig1GraphPanel;
	
	private JTextField sig2Param1TextField;
	private JTextField sig2Param2TextField;
	private JTextField sig2Param3TextField;
	private JTextField sig2Param4TextField;
	private JTextField sig2Param5TextField;
	private JTextField sig2Param6TextField;
	private JComboBox<SygnalBase> sig2ComboBox;
	private JButton btnGenSig2;
	
	private ArrayList<JTextField> sig2Params;
	private ArrayList<JLabel> sig2ParamLabels;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 818, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		sig1ComboBox = new JComboBox<SygnalBase>();
		sig1ComboBox.setBounds(29, 56, 141, 20);
		panel.add(sig1ComboBox);
		
		sig1Param1TextField = new JTextField("0");
		sig1Param1TextField.setBounds(269, 29, 86, 20);
		panel.add(sig1Param1TextField);
		sig1Param1TextField.setColumns(10);
		
		
		sig1Param2TextField = new JTextField("10");
		sig1Param2TextField.setBounds(269, 59, 86, 20);
		panel.add(sig1Param2TextField);
		sig1Param2TextField.setColumns(10);
		
		sig1Param3TextField = new JTextField("100");
		sig1Param3TextField.setBounds(269, 90, 86, 20);
		panel.add(sig1Param3TextField);
		sig1Param3TextField.setColumns(10);
		
		sig2ComboBox = new JComboBox<SygnalBase>();
		sig2ComboBox.setBounds(29, 336, 141, 20);
		panel.add(sig2ComboBox);
		
		sig1Param4TextField = new JTextField("10");
		sig1Param4TextField.setColumns(10);
		sig1Param4TextField.setBounds(269, 121, 86, 20);
		panel.add(sig1Param4TextField);
		
		sig1Param5TextField = new JTextField("5");
		sig1Param5TextField.setColumns(10);
		sig1Param5TextField.setBounds(269, 151, 86, 20);
		panel.add(sig1Param5TextField);
		
		sig1Param6TextField = new JTextField();
		sig1Param6TextField.setColumns(10);
		sig1Param6TextField.setBounds(269, 182, 86, 20);
		panel.add(sig1Param6TextField);
		
		sig2Param1TextField = new JTextField();
		sig2Param1TextField.setColumns(10);
		sig2Param1TextField.setBounds(269, 260, 86, 20);
		panel.add(sig2Param1TextField);
		
		sig2Param6TextField = new JTextField();
		sig2Param6TextField.setColumns(10);
		sig2Param6TextField.setBounds(269, 413, 86, 20);
		panel.add(sig2Param6TextField);
		
		sig2Param5TextField = new JTextField();
		sig2Param5TextField.setColumns(10);
		sig2Param5TextField.setBounds(269, 382, 86, 20);
		panel.add(sig2Param5TextField);
		
		sig2Param4TextField = new JTextField();
		sig2Param4TextField.setColumns(10);
		sig2Param4TextField.setBounds(269, 352, 86, 20);
		panel.add(sig2Param4TextField);
		
		sig2Param3TextField = new JTextField();
		sig2Param3TextField.setColumns(10);
		sig2Param3TextField.setBounds(269, 321, 86, 20);
		panel.add(sig2Param3TextField);
		
		sig2Param2TextField = new JTextField();
		sig2Param2TextField.setColumns(10);
		sig2Param2TextField.setBounds(269, 290, 86, 20);
		panel.add(sig2Param2TextField);
		
		btnGenSig1 = new JButton("Generuj sygna\u0142");
		btnGenSig1.setBounds(48, 118, 105, 36);
		panel.add(btnGenSig1);
		
		btnGenSig2 = new JButton("Generuj sygna\u0142");
		btnGenSig2.setBounds(39, 367, 105, 36);
		panel.add(btnGenSig2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(177, 29, 82, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(177, 59, 82, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(177, 90, 82, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(177, 121, 82, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(177, 151, 82, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(177, 182, 82, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(177, 263, 82, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(177, 293, 82, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setBounds(177, 324, 82, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setBounds(177, 355, 82, 14);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setBounds(177, 385, 82, 14);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setBounds(177, 416, 82, 14);
		panel.add(lblNewLabel_11);
		
		sig1Params = new ArrayList<JTextField>();
		sig1Params.add(sig1Param1TextField);
		sig1Params.add(sig1Param2TextField);
		sig1Params.add(sig1Param3TextField);
		sig1Params.add(sig1Param4TextField);
		sig1Params.add(sig1Param5TextField);
		sig1Params.add(sig1Param6TextField);
		
		sig2Params = new ArrayList<JTextField>();
		sig2Params.add(sig2Param1TextField);
		sig2Params.add(sig2Param2TextField);
		sig2Params.add(sig2Param3TextField);
		sig2Params.add(sig2Param4TextField);
		sig2Params.add(sig2Param5TextField);
		sig2Params.add(sig2Param6TextField);
		
		sig1ParamLabels = new ArrayList<JLabel>();
		sig1ParamLabels.add(lblNewLabel);
		sig1ParamLabels.add(lblNewLabel_1);
		sig1ParamLabels.add(lblNewLabel_2);
		sig1ParamLabels.add(lblNewLabel_3);
		sig1ParamLabels.add(lblNewLabel_4);
		sig1ParamLabels.add(lblNewLabel_5);
		
		sig2ParamLabels = new ArrayList<JLabel>();
		sig2ParamLabels.add(lblNewLabel_6);
		sig2ParamLabels.add(lblNewLabel_7);
		sig2ParamLabels.add(lblNewLabel_8);
		sig2ParamLabels.add(lblNewLabel_9);
		sig2ParamLabels.add(lblNewLabel_10);
		sig2ParamLabels.add(lblNewLabel_11);
		
		sig1GraphPanel = new JPanel();
		sig1GraphPanel.setBounds(457, 29, 271, 173);
		panel.add(sig1GraphPanel);
	}

	public ArrayList<JTextField> getSig1ParamsTextField() {
		return sig1Params;
	}

	public ArrayList<JTextField> getSig2ParamsTextField() {
		return sig2Params;
	}
	
	public void setSig1ParamsNames(String[] names){
		for(int i = 0 ; i < sig1ParamLabels.size() ; i++ ){
			if(i<names.length){
				sig1ParamLabels.get(i).setText(names[i]);
				sig1ParamLabels.get(i).setVisible(true);
				sig1Params.get(i).setVisible(true);
			}else{
				sig1ParamLabels.get(i).setVisible(false);
				sig1Params.get(i).setVisible(false);
			}
			
		}
	}

	public void setSig2ParamsNames(String[] names){
		for(int i = 0 ; i < sig2ParamLabels.size() ; i++ ){
			if(i<names.length){
				sig2ParamLabels.get(i).setText(names[i]);
				sig2ParamLabels.get(i).setVisible(true);
				sig2Params.get(i).setVisible(true);
			}else{
				sig2ParamLabels.get(i).setVisible(false);
				sig2Params.get(i).setVisible(false);
			}
			
		}
	}

	public JComboBox<SygnalBase> getSig1ComboBox() {
		return sig1ComboBox;
	}

	public JComboBox<SygnalBase> getSig2ComboBox() {
		return sig2ComboBox;
	}

	public JButton getBtnGenSig1() {
		return btnGenSig1;
	}

	public JButton getBtnGenSig2() {
		return btnGenSig2;
	}
	
	public void setSig1Chart(ChartPanel chart){
		sig1GraphPanel.add(chart);
		sig1GraphPanel.setVisible(true);
		chart.setVisible(true);
	}
}
