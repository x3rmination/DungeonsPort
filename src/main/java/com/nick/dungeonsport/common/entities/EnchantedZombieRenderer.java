package com.nick.dungeonsport.common.entities;

import com.nick.dungeonsport.DungeonsPort;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class EnchantedZombieRenderer extends AbstractZombieRenderer<EnchantedZombieEntity, EnchantedZombieModel<EnchantedZombieEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(DungeonsPort.MOD_ID, "textures/entity/enchanted_zombie.png");

    public EnchantedZombieRenderer(EntityRendererManager renderManagerIn) {
        //super(renderManagerIn, new TestZombieModel(1,true),0.5F);
        //super(renderManagerIn, new ZombieModel<>(0.0F, false));
        super(renderManagerIn, new EnchantedZombieModel<>(0.0F, false), new EnchantedZombieModel<>(0.5F, true), new EnchantedZombieModel<>(1.0F, true));
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EnchantedZombieEntity entity) {
        return TEXTURE;
    }

}
