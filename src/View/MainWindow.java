package View;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MainWindow {

	public JFrame frame;
	private JPanel panel;

	private JTabbedPane tabbedPane;
	public PanelSygnalu panelPierwszegoSygnalu;
	public PanelSygnalu panelDrugiegoSygnalu;
	public PanelOperacji panelWynikuOperacji;
	public PanelKonwersji panelKonwersji;



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
//		panel.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 32, 1084, 629);
		panel.add(tabbedPane);

		panelPierwszegoSygnalu = new PanelSygnalu();
		panelPierwszegoSygnalu.setLayout(null);
		tabbedPane.addTab("Pierwszy sygna\u0142", null, panelPierwszegoSygnalu, null);

		panelDrugiegoSygnalu = new PanelSygnalu();
		panelDrugiegoSygnalu.setLayout(null);
		tabbedPane.addTab("Drugi sygna\u0142", null, panelDrugiegoSygnalu, null);

		panelWynikuOperacji = new PanelOperacji();
		panelWynikuOperacji.setLayout(null);
		tabbedPane.addTab("Operacje", null, panelWynikuOperacji, null);
		
		panelKonwersji = new PanelKonwersji();
		panelKonwersji.setLayout(null);
		tabbedPane.add("Konwersja", panelKonwersji);

		panelWynikuOperacji.setLayout(null);

		

	}
}
