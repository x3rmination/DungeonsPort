package com.nick.dungeonsport;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("dungeonsport")
public class DungeonsPort {
    // Directly reference a log4j logger.

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "firstmod";
    public static final String MINECRAFT_ID = "minecraft";

    public DungeonsPort() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        //BlockInit.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //ItemInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //ToolsInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event) {}

    private void doClientStuff(final FMLClientSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {}
}



