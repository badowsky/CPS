package Transformation;
/**
 * Copyright 2014 Mark Bishop This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details: http://www.gnu.org/licenses
 * 
 * The author makes no warranty for the accuracy, completeness, safety, or
 * usefulness of any information provided and does not represent that its use
 * would not infringe privately owned right.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Class responsibility: Contain a matrix/vector data object.
 * 
 * An instance of this class has a data object (double[][], double[], or int[]).
 * It presents the object in a JTable on a JFrame. The data may be edited,
 * saved, or accessed externally. The menus include handlers for file
 * operations, discrete wavelet transforms, and plotting.
 *
 */

public final class MatrixForm {
	private static int instanceCounter = 0;
	private String name = "";
	private int index = -1; // not used in demo.
	private JFrame frame;
	private Object dataObject;
	private Object[][] rows = null;
	private Object[] columns = null;
	private JTable table;
	private DefaultTableModel model;
	private String lastDirectory = System.getProperty("user.dir");

	public MatrixForm(Object dataObject, String name, int index) {
		this.dataObject = dataObject;
		this.name = name;
		this.index = index;
		setForm(dataObject);
		instanceCounter++;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public String toDataString(Object data) {
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

	public void closeDispose() {
		frame.setVisible(false);
		dataObject = null;
		rows = null;
		columns = null;
		table = null;
		model = null;
		frame.dispose();
	}

	private void setForm(Object data) {
		frame = new JFrame();
		frame.setBounds(20, 120, 460, 319);
		frame.setLocation(instanceCounter * 10 + 20, instanceCounter * 25 + 100);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setTitle(name);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		makeMenus();
		Container content = frame.getContentPane();
		table = getTable((Object) data);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		content.add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	private void makeMenus() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenuItem mntmUpdate = new JMenuItem("Update Changes");
		mntmUpdate.setBackground(new Color(144, 238, 144));
		mntmUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		mntmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.isEditing()) {
					table.getCellEditor().stopCellEditing();
				}
				int m = table.getModel().getRowCount();
				int n = table.getModel().getColumnCount();
				if (dataObject instanceof double[][]) {
					double[][] A = new double[m][n];
					for (int i = 0; i < m; i++) {
						for (int j = 0; j < n; j++) {
							String d = table.getModel().getValueAt(i, j)
									.toString();
							A[i][j] = Double.valueOf(d);
						}
					}
					dataObject = A;
				} else if (dataObject instanceof double[]) {
					double[] v = new double[m];
					for (int i = 0; i < m; i++) {
						String d = table.getModel().getValueAt(i, 0).toString();
						v[i] = Double.valueOf(d);
					}
					dataObject = v;
				} else if (dataObject instanceof int[]) {
					int[] v = new int[m];
					for (int i = 0; i < m; i++) {
						String d = table.getModel().getValueAt(i, 0).toString();
						v[i] = Integer.valueOf(d);
					}
					dataObject = v;
				}
			}
		});

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fileSpec = FileOps.openDialog(lastDirectory);
				if (fileSpec != null) {
					lastDirectory = fileSpec.getPath();
					Object newData = null;
					try {
						newData = FileOps.openMatrix(fileSpec);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					new MatrixForm(newData, fileSpec.getName(), 0);
				}
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File fileSpec = FileOps.saveDialog(lastDirectory);
					if (fileSpec != null) {
						// DWT application specific extension is added here:
						String ext;
						ext = fileSpec.getCanonicalPath() + ".csv";
						fileSpec = new File(ext);
						FileOps.saveString(toDataString(dataObject), fileSpec);
						lastDirectory = fileSpec.getPath();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnFile.add(mntmSave);

		JMenu mnTransforms = new JMenu("Transforms");
		menuBar.add(mnTransforms);

		JMenuItem mntmDiforwardscrete = new JMenuItem("Forward DWT");
		mntmDiforwardscrete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDWT(DWT.Direction.forward);
			}
		});
		mnTransforms.add(mntmDiforwardscrete);

		JMenuItem mntmInverseDwt = new JMenuItem("Inverse DWT");
		mntmInverseDwt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDWT(DWT.Direction.reverse);
			}
		});
		mnTransforms.add(mntmInverseDwt);

		JMenuItem mntmDaubechies_1 = new JMenuItem("Daubechies");
		mntmDaubechies_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDWT(DWT.Direction.reverse);
			}
		});

		JMenuItem mntmMulti = new JMenuItem("Multi-resolution");
		mntmMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doMultiResolution();
			}
		});
		mnTransforms.add(mntmMulti);

		JMenuItem mntmPlot = new JMenuItem("Plot");
		mntmPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataObject instanceof double[][]) {
					Signal signal = new Signal(MatrixOps
							.transpose((double[][]) dataObject));
					signal.plot(Color.blue, 1);
				}
			}
		});
		menuBar.add(mntmPlot);
		menuBar.add(mntmUpdate);
	}

	private JTable getTable(Object data) {
		if (dataObject instanceof double[][]) {
			double[][] A = (double[][]) data;
			rows = toObject(A);
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
		for (int i = 0; i < columns.length; i++) {
			columns[i] = i;
		}
		model = new DefaultTableModel(rows, columns);
		JTable table = new JTable(model);
		table.getModel().addTableModelListener(table);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		model.fireTableDataChanged();
		return table;
	}

	private static Object[][] toObject(double[][] A) {
		Double[][] object = new Double[A.length][A[0].length];
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

	private void doDWT(DWT.Direction direction) {
		if (dataObject instanceof double[][]) {
			double[][] xy = (double[][]) dataObject;
			double[][] signal = MatrixOps.transpose(xy);
			signal = DWT.padPow2(signal);
			ParameterInput paramInput = new ParameterInput(signal[1].length);
			paramInput.setVisible(true);
			int parameter = paramInput.waveletParameter;
			int scale = paramInput.coarsestScale;
			Wavelet wavelet = paramInput.wavelet;
			double[] wT = null;
			try {
				wT = DWT.transform(signal[1], wavelet, parameter, scale,
						direction);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			double[][] dwt = new double[2][];
			dwt[1] = wT;
			dwt[0] = signal[0];
			StringBuilder sb = new StringBuilder();
			sb.append(direction + " DWT ");
			sb.append(wavelet + " ");
			sb.append(parameter + " " + scale + " " + name);
			Signal transform = new Signal(dwt, sb.toString());
			transform.plot(Color.RED, 3);
			transform.createTable();
		}
	}

	private void doMultiResolution() {
		double[][] xy = (double[][]) dataObject;
		double[][] signal = MatrixOps.transpose(xy);
		signal = DWT.padPow2(signal);
		ParameterInput paramInput = new ParameterInput(signal[1].length);
		paramInput.setVisible(true);
		int parameter = paramInput.waveletParameter;
		int coarsestScale = paramInput.coarsestScale;
		Wavelet wavelet = paramInput.wavelet;
		try {
			ArrayList<Object> multResol = DWT.mRA(signal[1], wavelet,
					parameter, coarsestScale);
			@SuppressWarnings("unchecked")
			VisualizeMultiResolution mRA = new VisualizeMultiResolution(
					(ArrayList<double[]>) multResol.get(0),
					(int[]) multResol.get(1), signal[0]);
			mRA.presentTable();
			mRA.plotMRA();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
