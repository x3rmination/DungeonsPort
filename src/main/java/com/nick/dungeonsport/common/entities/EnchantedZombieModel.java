package com.nick.dungeonsport.common.entities;

import net.minecraft.client.renderer.entity.model.ZombieModel;


public class EnchantedZombieModel<T extends EnchantedZombieEntity> extends ZombieModel<EnchantedZombieEntity> {

    public EnchantedZombieModel(float modelSize, boolean p_i1168_2_) {
        this(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
    }
    protected EnchantedZombieModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
        super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
    }


    @Override
    public boolean isAggressive(EnchantedZombieEntity entityIn) {
        return false;
    }


}
