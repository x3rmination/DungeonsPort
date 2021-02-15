package com.nick.dungeonsport.common.items;

import com.nick.dungeonsport.core.enums.ItemTiers;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class Growing_staff extends SwordItem {
    public Growing_staff(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, 1, -2, builder);
    }
}
