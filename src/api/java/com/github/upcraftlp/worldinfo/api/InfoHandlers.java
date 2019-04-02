package com.github.upcraftlp.worldinfo.api;

import com.github.upcraftlp.worldinfo.api.block.IBlockInfoHandler;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfoHandlers {

	private static final List<IBlockInfoHandler> BLOCK_INFO_HANDLERS = new ArrayList<>();

	/**
	 * add custom block handlers.
	 */
	public static void addBlockHandler(IBlockInfoHandler infoHandler) {
		BLOCK_INFO_HANDLERS.add(infoHandler);
	}

	public static List<String> getInfo(World world, BlockPos pos, IBlockState state, @Nullable TileEntity tileEntity) {
		List<String> info = new ArrayList<>();
		for (IBlockInfoHandler handler : BLOCK_INFO_HANDLERS) {
			info.addAll(handler.getInfo(world, pos, state, tileEntity));
		}
		return info;
	}

}
