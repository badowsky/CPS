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

import Model.FunkcjeCiagle.FunkcjaCiagla;
import Model.Konwersja.KonwersjaCA;
import Model.Konwersja.Kwantyzacja;
import Model.Sygnaly.Dyskretne.SygnalDyskretny;

public class PanelKwantyzacji extends JPanel {

	private ChartPanel wykresSygDoKwantyzacji;
	private ChartPanel wykresPoKwantyzacji;
	//private JButton btnProbkuj;
	private JButton btnKwantyzuj;
	private JLabel chartFromName, chartStepName, chartToName;
	private JTextField chartFrom, chartTo;
	private JTextField chartStep;
	//private ChartPanel podgladSygnalu;
	private JRadioButton wyborSygnaluPierwszego, wyborSygnaluDrugiego;
	private ButtonGroup grupaWyboruSygnalow;
	private JButton btnSaveResult;
	private JComboBox<Kwantyzacja> kwantyzacja;
	private PanelMiarPodobienstwa panelMiarPodobienstwa;
	
	private FunkcjaCiagla sygnalDoKwantyzowania;
	private SygnalDyskretny sygnalSkwantyzowany;
	
	public PanelKwantyzacji(){
		this.initialize();
	}
	
	private void initialize(){
		chartFromName = new JLabel("Podgl¹d od");
		chartFromName.setBounds(407, 5, 70, 20);
		this.add(chartFromName);
		chartStepName = new JLabel("Czêstotliwoœæ próbkowania");
		chartStepName.setBounds(670, 5, 200, 20);
		this.add(chartStepName);
		chartToName = new JLabel("Podgl¹d do");
		chartToName.setBounds(1013, 5, 70, 20);
		this.add(chartToName);
		
		chartFrom = new JTextField("-5");
		chartFrom.setBounds(407, 25, 50, 20);
		this.add(chartFrom);
		chartStep = new JTextField("1");
		chartStep.setBounds(707, 25, 50, 20);
		this.add(chartStep);
		chartTo = new JTextField("5");
		chartTo.setBounds(1019, 25, 50, 20);
		this.add(chartTo);
		
		wykresSygDoKwantyzacji = new ChartPanel(null);
		wykresSygDoKwantyzacji.setBorder(new LineBorder(new Color(105, 105, 105)));
		wykresSygDoKwantyzacji.setBounds(407, 50, 662, 258);
		this.add(wykresSygDoKwantyzacji);

		wykresPoKwantyzacji = new ChartPanel(null);
		wykresPoKwantyzacji.setBorder(new LineBorder(new Color(105, 105, 105)));
		wykresPoKwantyzacji.setBounds(407, 332, 662, 258);
		this.add(wykresPoKwantyzacji);

//		btnProbkuj = new JButton("Próbkuj >>");
//		btnProbkuj.setBounds(293, 110, 100, 40);
//		this.add(btnProbkuj);
		
		kwantyzacja = new JComboBox<Kwantyzacja>();
		kwantyzacja.setBounds(150, 200, 170, 20);
		this.add(kwantyzacja);
		
		btnKwantyzuj = new JButton("Kwantyzuj");
		btnKwantyzuj.setBounds(150, 230, 170, 40);
		this.add(btnKwantyzuj);

//		podgladSygnalu = new ChartPanel(null);
//		podgladSygnalu.setBorder(new LineBorder(new Color(0, 0, 0)));
//		podgladSygnalu.setBounds(10, 30, 270, 130);
//		this.add(podgladSygnalu);
		
		wyborSygnaluPierwszego = new JRadioButton("Pierwszy Sygna³");
		wyborSygnaluPierwszego.setBounds(190, 40, 110, 20);
		this.add(wyborSygnaluPierwszego);
		wyborSygnaluDrugiego = new JRadioButton("Drugi Sygna³");
		wyborSygnaluDrugiego.setBounds(190, 60, 110, 20);
		this.add(wyborSygnaluDrugiego);
		grupaWyboruSygnalow = new ButtonGroup();
		grupaWyboruSygnalow.add(wyborSygnaluPierwszego);
		grupaWyboruSygnalow.add(wyborSygnaluDrugiego);
		wyborSygnaluPierwszego.setSelected(true);
		
		
		panelMiarPodobienstwa = new PanelMiarPodobienstwa();
		panelMiarPodobienstwa.setBounds(30, 360, 330, 200);
		this.add(panelMiarPodobienstwa);

		
	}
	
	public void setChart(JFreeChart chart) {
		wykresSygDoKwantyzacji.setChart(chart);
		wykresSygDoKwantyzacji.getParent().repaint();
	}

	public void setSecondChart(JFreeChart chart) {
		wykresPoKwantyzacji.setChart(chart);
		wykresPoKwantyzacji.getParent().repaint();
	}

	public JComboBox<Kwantyzacja> getKwantyzacja() {
		return kwantyzacja;
	}

//	public JButton getBtnProbkuj() {
//		return btnProbkuj;
//	}
	
	public JButton getBtnKwantyzuj() {
		return btnKwantyzuj;
	}

	public JButton getBtnSaveResult() {
		return btnSaveResult;
	}

//	public ChartPanel getPodgladSygnalu() {
//		return podgladSygnalu;
//	}

	public JRadioButton getWyborSygnaluPierwszego() {
		return wyborSygnaluPierwszego;
	}

	public JRadioButton getWyborSygnaluDrugiego() {
		return wyborSygnaluDrugiego;
	}

	public ButtonGroup getGrupaWyboruSygnalow() {
		return grupaWyboruSygnalow;
	}

	public FunkcjaCiagla getSygnalDoKwantyzacji() {
		return sygnalDoKwantyzowania;
	}

	public SygnalDyskretny getSygnalSkwantyzowany() {
		return sygnalSkwantyzowany;
	}

	public void setSygnalDoKwantyzacji(FunkcjaCiagla sygnalDoProbkowania) {
		this.sygnalDoKwantyzowania = sygnalDoProbkowania;
	}

	public void setSygnalSkwantyzowany(SygnalDyskretny sygnalSprobkowany) {
		this.sygnalSkwantyzowany = sygnalSprobkowany;
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

	public PanelMiarPodobienstwa getPanelMiarPodobienstwa() {
		return panelMiarPodobienstwa;
	}
}
