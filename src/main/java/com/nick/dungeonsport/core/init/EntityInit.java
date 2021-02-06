package com.nick.dungeonsport.core.init;

import com.nick.dungeonsport.DungeonsPort;
import com.nick.dungeonsport.common.entities.TestZombieEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, DungeonsPort.MOD_ID);

    public static void init() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    public static final RegistryObject<EntityType<TestZombieEntity>> TEST_ZOMBIE = ENTITIES.register("test_zombie",
            () -> EntityType.Builder.create(TestZombieEntity::new, EntityClassification.MONSTER)
                    //hitbox
                    .size(0.5F,2)
                    .build(new ResourceLocation(DungeonsPort.MOD_ID, "test_zombie").toString()));



}
