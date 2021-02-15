package com.nick.dungeonsport.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import java.util.UUID;


public class Highland_Axe extends AxeItem {
    public Highland_Axe(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        super.onLeftClickEntity(stack, player, entity);
        if (Math.random() < 0.20) {
            if (entity instanceof LivingEntity) {
                entity.setSprinting(false);
                ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 10));
                ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, 35, 10));


            }
        }
        return false;
    }


}
