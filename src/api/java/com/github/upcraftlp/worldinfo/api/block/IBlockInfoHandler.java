package com.github.upcraftlp.worldinfo.api.block;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockInfoHandler {

	/**
	 * @return Custom Info to show in WorldInfo's HUD
	 */
	default List<String> getInfo(World world, BlockPos pos, IBlockState state, @Nullable TileEntity tileEntity) {
		return new ArrayList<>();
	}

}
