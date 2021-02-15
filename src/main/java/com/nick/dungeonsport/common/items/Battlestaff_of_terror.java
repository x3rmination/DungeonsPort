package com.nick.dungeonsport.common.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.registries.IForgeRegistryEntry;




public class Battlestaff_of_terror extends SwordItem {
    public Battlestaff_of_terror(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, 1, -2, builder);
    }
}
