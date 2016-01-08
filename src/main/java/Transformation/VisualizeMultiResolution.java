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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Class responsibility: provide a graphical display of simultaneous scale plots
 * for multi-resolution analysis results.
 */

public class VisualizeMultiResolution {
	double[][] mRAMatrix;
	double[] xAxis;
	private String formTitle = "Multi-resolution analysis";
	private String[] plotLabels;
	private JFrame frame;
	private Object dataObject;
	private Object[][] rows = null;
	private Object[] columns = null;
	private JTable table;
	private DefaultTableModel model;
	private static int instanceCounter = 0;
	int[] scaleIDs; // integers j such that scales = 2^-j

	/**
	 * 
	 * @param mraArrayList
	 *            An ArrayList<double[]> holding multi-resolution scale data
	 *            from finest scale to coarsest scale.
	 * @param scaleIDs
	 *            An array of j values corresponding to the scales used to
	 *            create the data such that: scale = 2^-j
	 * @param xAxis
	 *            the range sequence to be used for plotting
	 */
	public VisualizeMultiResolution(ArrayList<double[]> mraArrayList,
			int[] scaleIDs, double[] xAxis) {
		this.scaleIDs = scaleIDs;
		this.xAxis = xAxis;
		mRAMatrix = toMatrix(mraArrayList);
		dataObject = (Object) mRAMatrix;
	}

	/**
	 * Show the column matrix containing the scales, the row sum of which should
	 * reconstruct the original signal.
	 */
	public void presentTable() {
		frame = new JFrame();
		frame.setBounds(20, 120, 460, 319);
		frame.setLocation(instanceCounter * 10 + 20, instanceCounter * 25 + 100);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setTitle(formTitle);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fileSpec = FileOps.saveDialog("");
				FileOps.saveString(toDataString(dataObject), fileSpec);
			}
		});
		mnFile.add(mntmSave);
		Container content = frame.getContentPane();
		table = getTable((Object) dataObject);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		content.add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
		table.invalidate();
	}

	/**
	 * Plot the scales on the same graph and enable selective visibility of each
	 * from the Plots menu.
	 */
	public void plotMRA() {
		int n = scaleIDs.length;
		plotLabels = new String[n];
		double[][] columnVectors = MatrixOps.transpose(mRAMatrix);
		double[][][] scaleSets = new double[n][][];
		for (int i = 0; i < n; i++) {
			scaleSets[i] = new double[2][];
			scaleSets[i][0] = xAxis;
			scaleSets[i][1] = columnVectors[i];
			plotLabels[i] = "2^-" + Integer.toString(scaleIDs[i]);
		}
		plotLabels[n - 1] = "frequencies below L = " + scaleIDs[n - 2];
		Color[] color = new Color[n];
		int i = 0;
		while (i < n) {
			for (Colors clr : Colors.values()) {
				color[i] = clr.getColor();
				i += 1;
				if (i == n) {
					break;
				}
			}
		}
		PlotFrame pf = new PlotFrame(600, 600, 16);
		pf.formTitle = formTitle;
		pf.addMultiplePlots(scaleSets, color, plotLabels);
	}

	private String toDataString(Object data) {
		if (dataObject instanceof double[][]) {
			return StringUtils.toCsv((double[][]) data);
		} else if (dataObject instanceof double[]) {
			return StringUtils.toCsv((double[]) data);
		} else if (dataObject instanceof int[]) {
			return StringUtils.toCsv((int[]) data);
		} else {
			return "toString is not implemented for the data type.";
		}
	}

	private double[][] toMatrix(ArrayList<double[]> columnVectors) {
		int m = columnVectors.get(0).length;
		int n = columnVectors.size();
		double[][] matrix = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = columnVectors.get(j)[i];
			}
		}
		return matrix;
	}

	private JTable getTable(Object data) {
		if (dataObject instanceof double[][]) {
			double[][] A = (double[][]) data;
			rows = toObject(A, scaleIDs);
			columns = new Object[A[0].length];
		} else if (dataObject instanceof double[]) {
			double[] A = (double[]) data;
			rows = toObject(A);
			columns = new Object[1];
		} else if (dataObject instanceof int[]) {
			int[] A = (int[]) data;
			rows = toObject(A);
			columns = new Object[1];
		}

		model = new DefaultTableModel(rows, columns);
		JTable table = new JTable(model);
		JTableHeader header = table.getTableHeader();
		TableColumnModel tcm = header.getColumnModel();

		for (int i = 0; i < scaleIDs.length - 1; i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue("2^-" + Integer.toString(scaleIDs[i]));
		}
		TableColumn tc = tcm.getColumn(scaleIDs.length - 1);
		tc.setHeaderValue("> L");
		table.getModel().addTableModelListener(table);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		model.fireTableDataChanged();
		return table;
	}

	private static Object[][] toObject(double[][] A, int[] scales) {
		Double[][] object = new Double[A.length + 1][A[0].length];

		for (int i = 0; i < A.length; ++i) {
			object[i] = new Double[A[i].length];
			for (int j = 0; j < A[i].length; ++j) {
				object[i][j] = new Double(A[i][j]);
			}
		}
		return object;
	}

	private static Object[][] toObject(double[] v) {
		Double[][] object = new Double[v.length][1];
		for (int i = 0; i < v.length; ++i) {
			object[i] = new Double[1];
			object[i][0] = new Double(v[i]);
		}
		return object;
	}

	private static Object[][] toObject(int[] v) {
		Integer[][] object = new Integer[v.length][1];
		for (int i = 0; i < v.length; ++i) {
			object[i] = new Integer[1];
			object[i][0] = new Integer(v[i]);
		}
		return object;
	}
}
