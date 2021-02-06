package com.nick.dungeonsport.common.entities;

import com.nick.dungeonsport.DungeonsPort;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class TestZombieRenderer extends MobRenderer<TestZombieEntity, TestZombieModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(DungeonsPort.MOD_ID, "assets/dungeonsport/textures/entity/test_zombie.png");

    public TestZombieRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TestZombieModel(),0.5F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(TestZombieEntity entity) {
        return TEXTURE;
    }

}
