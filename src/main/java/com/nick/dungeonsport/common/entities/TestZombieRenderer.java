package com.nick.dungeonsport.common.entities;

import com.nick.dungeonsport.DungeonsPort;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class TestZombieRenderer extends MobRenderer<TestZombieEntity, TestZombieModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(DungeonsPort.MOD_ID, "textures/entity/testzombie.png");

    public TestZombieRenderer(EntityRendererManager manager) {
        super(manager, new TestZombieModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(TestZombieEntity entity) {
        return TEXTURE;
    }
}
