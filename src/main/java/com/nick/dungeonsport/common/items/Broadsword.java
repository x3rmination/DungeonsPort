package com.nick.dungeonsport.common.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class Broadsword extends SwordItem {
    public Broadsword(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, 1, -1.5F, builder);
    }
}
