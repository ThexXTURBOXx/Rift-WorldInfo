package com.github.upcraftlp.worldinfo.client.handler.block;

import com.github.upcraftlp.worldinfo.api.block.IBlockInfoHandler;
import com.github.upcraftlp.worldinfo.api.util.TurboUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAttachedStem;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockStem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.state.properties.ComparatorMode;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class DefaultBlockInfoHandler implements IBlockInfoHandler {

	@Override
	public List<String> getInfo(IBlockState state, @Nullable TileEntity te) {
		List<String> info = new ArrayList<>();
		Block block = state.getBlock();
		if (block instanceof BlockRedstoneWire) {
			if (state.has(BlockRedstoneWire.POWER)) {
				info.add(I18n.format("worldinfo.info.power") + ": " + state.get(BlockRedstoneWire.POWER));
			}
		}
		if (block instanceof BlockLever) {
			if (state.has(BlockLever.POWERED)) {
				info.add(I18n.format("worldinfo.info.power") + ": " + TurboUtils.boolToString(state.get(BlockLever.POWERED)));
			}
		}
		if (block instanceof BlockMobSpawner && te instanceof TileEntityMobSpawner) {
			info.add(I18n.format("worldinfo.info.mob") + ": " + I18n.format(
					((TileEntityMobSpawner) te).getSpawnerBaseLogic().getCachedEntity()
							.getType().getTranslationKey()));
		}
		if (block instanceof BlockRedstoneRepeater) {
			if (state.has(BlockRedstoneRepeater.DELAY)) {
				info.add(I18n.format("worldinfo.info.delay") + ": " + state.get(BlockRedstoneRepeater.DELAY));
			}
		}
		if (block instanceof BlockDaylightDetector) {
			if (state.has(BlockDaylightDetector.INVERTED)) {
				info.add(I18n.format("worldinfo.info.mode") + ": " + (state.get(BlockDaylightDetector.INVERTED) ?
						I18n.format("worldinfo.info.night") :
						I18n.format("worldinfo.info.day")));
			}
		}
		if (block instanceof BlockRedstoneComparator) {
			if (state.has(BlockRedstoneComparator.MODE)) {
				info.add(I18n.format("worldinfo.info.mode") + ": " +
						getTranslation(state.get(BlockRedstoneComparator.MODE)));
			}
		}
		if (block instanceof BlockCrops) {
			if (state.has(((BlockCrops) block).getAgeProperty())) {
				if (((BlockCrops) block).isMaxAge(state)) {
					info.add(I18n.format("worldinfo.info.growth") + ": " + I18n.format("worldinfo.info.mature"));
				} else {
					int age = state.get(((BlockCrops) block).getAgeProperty());
					info.add(I18n.format("worldinfo.info.growth") + ": "
							+ TurboUtils.round(age / (double) ((BlockCrops) block).getMaxAge() * 100d)
							+ "%");
				}
			}
		}
		if (block instanceof BlockNetherWart) {
			if (state.has(BlockNetherWart.AGE)) {
				int age = state.get(BlockNetherWart.AGE);
				if (age >= 3) {
					info.add(I18n.format("worldinfo.info.growth") + ": " + I18n.format("worldinfo.info.mature"));
				} else {
					info.add(I18n.format("worldinfo.info.growth") + ": "
							+ TurboUtils.round(age / 3d * 100d)
							+ "%");
				}
			}
		}
		if (block instanceof BlockStem) {
			if (state.has(BlockStem.AGE)) {
				int age = state.get(BlockStem.AGE);
				if (age >= 7) {
					info.add(I18n.format("worldinfo.info.growth") + ": " + I18n.format("worldinfo.info.mature"));
				} else {
					info.add(I18n.format("worldinfo.info.growth") + ": "
							+ TurboUtils.round(age / 7d * 100d)
							+ "%");
				}
			}
		}
		if (block instanceof BlockAttachedStem) {
			info.add(I18n.format("worldinfo.info.growth") + ": " + I18n.format("worldinfo.info.ripe"));
		}
		if (block instanceof BlockBeacon && te instanceof TileEntityBeacon) {
			int levels = ((TileEntityBeacon) te).getField(0);
			Potion p1 = Potion.getPotionById(((TileEntityBeacon) te).getField(1));
			Potion p2 = Potion.getPotionById(((TileEntityBeacon) te).getField(2));
			info.add(I18n.format("worldinfo.info.level") + ": " + levels);
			if (p1 != null)
				info.add(I18n.format("worldinfo.info.effect") + " 1: " + p1.getDisplayName().getString());
			if (p2 != null)
				info.add(I18n.format("worldinfo.info.effect") + " 2: " + p2.getDisplayName().getString());
		}
		return info;
	}

	private String getTranslation(ComparatorMode mode) {
		switch (mode) {
			case COMPARE:
				return I18n.format("worldinfo.info.compare");
			case SUBTRACT:
				return I18n.format("worldinfo.info.subtract");
			default:
				return I18n.format("worldinfo.info.unknown");
		}
	}

}
