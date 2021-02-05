package com.nick.dungeonsport.client.setup;


import com.nick.dungeonsport.DungeonsPort;
import com.nick.dungeonsport.common.entities.TestZombieRenderer;
import com.nick.dungeonsport.core.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DungeonsPort.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event){
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.TEST_ZOMBIE.get(), TestZombieRenderer::new);
        
    }
}
