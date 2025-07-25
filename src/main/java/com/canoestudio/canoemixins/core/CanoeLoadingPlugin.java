package com.canoestudio.canoemixins.core;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;


import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@IFMLLoadingPlugin.Name("CanoeMixinsCore")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE)
public class CanoeLoadingPlugin implements IFMLLoadingPlugin, IEarlyMixinLoader
{
    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.canoemixins.json");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    @Nullable
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
