package com.nick.dungeonsport.core.enums;

import com.google.common.base.Supplier;

import com.nick.dungeonsport.core.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public enum ItemTiers implements IItemTier {

    //Harvest Level, Durability, Efficiency, Attack Damage, Enchantability, Repair Material

    FIERY_IRON(4, 1000, 5.5f, 10.0f, 30, () -> {

        return Ingredient.fromItems(ItemInit.ENCHANTED_SHARD.get());

    }),

    HIGHLAND_AXE(5,1200,5.5f,10.0f,30, () -> {

        return Ingredient.fromItems(ItemInit.ENCHANTED_SHARD.get());
    }),

    BASE_AXE(3, 500, 4.5f, 7.0f, 15, () -> {

        return Ingredient.fromItems(ItemInit.ENCHANTED_SHARD.get());

    });


    private final int HarvestLevel;
    private final int MaxUses;
    private final float Efficiency;
    private final float AttackDamage;
    private final int Enchantability;
    private final Supplier<Ingredient> RepairMaterial;

    ItemTiers(int HarvestLevel, int MaxUses, float Efficiency, float AttackDamage, int Enchantability, Supplier<Ingredient> RepairMaterial) {
        this.HarvestLevel = HarvestLevel;
        this.MaxUses = MaxUses;
        this.Efficiency = Efficiency;
        this.AttackDamage = AttackDamage;
        this.Enchantability = Enchantability;
        this.RepairMaterial = RepairMaterial;


    }

    @Override
    public float getAttackDamage() {

        return AttackDamage;

    }

    @Override
    public float getEfficiency() {

        return Efficiency;

    }

    @Override
    public int getEnchantability() {

        return Enchantability;

    }

    @Override
    public int getHarvestLevel() {

        return HarvestLevel;

    }

    @Override
    public int getMaxUses() {

        return MaxUses;

    }

    @Override
    public Ingredient getRepairMaterial() {

        return RepairMaterial.get();

    }
}

