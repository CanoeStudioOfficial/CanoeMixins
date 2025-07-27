package com.canoestudio.canoemixins.config;

import com.canoestudio.canoemixins.CanoeMixins;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Level;

import java.util.HashSet;

@Config(modid = CanoeMixins.MODID,type = Type.INSTANCE,category = "general")
public class CanoeModConfig {

    @Config.Comment("Disables unnecessary lighting checks on leaves during worldgen for performance")
    @Config.Name("WorldGen Leaves Lighting Lag Fix (DramaticTrees)")
    @Config.RequiresMcRestart
    public static boolean worldGenLeavesLighting = false;

    @Config.Comment("Cache leaf and branch AABBs to save on memory allocation usage")
    @Config.Name("Collision Box Cache (DramaticTrees)")
    @Config.RequiresMcRestart
    public static boolean collisionBoxCache = false;

    @Config.Comment("Overhauls tree falling such as making volume dependant on speed/size and allowing for passing through or breaking additional blocks")
    @Config.Name("Tree Falling Overhaul (DramaticTrees)")
    @Config.RequiresMcRestart
    public static boolean treeFallingOverhaul = false;

    @Config.Comment("Prints the class names of solid blocks during tree collisions to console" + "\n" +
            "Requires \"Tree Falling Overhaul (DramaticTrees)\" enabled")
    @Config.Name("Debug Tree Collision Names")
    public static boolean treesCollisionNameDebug = false;

    @Config.Comment("List of blocks for falling trees to treat as non-solid when falling" + "\n" +
            "Requires \"Tree Falling Overhaul (DramaticTrees)\" enabled")
    @Config.Name("Tree Falling Non-Solid Blocks")
    public static String[] treeFallingNonSolidBlocks = {
            "dynamictrees:leaves0",
            "minecraft:leaves",
            "minecraft:vine",
            "minecraft:double_plant",
            "minecraft:tallgrass",
            "notreepunching:loose_rock/stone",
            "notreepunching:loose_rock/sandstone"
    };

    @Config.Comment("List of blocks from the non-solid list for falling trees to break while falling" + "\n" +
            "Requires \"Tree Falling Overhaul (DramaticTrees)\" enabled")
    @Config.Name("Tree Falling Non-Solid Breakable Blocks")
    public static String[] treeFallingNonSolidBreakableBlocks = {
            "dynamictrees:leaves0",
            "minecraft:leaves",
            "minecraft:vine",
            "minecraft:double_plant",
            "minecraft:tallgrass"
    };

    @Config.Comment("List of blocks for falling trees to treat as solid but still break while falling" + "\n" +
            "Requires \"Tree Falling Overhaul (DramaticTrees)\" enabled")
    @Config.Name("Tree Falling Solid Breakable Blocks")
    public static String[] treeFallingSolidBreakableBlocks = {};

    private static HashSet<Block> treeFallingNonSolidList = null;
    private static HashSet<Block> treeFallingNonSolidBreakableList = null;
    private static HashSet<Block> treeFallingSolidBreakableList = null;

    public static HashSet<Block> getTreeFallingNonSolidList() {
        if(treeFallingNonSolidList == null) {
            treeFallingNonSolidList = new HashSet<>();
            for(String string : treeFallingNonSolidBlocks) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string));
                if(block == null || block == Blocks.AIR) {
                    CanoeMixins.LOGGER.log(Level.WARN, "CanoeMixins DramaticTree Non-Solid list invalid block: " + string + ", ignoring.");
                    continue;
                }
                treeFallingNonSolidList.add(block);
            }
        }
        return treeFallingNonSolidList;
    }

    public static HashSet<Block> getTreeFallingNonSolidBreakableList() {
        if(treeFallingNonSolidBreakableList == null) {
            treeFallingNonSolidBreakableList = new HashSet<>();
            for(String string : treeFallingNonSolidBreakableBlocks) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string));
                if(block == null || block == Blocks.AIR) {
                    CanoeMixins.LOGGER.log(Level.WARN, "CanoeMixins DramaticTree Non-Solid Breakable list invalid block: " + string + ", ignoring.");
                    continue;
                }
                treeFallingNonSolidBreakableList.add(block);
            }
        }
        return treeFallingNonSolidBreakableList;
    }

    public static HashSet<Block> getTreeFallingSolidBreakableList() {
        if(treeFallingSolidBreakableList == null) {
            treeFallingSolidBreakableList = new HashSet<>();
            for(String string : treeFallingSolidBreakableBlocks) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string));
                if(block == null || block == Blocks.AIR) {
                    CanoeMixins.LOGGER.log(Level.WARN, "CanoeMixins DramaticTree Solid Breakable list invalid block: " + string + ", ignoring.");
                    continue;
                }
                treeFallingSolidBreakableList.add(block);
            }
        }
        return treeFallingSolidBreakableList;
    }

    public static void refreshConfig() {
        treeFallingNonSolidList = null;
        treeFallingNonSolidBreakableList = null;
        treeFallingSolidBreakableList = null;
    }


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
        @LangKey("config.canoemixins.connectionTimeout.timeout.name")
        @Config.RangeInt(min = 0)
        public int timeout = 5000;

        @Comment({"Enable timeout for Biomes o' Plenty when it's checking trail info."})
        public boolean bop = true;
        @Comment({"Enable timeout for Industrial Foregoing when it's checking contributors."})
        public boolean industrialForegoing = true;
    }



}
