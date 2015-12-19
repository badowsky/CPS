package View;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainWindow {

	public JFrame frame;
	private JPanel panel;

	private JTabbedPane tabbedPane;
	public SignalPanel firstSignalPanel;
	public SignalPanel secondSignalPanel;
	public OperationsPanel operationsPanel;
	public ConversionPanel conversionPanel;
	public QuantizationPanel quantizationPanel;
	public FiltrationPanel filtrationPanel;


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

//		JMenuBar menuBar = new JMenuBar();
//		menuBar.setBounds(0, 0, 1084, 21);
//		panel.add(menuBar);

//		JMenu mnFile = new JMenu("File");
//		menuBar.add(mnFile);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 32, 1084, 629);
		panel.add(tabbedPane);

		firstSignalPanel = new SignalPanel();
		firstSignalPanel.setLayout(null);
		tabbedPane.addTab("Pierwszy sygna\u0142", null, firstSignalPanel, null);

		secondSignalPanel = new SignalPanel();
		secondSignalPanel.setLayout(null);
		tabbedPane.addTab("Drugi sygna\u0142", null, secondSignalPanel, null);

		operationsPanel = new OperationsPanel();
		operationsPanel.setLayout(null);
		tabbedPane.addTab("Operacje", null, operationsPanel, null);
		
		quantizationPanel = new QuantizationPanel();
		quantizationPanel.setLayout(null);
		tabbedPane.add("Kwantyzacja", quantizationPanel);
		
		conversionPanel = new ConversionPanel();
		conversionPanel.setLayout(null);
		tabbedPane.add("Konwersja", conversionPanel);
		
		filtrationPanel = new FiltrationPanel();
		filtrationPanel.setLayout(null);
		tabbedPane.add("Filtracja", filtrationPanel);

		

	}
}
