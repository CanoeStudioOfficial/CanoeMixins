package com.canoestudio.canoemixins.mixin.optifinecitpatch;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.ItemEnchantedBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEnchantedBook.class)
public class RenderItemMixin {
    @ModifyReturnValue(
            method = "hasEffect",
            at = @At(value = "RETURN")
    )
    private boolean mixin(boolean original){
        return false;
    }
}
