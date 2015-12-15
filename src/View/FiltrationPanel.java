package View;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Model.Filtration.Filter.FilterBuilder;
import Model.Filtration.Windows.Window.WindowBuilder;

public class FiltrationPanel extends JPanel {
	
	private ChartPanel filterChart, filterTransmitantionChart;
	private ChartPanel windowChart;
	private ChartPanel resultChart;

	private JLabel lblK, lblM, lblN;
	private JTextField fieldK, fieldM, fieldN;
	
	private JComboBox<FilterBuilder> filterType;
	private JComboBox<WindowBuilder> windowType;
	
	public FiltrationPanel(){
		initialize();
	}
	
	private void initialize(){
		lblK = new JLabel("K");
		lblK.setBounds(50, 20, 20, 20);
		this.add(lblK);
		
		fieldK = new JTextField("8");
		fieldK.setBounds(60, 20, 30, 20);
		this.add(fieldK);
		
		lblM = new JLabel("M");
		lblM.setBounds(100, 20, 20, 20);
		this.add(lblM);
		
		fieldM = new JTextField("63");
		fieldM.setBounds(110, 20, 30, 20);
		this.add(fieldM);
		
		lblN = new JLabel("N");
		lblN.setBounds(150, 20, 20, 20);
		this.add(lblN);
		
		fieldN = new JTextField("128");
		fieldN.setBounds(160, 20, 30, 20);
		this.add(fieldN);
		
		filterChart = new ChartPanel(null);
		filterChart.setBorder(new LineBorder(new Color(105, 105, 105)));
		filterChart.setBounds(20, 50, 340, 258);
		this.add(filterChart);
		
		filterTransmitantionChart = new ChartPanel(null);
		filterTransmitantionChart.setBorder(new LineBorder(new Color(105, 105, 105)));
		filterTransmitantionChart.setBounds(370, 50, 340, 258);
		this.add(filterTransmitantionChart);
		
		windowChart = new ChartPanel(null);
		windowChart.setBorder(new LineBorder(new Color(105, 105, 105)));
		windowChart.setBounds(720, 50, 340, 258);
		this.add(windowChart);
		
		resultChart = new ChartPanel(null);
		resultChart.setBorder(new LineBorder(new Color(105, 105, 105)));
		resultChart.setBounds(370, 332, 690, 258);
		this.add(resultChart);
		
		filterType = new JComboBox<FilterBuilder>();
		filterType.setBounds(280, 20, 200, 20);
		this.add(filterType);
		
		windowType = new JComboBox<WindowBuilder>();
		windowType.setBounds(800, 20, 200, 20);
		this.add(windowType);
	}
	
	public void setFilterChart(JFreeChart chart){
		this.filterChart.setChart(chart);
	}
	
	public void setFilterTransmitationChart(JFreeChart chart){
		this.filterTransmitantionChart.setChart(chart);
	}
	
	public void setWindowChart(JFreeChart chart){
		this.windowChart.setChart(chart);
	}
	
	public void setResultChart(JFreeChart chart){
		this.resultChart.setChart(chart);
	}

	public JComboBox<FilterBuilder> getFilterTypeChoose() {
		return filterType;
	}

	public JTextField getFieldK() {
		return fieldK;
	}

	public JTextField getFieldM() {
		return fieldM;
	}

	public JTextField getFieldN() {
		return fieldN;
	}

	public JComboBox<WindowBuilder> getWindowTypeChoose() {
		return windowType;
	}
}
