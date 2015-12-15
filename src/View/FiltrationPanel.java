package View;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Model.Konwersja.KonwersjaCA;

public class FiltrationPanel extends JPanel {
	
	private ChartPanel filterChart, filterTransmitantionChart;
	private ChartPanel windowChart;
	private ChartPanel result;
	
	private JComboBox<Class> filterType;
	
	public FiltrationPanel(){
		initialize();
	}
	
	private void initialize(){
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
		
		result = new ChartPanel(null);
		result.setBorder(new LineBorder(new Color(105, 105, 105)));
		result.setBounds(370, 332, 690, 258);
		this.add(result);
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
		this.result.setChart(chart);
	}
}
