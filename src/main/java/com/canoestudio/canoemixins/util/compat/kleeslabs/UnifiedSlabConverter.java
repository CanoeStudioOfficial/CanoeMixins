package com.canoestudio.canoemixins.util.compat.kleeslabs;

import com.google.common.base.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public class UnifiedSlabConverter implements SlabConverter {

    public enum ConverterMode {
        SIMPLE,
        SMART,
        SMARTER
    }

    private final Block slabBlock;
    private final ConverterMode mode;

    public UnifiedSlabConverter(Block slabBlock, ConverterMode mode) {
        this.slabBlock = slabBlock;
        this.mode = mode;
    }

    @Override
    public IBlockState getSingleSlab(IBlockState state, BlockSlab.EnumBlockHalf blockHalf) {
        switch (mode) {
            case SMARTER:
                return smarterConvert(state, blockHalf);
            case SMART:
                return smartConvert(state, blockHalf);
            case SIMPLE:
            default:
                return simpleConvert(state, blockHalf);
        }
    }

    @Override
    public boolean isDoubleSlab(IBlockState state) {
        if (mode == ConverterMode.SMARTER) {
            for (IProperty<?> property : state.getPropertyKeys()) {
                if ("half".equals(property.getName())) {
                    Object value = state.getValue(property);
                    if (value instanceof IStringSerializable) {
                        return "full".equals(((IStringSerializable) value).getName());
                    } else if (value instanceof String) {
                        return "full".equals(value);
                    }
                }
            }
        }
        return true; // 默认行为
    }

    private IBlockState simpleConvert(IBlockState state, BlockSlab.EnumBlockHalf blockHalf) {
        return slabBlock.getDefaultState().withProperty(BlockSlab.HALF, blockHalf);
    }

    private IBlockState smartConvert(IBlockState state, BlockSlab.EnumBlockHalf blockHalf) {
        IBlockState newState = slabBlock.getDefaultState();
        for (IProperty<?> property : state.getPropertyKeys()) {
            if (newState.getPropertyKeys().contains(property)) {
                newState = copyProperty(state, newState, property);
            }
        }
        return newState.withProperty(BlockSlab.HALF, blockHalf);
    }

    private IBlockState smarterConvert(IBlockState state, BlockSlab.EnumBlockHalf blockHalf) {
        IBlockState newState = slabBlock.getDefaultState();
        for (IProperty<?> property : state.getPropertyKeys()) {
            if ("half".equals(property.getName())) {
                newState = handleHalfProperty(newState, property, blockHalf);
            } else {
                newState = copyProperty(state, newState, property);
            }
        }
        return newState;
    }

    private IBlockState copyProperty(IBlockState source, IBlockState target, IProperty<?> property) {
        try {
            return target.withProperty(property, source.getValue(property));
        } catch (IllegalArgumentException e) {
            return target; // 忽略不兼容属性
        }
    }

    private IBlockState handleHalfProperty(IBlockState state, IProperty<?> property, BlockSlab.EnumBlockHalf blockHalf) {
        Optional<?> opt = Optional.absent();
        if (blockHalf == BlockSlab.EnumBlockHalf.BOTTOM) {
            opt = property.parseValue("bottom");
        } else if (blockHalf == BlockSlab.EnumBlockHalf.TOP) {
            opt = property.parseValue("top");
        }
        return opt.isPresent() ? state.withProperty(property, opt.get()) : state;
    }
}