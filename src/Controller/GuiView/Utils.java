package Controller.GuiView;

import Model.Signals.MiaryPodobienstwa;
import Model.Signals.Discrete.SygnalDyskretnyReal;
import View.ComparsionMeasuresPanel;

public class Utils {
	public static void calculateComparsionMeasures(SygnalDyskretnyReal sygPierwszy, SygnalDyskretnyReal sygDrugi, ComparsionMeasuresPanel panel){
		panel.setMSE(MiaryPodobienstwa.mse(sygPierwszy, sygDrugi));
		panel.setSNR(MiaryPodobienstwa.snr(sygPierwszy, sygDrugi));
		panel.setPSNR(MiaryPodobienstwa.psnr(sygPierwszy, sygDrugi));
		panel.setMD(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
		panel.setENOB(MiaryPodobienstwa.md(sygPierwszy, sygDrugi));
	}
}
