package com.canoestudio.canoemixins.config;

import com.canoestudio.canoemixins.CanoeMixins;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = CanoeMixins.MODID,type = Type.INSTANCE,category = "general")
public class CanoeModConfig {

    @LangKey("config.canoemixins.railcraft.name")
    public static CategoryRailcraft railcraft = new CategoryRailcraft();

    public static class CategoryRailcraft{

        @Comment({"Fix a desync bug of multiblock that when the multiblock is across chunks. ",
                "When desync, client can get its inventory scrambled when right-clicking at the bugged multiblock. "})
        @LangKey("config.canoemixins.railcraft.multiblockfix.name")
        public boolean multiblockSyncFix = true;

        @Comment("Fix turbine being fixed for free when putting in crafting slot with a blade and take it out.")
        @LangKey("config.canoemixins.railcraft.turbineCraftingFix.name")
        public boolean turbineRepairingFix = true;
    }

    @LangKey("config.canoemixins.industrialforegoing.name")
    public static CategoryIF industrialforegoing = new CategoryIF();
    public static class CategoryIF{
        @Comment({"Fixes a item duplication bug for the insertion conveyor upgrade"})
        @LangKey("config.canoemixins.industrialforegoing.fixConveyorInsertionDuplication.name")
        public boolean fixConveyorInsertionDuplication = true;
    }

    public static CategoryConnectionTimeout connectionTimeout = new CategoryConnectionTimeout();
    public static class CategoryConnectionTimeout{
        @Config.RequiresMcRestart
        @Comment({"Add a timeout when it's retrieving info for some mods, prevent it from sticking the loading progress forever on a lossy internet connection.",
                "Set to 0 for infinite timeout."})
        @LangKey("config.mtepatches.connectionTimeout.timeout.name")
        @Config.RangeInt(min = 0)
        public int timeout = 5000;

        @Comment({"Enable timeout for Biomes o' Plenty when it's checking trail info."})
        public boolean bop = true;
        @Comment({"Enable timeout for Industrial Foregoing when it's checking contributors."})
        public boolean industrialForegoing = true;
    }

}
