package com.canoestudio.canoemixins.mixin.railcraft;

import com.canoestudio.canoemixins.config.CanoeModConfig;
import mods.railcraft.common.util.crafting.RotorRepairRecipe;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(RotorRepairRecipe.class)
public class MixinRotorRepairRecipe {
    @ModifyVariable(remap = true, at = @At(value = "STORE"), ordinal = 0, method = "getCraftingResult(Lnet/minecraft/inventory/InventoryCrafting;)Lnet/minecraft/item/ItemStack;")
    public ItemStack copyItemStack(ItemStack stack) {
        if (CanoeModConfig.railcraft.turbineRepairingFix)
            return stack.copy();
        else
            return stack;
    }
}