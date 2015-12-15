package Helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Model.Signals.Discrete.DiscreteSignalReal;

public class IOUtils {

	public static DiscreteSignalReal LoadSignal(File file) {
		DiscreteSignalReal signal = null;
		BufferedReader in = null;
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> y = new ArrayList<Double>();
		String[] values;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			while ((line = in.readLine()) != null) {
				if (line == "## PARAMS ##") {
					while (in.readLine() != "## END ##") {
						System.out.println("£aduje parametry");
					}
				}
				values = line.split(",");
				x.add(Double.parseDouble(values[0]));
				y.add(Double.parseDouble(values[1]));
			}

			signal = new DiscreteSignalReal(x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return signal;
	}

	public static void SaveSignal(File file, DiscreteSignalReal signal){
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file));
			String line;
			Iterator<Double> xIterator = signal.iteratorX();
			Iterator<Double> yIterator = signal.iteratorY();
			while (xIterator.hasNext() && yIterator.hasNext()) {
				line = xIterator.next() + "," + yIterator.next();
				out.write(line);
				out.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
