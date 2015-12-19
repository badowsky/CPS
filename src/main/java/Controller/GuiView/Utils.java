package Controller.GuiView;

import Model.Signals.ComparsionMeasures;
import Model.Signals.Discrete.DiscreteSignalReal;
import View.ComparsionMeasuresPanel;

public class Utils {
	public static void calculateComparsionMeasures(DiscreteSignalReal sygPierwszy, DiscreteSignalReal sygDrugi, ComparsionMeasuresPanel panel){
		panel.setMSE(ComparsionMeasures.mse(sygPierwszy, sygDrugi));
		panel.setSNR(ComparsionMeasures.snr(sygPierwszy, sygDrugi));
		panel.setPSNR(ComparsionMeasures.psnr(sygPierwszy, sygDrugi));
		panel.setMD(ComparsionMeasures.md(sygPierwszy, sygDrugi));
		panel.setENOB(ComparsionMeasures.md(sygPierwszy, sygDrugi));
	}
}
