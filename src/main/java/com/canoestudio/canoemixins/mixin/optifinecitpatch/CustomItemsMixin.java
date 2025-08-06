package com.canoestudio.canoemixins.mixin.optifinecitpatch;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.optifine.CustomItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CustomItems.class)
public abstract class CustomItemsMixin {

    @WrapWithCondition(method = "update(Lnet/minecraft/client/resources/IResourcePack;)V", at = @At(value = "INVOKE", target = "LConfig;dbg(Ljava/lang/String;)V"), remap = false)
    private static boolean optifinecitpatch_disableLogSpam(String s) {
        return false;
    }
}
