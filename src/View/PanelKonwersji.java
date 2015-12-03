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
import Model.Sygnaly.Dyskretne.SygnalDyskretnyReal;

public class PanelKonwersji extends JPanel {

	private ChartPanel wykresProbkowanie;
	private ChartPanel wykresPoKonwersji;
	private JButton btnProbkuj;
	private JButton btnOdtworz;
	private JLabel chartFromName, chartStepName, chartToName;
	private JTextField chartFrom, chartTo;
	private JTextField chartStep;
	private ChartPanel podgladSygnalu;
	private JRadioButton wyborSygnaluPierwszego, wyborSygnaluDrugiego;
	private ButtonGroup grupaWyboruSygnalow;
	private JButton btnSaveResult;
	private JComboBox<KonwersjaCA> konwersja;
	private PanelMiarPodobienstwa panelMiarPodobienstwa;
	
	private FunkcjaCiagla sygnalDoProbkowania;
	private SygnalDyskretnyReal sygnalSprobkowany;
	
	public PanelKonwersji(){
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
		
		wykresProbkowanie = new ChartPanel(null);
		wykresProbkowanie.setBorder(new LineBorder(new Color(105, 105, 105)));
		wykresProbkowanie.setBounds(407, 50, 662, 258);
		this.add(wykresProbkowanie);

		wykresPoKonwersji = new ChartPanel(null);
		wykresPoKonwersji.setBorder(new LineBorder(new Color(105, 105, 105)));
		wykresPoKonwersji.setBounds(407, 332, 662, 258);
		this.add(wykresPoKonwersji);

		btnProbkuj = new JButton("Próbkuj >>");
		btnProbkuj.setBounds(293, 110, 100, 40);
		this.add(btnProbkuj);
		
		konwersja = new JComboBox<KonwersjaCA>();
		konwersja.setBounds(150, 200, 170, 20);
		this.add(konwersja);
		
		btnOdtworz = new JButton("Odtwórz sygna³ analogowy");
		btnOdtworz.setBounds(150, 230, 170, 40);
		this.add(btnOdtworz);

		podgladSygnalu = new ChartPanel(null);
		podgladSygnalu.setBorder(new LineBorder(new Color(0, 0, 0)));
		podgladSygnalu.setBounds(10, 30, 270, 130);
		this.add(podgladSygnalu);
		
		wyborSygnaluPierwszego = new JRadioButton("Pierwszy Sygnal");
		wyborSygnaluPierwszego.setBounds(290, 40, 110, 20);
		this.add(wyborSygnaluPierwszego);
		wyborSygnaluDrugiego = new JRadioButton("Drugi Sygnal");
		wyborSygnaluDrugiego.setBounds(290, 60, 110, 20);
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
		wykresProbkowanie.setChart(chart);
		wykresProbkowanie.getParent().repaint();
	}

	public void setHistogram(JFreeChart chart) {
		wykresPoKonwersji.setChart(chart);
		wykresPoKonwersji.getParent().repaint();
	}

	public JComboBox<KonwersjaCA> getKonwersja() {
		return konwersja;
	}

	public JButton getBtnProbkuj() {
		return btnProbkuj;
	}
	
	public JButton getBtnOdtworz() {
		return btnOdtworz;
	}

	public JButton getBtnSaveResult() {
		return btnSaveResult;
	}

	public ChartPanel getPodgladSygnalu() {
		return podgladSygnalu;
	}

	public JRadioButton getWyborSygnaluPierwszego() {
		return wyborSygnaluPierwszego;
	}

	public JRadioButton getWyborSygnaluDrugiego() {
		return wyborSygnaluDrugiego;
	}

	public ButtonGroup getGrupaWyboruSygnalow() {
		return grupaWyboruSygnalow;
	}

	public FunkcjaCiagla getSygnalDoProbkowania() {
		return sygnalDoProbkowania;
	}

	public SygnalDyskretnyReal getSygnalSprobkowany() {
		return sygnalSprobkowany;
	}

	public void setSygnalDoProbkowania(FunkcjaCiagla sygnalDoProbkowania) {
		this.sygnalDoProbkowania = sygnalDoProbkowania;
	}

	public void setSygnalSprobkowany(SygnalDyskretnyReal sygnalSprobkowany) {
		this.sygnalSprobkowany = sygnalSprobkowany;
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
