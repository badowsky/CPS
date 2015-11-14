package View;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Helpers.MyCallable;
import Model.FunkcjeCiagle.FunkcjaCiagla;

public class PanelSygnalu extends JPanel {

	private JPanel generationPartPanel;
	private JComboBox<FunkcjaCiagla> signalChooser;
	private JButton btnGenerate;
	private JButton btnLoad;

	private ArrayList<JLabel> ParamsNames;
	private ArrayList<JTextField> Params;
	
	private JLabel chartFromName, chartStepName, chartToName;
	private JTextField chartFrom, chartTo;
	private JTextField chartStep;
	
	private ChartPanel chartPanel;
	private ChartPanel histogramPanel;

	public GenSigParamsPanel generatedParamsPanel;
	
	private MyCallable<JFreeChart> onChartChange;

	private static int NumberOfParams = 6;
	private static String[] DefaultParamsValues = { "10", "0", "3", "10", "5", "0.3" };

	public PanelSygnalu() {
		super();
		initialize();
	}

	private void initialize() {

		onChartChange = null;
		
		generationPartPanel = new JPanel();
		generationPartPanel.setBounds(30, 30, 330, 300);
		generationPartPanel.setBorder(
				new TitledBorder(new LineBorder(new Color(105, 105, 105)), "Generator sygna³u",
						TitledBorder.LEFT, TitledBorder.TOP, null, new Color(105, 105, 105)));
		generationPartPanel.setLayout(null);
		this.add(generationPartPanel);
		
		signalChooser = new JComboBox<FunkcjaCiagla>();
		signalChooser.setBounds(70, 30, 200, 20);
		generationPartPanel.add(signalChooser);

		btnGenerate = new JButton("Generuj sygna\u0142");
		btnGenerate.setBounds(100, 250, 143, 36);
		generationPartPanel.add(btnGenerate);

		ParamsNames = new ArrayList<JLabel>();
		JLabel tmpParamNamesLbl;
		for (int i = 0; i < PanelSygnalu.NumberOfParams; i++) {
			tmpParamNamesLbl = new JLabel("Labelek");
			tmpParamNamesLbl.setHorizontalAlignment(SwingConstants.RIGHT);
			tmpParamNamesLbl.setBounds(50, 70 + i * 30, 82, 14);
			generationPartPanel.add(tmpParamNamesLbl);
			ParamsNames.add(tmpParamNamesLbl);
		}
		
		Params = new ArrayList<JTextField>();
		JTextField tmpParamTF;
		for (int i = 0; i < PanelSygnalu.NumberOfParams; i++) {
			tmpParamTF = new JTextField(PanelSygnalu.DefaultParamsValues[i]);
			tmpParamTF.setBounds(150, 70 + i * 30, 86, 20);
			tmpParamTF.setColumns(10);
			generationPartPanel.add(tmpParamTF);
			Params.add(tmpParamTF);
		}
		
		chartFromName = new JLabel("Podgl¹d od");
		chartFromName.setBounds(407, 5, 70, 20);
		this.add(chartFromName);
		chartStepName = new JLabel("Czêstotliwoœæ próbkowania");
		chartStepName.setBounds(670, 5, 200, 20);
		//this.add(chartStepName);
		chartToName = new JLabel("Podgl¹d do");
		chartToName.setBounds(1013, 5, 70, 20);
		this.add(chartToName);
		
		chartFrom = new JTextField("0");
		chartFrom.setBounds(407, 25, 50, 20);
		this.add(chartFrom);
		chartStep = new JTextField("1000");
		chartStep.setBounds(707, 25, 50, 20);
		//this.add(chartStep);
		chartTo = new JTextField("10");
		chartTo.setBounds(1019, 25, 50, 20);
		this.add(chartTo);

		btnLoad = new JButton("Wczytaj");
		btnLoad.setBounds(30, 5, 89, 23);
		this.add(btnLoad);

		chartPanel = new ChartPanel(null);
		chartPanel.setBorder(new LineBorder(new Color(105, 105, 105)));
		chartPanel.setBounds(407, 50, 662, 258);
		this.add(chartPanel);

		histogramPanel = new ChartPanel(null);
		histogramPanel.setBorder(new LineBorder(new Color(105, 105, 105)));
		histogramPanel.setBounds(407, 332, 662, 258);
		this.add(histogramPanel);

		generatedParamsPanel = new GenSigParamsPanel();
		generatedParamsPanel.setBounds(30, 360, 330, 200);
		this.add(generatedParamsPanel);
		
		
	}

	public ArrayList<JTextField> getParamsFields() {
		return Params;
	}

	public void setParamsNames(String[] names) {
		for (int i = 0; i < ParamsNames.size(); i++) {
			if (i < names.length) {
				ParamsNames.get(i).setText(names[i]);
				ParamsNames.get(i).setVisible(true);
				Params.get(i).setVisible(true);
			} else {
				ParamsNames.get(i).setVisible(false);
				Params.get(i).setVisible(false);
			}

		}
	}

	public void setChart(JFreeChart chart) {
		Container chartPanelParent = chartPanel.getParent();
		chartPanel.setChart(chart);
		chartPanelParent.repaint();

		if(onChartChange != null){
			onChartChange.call(chart);
		}
	}

	public void setHistogram(JFreeChart chart) {
		Container histogramPanelParent = histogramPanel.getParent();
		histogramPanel.setChart(chart);
		histogramPanelParent.repaint();
	}

	public JComboBox<FunkcjaCiagla> getSignalChooser() {
		return signalChooser;
	}

	public JButton getBtnGenerate() {
		return btnGenerate;
	}

	public JButton getBtnLoad() {
		return btnLoad;
	}
	
	public void subscribeOnChartChange(MyCallable<JFreeChart> c){
		this.onChartChange = c;
	}

	public JLabel getChartFromName() {
		return chartFromName;
	}

	public JLabel getChartStepName() {
		return chartStepName;
	}

	public JLabel getChartToName() {
		return chartToName;
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

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

}
