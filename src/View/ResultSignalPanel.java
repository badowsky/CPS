package View;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Model.Operation.SignalOperation;

public class ResultSignalPanel extends JPanel {

	private ChartPanel chartPanel;
	private ChartPanel histogramPanel;
	private JButton btnDoOperation;
	private ChartPanel panelSecondSignalPrev;
	private ChartPanel panelFirstSignalPrev;
	private JButton btnSaveResult;
	private JComboBox<SignalOperation> operations;
	
	public ResultSignalPanel(){
		this.initialize();
	}
	
	private void initialize(){
		chartPanel = new ChartPanel(null);
		chartPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel.setBounds(402, 30, 667, 258);
		this.add(chartPanel);

		histogramPanel = new ChartPanel(null);
		histogramPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		histogramPanel.setBounds(402, 292, 667, 258);
		this.add(histogramPanel);

		btnDoOperation = new JButton("Wykonaj operacj\u0119");
		btnDoOperation.setBounds(262, 292, 130, 40);
		this.add(btnDoOperation);

		operations = new JComboBox<SignalOperation>();
		operations.setBounds(262, 268, 130, 20);
		this.add(operations);

		panelFirstSignalPrev = new ChartPanel(null);
		panelFirstSignalPrev.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFirstSignalPrev.setBounds(30, 30, 270, 130);
		this.add(panelFirstSignalPrev);

		panelSecondSignalPrev = new ChartPanel(null);
		panelSecondSignalPrev.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSecondSignalPrev.setBounds(30, 420, 270, 130);
		this.add(panelSecondSignalPrev);

		btnSaveResult = new JButton("Zapisz");
		btnSaveResult.setBounds(980, 567, 89, 23);
		this.add(btnSaveResult);
	}
	
	public void setChart(JFreeChart chart) {
		chartPanel.setChart(chart);
		chartPanel.getParent().repaint();
	}

	public void setHistogram(JFreeChart chart) {
		histogramPanel.setChart(chart);
		histogramPanel.getParent().repaint();
	}

	public JComboBox<SignalOperation> getOperations() {
		return operations;
	}

	public JButton getBtnDoOperation() {
		return btnDoOperation;
	}

	public JButton getBtnSaveResult() {
		return btnSaveResult;
	}

	public ChartPanel getPanelSecondSignalPrev() {
		return panelSecondSignalPrev;
	}

	public ChartPanel getPanelFirstSignalPrev() {
		return panelFirstSignalPrev;
	}
}
