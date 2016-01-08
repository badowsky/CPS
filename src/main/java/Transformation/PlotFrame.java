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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 * Class responsibility: provide a graphical display for x-y plots.
 */
public class PlotFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	// Public members
	public static int instanceCounter = 0;
	public int plotWidth = 600;
	public int plotHeight = 600;
	public Color pointColor = Color.red;
	public Color[] colors;
	public int pointSize = 2;
	public boolean drawLines = true;
	// Function parameters
	public String[] plotLabels;
	public String formTitle;
	public double[][][] plots;
	private double xMin, xMax, yMin, yMax;
	private double initXMin, initXMax, initYMin, initYMax; // allow unzoom
	private double xRange, yRange;
	// Image parameters
	private ImagePanel centerPanel;
	private double xGridInterval, yGridInterval;
	private int gridCount;
	private boolean isFirstView = true;
	private boolean isFirstPoint = true; // plot no line to first point
	private int lastx, lasty; // pixel coordinates of previous point plotted
	private Image offScreenImage = null; // image drawn
	private Graphics offScreenGraphics; // graphics context for off-screen image
	private BufferedImage pix = null;
	private JTextField textField;
	// exposed mouse parameters
	private float mouseDownX, mouseDownY;
	private Point clickPoint;
	private boolean isDrag = false;
	private boolean isMouseBtn1 = false;
	// private Rectangle selectionBounds; //disabled drag frame feature
	// exposed menu controls
	JMenuBar menuBar;
	JCheckBox[] cbxShowPlot;
	private JMenu mnVisiblePlots;
	private JCheckBox chckbxDrawLines;
	private JMenuItem mntmZoomInX;
	private JMenuItem mntmZoomOutX;
	private JMenuItem mntmZoomInY;
	private JMenuItem mntmZoomOutY;

	/**
	 * @param plotHeight
	 * @param plotWidth
	 * @param gridCount
	 */
	public PlotFrame(int plotHeight, int plotWidth, int gridCount) {
		instanceCounter += 1;
		this.plotHeight = plotHeight;
		this.plotWidth = plotWidth;
		this.gridCount = gridCount;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setPreferredSize(
				new Dimension(plotWidth, plotHeight));
		this.pack();
		this.setVisible(true);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (e.getComponent().getClass().getName() == "PlotFrame") {
					Component c = (Component) e.getSource();
					PlotFrame pf = (PlotFrame) c;
					setPlotWidth(pf.centerPanel.getWidth());
					setPlotHeight(pf.centerPanel.getHeight());
					reSize();
				}
			}
		});
		offScreenImage = createImage(plotWidth, plotHeight);
		offScreenGraphics = offScreenImage.getGraphics();
		centerPanel = new ImagePanel(offScreenImage);
		setContentPane(centerPanel);
		setMenus();
		setMouseEvents();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param color
	 * @param label
	 *            This will label a check box in the menu for showing or hiding
	 *            the plot.
	 */
	public synchronized void addSinglePlot(double[] x, double[] y, Color color,
			String label) {
		this.plotLabels = new String[] { label };
		this.formTitle = label;
		this.setTitle(formTitle);
		double[][][] plot = new double[1][2][];
		plot[0][0] = x;
		plot[0][1] = y;
		Color[] colors = new Color[] { color };
		addMultiplePlots(plot, colors, plotLabels);
	}

	/**
	 * Simultaneous plots
	 * 
	 * @param plots
	 *            An array of double[number of plots][2][], where plots[][i][0]
	 *            are x values and plots[][i][1] are y values.
	 * @param colors
	 * @param labels
	 *            This will label multiple check boxes in the menu for showing
	 *            or hiding individual plots.
	 */
	public synchronized void addMultiplePlots(double[][][] plots,
			Color[] colors, String[] labels) {
		this.plotLabels = labels;
		this.plots = plots;
		this.colors = colors;
		int numPlots = plots.length;
		this.setTitle(formTitle);
		if (isFirstView) {
			// When called for the first time, this method must initialize a few
			// things.
			xMin = min(plots, 0);
			initXMin = xMin;
			xMax = max(plots, 0);
			initXMax = xMax;
			yMin = min(plots, 1);
			initYMin = yMin;
			yMax = max(plots, 1);
			initYMax = yMax;
			xRange = xMax - xMin;
			yRange = yMax - yMin;
			xGridInterval = calcStepSize((float) xRange, gridCount);
			yGridInterval = calcStepSize((float) yRange, gridCount);

			clearForDraw();
			cbxShowPlot = new JCheckBox[numPlots];
			for (int p = 0; p < numPlots; p++) {
				cbxShowPlot[p] = new JCheckBox(plotLabels[p]);
				cbxShowPlot[p].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						subSample(xMin, xMax, yMin, yMax);
					}
				});
				cbxShowPlot[p].setSelected(true);
			}
			createPlotsMenuItems();
			isFirstView = false;
		}

		for (int p = 0; p < numPlots; p++) {
			if (cbxShowPlot[p].isSelected()) {
				offScreenGraphics.setColor(colors[p]);
				SetPointColorSize(colors[p], pointSize);
				isFirstPoint = true;
				int n = plots[p][0].length;
				for (int i = 0; i < n; i++) {
					int pixelx = (int) Math.round(plotWidth
							* (plots[p][0][i] - xMin) / xRange);
					int pixely = (int) Math.round(plotHeight
							* (yMax - plots[p][1][i]) / yRange);

					int offset = (int) (pointSize / 2.0);
					offScreenGraphics.drawImage(pix, pixelx - offset, pixely
							- offset, pointSize, pointSize, null);
					if (drawLines && !isFirstPoint) {

						offScreenGraphics
								.drawLine(lastx, lasty, pixelx, pixely);
					}
					lastx = pixelx;
					lasty = pixely;
					isFirstPoint = false;
				}
			}
		}
		repaint();
	}

	private void setMenus() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = FileOps.saveDialog(null);
				String fpath = null;
				try {
					fpath = f.getCanonicalPath() + ".png";
					f = new File(fpath);
					FileOps.saveImage(offScreenImage, f);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnFile.add(mntmSave);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		chckbxDrawLines = new JCheckBox("Draw Lines");
		chckbxDrawLines.setSelected(true);
		chckbxDrawLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxDrawLines.isSelected()) {
					drawLines = true;
				} else {
					drawLines = false;
				}
				zoom(1, 1);
			}
		});
		mnView.add(chckbxDrawLines);

		JMenuItem mntmFullUnzoom = new JMenuItem("Full unzoom");
		mntmFullUnzoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				subSample(initXMin, initXMax, initYMin, initYMax);
				zoom(1, 1);
			}
		});
		mnView.add(mntmFullUnzoom);

		mntmZoomInX = new JMenuItem("Zoom in X");
		mntmZoomInX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoom(2, 1);
			}
		});
		mnView.add(mntmZoomInX);

		mntmZoomOutX = new JMenuItem("Zoom out X");
		mntmZoomOutX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoom(0.5f, 1);
			}
		});
		mnView.add(mntmZoomOutX);

		mntmZoomInY = new JMenuItem("Zoom in Y");
		mntmZoomInY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoom(1, 2);
			}
		});
		mnView.add(mntmZoomInY);

		mntmZoomOutY = new JMenuItem("Zoom out Y");
		mntmZoomOutY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoom(1, 0.5f);
			}
		});
		mnView.add(mntmZoomOutY);

		mnVisiblePlots = new JMenu("Plots");
		menuBar.add(mnVisiblePlots);

		textField = new JTextField();
		textField.setToolTipText("Click coordinates\n");
		textField.setText("Coordinates");
		menuBar.add(textField);
	}

	private void createPlotsMenuItems() {
		for (int i = 0; i < cbxShowPlot.length; i++) {
			mnVisiblePlots.add(cbxShowPlot[i]);
		}
	}

	private void setPlotWidth(int width) {
		this.plotWidth = width;
	}

	private void setPlotHeight(int height) {
		this.plotHeight = height;
	}

	private void setMouseEvents() {
		centerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					isMouseBtn1 = true;
					float x = (float) e.getX();
					float y = (float) e.getY();
					float pW = (float) plotWidth;
					float pH = (float) plotHeight;
					float valX = (float) ((x * xRange) / pW + xMin);
					float valY = (float) ((-1.0 * y * yRange) / pH + yMax);
					mouseDownX = valX;
					mouseDownY = valY;
					String patternStd = "#.##"; // or: pattern = "0.###E0" ,
												// etc.
					String patternSci = "0.##E0";
					NumberFormat nfx = new DecimalFormat(patternStd);
					NumberFormat nfy = nfx;
					if (Math.abs(valX) < 0.01 || Math.abs(valX) > 1000) {
						nfx = new DecimalFormat(patternSci);
					}
					if (Math.abs(valY) < 0.01 || Math.abs(valY) > 1000) {
						nfy = new DecimalFormat(patternSci);
					}
					textField.setText(nfx.format(valX) + ", "
							+ nfy.format(valY));
					clickPoint = e.getPoint();
				}
				// selectionBounds = null;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (isDrag && e.getButton() == MouseEvent.BUTTON1) {
					float x = (float) e.getX();
					float y = (float) e.getY();
					if (Math.abs(clickPoint.x - e.getX()) > 20
							&& Math.abs(clickPoint.y - e.getY()) > 20) {
						float pW = (float) plotWidth;
						float pH = (float) plotHeight;
						float valX = (float) ((x * xRange) / pW + xMin);
						float valY = (float) ((-1.0 * y * yRange) / pH + yMax);
						subSample(Math.min(mouseDownX, valX),
								Math.max(mouseDownX, valX),
								Math.min(mouseDownY, valY),
								Math.max(mouseDownY, valY));
						isDrag = false;
						isMouseBtn1 = false;
						zoom(1, 1);
					}
				}
				clickPoint = null;
			}
		});
		centerPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				if (Math.abs(clickPoint.x - e.getX()) > 20
						&& Math.abs(clickPoint.y - e.getY()) > 20) {
					if (isMouseBtn1) {
						isDrag = true;
						// This can be uncommented for a drag frame, but it
						// slows things down.
						// Point dragPoint = e.getPoint();
						// int x = Math.min(clickPoint.x, dragPoint.x);
						// int y = Math.min(clickPoint.y, dragPoint.y);
						// int width = Math.max(clickPoint.x - dragPoint.x,
						// dragPoint.x - clickPoint.x);
						// int height = Math.max(clickPoint.y - dragPoint.y,
						// dragPoint.y - clickPoint.y);
						// selectionBounds = new Rectangle(x, y, width, height);
						// repaint();
					}
				}
			}
		});
	}

	private void reSize() {
		offScreenImage.flush();
		offScreenImage = createImage(plotWidth, plotHeight);
		offScreenGraphics = offScreenImage.getGraphics();
		centerPanel = new ImagePanel(offScreenImage);
		setContentPane(centerPanel);
		setMouseEvents();
		zoom(1, 1);
	}

	private synchronized void zoom(float factorX, float factorY) {
		clearForDraw();
		xMin = xMin / factorX;
		xMax = xMax / factorX;
		yMin = yMin / factorY;
		yMax = yMax / factorY;
		xRange = xMax - xMin;
		yRange = yMax - yMin;
		addMultiplePlots(plots, colors, plotLabels);
		repaint();
		menuBar.revalidate();
	}

	private synchronized void subSample(double xMin, double xMax, double yMin,
			double yMax) {
		clearForDraw();
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		xRange = xMax - xMin;
		yRange = yMax - yMin;
		addMultiplePlots(plots, colors, plotLabels);
	}

	/** Clears the plot and draws the axes and grid lines. */
	private synchronized void clearForDraw() {
		offScreenGraphics.setColor(Color.white);
		offScreenGraphics.fillRect(0, 0, plotWidth, plotHeight);
		offScreenGraphics.setColor(Color.lightGray);// Grid color.
		int gridPixel;

		double gridX = Math.ceil(xMin / xGridInterval) * xGridInterval;
		while (gridX <= xMax) {
			gridPixel = (int) Math.round(plotWidth * (gridX - xMin) / xRange);
			offScreenGraphics.drawLine(gridPixel, 0, gridPixel, plotHeight);
			gridX += xGridInterval;
		}

		double gridY = Math.ceil(yMin / yGridInterval) * yGridInterval;
		while (gridY <= yMax) {
			gridPixel = (int) Math.round(plotHeight * (yMax - gridY) / yRange);
			offScreenGraphics.drawLine(0, gridPixel, plotWidth, gridPixel);
			gridY += yGridInterval;
		}

		int xZero = (int) Math.round(-xMin * plotWidth / xRange);
		int yZero = (int) Math.round(yMax * plotHeight / yRange);
		offScreenGraphics.setColor(Color.black);
		offScreenGraphics.drawLine(xZero, 0, xZero, plotHeight);
		offScreenGraphics.drawLine(0, yZero, plotWidth, yZero);
		isFirstPoint = true;
		repaint();
	}

	private void SetPointColorSize(Color clr, int size) {
		pointColor = clr;
		pointSize = size;
		pix = new BufferedImage(pointSize, pointSize,
				BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < pointSize; i++) {
			for (int j = 0; j < pointSize; j++) {
				pix.setRGB(i, j, clr.getRGB());
			}
		}
	}

	private static double min(double[] A) {
		int n = A.length;
		double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i < n; i++) {
			if (A[i] < min) {
				min = A[i];
			}
		}
		return min;
	}

	private static double max(double[] A) {
		int n = A.length;
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < n; i++) {
			if (A[i] > max) {
				max = A[i];
			}
		}
		return max;
	}

	/**
	 * 
	 * @param plots
	 *            p sets of xy data as a double[p][index][] array where, for p
	 *            sets, plots[0:p-1][0] = x and plots[0:p-1][1] = y
	 * @param index
	 *            0 for x and 1 for y
	 * @return minimum of all x or y
	 */
	private static double min(double[][][] plots, int index) {
		int p = plots.length;
		double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i < p; i++) {
			double[] val = plots[i][index];
			double temp = min(val);
			if (temp < min) {
				min = temp;
			}
		}
		return min;
	}

	/**
	 * 
	 * @param plots
	 *            p sets of xy data as a double[p][index][] array where, for p
	 *            sets, plots[0:p-1][0] = x and plots[0:p-1][1] = y
	 * @param index
	 *            0 for x and 1 for y
	 * @return maximum of all x or y
	 */
	private static double max(double[][][] plots, int index) {
		int p = plots.length;
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < p; i++) {
			double[] val = plots[i][index];
			double temp = max(val);
			if (temp > max) {
				max = temp;
			}
		}
		return max;
	}

	/**
	 * Renders the graph using the offScreenImage
	 * 
	 */
	public synchronized void paint(Graphics g) {
		if (isDrag) {
			// // This can be uncommented for a drag frame, but it slows things
			// down.
			// Graphics2D g2d = (Graphics2D) g.create();
			// g2d.setColor(new Color(255, 255, 255, 128));
			//
			// Area fill = new Area(new Rectangle(new Point(0, 0), getSize()));
			// if (selectionBounds != null) {
			// fill.subtract(new Area(selectionBounds));
			// }
			// g2d.fill(fill);
			// if (selectionBounds != null) {
			// g2d.setColor(Color.BLACK);
			// g2d.draw(selectionBounds);
			// }
			// g2d.dispose();

			// menuBar.invalidate();
			// menuBar.repaint();
		} else {
			g.drawImage(offScreenImage, 0, 0, ImageObserver.WIDTH,
					ImageObserver.HEIGHT, this.centerPanel);
			centerPanel.invalidate();
			centerPanel.repaint();
		}
	}

	/** Override update to avoid redrawing background. */
	public void update(Graphics g) {
		paint(g);
	}

	private static float calcStepSize(float range, float targetSteps) {
		// calculate an initial guess at step size
		float tempStep = range / targetSteps;

		// get the magnitude of the step size
		float mag = (float) Math.floor(Math.log10(tempStep));
		float magPow = (float) Math.pow(10, mag);

		// calculate most significant digit of the new step size
		float magMsd = (int) (tempStep / magPow + 0.5);

		// promote the MSD to either 1, 2, or 5
		if (magMsd > 5.0)
			magMsd = 10.0f;
		else if (magMsd > 2.0)
			magMsd = 5.0f;
		else if (magMsd > 1.0)
			magMsd = 2.0f;

		return magMsd * magPow;
	}

}
