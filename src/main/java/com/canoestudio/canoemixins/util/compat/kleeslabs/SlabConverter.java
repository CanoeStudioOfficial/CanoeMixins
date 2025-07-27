package com.canoestudio.canoemixins.util.compat.kleeslabs;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;

public interface SlabConverter {

    IBlockState getSingleSlab(IBlockState state, BlockSlab.EnumBlockHalf blockHalf);

    default boolean isDoubleSlab(IBlockState state) {
        return true;
    }

}
