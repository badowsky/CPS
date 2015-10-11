package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Model.SignalGenerator;
import Operation.SignalOperation;

public class MainWindow {

	public JFrame frame;
	private JPanel panel;
	
	private JTextField sig1Param1TextField;
	private JTextField sig1Param2TextField;
	private JTextField sig1Param3TextField;
	private JTextField sig1Param4TextField;
	private JTextField sig1Param5TextField;
	private JTextField sig1Param6TextField;
	private JComboBox<SignalGenerator> sig1ComboBox;
	private JButton btnGenSig1;
	private JButton btnWczytajSig1;
	
	private ArrayList<JTextField> sig1Params;
	private ArrayList<JLabel> sig1ParamLabels;
	private ChartPanel chartPanel1;
	private ChartPanel histogramPanel1;
	
	private JTextField sig2Param1TextField;
	private JTextField sig2Param2TextField;
	private JTextField sig2Param3TextField;
	private JTextField sig2Param4TextField;
	private JTextField sig2Param5TextField;
	private JTextField sig2Param6TextField;
	private JComboBox<SignalGenerator> sig2ComboBox;
	private JButton btnGenSig2;
	private JButton btnWczytajSig2;
	
	private ArrayList<JTextField> sig2Params;
	private ArrayList<JLabel> sig2ParamLabels;
	private ChartPanel chartPanel2;
	private ChartPanel histogramPanel2;
	private JTabbedPane tabbedPane;
	private JPanel panelTab1;
	private JPanel panelTab2;
	private JPanel panelTab3;
	

	private ChartPanel chartPanel3;
	private ChartPanel histogramPanel3;
	private JButton btnWykonajOperacje;
	private ChartPanel panelSig2Prev;
	private ChartPanel panelSig1Prev;
	private JButton btnSaveResult;
	private JComboBox<SignalOperation> rodzajOperComboBox;

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
		frame.setBounds(100, 100, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1084, 21);
		panel.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 32, 1084, 629);
		panel.add(tabbedPane);
		
		panelTab1 = new JPanel();
		panelTab1.setLayout(null);
		tabbedPane.addTab("Pierwszy sygna\u0142", null, panelTab1, null);
		
		panelTab2 = new JPanel();
		panelTab2.setLayout(null);
		tabbedPane.addTab("Drugi sygna\u0142", null, panelTab2, null);
		

		panelTab3 = new JPanel();
		panelTab3.setLayout(null);
		tabbedPane.addTab("Operacje", null, panelTab3, null);
		
		sig1Param1TextField = new JTextField("0");
		sig1Param1TextField.setBounds(293, 70, 86, 20);
		panelTab1.add(sig1Param1TextField);
		sig1Param1TextField.setColumns(10);		
		
		sig1Param2TextField = new JTextField("10");
		sig1Param2TextField.setBounds(293, 100, 86, 20);
		panelTab1.add(sig1Param2TextField);
		sig1Param2TextField.setColumns(10);
		
		sig1Param3TextField = new JTextField("100");
		sig1Param3TextField.setBounds(293, 130, 86, 20);
		panelTab1.add(sig1Param3TextField);
		sig1Param3TextField.setColumns(10);
		
		sig1Param4TextField = new JTextField("10");
		sig1Param4TextField.setColumns(10);
		sig1Param4TextField.setBounds(293, 160, 86, 20);
		panelTab1.add(sig1Param4TextField);
		
		sig1Param5TextField = new JTextField("5");
		sig1Param5TextField.setColumns(10);
		sig1Param5TextField.setBounds(293, 190, 86, 20);
		panelTab1.add(sig1Param5TextField);
		
		sig1Param6TextField = new JTextField();
		sig1Param6TextField.setColumns(10);
		sig1Param6TextField.setBounds(293, 220, 86, 20);
		panelTab1.add(sig1Param6TextField);
		
		sig1ComboBox = new JComboBox<SignalGenerator>();
		sig1ComboBox.setBounds(38, 127, 160, 20);
		panelTab1.add(sig1ComboBox);
		
		sig2Param1TextField = new JTextField("0");
		sig2Param1TextField.setColumns(10);
		sig2Param1TextField.setBounds(293, 70, 86, 20);
		panelTab2.add(sig2Param1TextField);
		
		sig2Param2TextField = new JTextField("10");
		sig2Param2TextField.setColumns(10);
		sig2Param2TextField.setBounds(293, 100, 86, 20);
		panelTab2.add(sig2Param2TextField);
		
		sig2Param3TextField = new JTextField("100");
		sig2Param3TextField.setColumns(10);
		sig2Param3TextField.setBounds(293, 130, 86, 20);
		panelTab2.add(sig2Param3TextField);
		
		sig2Param4TextField = new JTextField("10");
		sig2Param4TextField.setColumns(10);
		sig2Param4TextField.setBounds(293, 160, 86, 20);
		panelTab2.add(sig2Param4TextField);
		
		sig2Param5TextField = new JTextField("5");
		sig2Param5TextField.setColumns(10);
		sig2Param5TextField.setBounds(293, 190, 86, 20);
		panelTab2.add(sig2Param5TextField);
		
		sig2Param6TextField = new JTextField();
		sig2Param6TextField.setColumns(10);
		sig2Param6TextField.setBounds(293, 220, 86, 20);
		panelTab2.add(sig2Param6TextField);

		sig2ComboBox = new JComboBox<SignalGenerator>();
		sig2ComboBox.setBounds(38, 127, 160, 20);
		panelTab2.add(sig2ComboBox);
		
		btnGenSig1 = new JButton("Generuj sygna\u0142");
		btnGenSig1.setBounds(48, 158, 143, 36);
		panelTab1.add(btnGenSig1);
		
		btnGenSig2 = new JButton("Generuj sygna\u0142");
		btnGenSig2.setBounds(48, 158, 143, 36);
		panelTab2.add(btnGenSig2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(201, 70, 82, 14);
		panelTab1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(201, 100, 82, 14);
		panelTab1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(201, 130, 82, 14);
		panelTab1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(201, 160, 82, 14);
		panelTab1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(201, 190, 82, 14);
		panelTab1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(201, 220, 82, 14);
		panelTab1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(201, 70, 82, 14);
		panelTab2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(201, 100, 82, 14);
		panelTab2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(201, 130, 82, 14);
		panelTab2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9.setBounds(201, 160, 82, 14);
		panelTab2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10.setBounds(201, 190, 82, 14);
		panelTab2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_11.setBounds(201, 220, 82, 14);
		panelTab2.add(lblNewLabel_11);
		
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
		
		btnWczytajSig1 = new JButton("Wczytaj");
		btnWczytajSig1.setBounds(985, 32, 89, 23);
		panelTab1.add(btnWczytajSig1);
		
		btnWczytajSig2 = new JButton("Wczytaj");
		btnWczytajSig2.setBounds(985, 32, 89, 23);
		panelTab2.add(btnWczytajSig2);
		
		
		chartPanel1 = new ChartPanel(null);
		chartPanel1.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel1.setBounds(407, 70, 667, 258);
		panelTab1.add(chartPanel1);
		
		chartPanel2 = new ChartPanel(null);
		chartPanel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel2.setBounds(407, 70, 667, 258);
		panelTab2.add(chartPanel2);
		panelTab3.setLayout(null);
		
		chartPanel3 = new ChartPanel(null);
		chartPanel3.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel3.setBounds(402, 30, 667, 258);
		panelTab3.add(chartPanel3);
		
		histogramPanel1 = new ChartPanel(null);
		histogramPanel1.setBorder(new LineBorder(new Color(0, 0, 0)));
		histogramPanel1.setBounds(407, 332, 667, 258);
		panelTab1.add(histogramPanel1);
		
		histogramPanel2 = new ChartPanel(null);
		histogramPanel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		histogramPanel2.setBounds(407, 332, 667, 258);
		panelTab2.add(histogramPanel2);
		
		histogramPanel3 = new ChartPanel(null);
		histogramPanel3.setBorder(new LineBorder(new Color(0, 0, 0)));
		histogramPanel3.setBounds(402, 292, 667, 258);
		panelTab3.add(histogramPanel3);
		
		btnWykonajOperacje = new JButton("Wykonaj operacj\u0119");
		btnWykonajOperacje.setBounds(141, 292, 130, 40);
		panelTab3.add(btnWykonajOperacje);
		
		rodzajOperComboBox = new JComboBox<SignalOperation>();
		rodzajOperComboBox.setBounds(141, 268, 130, 20);
		panelTab3.add(rodzajOperComboBox);
		
		panelSig1Prev = new ChartPanel(null);
		panelSig1Prev.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSig1Prev.setBounds(30, 30, 200, 100);
		panelTab3.add(panelSig1Prev);
		
		panelSig2Prev = new ChartPanel(null);
		panelSig2Prev.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSig2Prev.setBounds(30, 450, 200, 100);
		panelTab3.add(panelSig2Prev);
		
		btnSaveResult = new JButton("Zapisz");
		btnSaveResult.setBounds(980, 567, 89, 23);
		panelTab3.add(btnSaveResult);
		
		
		
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

	public JComboBox<SignalGenerator> getSig1ComboBox() {
		return sig1ComboBox;
	}

	public JComboBox<SignalGenerator> getSig2ComboBox() {
		return sig2ComboBox;
	}

	public JButton getBtnGenSig1() {
		return btnGenSig1;
	}

	public JButton getBtnGenSig2() {
		return btnGenSig2;
	}
	
	public void setChart1(JFreeChart chart) {
		Container chartPanelParent = chartPanel1.getParent();
		chartPanel1.setChart(chart);
		chartPanelParent.repaint();
		
		Container panelSig1PrevParent = panelSig1Prev.getParent();
		panelSig1Prev.setChart(chart);
		panelSig1PrevParent.repaint();
	}
	
	public void setHistogram1(JFreeChart chart) {
		Container histogramPanelParent = histogramPanel1.getParent();
		histogramPanel1.setChart(chart);
		histogramPanelParent.repaint();
	}
	
	public void setChart2(JFreeChart chart) {
		chartPanel2.setChart(chart);
		chartPanel2.getParent().repaint();
		
		panelSig2Prev.setChart(chart);
		panelSig2Prev.getParent().repaint();
	}
	
	public void setHistogram2(JFreeChart chart) {
		histogramPanel2.setChart(chart);
		histogramPanel2.getParent().repaint();
	}
	
	public void setChart3(JFreeChart chart) {
		chartPanel3.setChart(chart);
		chartPanel3.getParent().repaint();
	}
	
	public void setHistogram3(JFreeChart chart) {
		histogramPanel3.setChart(chart);
		histogramPanel3.getParent().repaint();
	}

	public JButton getBtnWczytajSig1() {
		return btnWczytajSig1;
	}

	public JButton getBtnWczytajSig2() {
		return btnWczytajSig2;
	}

	public JComboBox<SignalOperation> getRodzajOperComboBox() {
		return rodzajOperComboBox;
	}

	public JButton getBtnWykonajOperacje() {
		return btnWykonajOperacje;
	}

	public JButton getBtnSaveResult() {
		return btnSaveResult;
	}
}
