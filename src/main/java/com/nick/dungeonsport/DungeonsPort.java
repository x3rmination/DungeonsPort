package com.nick.dungeonsport;


import com.nick.dungeonsport.common.entities.EnchantedZombieEntity;
import com.nick.dungeonsport.common.entities.EnchantedZombieRenderer;
import com.nick.dungeonsport.core.init.BlockInit;
import com.nick.dungeonsport.core.init.EntityInit;
import com.nick.dungeonsport.core.init.ItemInit;
import com.nick.dungeonsport.core.init.ToolsInit;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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

    public static final String MOD_ID = "dungeonsport";
    public static final String MINECRAFT_ID = "minecraft";

    public DungeonsPort() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        BlockInit.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ToolsInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityInit.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(EntityInit.ENCHANTED_ZOMBIE.get(), EnchantedZombieEntity.setCustomAttributes().create());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {}

    @SubscribeEvent
    public void setupClient(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.ENCHANTED_ZOMBIE.get(), EnchantedZombieRenderer::new);
    }
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {}
}



