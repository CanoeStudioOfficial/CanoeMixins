package com.canoestudio.canoemixins.mixin.dramatictrees;

import com.canoestudio.canoemixins.config.CanoeModConfig;
import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicLeaves;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockDynamicLeaves.class)
public abstract class BlockDynamicLeaves_LightingMixin {

    @Unique
    private boolean canoemixins$worldGen;

    @Inject(
            method = "age",
            at = @At(value = "INVOKE", target = "Lcom/ferreusveritas/dynamictrees/blocks/BlockDynamicLeaves;getNewLeavesPropertiesHandler(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;IZ)Lcom/ferreusveritas/dynamictrees/blocks/BlockDynamicLeaves$NewLeavesPropertiesHandler;"),
            remap = false
    )
    private void fermiummixins_dynamicTreesBlockDynamicLeaves_age(World world, BlockPos pos, IBlockState state, Random rand, SafeChunkBounds safeBounds, CallbackInfoReturnable<Integer> cir) {
        if (!CanoeModConfig.worldGenLeavesLighting) return;
        this.canoemixins$worldGen = safeBounds != SafeChunkBounds.ANY;
    }

    @Inject(
            method = "age",
            at = @At("RETURN"),
            remap = false
    )
    private void fermiummixins_dynamicTreesBlockDynamicLeaves_age_return(World world, BlockPos pos, IBlockState state, Random rand, SafeChunkBounds safeBounds, CallbackInfoReturnable<Integer> cir) {
        if (!CanoeModConfig.worldGenLeavesLighting) return;
        this.canoemixins$worldGen = false;
    }

    @Redirect(
            method = "isLocationSuitableForNewLeaves",
            at = @At(value = "INVOKE", target = "Lcom/ferreusveritas/dynamictrees/blocks/BlockDynamicLeaves;hasAdequateLight(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/World;Lcom/ferreusveritas/dynamictrees/api/treedata/ILeavesProperties;Lnet/minecraft/util/math/BlockPos;)Z"),
            remap = false
    )
    private boolean fermiummixins_dynamicTreesBlockDynamicLeaves_isLocationSuitableForNewLeaves(BlockDynamicLeaves instance, IBlockState blockState, World world, ILeavesProperties leavesProperties, BlockPos pos) {
        if (!CanoeModConfig.worldGenLeavesLighting) {
            return instance.hasAdequateLight(blockState, world, leavesProperties, pos);
        }
        return this.canoemixins$worldGen || instance.hasAdequateLight(blockState, world, leavesProperties, pos);
    }
}