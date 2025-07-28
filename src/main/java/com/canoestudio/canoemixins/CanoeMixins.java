package com.canoestudio.canoemixins;

import com.canoestudio.canoemixins.core.LoadedModChecker;
import com.canoestudio.canoemixins.modbugfix.railcraft.RCMultiblockPatch;
import com.canoestudio.canoemixins.util.compat.screenshotclipboard.ScreenshotToClipboard;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class CanoeMixins {

    public static final String MODID = Tags.MOD_ID;
    public static final String NAME = Tags.MOD_NAME;
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOGGER = LogManager.getLogger();

    private static Logger logger;

    public CanoeMixins() {
        MinecraftForge.EVENT_BUS.register(this);
        if(LoadedModChecker.railcraft.isLoaded()){
            MinecraftForge.EVENT_BUS.register(RCMultiblockPatch.class);
        }
//        if(LoadedModChecker.buildcraftcore.isLoaded()){
//            BCCapabilityAdapter.INSTANCE.init();
//            MinecraftForge.EVENT_BUS.register(BCCapabilityAdapter.INSTANCE);
//        }
    }

    public static @Nullable Logger getLogger(){return logger;}
    public static void logInfo(String log){
        logger.info(log);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(ScreenshotToClipboard.class);
        }
    }




}
