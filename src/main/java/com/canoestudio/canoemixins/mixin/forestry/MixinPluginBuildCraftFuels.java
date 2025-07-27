package com.canoestudio.canoemixins.mixin.forestry;

import com.canoestudio.canoemixins.config.CanoeModConfig;
import forestry.core.config.Constants;
import forestry.core.utils.ModUtil;
import forestry.plugins.PluginBuildCraftFuels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PluginBuildCraftFuels.class,remap = false)
public abstract class MixinPluginBuildCraftFuels {
    @Inject(method = "isAvailable",at = @At("HEAD"),cancellable = true)
    public void redirectBCVersionCheck(CallbackInfoReturnable<Boolean> cir){
        if(CanoeModConfig.forestry.bc8Compat){
            cir.setReturnValue(ModUtil.isModLoaded(Constants.BCLIB_MOD_ID));
        }
    }
}
