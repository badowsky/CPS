package View;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Model.Conversion.Quantization;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;

public class QuantizationPanel extends JPanel {

	private ChartPanel signalForQuantizationChartPanel;
	private ChartPanel signalAfterQuantizationChartPanel;
	private JButton btnDoQuantization;
	private JLabel chartFromName, chartToName;
	private JTextField chartFrom, chartTo;
	private JLabel numberOfLevelsLbl;
	private JTextField numberOfLevelsVal;
	private JRadioButton firstSignalChoose, secondSignalChoose;
	private ButtonGroup signalChooseGroup;
	private JButton btnSaveResult;
	private JComboBox<Quantization> quantization;
	private ComparsionMeasuresPanel signalsComparsionPanel;
	
	private ContinuousSignal signalForQuantization;
	private DiscreteSignalReal signalAfterQuantization;
	
	public QuantizationPanel(){
		this.initialize();
	}
	
	private void initialize(){
		chartFromName = new JLabel("Podglad od");
		chartFromName.setBounds(407, 5, 70, 20);
		this.add(chartFromName);

		chartToName = new JLabel("Podglad do");
		chartToName.setBounds(1013, 5, 70, 20);
		this.add(chartToName);
		
		chartFrom = new JTextField("0");
		chartFrom.setBounds(407, 25, 50, 20);
		this.add(chartFrom);

		chartTo = new JTextField("10");
		chartTo.setBounds(1019, 25, 50, 20);
		this.add(chartTo);
		
		numberOfLevelsLbl = new JLabel("Ilosc stopni");
		numberOfLevelsLbl.setBounds(190, 160, 80, 20);
		this.add(numberOfLevelsLbl);
		
		numberOfLevelsVal = new JTextField("10");
		numberOfLevelsVal.setBounds(250, 160, 50, 20);
		this.add(numberOfLevelsVal);
		
		signalForQuantizationChartPanel = new ChartPanel(null);
		signalForQuantizationChartPanel.setBorder(new LineBorder(new Color(105, 105, 105)));
		signalForQuantizationChartPanel.setBounds(407, 50, 662, 258);
		this.add(signalForQuantizationChartPanel);

		signalAfterQuantizationChartPanel = new ChartPanel(null);
		signalAfterQuantizationChartPanel.setBorder(new LineBorder(new Color(105, 105, 105)));
		signalAfterQuantizationChartPanel.setBounds(407, 332, 662, 258);
		this.add(signalAfterQuantizationChartPanel);
		
		quantization = new JComboBox<Quantization>();
		quantization.setBounds(150, 200, 170, 20);
		this.add(quantization);
		
		btnDoQuantization = new JButton("Kwantyzuj");
		btnDoQuantization.setBounds(150, 230, 170, 40);
		this.add(btnDoQuantization);
		
		firstSignalChoose = new JRadioButton("Pierwszy Sygnal");
		firstSignalChoose.setBounds(190, 80, 110, 20);
		this.add(firstSignalChoose);
		secondSignalChoose = new JRadioButton("Drugi Sygnal");
		secondSignalChoose.setBounds(190, 100, 110, 20);
		this.add(secondSignalChoose);
		signalChooseGroup = new ButtonGroup();
		signalChooseGroup.add(firstSignalChoose);
		signalChooseGroup.add(secondSignalChoose);
		firstSignalChoose.setSelected(true);
		
		
		signalsComparsionPanel = new ComparsionMeasuresPanel();
		signalsComparsionPanel.setBounds(30, 360, 330, 200);
		this.add(signalsComparsionPanel);

		
	}
	
	public void setChart(JFreeChart chart) {
		signalForQuantizationChartPanel.setChart(chart);
		signalForQuantizationChartPanel.getParent().repaint();
	}

	public void setSecondChart(JFreeChart chart) {
		signalAfterQuantizationChartPanel.setChart(chart);
		signalAfterQuantizationChartPanel.getParent().repaint();
	}

	public JComboBox<Quantization> getQuantization() {
		return quantization;
	}
	
	public JButton getBtnDoQuantization() {
		return btnDoQuantization;
	}

	public JButton getBtnSaveResult() {
		return btnSaveResult;
	}

	public JRadioButton getFirstSignalChoose() {
		return firstSignalChoose;
	}

	public JRadioButton getSecondSignalChoose() {
		return secondSignalChoose;
	}

	public ButtonGroup getSignalChooseGroup() {
		return signalChooseGroup;
	}

	public ContinuousSignal getSignalForQuantization() {
		return signalForQuantization;
	}

	public DiscreteSignalReal getSignalAfterQuantization() {
		return signalAfterQuantization;
	}

	public void setSignalForQuantization(ContinuousSignal signal) {
		this.signalForQuantization = signal;
	}

	public void setSignalAfterQuantization(DiscreteSignalReal signal) {
		this.signalAfterQuantization = signal;
	}

	public JLabel getChartFromName() {
		return chartFromName;
	}

	public JTextField getChartFrom() {
		return chartFrom;
	}
	
	public JTextField getChartTo() {
		return chartTo;
	}

	public ComparsionMeasuresPanel getSignalsComparsionPanel() {
		return signalsComparsionPanel;
	}

	public JLabel getNumberOfLevelsLbl() {
		return numberOfLevelsLbl;
	}

	public JTextField getNumberOfLevelsVal() {
		return numberOfLevelsVal;
	}
}
