package com.nick.dungeonsport.common.items;

import com.nick.dungeonsport.core.enums.ItemTiers;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class Claymore extends SwordItem {
    public Claymore(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, 1, -1.5F, builder);
    }
}
