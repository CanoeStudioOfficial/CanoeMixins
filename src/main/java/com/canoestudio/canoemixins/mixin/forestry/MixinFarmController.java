package com.canoestudio.canoemixins.mixin.forestry;

import com.canoestudio.canoemixins.config.CanoeModConfig;
import forestry.farming.multiblock.FarmController;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Stack;

@Mixin(value = FarmController.class,remap = false)
public class MixinFarmController {

    @Shadow @Final private Stack<ItemStack> pendingProduce;
    @Intrinsic
    public void addPendingProduce(ItemStack stack) {
        if(CanoeModConfig.forestry.multiFarmSoilReplaceFix)
            this.pendingProduce.push(stack);
    }
}
