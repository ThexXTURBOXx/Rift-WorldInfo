package com.github.upcraftlp.worldinfo.client.handler.block;

import com.github.upcraftlp.worldinfo.api.block.IBlockInfoHandler;
import com.github.upcraftlp.worldinfo.api.util.TurboUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAttachedStem;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChorusFlower;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockFrostedIce;
import net.minecraft.block.BlockKelpTop;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockNote;
import net.minecraft.block.BlockPressurePlateWeighted;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneLamp;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockSeaPickle;
import net.minecraft.block.BlockSnowLayer;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockStructure;
import net.minecraft.block.BlockTurtleEgg;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DefaultBlockInfoHandler implements IBlockInfoHandler {

	@Override
	public List<String> getInfo(World world, BlockPos pos, IBlockState state, @Nullable TileEntity te) {
		List<String> info = new ArrayList<>();
		Block block = state.getBlock();
		if (block instanceof BlockRedstoneWire) {
			if (state.has(BlockRedstoneWire.POWER)) {
				info.add(I18n.format("worldinfo.info.power") + ": " + state.get(BlockRedstoneWire.POWER));
			}
		}
		if (block instanceof BlockLever) {
			if (state.has(BlockLever.POWERED)) {
				info.add(I18n.format("worldinfo.info.power") + ": " + TurboUtils.boolToStringOnOff(state.get(BlockLever.POWERED)));
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
			if (state.has(BlockDaylightDetector.POWER)) {
				info.add(I18n.format("worldinfo.info.power") + ": " + state.get(BlockDaylightDetector.POWER));
			}
		}
		if (block instanceof BlockRedstoneComparator) {
			if (state.has(BlockRedstoneComparator.MODE)) {
				info.add(I18n.format("worldinfo.info.mode") + ": " +
						TurboUtils.getTranslation(state.get(BlockRedstoneComparator.MODE)));
			}
		}
		if (block instanceof BlockCrops) {
			if (state.has(((BlockCrops) block).getAgeProperty())) {
				info.add(TurboUtils.getStageProgress(state, ((BlockCrops) block).getAgeProperty(), ((BlockCrops) block).getMaxAge()));
			}
		}
		if (block instanceof BlockCocoa) {
			if (state.has(BlockCocoa.AGE)) {
				info.add(TurboUtils.getStageProgress(state, BlockCocoa.AGE, 2));
			}
		}
		if (block instanceof BlockNetherWart) {
			if (state.has(BlockNetherWart.AGE)) {
				info.add(TurboUtils.getStageProgress(state, BlockNetherWart.AGE, 3));
			}
		}
		if (block instanceof BlockStem) {
			if (state.has(BlockStem.AGE)) {
				info.add(TurboUtils.getStageProgress(state, BlockStem.AGE, 7));
			}
		}
		if (block instanceof BlockAttachedStem) {
			info.add(I18n.format("worldinfo.info.growth") + ": " + I18n.format("worldinfo.info.ripe"));
		}
		if (block instanceof BlockBeacon && te instanceof TileEntityBeacon) {
			int levels = ((TileEntityBeacon) te).getField(0);
			info.add(I18n.format("worldinfo.info.level") + ": " + levels);
		}
		if (block instanceof BlockLeaves) {
			if (state.has(BlockLeaves.PERSISTENT) && state.get(BlockLeaves.PERSISTENT)) {
				info.add(I18n.format("worldinfo.info.persistent") + ": " + TurboUtils.boolToStringYesNo(state.get(BlockLeaves.PERSISTENT)));
			}
		}
		if (block instanceof BlockNote) {
			if (state.has(BlockNote.INSTRUMENT)) {
				info.add(I18n.format("worldinfo.info.instrument") + ": " + TurboUtils.getTranslation(state.get(BlockNote.INSTRUMENT)));
			}
			if (state.has(BlockNote.NOTE)) {
				info.add(I18n.format("worldinfo.info.note") + ": " + TurboUtils.intToNote(state.get(BlockNote.NOTE)));
			}
		}
		if (block instanceof BlockSnowLayer) {
			if (state.has(BlockSnowLayer.LAYERS)) {
				info.add(I18n.format("worldinfo.info.layers") + ": " + state.get(BlockSnowLayer.LAYERS));
			}
		}
		if (block instanceof BlockCake) {
			if (state.has(BlockCake.BITES)) {
				info.add(I18n.format("worldinfo.info.bites") + ": " + state.get(BlockCake.BITES));
			}
		}
		if (block instanceof BlockCauldron) {
			if (state.has(BlockCauldron.LEVEL)) {
				info.add(I18n.format("worldinfo.info.water_level") + ": " + TurboUtils.round(state.get(BlockCauldron.LEVEL) / 3d * 1000d, 2) + "mB");
			}
		}
		if (block instanceof BlockEndPortalFrame) {
			if (state.has(BlockEndPortalFrame.EYE)) {
				info.add(I18n.format("worldinfo.info.eye") + ": " + TurboUtils.boolToStringYesNo(state.get(BlockEndPortalFrame.EYE)));
			}
		}
		if (block instanceof BlockRedstoneLamp) {
			if (state.has(BlockRedstoneLamp.LIT)) {
				info.add(I18n.format("worldinfo.info.lit") + ": " + TurboUtils.boolToStringOnOff(state.get(BlockRedstoneLamp.LIT)));
			}
		}
		if (block instanceof BlockCommandBlock) {
			if (state.has(BlockCommandBlock.CONDITIONAL)) {
				info.add(I18n.format("worldinfo.info.conditional") + ": " + TurboUtils.boolToStringYesNo(state.get(BlockCommandBlock.CONDITIONAL)));
			}
		}
		if (block instanceof BlockPressurePlateWeighted) {
			if (state.has(BlockPressurePlateWeighted.POWER)) {
				info.add(I18n.format("worldinfo.info.power") + ": " + state.get(BlockPressurePlateWeighted.POWER));
			}
		}
		if (block instanceof BlockChorusFlower) {
			if (state.has(BlockChorusFlower.AGE)) {
				info.add(TurboUtils.getStageProgress(state, BlockChorusFlower.AGE, 5));
			}
		}
		if (block instanceof BlockFrostedIce) {
			if (state.has(BlockFrostedIce.AGE)) {
				info.add(I18n.format("worldinfo.info.age") + ": " + state.get(BlockFrostedIce.AGE));
			}
		}
		if (block instanceof BlockKelpTop) {
			if (state.has(BlockKelpTop.AGE)) {
				info.add(TurboUtils.getStageProgress(state, BlockKelpTop.AGE, 25));
			}
		}
		if (block instanceof BlockTurtleEgg) {
			if (state.has(BlockTurtleEgg.EGGS)) {
				info.add(I18n.format("worldinfo.info.amount") + ": " + state.get(BlockTurtleEgg.EGGS));
			}
			if (state.has(BlockTurtleEgg.HATCH)) {
				info.add(I18n.format("worldinfo.info.age") + ": " + state.get(BlockTurtleEgg.HATCH));
			}
		}
		if (block instanceof BlockSeaPickle) {
			if (state.has(BlockSeaPickle.PICKLES)) {
				info.add(I18n.format("worldinfo.info.amount") + ": " + state.get(BlockSeaPickle.PICKLES));
			}
		}
		if (block instanceof BlockStructure) {
			if (state.has(BlockStructure.MODE)) {
				info.add(I18n.format("worldinfo.info.amount") + ": " + TurboUtils.getTranslation(state.get(BlockStructure.MODE)));
			}
		}
		if (block instanceof BlockEnchantmentTable) {
			info.add(I18n.format("worldinfo.info.enchantability") + ": " + TurboUtils.getEnchantability(world, pos));
		}
		if (block instanceof BlockFlowerPot) {
			ItemStack flower = block.getItem(world, pos, state);
			if (flower.isEmpty() || ItemStack.areItemsEqual(flower, new ItemStack(Blocks.FLOWER_POT))) {
				info.add(I18n.format("worldinfo.info.flower") + ": " + I18n.format("worldinfo.info.empty"));
			} else {
				info.add(I18n.format("worldinfo.info.flower") + ": " + flower.getDisplayName().getFormattedText());
			}
		}
		return info;
	}

}
