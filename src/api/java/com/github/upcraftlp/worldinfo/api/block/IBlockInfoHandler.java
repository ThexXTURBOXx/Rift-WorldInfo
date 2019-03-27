package com.github.upcraftlp.worldinfo.api.block;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;

public interface IBlockInfoHandler {

	/**
	 * @return Custom Info to show in WorldInfo's HUD
	 */
	default List<String> getInfo(IBlockState state, @Nullable TileEntity tileEntity) {
		return new ArrayList<>();
	}

}
