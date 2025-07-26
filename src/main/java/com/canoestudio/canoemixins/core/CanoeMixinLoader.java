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
        mixins.add("mixin.canoemixins.railcraft.json");
        mixins.add("mixin.canoemixins.industrialforegoing.json");
        mixins.add("mixin.canoemixins.dramatictrees.json");

        return mixins;
    }
}
