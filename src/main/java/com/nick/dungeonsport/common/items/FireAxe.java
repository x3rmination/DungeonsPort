package com.nick.dungeonsport.common.items;

import com.nick.dungeonsport.DungeonsPort;
import com.nick.dungeonsport.core.init.ItemInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.UUID;


public class FireAxe extends AxeItem {
    public FireAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }


    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn)  {
        super.onCreated(stack, worldIn, playerIn);
        if (true) {
            stack.addEnchantment(Enchantments.FIRE_ASPECT,3);
        }
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player,Entity entity) {
        super.onLeftClickEntity(stack, player, entity);
        if (true) {
            entity.setFire(5);

        }
        return false;



    }




}
