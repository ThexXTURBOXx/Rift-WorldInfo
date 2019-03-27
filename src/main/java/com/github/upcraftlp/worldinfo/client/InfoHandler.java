package com.github.upcraftlp.worldinfo.client;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;

public class InfoHandler {

	public static List<String> getBlockInfo(IBlockState state) {
		List<String> info = new ArrayList<>();
		Block block = state.getBlock();
		if (block instanceof BlockRedstoneWire) {
			info.add("Power: " + state.get(BlockRedstoneWire.POWER));
		}
		return info;
	}

}
