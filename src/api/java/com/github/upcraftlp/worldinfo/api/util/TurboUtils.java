package com.github.upcraftlp.worldinfo.api.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.state.properties.ComparatorMode;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

	public static String boolToStringOnOff(boolean bool) {
		return bool ?
				I18n.format("worldinfo.info.on") :
				I18n.format("worldinfo.info.off");
	}

	public static String boolToStringYesNo(boolean bool) {
		return bool ?
				I18n.format("worldinfo.info.yes") :
				I18n.format("worldinfo.info.no");
	}

	public static String round(Number r) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(r);
	}

	public static String toUpper(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static String intToNote(int note) {
		if (note < 0 || note > 24)
			return I18n.format("worldinfo.info.unknown");
		String n;
		switch (note % 12) {
			case 0:
				n = "F#";
				break;
			case 1:
				n = "G";
				break;
			case 2:
				n = "G#";
				break;
			case 3:
				n = "A";
				break;
			case 4:
				n = "A#";
				break;
			case 5:
				n = "B";
				break;
			case 6:
				n = "C";
				break;
			case 7:
				n = "C#";
				break;
			case 8:
				n = "D";
				break;
			case 9:
				n = "D#";
				break;
			case 10:
				n = "E";
				break;
			case 11:
				n = "F";
				break;
			default:
				return I18n.format("worldinfo.info.unknown");
		}
		if (note < 12) {
			n += 3;
		} else if (note < 24) {
			n += 4;
		} else {
			n += 5;
		}
		return n;
	}

	public static String getTranslation(ComparatorMode mode) {
		switch (mode) {
			case COMPARE:
				return I18n.format("worldinfo.info.compare");
			case SUBTRACT:
				return I18n.format("worldinfo.info.subtract");
			default:
				return I18n.format("worldinfo.info.unknown");
		}
	}

	public static String getTranslation(NoteBlockInstrument instrument) {
		switch (instrument) {
			case BASEDRUM:
				return I18n.format("worldinfo.info.basedrum");
			case BASS:
				return I18n.format("worldinfo.info.bass");
			case BELL:
				return I18n.format("worldinfo.info.bell");
			case HAT:
				return I18n.format("worldinfo.info.hat");
			case HARP:
				return I18n.format("worldinfo.info.harp");
			case CHIME:
				return I18n.format("worldinfo.info.chime");
			case FLUTE:
				return I18n.format("worldinfo.info.flute");
			case SNARE:
				return I18n.format("worldinfo.info.snare");
			case GUITAR:
				return I18n.format("worldinfo.info.guitar");
			case XYLOPHONE:
				return I18n.format("worldinfo.info.xylophone");
			default:
				return I18n.format("worldinfo.info.unknown");
		}
	}

	public static String getTranslation(StructureMode mode) {
		switch (mode) {
			case CORNER:
				return I18n.format("worldinfo.info.corner");
			case DATA:
				return I18n.format("worldinfo.info.data");
			case LOAD:
				return I18n.format("worldinfo.info.load");
			case SAVE:
				return I18n.format("worldinfo.info.save");
			default:
				return I18n.format("worldinfo.info.unknown");
		}
	}

	public static int getEnchantability(World world, BlockPos position) {
		int l = 0;
		for (int j = -1; j <= 1; ++j) {
			for (int k = -1; k <= 1; ++k) {
				if ((j != 0 || k != 0) && world.isAirBlock(position.add(k, 0, j)) && world.isAirBlock(position.add(k, 1, j))) {
					if (world.getBlockState(position.add(k * 2, 0, j * 2)).getBlock() == Blocks.BOOKSHELF) {
						++l;
					}

					if (world.getBlockState(position.add(k * 2, 1, j * 2)).getBlock() == Blocks.BOOKSHELF) {
						++l;
					}

					if (k != 0 && j != 0) {
						if (world.getBlockState(position.add(k * 2, 0, j)).getBlock() == Blocks.BOOKSHELF) {
							++l;
						}

						if (world.getBlockState(position.add(k * 2, 1, j)).getBlock() == Blocks.BOOKSHELF) {
							++l;
						}

						if (world.getBlockState(position.add(k, 0, j * 2)).getBlock() == Blocks.BOOKSHELF) {
							++l;
						}

						if (world.getBlockState(position.add(k, 1, j * 2)).getBlock() == Blocks.BOOKSHELF) {
							++l;
						}
					}
				}
			}
		}
		return l;
	}

}
