package com.canoestudio.canoemixins.mixin.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.biome.overworld.BOPOverworldBiome;
import biomesoplenty.common.biome.overworld.BiomeGenCoralReef;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.world.generator.GeneratorFlora.Builder;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BiomeGenCoralReef.class, remap = false)
public abstract class MixinBiomeCoralReef extends BOPOverworldBiome {
    public MixinBiomeCoralReef(String idName, PropsBuilder defaultBuilder) {
        super(idName, defaultBuilder);
    }

    @Inject(method = "<init>()V", at = @At("TAIL"))
    private void injectGenerators(CallbackInfo ci) {
        this.addGenerator("pink_coral_saltwater", GeneratorStage.LILYPAD, (new Builder()).amountPerChunk(15.0F).replace(Material.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.PINK)).scatterYMethod(GeneratorUtils.ScatterYMethod.AT_GROUND).create());
        this.addGenerator("orange_coral_saltwater", GeneratorStage.LILYPAD, (new Builder()).amountPerChunk(15.0F).replace(Material.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ORANGE)).scatterYMethod(GeneratorUtils.ScatterYMethod.AT_GROUND).create());
        this.addGenerator("blue_coral_saltwater", GeneratorStage.LILYPAD, (new Builder()).amountPerChunk(15.0F).replace(Material.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.BLUE)).scatterYMethod(GeneratorUtils.ScatterYMethod.AT_GROUND).create());
        this.addGenerator("glowing_coral_saltwater", GeneratorStage.LILYPAD, (new Builder()).amountPerChunk(15.0F).replace(Material.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.GLOWING)).scatterYMethod(GeneratorUtils.ScatterYMethod.AT_GROUND).create());
        this.addGenerator("algae_saltwater", GeneratorStage.LILYPAD, (new Builder()).amountPerChunk(3.0F).replace(Material.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(GeneratorUtils.ScatterYMethod.AT_GROUND).create());
    }
}