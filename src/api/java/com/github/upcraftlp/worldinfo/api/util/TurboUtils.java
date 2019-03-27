package com.github.upcraftlp.worldinfo.api.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import net.minecraft.client.resources.I18n;

public class TurboUtils {

	public static double maxOr(double defaultVal, double... n) {
		if (n.length == 0)
			return defaultVal;
		double max = n[0];
		for (int i = 1; i < n.length; i++) {
			max = Math.max(max, n[i]);
		}
		return max;
	}

	public static String boolToString(boolean bool) {
		return bool ?
				I18n.format("worldinfo.info.on") :
				I18n.format("worldinfo.info.off");
	}

	public static String round(Number r) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(r);
	}

	public static String toUpper(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

}
