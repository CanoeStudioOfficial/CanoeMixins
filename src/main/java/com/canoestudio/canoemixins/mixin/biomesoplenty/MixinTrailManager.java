package com.canoestudio.canoemixins.mixin.biomesoplenty;

import biomesoplenty.common.remote.TrailManager;
import com.canoestudio.canoemixins.config.CanoeModConfig;
import com.canoestudio.canoemixins.util.ConnectionTimeoutUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.net.HttpURLConnection;

@Mixin(value = TrailManager.class,remap = false)
public abstract class MixinTrailManager {
    @ModifyVariable(method = "retrieveTrails()V",at = @At("STORE"),ordinal = 0)
    private static HttpURLConnection addTimeout(HttpURLConnection connection){
        if(CanoeModConfig.connectionTimeout.bop){
            ConnectionTimeoutUtils.setTimeout(connection);
        }
        return connection;
    }
}