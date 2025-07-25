package com.canoestudio.canoemixins.core;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;

public class CanoeMixinLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        List<String> mixins = new ArrayList<>();
        mixins.add("mixin.canoemixins.biomesoplenty.json");
        mixins.add("mixin.canoemixins.dynamictrees.json");

        return mixins;
    }
}
