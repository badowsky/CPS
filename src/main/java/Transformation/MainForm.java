package Transformation;
/**
 * Author Mark Bishop; 2014
 * License GNU v3; 
 * This class is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import gnu.jel.CompilationException;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class responsibility: provide the application entry point in a graphical user
 * interface.
 */

public class MainForm {

	public double xMin;
	public double xMax;
	private JFrame frmPlotGenerator;
	private JTextField textFx;
	private JTextField textXmin;
	private JTextField textXmax;
	private JTextField textIncrement;
	public String function = ""; // Expression for f(x), y = f(x)
	public String label = "";
	private JLabel lblFrom;
	private JLabel lblTo;
	private JLabel lblIncrement;
	private JButton btnEmptyTable;;
	private JSpinner spinner;
	private JMenuItem mntmSin2piF;
	private JMenuItem mntmHyperbola;
	private JMenuItem mntmParabola;
	private JMenuItem mntmTanx;
	private JMenuItem mntmMorletWavelet;
	private JMenuItem mntmDeltaFunction;
	private JMenuItem mntmShahx;
	private JMenuItem mntmGaussModifiedMixed;
	private JMenuItem mntmDropout;
	private JMenuItem mntmCantor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frmPlotGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm() {
		initialize();
		mntmSin2piF.doClick();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPlotGenerator = new JFrame();
		frmPlotGenerator.setAlwaysOnTop(true);
		frmPlotGenerator.setTitle("Plot Generator");
		frmPlotGenerator.setBackground(new Color(204, 255, 255));
		frmPlotGenerator.getContentPane().setBackground(
				new Color(255, 255, 204));
		frmPlotGenerator.setBounds(400, 400, 390, 298);
		frmPlotGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		makeMenus();
		frmPlotGenerator.getContentPane().setLayout(null);
		JButton btnPlot = new JButton("Plot");
		btnPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					xMin = Signal.compileExpression(1, textXmin.getText());
					xMax = Signal.compileExpression(1, textXmax.getText());
					String sCount = (String) spinner.getValue();
					int count = Integer.parseInt(sCount);
					textIncrement.setText(Double.toString(Signal
							.dxFromCountRange(xMax, xMin, count)));

					frmPlotGenerator.invalidate();
					frmPlotGenerator.repaint();
					function = textFx.getText().toLowerCase();
					Signal signal = null;
					if (function.contains("example")) {
						double[][] xy = null;
						String test = function.trim();
//						switch (test) {
//						case "example delta":
//							xy = Examples.delta(xMin, xMax, count);
//							function = "delta";
//							break;
//						case "example shah":
//							xy = Examples.deltaComb(xMin, xMax, count, 128);
//							function = "shah";
//							break;
//						case "example gaussmodifiedsin":
//							xy = Examples.gaussModSin(xMin, xMax, count);
//							function = "Gaussian mod sinusoids";
//							break;
//						case "example dropoutdetection":
//							xy = Examples.signalDropout();
//							function = "mixed with dropout";
//							break;
//						case "example cantor":
//							xy = Examples.cantor(xMin, xMax, count);
//							function = "cantor";
//							break;
//						case "example random":
//							xy = Examples.random(xMin, xMax, count);
//							function = "random";
//							break;
//						}
						signal = new Signal(xy);
					} else {
						signal = new Signal(xMin, xMax, count, function,
								Signal.RangeOption.MinMax);
					}
					signal.plot(Color.blue, 2);
					signal.createTable();

				} catch (CompilationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPlot.setBounds(9, 173, 108, 31);
		frmPlotGenerator.getContentPane().add(btnPlot);

		textFx = new JTextField();
		textFx.setText("x");
		textFx.setBounds(9, 27, 337, 19);
		frmPlotGenerator.getContentPane().add(textFx);
		textFx.setColumns(10);

		textXmin = new JTextField();
		textXmin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					xMin = Signal.compileExpression(1, textXmin.getText());
					String sCount = (String) spinner.getValue();
					int count = Integer.parseInt(sCount);
					textIncrement.setText(Double.toString(Signal
							.dxFromCountRange(xMax, xMin, count)));
					textIncrement.invalidate();

				} catch (CompilationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		textXmin.setText("0");
		textXmin.setBounds(95, 52, 251, 19);
		frmPlotGenerator.getContentPane().add(textXmin);
		textXmin.setColumns(10);

		textXmax = new JTextField();
		textXmax.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					xMax = Signal.compileExpression(1, textXmax.getText());
					String sCount = (String) spinner.getValue();
					int count = Integer.parseInt(sCount);
					textIncrement.setText(Double.toString(Signal
							.dxFromCountRange(xMax, xMin, count)));
					textIncrement.invalidate();
				} catch (CompilationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		textXmax.setText("16");
		textXmax.setColumns(10);
		textXmax.setBounds(95, 83, 251, 19);
		frmPlotGenerator.getContentPane().add(textXmax);

		textIncrement = new JTextField();
		textIncrement.setText("1");
		textIncrement.setEditable(false);
		textIncrement.setColumns(10);
		textIncrement.setBounds(95, 142, 251, 19);
		frmPlotGenerator.getContentPane().add(textIncrement);

		JLabel lblSamples = new JLabel("# samples");
		lblSamples.setBounds(9, 114, 81, 19);
		frmPlotGenerator.getContentPane().add(lblSamples);

		lblFrom = new JLabel("From:");
		lblFrom.setBounds(7, 46, 70, 31);
		frmPlotGenerator.getContentPane().add(lblFrom);

		lblTo = new JLabel("To:");
		lblTo.setBounds(9, 74, 70, 31);
		frmPlotGenerator.getContentPane().add(lblTo);

		lblIncrement = new JLabel("Increment:");
		lblIncrement.setBounds(9, 135, 87, 31);
		frmPlotGenerator.getContentPane().add(lblIncrement);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] { "4", "8", "16",
				"32", "64", "128", "256", "512", "1024", "2048", "4096",
				"8192", "16384", "32768", "65536" }));
		spinner.setValue("1024");
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String sCount = (String) spinner.getValue();
				int count = Integer.parseInt(sCount);
				textIncrement.setText(Double.toString(Signal.dxFromCountRange(
						xMax, xMin, count)));
				textIncrement.invalidate();
			}
		});
		spinner.setBounds(95, 114, 63, 20);
		frmPlotGenerator.getContentPane().add(spinner);

		JLabel lblNewLabel = new JLabel("Expression");
		lblNewLabel.setBounds(9, 12, 108, 15);
		frmPlotGenerator.getContentPane().add(lblNewLabel);
	}

	private void makeMenus() {
		JMenuBar menuBar = new JMenuBar();
		frmPlotGenerator.setJMenuBar(menuBar);
		JMenu mnCartesianMenu = new JMenu("Try Examples");
		menuBar.add(mnCartesianMenu);

		mntmSin2piF = new JMenuItem("Sin(2pi3x)");
		mntmSin2piF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				function = "sin(2*pi*3*x)";
				xMin = 0;
				xMax = Math.PI;

				SetTextFields(arg0);
			}
		});

		JMenuItem mntmLineX = new JMenuItem("Line: 2x + 1");
		mntmLineX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "2*x + 1";
				xMin = -20;
				xMax = 50;
				SetTextFields(e);
			}
		});
		mnCartesianMenu.add(mntmLineX);

		mntmDeltaFunction = new JMenuItem("delta function");
		mntmDeltaFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "example delta";
				xMin = -512;
				xMax = 511;
				SetTextFields(e);
			}
		});
		mnCartesianMenu.add(mntmDeltaFunction);

		mntmShahx = new JMenuItem("Dirac comb, T = 128");
		mntmShahx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "example Shah";
				xMin = -512;
				xMax = 511;
				SetTextFields(e);
			}
		});
		mnCartesianMenu.add(mntmShahx);
		mnCartesianMenu.add(mntmSin2piF);

		mntmHyperbola = new JMenuItem("Hyperbola");
		mntmHyperbola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				function = "4/x";
				xMin = -10;
				xMax = 10;
				SetTextFields(arg0);
			}
		});

		mntmGaussModifiedMixed = new JMenuItem("Gauss modified mixed Sin");
		mntmGaussModifiedMixed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "example GaussModifiedSin";
				xMin = -512;
				xMax = 511;
				SetTextFields(e);
			}
		});
		mnCartesianMenu.add(mntmGaussModifiedMixed);
		mnCartesianMenu.add(mntmHyperbola);

		mntmParabola = new JMenuItem("Parabola");
		mntmParabola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				function = "4*pow(x,2)";
				xMin = -5;
				xMax = 5;
				SetTextFields(arg0);
			}
		});
		mnCartesianMenu.add(mntmParabola);

		mntmTanx = new JMenuItem("Tan(x)");
		mntmTanx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				function = "tan(x)";
				xMin = -2.1 * Math.PI;
				xMax = 2.04 * Math.PI;
				SetTextFields(arg0);
			}
		});
		mnCartesianMenu.add(mntmTanx);

		mntmMorletWavelet = new JMenuItem("Morlet (real part)");
		mntmMorletWavelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				function = "1/((pow(pi,0.25)))*cos(6*x)*exp(-(x*x)/2)";
				xMin = -3 * Math.PI;
				xMax = 3 * Math.PI;
				SetTextFields(arg0);
			}
		});
		mnCartesianMenu.add(mntmMorletWavelet);

		mntmDropout = new JMenuItem("Dropout in signal");
		mntmDropout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "example dropoutDetection";
				xMin = 0;
				xMax = 40;
				SetTextFields(e);
			}
		});
		mnCartesianMenu.add(mntmDropout);

		mntmCantor = new JMenuItem("Cantor");
		mntmCantor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "example cantor";
				xMin = 0;
				xMax = 1;
				SetTextFields(e);
			}
		});
		mnCartesianMenu.add(mntmCantor);

		JMenuItem mntmRandom = new JMenuItem("Random");
		mntmRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "example random";
				xMin = 0;
				xMax = 1;
				SetTextFields(e);
			}
		});
		mnCartesianMenu.add(mntmRandom);

		JMenuItem mntmTestFiltersrequires = new JMenuItem(
				"Test filters (requires csv)");
		mntmTestFiltersrequires.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DWT.relativeErrors();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCartesianMenu.add(mntmTestFiltersrequires);

		btnEmptyTable = new JButton("Make Empty Table");
		menuBar.add(btnEmptyTable);
		btnEmptyTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[][] xy = new double[2][2];
				Signal blank = new Signal(xy, "New");
				blank.createTable();
			}
		});
	}

	/**
	 * Place menu choices in text boxes on the form when a menu example is
	 * chosen.
	 * 
	 * @param arg0
	 *            event passed from menu item action handler
	 */
	private void SetTextFields(ActionEvent arg0) {
		textFx.setText(function);
		textXmin.setText(String.valueOf(xMin));
		textXmax.setText(String.valueOf(xMax));
		String sCount = (String) spinner.getValue();
		int count = Integer.parseInt(sCount);
		textIncrement.setText(Double.toString((xMax - xMin + 1) / (count)));
		JMenuItem eventFrom = (JMenuItem) arg0.getSource();
		label = eventFrom.getText();
	}
}
