package com.github.upcraftlp.worldinfo.api.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public interface IBlockRenderHandler {

    default float getWidth(IBlockState state, IBlockReader world, BlockPos pos) {
        return 1.0F;
    }

    default float getHeight(IBlockState state, IBlockReader world, BlockPos pos) {
        return 1.0F;
    }

    default float getScale() {
        return 1.0F;
    }

    /**
     * @return a (translated) String for displaying the Block's name
     */
    default String getBlockDisplayString(ItemStack stack, IBlockState state, IBlockReader blockReader, BlockPos pos) {
        return stack.func_200301_q().getFormattedText();
    }

    /**
     * hook for rendering the Blockstate yourself<br/>
     * return {@code true} if you want to cancel regular rendering
     */
    default boolean renderBlock(IBlockReader blockReader, IBlockState state, BlockPos pos) {
        return false;
    }
}
