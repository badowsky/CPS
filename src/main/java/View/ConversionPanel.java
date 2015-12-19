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

import Model.Conversion.ConversionCA;
import Model.Signals.Continuous.ContinuousSignal;
import Model.Signals.Discrete.DiscreteSignalReal;

public class ConversionPanel extends JPanel {

	private ChartPanel samplingChanrtPanel;
	private ChartPanel convertedChartPanel;
	private JButton btnDoSampling;
	private JButton btnReCreate;
	private JLabel chartFromName, chartStepName, chartToName;
	private JTextField chartFrom, chartTo, chartStep;
	private ChartPanel signalPreview;
	private JRadioButton firstSignalChoose, secondSignalChoose;
	private ButtonGroup signalChooseGroup;
	private JButton btnSaveResult;
	private JComboBox<ConversionCA> conversion;
	private ComparsionMeasuresPanel signalsComparsionPanel;
	
	private ContinuousSignal signalForSampling;
	private DiscreteSignalReal signalSampled;
	
	public ConversionPanel(){
		this.initialize();
	}
	
	private void initialize(){
		chartFromName = new JLabel("Podglad od");
		chartFromName.setBounds(407, 5, 70, 20);
		this.add(chartFromName);
		chartStepName = new JLabel("Czestotliwosc probkowania");
		chartStepName.setBounds(670, 5, 200, 20);
		this.add(chartStepName);
		chartToName = new JLabel("Podglad do");
		chartToName.setBounds(1013, 5, 70, 20);
		this.add(chartToName);
		
		chartFrom = new JTextField("0");
		chartFrom.setBounds(407, 25, 50, 20);
		this.add(chartFrom);
		chartStep = new JTextField("1");
		chartStep.setBounds(707, 25, 50, 20);
		this.add(chartStep);
		chartTo = new JTextField("10");
		chartTo.setBounds(1019, 25, 50, 20);
		this.add(chartTo);
		
		samplingChanrtPanel = new ChartPanel(null);
		samplingChanrtPanel.setBorder(new LineBorder(new Color(105, 105, 105)));
		samplingChanrtPanel.setBounds(407, 50, 662, 258);
		this.add(samplingChanrtPanel);

		convertedChartPanel = new ChartPanel(null);
		convertedChartPanel.setBorder(new LineBorder(new Color(105, 105, 105)));
		convertedChartPanel.setBounds(407, 332, 662, 258);
		this.add(convertedChartPanel);

		btnDoSampling = new JButton("Próbkuj >>");
		btnDoSampling.setBounds(293, 110, 100, 40);
		this.add(btnDoSampling);
		
		conversion = new JComboBox<ConversionCA>();
		conversion.setBounds(150, 200, 170, 20);
		this.add(conversion);
		
		btnReCreate = new JButton("Odtwórz sygna³ analogowy");
		btnReCreate.setBounds(150, 230, 170, 40);
		this.add(btnReCreate);

		signalPreview = new ChartPanel(null);
		signalPreview.setBorder(new LineBorder(new Color(0, 0, 0)));
		signalPreview.setBounds(10, 30, 270, 130);
		this.add(signalPreview);
		
		firstSignalChoose = new JRadioButton("Pierwszy Sygnal");
		firstSignalChoose.setBounds(290, 40, 110, 20);
		this.add(firstSignalChoose);
		secondSignalChoose = new JRadioButton("Drugi Sygnal");
		secondSignalChoose.setBounds(290, 60, 110, 20);
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
		samplingChanrtPanel.setChart(chart);
		samplingChanrtPanel.getParent().repaint();
	}

	public void setHistogram(JFreeChart chart) {
		convertedChartPanel.setChart(chart);
		convertedChartPanel.getParent().repaint();
	}

	public JComboBox<ConversionCA> getConversionType() {
		return conversion;
	}

	public JButton getBtnDoSampling() {
		return btnDoSampling;
	}
	
	public JButton getBtnReCreate() {
		return btnReCreate;
	}

	public JButton getBtnSaveResult() {
		return btnSaveResult;
	}

	public ChartPanel getSignalPreview() {
		return signalPreview;
	}

	public JRadioButton getFirstSignalChoose() {
		return firstSignalChoose;
	}

	public JRadioButton getSecondSignalChoose() {
		return secondSignalChoose;
	}

	public ButtonGroup getSingalChooseGroup() {
		return signalChooseGroup;
	}

	public ContinuousSignal getSignalForSampling() {
		return signalForSampling;
	}

	public DiscreteSignalReal getSignalSampled() {
		return signalSampled;
	}

	public void setSignalForSampling(ContinuousSignal signal) {
		this.signalForSampling = signal;
	}

	public void setSignalSampled(DiscreteSignalReal signal) {
		this.signalSampled = signal;
	}

	public JLabel getChartFromName() {
		return chartFromName;
	}

	public JTextField getChartFrom() {
		return chartFrom;
	}

	public JTextField getChartStep() {
		return chartStep;
	}
	
	public JTextField getChartTo() {
		return chartTo;
	}

	public ComparsionMeasuresPanel getSignalComparsionPanel() {
		return signalsComparsionPanel;
	}
}
